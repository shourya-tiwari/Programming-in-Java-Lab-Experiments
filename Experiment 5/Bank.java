import java.util.ArrayList;

public class Bank {

    ArrayList<Customer> customers = new ArrayList<>();

    public void addCustomer(Customer c) {
        customers.add(c);
    }

    public Account findAccount(String accNo) throws Exception {
        for (Customer c : customers) {
            for (Account a : c.accounts) {
                if (a.accNo.equals(accNo))
                    return a;
            }
        }
        throw new Exception("Account not found");
    }
}