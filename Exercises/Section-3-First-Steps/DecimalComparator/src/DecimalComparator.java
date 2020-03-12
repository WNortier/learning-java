public class DecimalComparator {
    public static boolean areEqualByThreeDecimalPlaces(double firstNumber, double secondNumber) {
        double difference = Math.abs(firstNumber - secondNumber);
        if (difference <= 0.0009) {
            return true;
        }

        return false;
    }
}

