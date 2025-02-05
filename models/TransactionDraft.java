package modelsDraft;

public class TransactionDraft {
    private int transaction_id;
    private int member_id;
    private int book_id;
    private int currentYear;
    private int currentMonth;
    private int currentDay;
    private int dueYear;
    private int dueMonth;
    private int dueDay;
    private int returnYear;
    private int returnMonth;
    private int returnDay;
    private int fine;


    public TransactionDraft(int book_id, int member_id, int[] currentDate, int[] dueDate) {
        setMember_id(member_id);
        setBook_id(book_id);
        setCurrentDate(currentDate);
        setDueDate(dueDate);
    }
   public TransactionDraft(int book_id, int member_id, int[] dueDate, int[] returnDate, int fine) {
       setBook_id(book_id);
       setMember_id(member_id);
       setDueDate(dueDate);
       setReturnDate(returnDate);
       setFine(fine);
    }
    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }
    public int getTransaction_id() {
        return transaction_id;
    }
    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }
    public int getMember_id() {
        return member_id;
    }
    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }
    public int getBook_id() {
        return book_id;
    }
  public void setCurrentDate(int[] currentDate) {
        this.currentYear = currentDate[0];
        this.currentMonth = currentDate[1];
        this.currentDay = currentDate[2];
  }
  public String getCurrentDate() {
        return currentYear + "-" + currentMonth + "-" + currentDay;
  }
  public void setDueDate(int[] dueDate) {
        this.dueYear = dueDate[0];
        this.dueMonth = dueDate[1];
        this.dueDay = dueDate[2];
  }
  public String getDueDate() {
        return dueYear + "-" + dueMonth + "-" + dueDay;
  }
  public void setFine(int fine) {
        this.fine = fine;
  }
  public int getFine() {
        return fine;
  }
  public void setReturnDate(int[] returnDate) {
        this.returnYear = returnDate[0];
        this.returnMonth = returnDate[1];
        this.returnDay = returnDate[2];
  }
  public String getReturnDate() {
        return returnYear + "-" + returnMonth + "-" + returnDay;
  }
    @Override
    public String toString() {
        return "Transaction id:"+getTransaction_id()+", Member_id:"+getMember_id()+", Book id:"+getBook_id()+", Borrow_date:"+getCurrentDate()+", Due date:"+getDueDate();
    }

}
