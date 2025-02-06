package modelsDraft;

import java.util.List;

public class BookDraft {
    private final int bookId;
    private final String title;
    private final String category;
    private final String author;
    private final boolean isBorrowed;

    private BookDraft(BookBuilder builder) {
        this.bookId = builder.bookId;
        this.title = builder.title;
        this.category = builder.category;
        this.author = builder.author;
        this.isBorrowed = builder.isBorrowed;
    }

    public int getBookId() {
        return bookId;
    }


    public String getTitle() {
        return title;
    }


    public String getCategory() {
        return category;
    }


    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    @Override
    public String toString() {
        return "id: " + bookId + ", title: '" + title + "', category: '" + category + "', author: '" + author + "', is borrowed? " + isBorrowed;
    }

    public static class BookBuilder {
        private int bookId;
        private String title;
        private String category;
        private String author;
        private boolean isBorrowed;

        public BookBuilder setBookId(int bookId) {
            this.bookId = bookId;
            return this;
        }

        public BookBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public BookBuilder setCategory(String category) {
            this.category = category;
            return this;
        }

        public BookBuilder setAuthor(String author) {
            this.author = author;
            return this;
        }

        public BookBuilder setBorrowed(boolean isBorrowed) {
            this.isBorrowed = isBorrowed;
            return this;
        }

        public BookDraft build() {
            return new BookDraft(this);
        }
    }

    public interface IBookRepositoryDraft {
        List<BookDraft> getAllBooks();
        BookDraft getBookById(int id);
        boolean addBook(BookDraft book);
        void updateBook(BookDraft book);
        void deleteBook(int id);
    }
}
