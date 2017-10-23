package rational;

public class RationalNumberTester {
    public static void main(String[] args) {
        RationalNumber rationalNumber = new RationalNumber(1,2);
        RationalNumber addRationalNumber = rationalNumber.add(new RationalNumber(1,2));
        RationalNumber subRationalNumber = rationalNumber.sub(new RationalNumber(1,2));
        RationalNumber mltRationalNumber = rationalNumber.mult(new RationalNumber(1,2));
        RationalNumber divRationalNumber = rationalNumber.div(new RationalNumber(1,2));

    }
}
