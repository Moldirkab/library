package repositoriesDraft.interfaces;

import modelsDraft.TransactionDraft;

public interface ITransactionsRepositoryDraft {
    String getFullTransactionDetails(int transactionId);
    boolean borrowBook(TransactionDraft transaction);
    String getDueDate(int book_id);
    boolean returnBook(TransactionDraft transaction);

}
