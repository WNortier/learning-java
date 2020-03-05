package academy.learnprogramming;

public class Main {

    public static void main(String[] args) {
	    // 8 Primitive types
        //Choosing the right type for a particular problem comes with experience

        // byte -128 to +127
        // short
        // int
        // long
        // float
        // double
        // char
        // boolean

        // Most commonly used int, double and boolean and a little less common long and char
        // short, float and byte are used rarely
        //Strings are immutable in Java, when we concatenate values a new string is actually created
        String myString = "This is a string";
        System.out.println(myString);

        //Concatenating string literal
        myString = myString + " and this is more concatenated!";
        System.out.println(myString);

        //Including unicode
        myString = myString + " \u00A9 2019";
        System.out.println(myString);

        //A number string
        String numberString = "250.55";
        numberString = numberString + "49.95";
        System.out.println(numberString);

        //Adding numeric data type (int) to string converts it to a string
        String lastString = "10";
        int myInt = 50;
        lastString = lastString + myInt;
        System.out.println(lastString);
        //Similar with double
        double doubleNumber = 120.47d;
        lastString = lastString + doubleNumber;
        System.out.println(lastString);

        //Appending values to strings should preferably be done with a 'StringBuffer' and not +
    }
}
