import java.util.Scanner;

public class MinimumElementChallenge {
    public static void main(String[] args) {
        int[] integerArray = readIntegers(5);
        int minimumValue = findMin(integerArray);
        System.out.println(minimumValue);
    }

    private static Scanner scanner = new Scanner(System.in);

    public static int[] readIntegers(int amount) {
        int[] newArray = new int[amount];
        System.out.println("Please enter " + amount + " values\r");
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = scanner.nextInt();
            scanner.nextLine();
        }
        return newArray;
    }

    public static int findMin(int[] integerArray) {
        int min = integerArray[0];


            for (int i = 0; i < integerArray.length; i++) {
                if (integerArray[i] < min) {
                    min = integerArray[i];
                } else {
                    continue;
                }
            }
        return min;
    }
}
