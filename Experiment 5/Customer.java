import java.util.ArrayList;

public class Customer {
    String id;
    String name;
    String aadhar;
    ArrayList<Account> accounts;

    public Customer(String id, String name, String aadhar) {
        this.id = id;
        this.name = name;
        this.aadhar = aadhar;
        accounts = new ArrayList<>();
    }

    // -------- Add account to customer --------
    public void addAccount(Account a) {
        accounts.add(a);
    }

    // -------- Display consolidated info --------
    public void showAllAccounts() {

        System.out.println("\n=================================");
        System.out.println("Customer ID : " + id);
        System.out.println("Name        : " + name);

        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }

        System.out.println("Accounts:");

        for (Account a : accounts) {
            System.out.println("-----------------------------");
            System.out.println("Account No : " + a.accNo);
            System.out.println("Type       : " + a.accType);
            System.out.println("Balance    : " + a.getBalance());
        }
    }
}