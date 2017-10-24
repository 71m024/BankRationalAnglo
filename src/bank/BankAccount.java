package bank;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

/**
 A bank account has a balance that can be changed by
 deposits and withdrawals.
 */
public class BankAccount {

    private BigDecimal balance;
    private LocalDateTime lastUpdate;                           //the last time, when the interest rate was added to the balance
    private int transactionFeeDivider = 500;                    //divide the transaction amount with this to get the fee
    private int freeTranactionLimit = 1;                        //number of free transactions, before a fee is charged
    private int transactions = 0;                               //how many transactions happened without fee?
    private BigDecimal pendingFee = new BigDecimal("0");    //fee which will be charged on the next balance update
    private final int accountNumber;

    private static final int BIC = 55555;
    private static final int COUNTRY_CODE = 121700;
    private static final String COUNTRY_CHARS = "CH";
    private static final BigDecimal INTEREST_RATE = new BigDecimal("0.005");

    /**
     Constructs a bank account with a zero balance.
     @param accountNumber identifier for the account
     */
    public BankAccount(int accountNumber) {
        this(accountNumber, BigDecimal.ZERO);
    }

    /**
     Constructs a bank account with a given balance.
     @param accountNumber identifier for the account
     @param initialBalance the initial balance
     */
    public BankAccount(int accountNumber, BigDecimal initialBalance) {
        this.accountNumber = accountNumber;
        balance = initialBalance;
        lastUpdate = LocalDateTime.now();
    }

    /**
     Deposits money into the bank account.
     @param amount the amount to deposit
     */
    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
        generateFee(amount);
    }

    /**
     Withdraws money from the bank account.
     @param amount the amount to withdraw
     */
    public void withdraw(BigDecimal amount) {
        balance = balance.subtract(amount);
        generateFee(amount);
    }

    /**
     * Update the balance with the interest rate since the last time, it was given.
     */
    public void periodicalBalanceUpdate() {
        periodicalBalanceUpdateTest(null);
    }

    /**
     * This method is for testing purposes.
     * It does the same thing as {@link #periodicalBalanceUpdate()}, but with a simulated date for now.
     * @param testDate date to test with, normally it would be now
     */
    public void periodicalBalanceUpdateTest(LocalDateTime testDate) {
        LocalDateTime now = LocalDateTime.now();
        if (testDate != null) {
            now = testDate;
        }
        int currentYear = now.getYear();

        //check, if the current year is a leap year
        boolean isLeapYear = (currentYear % 400 == 0) || ((currentYear % 4 == 0) && (currentYear % 100 != 0));

        int daysInCurrentYear = isLeapYear ? 366 : 365;
        BigDecimal millisInCurrentYear = BigDecimal.valueOf((long)daysInCurrentYear * 24 * 60 * 60 * 1000);
        BigDecimal millisSinceLastUpdate = BigDecimal.valueOf(lastUpdate.until(now, ChronoUnit.MILLIS));
        BigDecimal timeFactor = millisSinceLastUpdate.divide(millisInCurrentYear, MathContext.DECIMAL64);
        lastUpdate = now;


        balance = balance.add(balance.multiply(INTEREST_RATE).multiply(timeFactor));      //apply the interest rate
        balance = balance.subtract(pendingFee);                                           //charge the fee
        pendingFee = BigDecimal.valueOf(0);                                     //reset the charged fee
    }

    /**
     * Calculates and adds the transaction fee to the pending fee. Only if the freeTransactionLimit is exceeded.
     */
    private void generateFee(BigDecimal transactionAmount) {
        if (transactions >=  freeTranactionLimit) {
            pendingFee = pendingFee.add(transactionAmount.divide(BigDecimal.valueOf((long)transactionFeeDivider)));
        }
        transactions++;
    }

    /**
     Gets the current balance of the bank account.
     @return the current balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Getter of the transaction fee divider
     *
     * @return the transaction fee
     */
    public int getTransactionFeeDivider() {
        return transactionFeeDivider;
    }

    /**
     * Set the transaction fee divider
     *
     * @param transactionFeeDivider the transaction fee divider
     */
    public void setTransactionFeeDivider(int transactionFeeDivider) {
        this.transactionFeeDivider = transactionFeeDivider;
    }

    /**
     * Generates the iban and returns it
     *
     * @return the iban string
     */
    public String generateIBAN() {
        String iBan = String.valueOf(accountNumber);
        //add zeros to match the length of twelve digits
        iBan = String.join("", Collections.nCopies(12-iBan.length(), "0")) + iBan;
        //add BIC
        iBan = BIC + iBan;
        //add country_code for the quotient
        String toDivide = iBan + COUNTRY_CODE;
        BigInteger toDivideBigInt = new BigInteger(toDivide);
        //divide (iban + country_code) by 97 and substract the rest from 98
        String quotient = String.valueOf(98 - toDivideBigInt.mod(new BigInteger("97")).intValue());
        //add zeros to match the number of digits of two
        iBan = String.join("", Collections.nCopies(2-quotient.length(), "0")) + iBan;
        //add the quotient
        iBan = quotient + iBan;
        //add country_chars
        iBan = COUNTRY_CHARS + iBan;
        //add spaces for better style
        return breakIntoPeaces(iBan);
    }

    /**
     * Splits iban into peaces of four digits.
     *
     * @param iBan iban to split
     * @return splitted iban
     */
    private String breakIntoPeaces(String iBan) {
        StringBuilder brokenIBAN = new StringBuilder();
        int initialLength = iBan.length();
        for (int i = 0; i < initialLength-1; i+=4) {
            brokenIBAN.append(iBan.substring(0, 4)).append(" ");
            iBan = iBan.substring(4);
        }
        brokenIBAN.append(iBan);
        return brokenIBAN.toString();
    }
}