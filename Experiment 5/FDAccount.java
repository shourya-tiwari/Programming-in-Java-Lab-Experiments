public class FDAccount extends Account {

    public FDAccount(String no, double bal) {
        super(no, "FIXED", bal);
    }

    @Override
    public void withdraw(double amt) throws Exception {
        throw new Exception("FD cannot be withdrawn before maturity");
    }
}