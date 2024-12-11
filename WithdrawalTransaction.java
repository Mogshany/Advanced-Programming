import java.util.Calendar;

public class WithdrawalTransaction extends BaseTransaction {
    private boolean reversed = false;

    public WithdrawalTransaction(double amount, Calendar date, String transactionID) {
        super(amount, date, transactionID);
    }

    @Override
    public void apply(BankAccount ba) throws InsufficientFundsException {
        if (ba.getBalance() < getAmount()) {
            throw new InsufficientFundsException("Insufficient funds for this transaction.");
        } else {
            ba.withdraw(getAmount());
            System.out.println("Withdrawal successful.");
        }
    }

    public void apply(BankAccount ba, boolean allowPartial) {
        try {
            if (ba.getBalance() >= getAmount()) {
                ba.withdraw(getAmount());
                System.out.println("Withdrawal successful.");
            } else if (allowPartial && ba.getBalance() > 0) {
                double remainingBalance = ba.getBalance();
                ba.withdraw(remainingBalance);
                System.out.println("Partially withdrew: " + remainingBalance);
            } else {
                throw new InsufficientFundsException("Insufficient funds.");
            }
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Transaction attempt complete.");
        }
    }

    public boolean reverse(BankAccount ba) {
        if (!reversed) {
            ba.deposit(getAmount());
            reversed = true;
            System.out.println("Withdrawal reversed successfully.");
            return true;
        }
        return false;
    }
}
