package bank;

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
        BankAccount momsChecking = new BankAccount(3000);

        harrysChecking.deposit(2000);
        harrysChecking.withdraw(500);

        momsChecking.withdraw(500);

        System.out.println(harrysChecking.getBalance());
        System.out.println("Expected: 1500");

        System.out.println(momsChecking.getBalance());
        System.out.println("Expected: 2500");

        //Update the interest rate for 182 days in the future
        harrysChecking.periodicalBalanceUpdateTest(LocalDateTime.now().plusDays(182));

        System.out.println(harrysChecking.getBalance());
        System.out.println("Expected: +-1503.75");
    }
}
