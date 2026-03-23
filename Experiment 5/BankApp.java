import java.util.Scanner;

public class BankApp {

    static Bank bank = new Bank();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n========= BANK MENU =========");
            System.out.println("1. Register Customer");
            System.out.println("2. Transaction");
            System.out.println("3. View Account Details");
            System.out.println("4. View All Customers");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int ch = sc.nextInt();

            try {
                if (ch == 1) registerCustomer();
                else if (ch == 2) doTransaction();
                else if (ch == 3) viewDetails();
                else if (ch == 4) showAllCustomers();
                else break;
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }

    // -------- Register Customer --------
    static void registerCustomer() {

        System.out.println("\n------ Register Customer ------");

        System.out.print("Enter Name       : ");
        String name = sc.next();

        System.out.print("Enter Aadhar No. : ");
        String ad = sc.next();

        Customer c = new Customer("C" + (bank.customers.size() + 1), name, ad);

        System.out.println("\nSelect Account Type:");
        System.out.println("1. Savings");
        System.out.println("2. Current");
        System.out.println("3. Fixed Deposit");
        System.out.println("4. Loan");
        System.out.print("Choice: ");

        int t = sc.nextInt();

        System.out.print("Enter Account No : ");
        String acc = sc.next();

        double amt = 0;

        // -------- Proper labels --------
        if (t == 1 || t == 2) {
            System.out.print("Enter Initial Balance : ");
            amt = sc.nextDouble();
        }
        else if (t == 3) {
            System.out.print("Enter FD Amount       : ");
            amt = sc.nextDouble();
        }
        else if (t == 4) {
            System.out.print("Enter Loan Amount     : ");
            amt = sc.nextDouble();
        }

        // -------- Create Account --------
        if (t == 1)
            c.addAccount(new SavingsAccount(acc, amt));
        else if (t == 2)
            c.addAccount(new CurrentAccount(acc, amt));
        else if (t == 3)
            c.addAccount(new FDAccount(acc, amt));
        else
            c.addAccount(new LoanAccount(acc, amt));

        bank.addCustomer(c);

        System.out.println("\nCustomer Registered Successfully!");
        System.out.println("Customer ID : " + c.id);
    }

    // -------- Transactions --------
    static void doTransaction() throws Exception {

        System.out.println("\n------ Transaction Menu ------");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Transfer");
        System.out.print("Choice: ");

        int op = sc.nextInt();

        if (op == 3) {

            System.out.print("From Account No : ");
            Account from = bank.findAccount(sc.next());

            System.out.print("To Account No   : ");
            Account to = bank.findAccount(sc.next());

            System.out.print("Amount          : ");
            double amt = sc.nextDouble();

            from.withdraw(amt);
            to.deposit(amt);

            System.out.println("Transfer Successful!");
        } 
        else {

            System.out.print("Account No : ");
            Account a = bank.findAccount(sc.next());

            System.out.print("Amount     : ");
            double amt = sc.nextDouble();

            if (op == 1) {
                a.deposit(amt);
                System.out.println("Deposit Successful!");
            } 
            else {
                a.withdraw(amt);
                System.out.println("Withdrawal Successful!");
            }
        }
    }

    // -------- View Single Account --------
    static void viewDetails() throws Exception {

        System.out.print("\nEnter Account No : ");
        Account a = bank.findAccount(sc.next());

        a.showDetails();
    }

    // -------- View All Customers --------
    static void showAllCustomers() {

        if (bank.customers.isEmpty()) {
            System.out.println("\nNo customers found.");
            return;
        }

        for (Customer c : bank.customers) {
            c.showAllAccounts();
        }
    }
}