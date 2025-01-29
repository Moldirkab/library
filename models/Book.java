package models;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private boolean isBorrowed;

    public Book(String title , String author, boolean isBorrowed) {
        this.title = title;
        this.author = author;
        this.isBorrowed = isBorrowed;
    }
    public Book(int bookId, String title, String author, boolean isBorrowed) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isBorrowed = isBorrowed;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }
    @Override
    public String toString() {
        return "id: "+getBookId()+ ", title: "+ getTitle()+", author: "+getAuthor()+", is borrowed ?: "+isBorrowed;
    }
}
