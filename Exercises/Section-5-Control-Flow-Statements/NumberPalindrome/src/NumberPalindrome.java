public class NumberPalindrome {

    public static boolean isPalindrome(int number){

        int reverse = 0;
        int originalNumber = number;

        while (Math.abs(number) > 0) {
            int lastDigit = number % 10;

            reverse = reverse * 10;

            reverse += lastDigit;

            number /= 10;
        }

        if(Math.abs(originalNumber) == Math.abs(reverse)) {
            return true;
        } else {
            return false;
        }
    }
}
