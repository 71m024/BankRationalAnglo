package rational;

public class RationalNumber {
    private final int numerator;
    private final int denominator;

    public RationalNumber(int numerator, int denominator) throws IllegalArgumentException {
        if (denominator == 0){
            throw new IllegalArgumentException("Argument 'denominator' is 0");
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Override
    public String toString() {
        return "RationalNumber{" +
                "numerator=" + numerator +
                ", denominator=" + denominator +
                '}';
    }

    public RationalNumber add(RationalNumber rationalNumber) {
        return null;
    }

    public RationalNumber sub(RationalNumber rationalNumber) {
        return null;
    }

    public RationalNumber mult(RationalNumber rationalNumber) {
        return null;
    }

    public RationalNumber div(RationalNumber rationalNumber) {
        return this;
    }
}
