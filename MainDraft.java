import controllersDraft.*;

import controllersDraft.interfaces.*;
import dataDraft.*;
import factoryDraft.*;
import modelsDraft.*;
import repositoriesDraft.*;
import repositoriesDraft.interfaces.*;

import controllersDraft.interfaces.IBookControllerDraft;
import controllersDraft.interfaces.IReaderControllerDraft;
import controllersDraft.interfaces.IStaffControllerDraft;
import controllersDraft.interfaces.ITransactionsControllerDraft;
import dataDraft.DBDraft;
import dataDraft.PostgresDBDraft;
import repositoriesDraft.*;
MansurNew
import repositoriesDraft.interfaces.*;

import repositoriesDraft.interfaces.IBookRepositoryDraft;
import repositoriesDraft.interfaces.IReaderRepositoryDraft;
import repositoriesDraft.interfaces.IStaffRepositoryDraft;
import repositoriesDraft.interfaces.ITransactionsRepositoryDraft;
main

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

        IBookRepositoryDraft bookRepository = new BookRepositoryDraft(db.getConnection());
        IBookControllerDraft bookController = new BookControllerDraft(bookRepository);
        IReaderRepositoryDraft readerRepository = new ReaderRepositoryDraft(db.getConnection());
        IReaderControllerDraft readerController = new ReaderControllerDraft(readerRepository);
        IStaffRepositoryDraft staffRepository = new StaffRepositoryDraft(db.getConnection());
        IStaffControllerDraft staffController = new StaffControllerDraft(staffRepository);
        ITransactionsRepositoryDraft transactionsRepository = new TransactionsRepositoryDraft(db.getConnection());
        ITransactionsControllerDraft transactionsController=new TransactionsControllerDraft(transactionsRepository);
        MyApplicationDraft app=new MyApplicationDraft(bookController, readerController, staffController, transactionsController);
        app.readerOrStaff();
        app.actions();
        db.close();
    }
}