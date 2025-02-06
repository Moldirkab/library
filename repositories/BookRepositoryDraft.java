package repositoriesDraft.interfaces;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryDraft implements BookDraft.IBookRepositoryDraft {
    private final Connection connection;

    public BookRepositoryDraft(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<BookDraft> getAllBooks() {
        List<BookDraft> books = new ArrayList<>();
        String query = "SELECT * FROM \"Books\" Order by book_id ASC";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                books.add(new BookDraft.BookBuilder()
                        .setBookId(rs.getInt("book_id"))
                        .setTitle(rs.getString("title"))
                        .setAuthor(rs.getString("author"))
                        .setBorrowed(rs.getBoolean("is_borrowed"))
                        .setCategory(rs.getString( "category")).build()
                );
            }
            return books;

        } catch (SQLException e) {
            System.out.println("Error fetching books: " + e.getMessage());
        }
        return books;
    }

    @Override
    public BookDraft getBookById(int id) {
        String query = "SELECT * FROM \"Books\" WHERE book_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new BookDraft.BookBuilder()
                                .setBookId(rs.getInt("book_id"))
                                .setTitle(rs.getString("title"))
                                .setAuthor(rs.getString("author"))
                                .setBorrowed(rs.getBoolean("is_borrowed"))
                            .setCategory(rs.getString("category")).build();
                    };
                }
        } catch (SQLException e) {
            System.out.println("Error fetching book by ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean addBook(BookDraft book) {
        String query = "INSERT INTO \"Books\" (title, author, is_borrowed) VALUES (?,?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setBoolean(3, book.isBorrowed());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error adding book: " + e.getMessage());
        }
        return false;
    }

    @Override
    public void updateBook(BookDraft book) {
        String query = "UPDATE \"Books\" SET title = ?, author = ?, is_borrowed = ?, category=? WHERE book_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setBoolean(3, book.isBorrowed());
            pstmt.setString(4,book.getCategory());
            pstmt.setInt(4, book.getBookId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating book: " + e.getMessage());
        }
    }

    @Override
    public void deleteBook(int id) {
        String query = "DELETE FROM \"Books\" WHERE book_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            if(pstmt.executeUpdate()>0){
                System.out.println("Book deleted successfully");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting book: " + e.getMessage());
        }
    }
}
