public class SavingsAccount extends Account {

    public SavingsAccount(String no, double bal) {
        super(no, "SAVINGS", bal);
    }

    @Override
    public void withdraw(double amt) throws Exception {
        if (balance - amt < 500)
            throw new Exception("Balance cannot go below 500");
        super.withdraw(amt);
    }
}