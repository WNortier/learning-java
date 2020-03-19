public class NumberToWords {
    public static void numberToWords(int number){
        if (number < 0) {
            System.out.println("Invalid Value");
        }

        if (number == 0){
            System.out.println("Zero");
        }

        int reversedNumber = NumberToWords.reverser(number);
        int originalLength = NumberToWords.getDigitCount(number);
        int reversedLength = NumberToWords.getDigitCount(reversedNumber);
        int times = 0;

        while (reversedNumber > 0) {
            int finalDigit = reversedNumber % 10;
            switch(finalDigit){
                case 0:
                    System.out.println("Zero");
                    break;
                case 1:
                    System.out.println("One");
                    break;
                case 2:
                    System.out.println("Two");
                    break;
                case 3:
                    System.out.println("Three");
                    break;
                case 4:
                    System.out.println("Four");
                    break;
                case 5:
                    System.out.println("Five");
                    break;
                case 6:
                    System.out.println("Six");
                    break;
                case 7:
                    System.out.println("Seven");
                    break;
                case 8:
                    System.out.println("Eight");
                    break;
                case 9:
                    System.out.println("Nine");
                    break;
            }
            reversedNumber = reversedNumber/10;
        }

        if (originalLength != reversedLength) {
            times = originalLength - reversedLength;
            for (int i=0;i<times;i++){
                System.out.println("Zero");
            }
        }
    }

    public static int reverser(int number){

        int reversedNumber = 0;
        int reverseThisNumber = Math.abs(number);

        while(reverseThisNumber > 0){
            int lastDigit = reverseThisNumber % 10;
            reversedNumber += lastDigit;
            reverseThisNumber /= 10;
            reversedNumber *= 10;
        }

        reversedNumber /= 10;

        if (number < 0) {
            return reversedNumber * -1;
        } else {
            return reversedNumber;
        }
    }

    public static int getDigitCount(int number){
      if (number < 0){
          return -1;
      }
        int length = String.valueOf(number).length();
        return length;
    }

}
