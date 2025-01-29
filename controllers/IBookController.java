package controllers;
import models.Book;
import java.util.List;

public interface IBookController {
    List<Book> getAllBooks();
    Book getBookById(int id);
    boolean addBook(Book book);
    void updateBook(Book book);
    void deleteBook(int id);
}
