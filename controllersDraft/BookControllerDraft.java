package controllersDraft;

import controllersDraft.interfaces.IBookControllerDraft;
import modelsDraft.BookDraft;
import repositoriesDraft.interfaces.IBookRepositoryDraft;
import java.util.List;

public class BookControllerDraft implements IBookControllerDraft {
    private  final IBookRepositoryDraft repository;

    public BookControllerDraft(IBookRepositoryDraft repository) {
        this.repository = repository;
    }

    @Override
    public List<BookDraft> getAllBooks() {
        return repository.getAllBooks();
    }

    @Override
    public BookDraft getBookById(int id) {
        return repository.getBookById(id);
    }

    @Override
    public boolean addBook(BookDraft book) {
        boolean added=repository.addBook(book);
        return added;
    }

    @Override
    public void updateBook(BookDraft book) {
        repository.updateBook(book);
    }

    @Override
    public void deleteBook(int id) {
        repository.deleteBook(id);
    }
}