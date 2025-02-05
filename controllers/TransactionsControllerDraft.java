package controllersDraft;
import controllersDraft.interfaces.ITransactionsControllerDraft;
import modelsDraft.TransactionDraft;
import repositoriesDraft.interfaces.ITransactionsRepositoryDraft;


public class TransactionsControllerDraft implements ITransactionsControllerDraft {
    ITransactionsRepositoryDraft repo;

    public TransactionsControllerDraft(ITransactionsRepositoryDraft repo) {
        this.repo = repo;
    }

    @Override
    public String borrowBook(int book_id, int member_id, int[] currentDate) {
        int[] dueDate = CalculatorDraft.addDaysToDate(currentDate, 14);
        TransactionDraft transaction = new TransactionDraft(book_id, member_id, currentDate, dueDate);
        boolean created = repo.borrowBook(transaction);
        return created ? "You have successfully borrowed the book:\n" + transaction.toString() : "You cannot borrow the book";
    }

    @Override
    public String returnBook(int book_id, int member_id, int[] returnDate) {
        String dueDateString = repo.getDueDate(book_id);
        if(dueDateString == null) {
            return "The book with id" +book_id+ " was not borrowed";
        }
        String[] parts = dueDateString.split("-");
        int[] dueDate = new int[]{Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])};
        int fine = CalculatorDraft.calculateFine(returnDate, dueDate);
        TransactionDraft transaction = new TransactionDraft(book_id, member_id, returnDate, dueDate, fine);
        boolean returned = repo.returnBook(transaction);
        if (fine > 0 && returned) {
            return "The book was returned after the due date. You have to pay a fine of " + fine + "$.";
        } else if (fine == 0 && returned) {
            return "You have successfully returned the book.";
        }
        return "Failed to return the book.";
    }
    @Override
   public String getFullTransactionDetails(int transactionId){
        String result=repo.getFullTransactionDetails(transactionId);
        return result;
   }
}