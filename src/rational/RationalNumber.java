package rational;

/**
 * A RationalNumber Object has a numerator and a denominator
 * can be changed by adding, subtracting, multiplying and dividing
 */
public class RationalNumber {
    private int numerator;
    private int denominator;

    /**
     * Constructs a RationalNumber with given values
     * @param numerator the numerator value
     * @param denominator the denominator value
     * @throws IllegalArgumentException throws Exception when arguments are bad
     */
    public RationalNumber(int numerator, int denominator) throws IllegalArgumentException {
        if (denominator == 0){
            throw new IllegalArgumentException("Argument 'denominator' is 0");
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     * Constructs a RationalNumber with another RationalNumber
     * @param rationalNumber other RationalNumber
     */
    public RationalNumber(RationalNumber rationalNumber) {
        this.numerator = rationalNumber.numerator;
        this.denominator = rationalNumber.denominator;
    }

    /**
     * Generates the opposite RationalNumber
     * @param rationalNumber given RationalNumber
     * @return opposite RationalNumber
     */
    private static RationalNumber opposite(RationalNumber rationalNumber){
        return new RationalNumber(-rationalNumber.numerator, rationalNumber.denominator);
    }

    /**
     * Generates the inverse RationalNumber
     * @param rationalNumber given RationalNumber
     * @return inverse RationalNumber
     */
    private static RationalNumber inverse(RationalNumber rationalNumber) throws IllegalArgumentException{
        try{
            return  new RationalNumber(rationalNumber.denominator, rationalNumber.numerator);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Fraction 'divisor' is 0", e);
        }

    }

    /**
     * Calculates the greatest common divisor
     * @param p first number
     * @param q second number
     * @return greatest common divisor
     */
    private static int greatestCommonDivisor(int p, int q) {
        while (q != 0) {
            int t = q;
            q = p % q;
            p = t;
        }
        return Math.abs(p);
    }

    /**
     * return string of Object
     * @return
     */
    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    /**
     * adds a RationalNumber to current
     * @param rationalNumber other RationalNumber
     * @return added RationalNumber
     */
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

    /**
     * subtracts a RationalNumber from current
     * @param rationalNumber other RationalNumber
     * @return subtracted RationalNumber
     */
    public RationalNumber sub(RationalNumber rationalNumber) {
        return this.add(opposite(rationalNumber));
    }

    /**
     * Multipies a RationalNumber with current
     * @param rationalNumber other RationalNumber
     * @return multiplied RationalNumber
     */
    public RationalNumber mlt(RationalNumber rationalNumber) {
        this.numerator *= rationalNumber.numerator;
        this.denominator *= rationalNumber.denominator;
        int greatestCommonDivisor = greatestCommonDivisor(this.numerator,this.denominator);
        if (greatestCommonDivisor>1){
            this.numerator /= greatestCommonDivisor;
            this.denominator /= greatestCommonDivisor;
        }
        return this;
    }

    /**
     * Divide with other RationalNumber
     * @param rationalNumber other RationalNumber
     * @return divided Rational Number
     */
    public RationalNumber div(RationalNumber rationalNumber) {
        return this.mlt(inverse(rationalNumber));
    }
}
