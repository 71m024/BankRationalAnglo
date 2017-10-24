package rational;

/**
 * A class to test the RationalNumber class.
 */
public class RationalNumberTester {
    /**
     * Method to test functionality of the RationalNumber class
     * @param args not used
     */
    public static void main(String[] args) {
        RationalNumber number1 = new RationalNumber(1,2);
        RationalNumber number2 = new RationalNumber(1,3);

        System.out.println("1/2 + 1/3 = " + number1.add(number2));

        RationalNumber number3 = new RationalNumber(1,2);
        RationalNumber number4 = new RationalNumber(1,3);

        System.out.println("1/2 - 1/3 = " + number3.sub(number4));

        RationalNumber number5 = new RationalNumber(1,2);
        RationalNumber number6 = new RationalNumber(1,3);

        System.out.println("1/2 * 1/3 = " + number5.mlt(number6));

        RationalNumber number7 = new RationalNumber(1,2);
        RationalNumber number8 = new RationalNumber(1,3);

        System.out.println("1/2 / 1/3 = " + number7.div(number8));





    }
}
