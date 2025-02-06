package interfaces;

import modelsDraft.BookDraft;
import java.util.List;

public interface IBookRepositoryDraft {
    List<BookDraft> getAllBooks();
    BookDraft getBookById(int id);
    boolean addBook(BookDraft book);
    void updateBook(BookDraft book);
    void deleteBook(int id);
}
