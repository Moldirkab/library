import controllersDraft.*;
import controllersDraft.interfaces.*;
import dataDraft.*;
import factoryDraft.*;
import modelsDraft.*;
import repositoriesDraft.*;
import repositoriesDraft.interfaces.*;

public class MainDraft {
    public static void main(String[] args) {
        String dbUrl = ConfigLoader.get("database.url");
        String username = ConfigLoader.get("database.username");
        String password = ConfigLoader.get("database.password");
        String dbName = ConfigLoader.get("database.dbName");


        DBDraft db = PostgresDBDraft.getInstance(dbUrl, username, password, dbName);
        IBookRepositoryDraft bookRepository = RepositoryFactory.createBookRepository(db.getConnection());
        IBookControllerDraft bookController = ControllerFactory.createBookController(bookRepository);
        IReaderRepositoryDraft readerRepository = RepositoryFactory.createReaderRepository(db.getConnection());
        IReaderControllerDraft readerController = ControllerFactory.createReaderController(readerRepository);
        IStaffRepositoryDraft staffRepository = RepositoryFactory.createStaffRepository(db.getConnection());
        IStaffControllerDraft staffController = ControllerFactory.createStaffController(staffRepository);
        ITransactionsRepositoryDraft transactionsRepository = RepositoryFactory.createTransactionRepository(db.getConnection());
        ITransactionsControllerDraft transactionsController=ControllerFactory.createTransactionsController(transactionsRepository);
        MyApplicationDraft app=new MyApplicationDraft(bookController, readerController, staffController, transactionsController);
        app.readerOrStaff();
        app.actions();
        db.close();
    }
}