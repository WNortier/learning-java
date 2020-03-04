package academy.learnprogramming;

public class Main {

    public static void main(String[] args) {

        byte myByte = 10;
        short myShort = 20;
        int myInt = 50;
        long myLong = 50000L + 10L * (myByte + myShort + myInt);
        System.out.println("My long value " + myLong);

        short shortTotal = (short) (1000 + 10 * (myByte + myShort + myInt));
        System.out.println("My short value " + shortTotal);
    }
}
