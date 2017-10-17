package bank;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.GregorianCalendar;

/**
 A bank account has a balance that can be changed by
 deposits and withdrawals.
 */
public class BankAccount {

    private double balance;
    private LocalDateTime lastUpdate;           //the last time, when the interest rate was added to the balance

    private static final float INTEREST_RATE = 0.005f;

    /**
     Constructs a bank account with a zero balance.
     */
    public BankAccount() {
        this(0);
    }

    /**
     Constructs a bank account with a given balance.
     @param initialBalance the initial balance
     */
    public BankAccount(double initialBalance) {
        balance = initialBalance;
        lastUpdate = LocalDateTime.now();
    }

    /**
     Deposits money into the bank account.
     @param amount the amount to deposit
     */
    public void deposit(double amount) {
        balance = balance + amount;
    }

    /**
     Withdraws money from the bank account.
     @param amount the amount to withdraw
     */
    public void withdraw(double amount) {
        balance = balance - amount;
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
        long millisInCurrentYear = (long)daysInCurrentYear * 24 * 60 * 60 * 1000;
        long millisSinceLastUpdate = lastUpdate.until(now, ChronoUnit.MILLIS);
        double timeFactor = (double)millisSinceLastUpdate / millisInCurrentYear;
        lastUpdate = now;

        balance += balance * INTEREST_RATE * timeFactor; //add the interest rate to the balance
    }

    /**
     Gets the current balance of the bank account.
     @return the current balance
     */
    public double getBalance() {
        return balance;
    }
}