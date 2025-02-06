import java.util.InputMismatchException;
import java.util.Scanner;

import controllersDraft.interfaces.IBookControllerDraft;
import controllersDraft.interfaces.IReaderControllerDraft;
import controllersDraft.interfaces.IStaffControllerDraft;
import controllersDraft.interfaces.ITransactionsControllerDraft;
import modelsDraft.BookDraft;
import modelsDraft.*;

public class MyApplicationDraft {
    private final IBookControllerDraft bookController;
    private final IReaderControllerDraft readerController;
    private final IStaffControllerDraft staffController;
    private final ITransactionsControllerDraft transactionsController;

    private static ReaderDraft loggedReader;
    private static StaffDraft loggedInStaff;
    private static modelsDraft.BookDraft searched_book;
    private final Scanner scanner = new Scanner(System.in);


    public MyApplicationDraft(IBookControllerDraft bookController, IReaderControllerDraft readerController, IStaffControllerDraft staffController, ITransactionsControllerDraft transactionsController) {
        this.bookController = bookController;
        this.readerController = readerController;
        this.staffController = staffController;
        this.transactionsController = transactionsController;
    }

    public void readerOrStaff() {
        while (true) {
            System.out.print("Are you a reader or a staff? (Enter 'reader' or 'staff') : ");
            String role = scanner.next().toLowerCase();
            switch (role) {
                case "reader":
                    loginOrSignUpReader();
                    return;
                case "staff":
                    loginOrSignUpStaff();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }

        }
    }

