package controllersDraft;


public class CalculatorDraft {
    public static int[] addDaysToDate(int[] startDate, int daysToAdd) {
        int year = startDate[0];
        int month = startDate[1];
        int day = startDate[2] + daysToAdd;

        while (day > getDaysInMonth(year, month)) {
            day -= getDaysInMonth(year, month);
            month++;
            if (month > 12) {
                month = 1;
                year++;
            }
        }
        return new int[]{year, month, day};
    }

    public static int calculateFine(int[] returnDate, int[] dueDate) {
        int fine = 0;
        if (returnDate[0] > dueDate[0]) {
            fine = calculateDaysLate(returnDate, dueDate);
        } else if (returnDate[0] == dueDate[0]) {
            if (returnDate[1] > dueDate[1]) {
                fine = calculateDaysLate(returnDate, dueDate);
            } else if (returnDate[1] == dueDate[1] && returnDate[2] > dueDate[2]) {
                fine = returnDate[2] - dueDate[2];
            }
        }
        return fine;
    }

    private static int calculateDaysLate(int[] returnDate, int[] dueDate) {
        if (returnDate[0] == dueDate[0]) {
            return (getDaysInMonth(dueDate[0], dueDate[1]) - dueDate[2]) + returnDate[2];
        }
        return 0;
    }

    public static int getDaysInMonth(int year, int month) {
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                return isLeapYear(year) ? 29 : 28;
            default:
                return 0;
        }
    }

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    }
}

