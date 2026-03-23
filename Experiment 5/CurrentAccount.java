public class CurrentAccount extends Account {

    double overdraft = 2000;

    public CurrentAccount(String no, double bal) {
        super(no, "CURRENT", bal);
    }

    @Override
    public void withdraw(double amt) throws Exception {
        if (amt > balance + overdraft)
            throw new Exception("Overdraft limit exceeded");

        balance -= amt;

        if (balance < 0)
            history.add(new Transaction("DEBIT(OD)", amt));
        else
            history.add(new Transaction("DEBIT", amt));
    }
}