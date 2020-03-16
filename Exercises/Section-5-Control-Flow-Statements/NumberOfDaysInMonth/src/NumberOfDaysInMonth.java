public class NumberOfDaysInMonth {

    public static boolean isLeapYear(int year) {
        if (year <= 0 || year > 9999) {
            return false;
        }

        if (year % 4 == 0 && year % 100 != 0) {
            return true;
        } else if (year % 400 == 0) {
            return true;
        }
        return false;
    }

    public static int getDaysInMonth(int month, int year) {

        if (month < 1 || month > 12 || year < 1 || year > 9999) {
            return -1;
        }

        boolean leapYearCheck = isLeapYear(year);
        System.out.println(leapYearCheck);
        System.out.println(month);
        if (leapYearCheck) {
            switch (month) {
                case 1:
                case 3:
                case 10:
                case 12:
                case 8:
                case 7:
                case 5:
                    return 31;
                case 2:
                    return 29;
                case 4:
                case 11:
                case 9:
                case 6:
                    return 30;
            }
        } else {
            switch (month) {
                case 1:
                case 3:
                case 10:
                case 12:
                case 8:
                case 7:
                case 5:
                    return 31;
                case 2:
                    return 28;
                case 4:
                case 11:
                case 9:
                case 6:
                    return 30;
            }
        }
        return -1;
    }
}
