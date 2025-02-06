package controllersDraft;

import interfaces.IBookControllerDraft;

import java.util.List;

public class BookControllerDraft implements IBookControllerDraft {
    private  final BookDraft.IBookRepositoryDraft repository;

    public BookControllerDraft(BookDraft.IBookRepositoryDraft repository) {
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
