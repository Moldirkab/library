package controllers;

import controllers.IBookController;
import models.Book;
import repositories.IBookRepository;
import java.util.List;

public class BookController implements IBookController {
    private  final IBookRepository repository;

    public BookController(IBookRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Book> getAllBooks() {
        return repository.getAllBooks();
    }

    @Override
    public Book getBookById(int id) {
        return repository.getBookById(id);
    }

    @Override
    public boolean addBook(Book book) {
        boolean added=repository.addBook(book);
        return added;
    }

    @Override
    public void updateBook(Book book) {
        repository.updateBook(book);
    }

    @Override
    public void deleteBook(int id) {
        repository.deleteBook(id);
    }
}
