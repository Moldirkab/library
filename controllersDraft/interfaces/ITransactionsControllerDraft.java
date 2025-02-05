package controllersDraft.interfaces;

public interface ITransactionsControllerDraft {
    String getFullTransactionDetails(int transactionId );
    String borrowBook(int member_id, int book_id,int[] currentDate);
    String returnBook(int member_id, int book_id,int[] currentDate);
}
