package repositoriesDraft;
import modelsDraft.TransactionDraft;
import repositoriesDraft.interfaces.ITransactionsRepositoryDraft;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TransactionsRepositoryDraft implements ITransactionsRepositoryDraft {
    private final Connection connection;

    public TransactionsRepositoryDraft(Connection connection) {
        this.connection = connection;
    };

    @Override
    public String getFullTransactionDetails(int transactionId) {
        String query = """
            SELECT t.transaction_id, t.borrow_date, 
                   t.due_date, 
                   b.title AS book_title,
                   r.name AS reader_name, r.surname AS reader_surname
            FROM transactions t
            JOIN \"Books\" b ON t.book_id = b.book_id
            JOIN readers r ON t.id = r.id
            WHERE t.transaction_id = ?;
        """;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, transactionId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return String.format("""
                        Transaction ID: %d
                        Book: %s
                        Reader: %s %s
                        Borrow Date: %s
                        Due Date: %s
                  
                        """,
                            rs.getInt("transaction_id"),
                            rs.getString("book_title"),
                            rs.getString("reader_name"),
                            rs.getString("reader_surname"),
                           rs.getString("due_date"),
                            rs.getString("borrow_date")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Transaction not found.";
    }
    public boolean borrowBook(TransactionDraft transaction) {
        String insertSql = "INSERT INTO transactions (book_id, id, borrow_date, due_date) VALUES (?, ?, ?, ?)";
        String selectSql = "SELECT transaction_id FROM transactions WHERE book_id = ? ORDER BY transaction_id DESC LIMIT 1";
        String updateSql = "UPDATE \"Books\" SET is_borrowed = true WHERE book_id = ?";

        try (
                PreparedStatement st = connection.prepareStatement(insertSql);
                PreparedStatement selectSt = connection.prepareStatement(selectSql);
                PreparedStatement updateSt = connection.prepareStatement(updateSql)
        ) {
            st.setInt(1, transaction.getBook_id());
            st.setInt(2, transaction.getMember_id());
            st.setString(3, transaction.getCurrentDate());
            st.setString(4, transaction.getDueDate());
            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                selectSt.setInt(1, transaction.getBook_id());
                try (ResultSet rs = selectSt.executeQuery()) {
                    if (rs.next()) {
                        int transactionId = rs.getInt("transaction_id");
                        transaction.setTransaction_id(transactionId);
                    }
                }

                updateSt.setInt(1, transaction.getBook_id());
                updateSt.executeUpdate();

                return true;
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }

        return false;
    }


    @Override
    public String getDueDate(int book_id) {
        String sql = "SELECT due_date FROM transactions WHERE book_id = ? ORDER BY transaction_id DESC LIMIT 1";

        try (
                PreparedStatement st = connection.prepareStatement(sql)
        ) {
            st.setInt(1, book_id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    String dueDate = rs.getString("due_date");
                    if (dueDate != null) {
                        return dueDate;
                    } else {
                        System.out.println("No due_date found for book_id: " + book_id);
                        return null;
                    }
                } else {
                    System.out.println("No record found for book_id: " + book_id);
                    return null;
                }
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return null;
        }
    }


    @Override
    public boolean returnBook(TransactionDraft transaction) {
        String updateTransactionSql = "UPDATE transactions SET return_date = ?, fine = ? WHERE transaction_id = (SELECT transaction_id FROM transactions WHERE book_id = ? ORDER BY transaction_id DESC LIMIT 1)";
        String updateBookSql = "UPDATE \"Books\" SET is_borrowed = false WHERE book_id = ?";

        try (
                PreparedStatement updateTransactionStm = connection.prepareStatement(updateTransactionSql);
                PreparedStatement updateBookSt = connection.prepareStatement(updateBookSql)
        ) {
            updateTransactionStm.setString(1, transaction.getReturnDate());
            updateTransactionStm.setInt(2, transaction.getFine());
            updateTransactionStm.setInt(3, transaction.getBook_id());
            updateTransactionStm.executeUpdate();

            updateBookSt.setInt(1, transaction.getBook_id());
            updateBookSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return false;
    }
}

