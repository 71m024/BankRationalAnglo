package rational;

public class RationalNumber {
    private int numerator;
    private int denominator;

    public RationalNumber(int numerator, int denominator) throws IllegalArgumentException {
        if (denominator == 0){
            throw new IllegalArgumentException("Argument 'denominator' is 0");
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public RationalNumber(RationalNumber rationalNumber) {
        this.numerator = rationalNumber.numerator;
        this.denominator = rationalNumber.denominator;
    }

    private static RationalNumber negativeRationalNumber(RationalNumber rationalNumber){
        return new RationalNumber(-rationalNumber.numerator, rationalNumber.denominator);
    }

    private static RationalNumber inverseRationalNumber(RationalNumber rationalNumber){
        try{
            return  new RationalNumber(rationalNumber.denominator, rationalNumber.numerator);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Fraction 'divisor' is 0", e);
        }

    }

    private static int greatestCommonDivisor(int p, int q) {
        while (q != 0) {
            int t = q;
            q = p % q;
            p = t;
        }
        return Math.abs(p);
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    public RationalNumber add(RationalNumber rationalNumber) {
        if (this.denominator == rationalNumber.denominator){
            this.numerator += rationalNumber.numerator;
        }
        else {
            this.numerator *= rationalNumber.denominator;
            this.numerator += rationalNumber.numerator * this.denominator;
            this.denominator *= rationalNumber.denominator;
        }
        int greatestCommonDivisor = greatestCommonDivisor(this.numerator,this.denominator);
        this.numerator /= greatestCommonDivisor;
        this.denominator /= greatestCommonDivisor;
        return this;
    }

    public RationalNumber sub(RationalNumber rationalNumber) {
        return this.add(negativeRationalNumber(rationalNumber));
    }

    public RationalNumber mlt(RationalNumber rationalNumber) {
        this.numerator *= rationalNumber.numerator;
        this.denominator *= rationalNumber.denominator;
        int greatestCommonDivisor = greatestCommonDivisor(this.numerator,this.denominator);
        this.numerator /= greatestCommonDivisor;
        this.denominator /= greatestCommonDivisor;
        return this;
    }

    public RationalNumber div(RationalNumber rationalNumber) {
        return this.mlt(inverseRationalNumber(rationalNumber));
    }
}
