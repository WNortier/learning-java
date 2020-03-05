public class FloatAndDouble {
    public static void main(String[] args) {
        float myMinFloatValue = Float.MIN_VALUE;
        float myMaxFloatValue = Float.MAX_VALUE;
        System.out.println("Float minimum value = " + myMinFloatValue);
        System.out.println("Float maximum value = " + myMaxFloatValue);

        double myMinDoubleValue = Double.MIN_VALUE;
        double myMaxDoubleValue = Double.MAX_VALUE;
        System.out.println("Double minimum value = " + myMinDoubleValue);
        System.out.println("Double maximum value = " + myMaxDoubleValue);

        int myIntValue = 5 / 2;
        float myFloatValue = (float) 5 / 3f;
        double myDoubleValue = (double)5 / 3d;
        System.out.println("MyIntValue= " + myIntValue);
        //float values have single precision
        System.out.println("MyFloatValue= " + myFloatValue);
        //double values have double precision and are more precise
        System.out.println("MyDoubleValue= " + myDoubleValue);

        //Convert a given number of pounds to kilograms
        //HINT: 1 pound is equal to 0.45359237 of a kilogram

        double numberOfPounds = 200;
        double result = numberOfPounds * 0.45359237d;
        System.out.println(numberOfPounds + " pounds is equal to " + result);

        double pi = 3.1415927d;
        double anotherNumber = 3_000_000.4_567_890d;
        System.out.println(pi);
        System.out.println(anotherNumber);
    }
}

