package academy.learnprogramming;

public class Main {

    public static void main(String[] args) {
	    //chars are 2 bytes in size to account for all Unicode characters from different languages
        char myChar = 'D';
        //The unicode character convention is backslash u followed by the code for the character
        char myUnicodeChar = '\u0044';
        char myCopyrightChar = '\u00a9';

        boolean myTrueBooleanValue = true;
        boolean myFalseBooleanValue = false;
        boolean isCustomerOverTwentyOne = true;

        System.out.println(myChar);
        System.out.println(myUnicodeChar);
        System.out.println(myCopyrightChar);

        System.out.println(myTrueBooleanValue);
        System.out.println(myFalseBooleanValue);
    }
}
