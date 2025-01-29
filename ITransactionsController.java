package controllers;

public interface ITransactionsController {
    String borrowBook(int member_id, int book_id,int[] currentDate);
    String returnBook(int member_id, int book_id,int[] currentDate);
}