import java.util.ArrayList;

public class Account {
    protected String accNo;
    protected String accType;
    protected double balance;
    protected ArrayList<Transaction> history;

    public Account(String no, String type, double bal) {
        accNo = no;
        accType = type;
        balance = bal;
        history = new ArrayList<>();
        history.add(new Transaction("OPENING", bal));
    }

    public void deposit(double amt) {
        balance += amt;
        history.add(new Transaction("CREDIT", amt));
    }

    public void withdraw(double amt) throws Exception {
        if (amt > balance)
            throw new Exception("Not enough balance");
        balance -= amt;
        history.add(new Transaction("DEBIT", amt));
    }

    public double getBalance() {
        return balance;
    }

    public void showDetails() {
        System.out.println("\n===== ACCOUNT DETAILS =====");
        System.out.println("Account No : " + accNo);
        System.out.println("Type       : " + accType);
        System.out.println("Balance    : " + balance);

        System.out.println("\n--- Transaction History ---");
        System.out.printf("%-25s %-12s %s\n", "Date", "Type", "Amount");
        for (Transaction t : history) t.show();
    }
}