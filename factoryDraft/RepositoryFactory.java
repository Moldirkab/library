package factoryDraft;
import java.sql.Connection;

import repositoriesDraft.*;
import repositoriesDraft.interfaces.*;

public class RepositoryFactory {
    public static IReaderRepositoryDraft createReaderRepository(Connection connection) {
        return new ReaderRepositoryDraft(connection);
    }
    public static IStaffRepositoryDraft createStaffRepository(Connection connection) {
        return new StaffRepositoryDraft(connection);
    }
    public static IBookRepositoryDraft createBookRepository(Connection connection) {
        return new BookRepositoryDraft(connection);
    }
    public static ITransactionsRepositoryDraft createTransactionRepository(Connection connection) {
        return new TransactionsRepositoryDraft(connection);
    }
}
