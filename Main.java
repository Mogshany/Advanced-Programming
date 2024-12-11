import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(500.0);
        Calendar date = Calendar.getInstance();

        DepositTransaction deposit = new DepositTransaction(200.0, date, "TXN001");
        WithdrawalTransaction withdrawal = new WithdrawalTransaction(100.0, date, "TXN002");

        deposit.apply(account);
        deposit.printTransactionDetails();

        try {
            withdrawal.apply(account);
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
        withdrawal.printTransactionDetails();

        // Test reverse functionality
        withdrawal.reverse(account);
        System.out.println("Balance after reverse: " + account.getBalance());
    }
}