    public void loginOrSignUpReader() {
        while (true) {
            System.out.println("Choose an operation:");
            System.out.println("1. Log in");
            System.out.println("2. Sign up");
            System.out.println("0. Exit");
            System.out.print("Enter your choice (1, 2, or 0 to exit): ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 1) {
                    System.out.print("Enter your ID for log in: ");
                    int searchId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter your password for log in: ");
                    String searchPassword = scanner.nextLine();

                    ReaderDraft foundReader = readerController.findReaderByIdPassword(searchId, searchPassword);
                    if (foundReader != null) {
                        System.out.println("Logged in successfully." + " Welcome, " + foundReader.getName());
                        loggedReader = foundReader;
                        return;
                    } else {
                        System.out.println("Reader with ID " + searchId + " not found or password is incorrect.");
                    }
                } else if (choice == 2) {
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter your surname: ");
                    String surname = scanner.nextLine();


                    String email = null;
                    while (true) {
                        System.out.print("Enter your email: ");
                        email = scanner.nextLine();
                        if (!email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
                            System.out.println("Invalid email format.");
                        } else {
                            break;
                        }
                    }

                    String password = null;
                    while (true) {
                        System.out.print("Enter your password (8 characters): ");
                        password = scanner.nextLine();
                        if (password.length() < 8) {
                            System.out.println("Password is too short.");
                        } else {
                            break;
                        }
                    }
                    ReaderDraft newReader = (ReaderDraft) new ReaderDraft.ReaderBuilder()
                            .setEmail(email)
                            .setName(name)
                            .setSurname(surname)
                            .setPassword(password)
                            .build();
                    readerController.addReader(newReader);
                    loggedReader = newReader;
                    System.out.println("Logged in successfully. Welcome, " + newReader.getName() + "!");
                    return;
                } else if (choice == 0) {
                    System.out.println("Exiting...");
                    return;
                } else {
                    System.out.println("Invalid option. Please select either 1, 2, or 0 to exit.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }


    public void loginOrSignUpStaff() {
        while (true) {
            System.out.println("Choose an operation:");
            System.out.println("1. Log in");
            System.out.println("2. Sign up");
            System.out.print("Enter your choice (1 or 2): ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                if (choice == 1) {
                    System.out.print("Enter the id for log in: ");
                    int searchId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter the password for log in: ");
                    String password = scanner.nextLine();
                    StaffDraft foundStaff = staffController.findMemberByIdPassword(searchId, password);
                    if (foundStaff != null) {
                        System.out.println("Logged in successfully. Welcome, " + foundStaff.getName() + "!");
                        loggedInStaff = foundStaff;
                        return;
                    } else {
                        System.out.println("Staff with ID " + searchId + " not found.");
                    }
                } else if (choice == 2) {

                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter your surname: ");
                    String surname = scanner.nextLine();

                    System.out.print("Enter your salary: ");
                    int salary = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter your password (8 characters): ");
                    String password = null;
                    while (true) {
                        password = scanner.nextLine();
                        if (password.length() < 8) {
                            System.out.println("Password is too short.");
                        } else {
                            break;
                        }
                    }
                    StaffDraft newStaff = (StaffDraft) new StaffDraft.StaffBuilder()
                            .setSalary(salary)
                            .setName(name)
                            .setSurname(surname)
                            .setPassword(password)
                            .build();
                    staffController.addMember(newStaff);
                    System.out.println("New reader created: " + newStaff);
                    loggedInStaff = newStaff;
                    return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void exit() {
        System.exit(0);
    }

    public void actions() {
        if (loggedReader != null) {
            readerActionsMenu();
            return;
        } else if (loggedInStaff != null) {
            while (true) {
                System.out.println("\nChoose the work: staff-related or book-related. (Enter 'staff' or 'book').");
                String choice = scanner.nextLine().toLowerCase();
                switch (choice) {
                    case "staff":
                        staffActionsMenu();
                        return;
                    case "book":
                        bookActions();
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }

        }
    }

    private void readerActionsMenu() {
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Get all books");
            System.out.println("2. Borrow a book");
            System.out.println("3. Return a book");
            System.out.println("4. Get Full Transaction Details");
            System.out.println("5. Log out");
            System.out.print("Select an action: ");
            try {
                int option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1:
                        showAllBooks();
                        break;
                    case 2:
                        borrowBook();
                        break;
                    case 3:
                        returnBook();
                        break;
                    case 4:
                        getFullTransactionDetails();
                        return;
                    case 5:
                        exit();
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void staffActionsMenu() {
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add an employee");
            System.out.println("2. Show all employees");
            System.out.println("3. Find an employee by ID");
            System.out.println("4. Delete an employee");
            System.out.println("5. Get full transaction details");
            System.out.println("6. Exit");
            System.out.print("Select an action: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter the name: ");
                        String name = scanner.next();
                        System.out.print("Enter the surname: ");
                        String surname = scanner.next();
                        System.out.print("Enter the salary: ");
                        int salary = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter the password: ");
                        String password = scanner.next();
                        StaffDraft staff = (StaffDraft) new StaffDraft.StaffBuilder()
                                .setSalary(salary)
                                .setName(name)
                                .setSurname(surname)
                                .setPassword(password).build();
                        StaffDraft staffAdded = staffController.addMember(staff);
                        if (staffAdded != null) {
                            System.out.println("New person was created successfully");
                        } else {
                            System.out.println("New person was not created. Try again.");
                        }
                    }
                    case 2 -> staffController.showAllMembers();
                    case 3 -> {
                        System.out.print("Enter the person's ID to search: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter the person's password: ");
                        String password = scanner.nextLine();
                        StaffDraft staff = staffController.findMemberByIdPassword(id, password);
                        if (staff != null) {
                            System.out.println(staff);
                        } else {
                            System.out.println("Staff with ID " + id + " not found or the password is incorrect .");
                        }
                    }
                    case 4 -> {
                        System.out.print("Enter the person's ID to delete: ");
                        int id = scanner.nextInt();
                        staffController.deleteMemberById(id);
                        staffController.showAllMembers();
                    }
                    case 5 -> {
                        getFullTransactionDetails();
                    }
                    case 6 -> {
                        System.out.println("Exiting...");
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid choice!");
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void searchForBook() {
        showAllBooks();
        System.out.println("Please enter the book id: ");
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            searched_book = bookController.getBookById(choice);
            if (searched_book == null) {
                System.out.println("No book found with the id: " + choice);
            }
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    private void getFullTransactionDetails() {
        System.out.println("Enter the full transaction id:");
        int id = scanner.nextInt();
        String result = transactionsController.getFullTransactionDetails(id);
        System.out.print(result);
    }

    private void borrowBook() {
        while (true) {
            searchForBook();
            if (searched_book != null && !searched_book.isBorrowed()) {
                System.out.println("The book is available! You can borrow it.");
                break;
            } else {
                System.out.println("The book is not available! Try again.");
            }
        }
        try {
            System.out.println("Please enter the current year, month, and day to borrow the book: ");
            int[] currentDate = new int[3];
            for (int i = 0; i < 3; i++) {
                currentDate[i] = scanner.nextInt();
            }
            String borrowResponse = transactionsController.borrowBook(searched_book.getBookId(), loggedReader.getId(), currentDate);
            System.out.println(borrowResponse);
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    private void returnBook() {
        System.out.println("Please enter the book id: ");
        try {
            int id = scanner.nextInt();
            searched_book = bookController.getBookById(id);
            if (searched_book == null) {
                System.out.println("No book found with the id: " + id);
            } else {
                System.out.println("Book found. Please enter the current year, month, and day in order to return the book: ");
                int[] returnDate = new int[3];
                for (int i = 0; i < 3; i++) {
                    returnDate[i] = scanner.nextInt();
                }
                String returnResponse = transactionsController.returnBook(searched_book.getBookId(), loggedReader.getId(), returnDate);
                System.out.println(returnResponse);
            }
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    public void bookActions() {
        while (true) {
            System.out.println("\nChoose an action:");
            System.out.println("1. Add a book");
            System.out.println("2. Show all books");
            System.out.println("3. Update a book");
            System.out.println("4. Delete a book");
            System.out.println("5. Exit");

            int choice = getUserInput();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    showAllBooks();
                    break;
                case 3:
                    updateBook();
                    break;
                case 4:
                    deleteBook();
                case 5:
                    exit();
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    public int getUserInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return -1;
        }
    }

    public void addBook() {
        try {
            System.out.println("Enter book title:");
            String title = scanner.nextLine();
            System.out.println("Enter book author:");
            String author = scanner.nextLine();
            String category = scanner.nextLine();
            System.out.println("Is the book borrowed? (true:false):");
            boolean isBorrowed = Boolean.parseBoolean(scanner.nextLine());
            BookDraft newBook = new BookDraft.BookBuilder()
                    .setTitle(title)
                    .setAuthor(author)
                    .setBorrowed(isBorrowed)
                    .setCategory(category)
                    .build();
            if (bookController.addBook(newBook)) {
                System.out.println("Book added successfully.");
            } else {
                System.out.println("Something went wrong, please try again.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void showAllBooks() {
        System.out.println("\nList of books:");
        bookController.getAllBooks().forEach(book -> {
            System.out.printf("ID: %d, Title: %s, Author: %s, Borrowed: %b%n",
                    book.getBookId(), book.getTitle(), book.getAuthor(), book.isBorrowed());
        });
    }

    public void deleteBook() {
        System.out.println("\nEnter book id:");
        try {
            int id = scanner.nextInt();
            scanner.nextLine();
            bookController.deleteBook(id);
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }

    }

    public void updateBook() {
        System.out.print("Enter the book ID to update: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        BookDraft book = bookController.getBookById(bookId);

        if (book == null) {
            System.out.println("No book found with ID " + bookId);
            return;
        }

        System.out.println("Current book: " + book.toString());

        System.out.println("\nWhich field would you like to update?");
        System.out.println("1. Title");
        System.out.println("2. Author");
        System.out.println("3. Borrowed status");
        System.out.println("4. Category");
        System.out.println("5. No update (Cancel)");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter new title: ");
                String newTitle = scanner.nextLine();
                book = updateBookField(book, newTitle, book.getAuthor(), book.isBorrowed(), book.getCategory());
                break;

            case 2:
                System.out.print("Enter new author: ");
                String newAuthor = scanner.nextLine();
                book = updateBookField(book, book.getTitle(), newAuthor, book.isBorrowed(), book.getCategory());
                break;

            case 3:
                System.out.print("Is the book borrowed? (yes/no): ");
                String borrowedInput = scanner.nextLine().toLowerCase();
                boolean newBorrowedStatus = borrowedInput.equals("yes");
                book = updateBookField(book, book.getTitle(), book.getAuthor(), newBorrowedStatus, book.getCategory());
                break;

            case 4:
                System.out.print("Enter new category: ");
                String newCategory = scanner.nextLine();
                book = updateBookField(book, book.getTitle(), book.getAuthor(), book.isBorrowed(), newCategory);
                break;

            case 5:
                System.out.println("No update made. Returning to main menu.");
                return;

            default:
                System.out.println("Invalid option. Returning to main menu.");
                return;
        }

        bookController.updateBook(book);
        System.out.println("Book updated successfully!");
    }

    private BookDraft updateBookField(BookDraft book, String newTitle, String newAuthor, boolean newBorrowedStatus, String newCategory) {
        return new BookDraft.BookBuilder()
                .setBookId(book.getBookId())
                .setTitle(newTitle)
                .setAuthor(newAuthor)
                .setBorrowed(newBorrowedStatus)
                .setCategory(newCategory)
                .build();
    }
}