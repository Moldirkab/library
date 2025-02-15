package factoryDraft;

import controllersDraft.*;
import repositoriesDraft.interfaces.*;

public class ControllerFactory {

    public static BookControllerDraft createBookController(IBookRepositoryDraft bookRepository) {
        return new BookControllerDraft(bookRepository);
    }

    public static ReaderControllerDraft createReaderController(IReaderRepositoryDraft readerRepository) {
        return new ReaderControllerDraft(readerRepository);
    }

    public static StaffControllerDraft createStaffController(IStaffRepositoryDraft staffRepository) {
        return new StaffControllerDraft(staffRepository);
    }

    public static TransactionsControllerDraft createTransactionsController(ITransactionsRepositoryDraft transactionsRepository) {
        return new TransactionsControllerDraft(transactionsRepository);
    }
}

