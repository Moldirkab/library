package controllersDraft.interfaces;
import modelsDraft.BookDraft;

import java.util.List;

public interface IBookControllerDraft {
    List<BookDraft> getAllBooks();
    BookDraft getBookById(int id);
    boolean addBook(BookDraft book);
    void updateBook(BookDraft book);
    void deleteBook(int id);
}
