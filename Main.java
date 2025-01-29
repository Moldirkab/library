import controllers.*;
import data.DB;
import data.PostgresDB;
import repositories.*;

public class Main {
    public static void main(String[] args) {
        DB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres","070906","NewDB");
        IBookRepository bookRepository = new BookRepository(db.getConnection());
        IBookController bookController = new BookController(bookRepository);
        IReaderRepository readerRepository = new ReaderRepository(db.getConnection());
        IReaderController readerController = new ReaderController(readerRepository);
        IStaffRepository staffRepository = new StaffRepository(db.getConnection());
        IStaffController staffController = new StaffController(staffRepository);
        ITransactionsRepository transactionsRepository = new TransactionsRepository(db.getConnection());
        ITransactionsController transactionsController=new TransactionsController(transactionsRepository);
        MyApplication app=new MyApplication(bookController, readerController, staffController, transactionsController);
        app.readerOrStaff();
        app.actions();
        db.close();
    }
}