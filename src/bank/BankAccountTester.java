package bank;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 A class to test the BankAccount class.
 */
public class BankAccountTester {
    /**
     Tests the methods of the BankAccount class.
     @param args not used
     */
    public static void main(String[] args) {
        BankAccount harrysChecking = new BankAccount();
        BankAccount momsChecking = new BankAccount(new BigDecimal("3000"));
        momsChecking.setTransactionFeeDivider(1000);

        harrysChecking.deposit(new BigDecimal("1500"));

        momsChecking.withdraw(new BigDecimal("500"));
        momsChecking.deposit(new BigDecimal("1000"));

        System.out.println(harrysChecking.getBalance());
        System.out.println("Expected: 1500\n");

        System.out.println(momsChecking.getBalance());
        System.out.println("Expected: 3500\n");

        //Update the interest rate for 182 days in the future
        harrysChecking.periodicalBalanceUpdateTest(LocalDateTime.now().plusDays(182));
        momsChecking.periodicalBalanceUpdate();

        System.out.println(harrysChecking.getBalance());
        System.out.println("Expected: +-1503.75\n");

        System.out.println(momsChecking.getBalance());
        System.out.println("Expected: +-1499\n");
    }
}
