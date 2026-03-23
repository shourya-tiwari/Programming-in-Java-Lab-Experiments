public class LoanAccount extends Account {

    public LoanAccount(String no, double loanAmount) {
        super(no, "LOAN", loanAmount);
    }

    // Paying loan (deposit reduces outstanding balance)
    @Override
    public void deposit(double amt) {
        if (amt > balance)
            amt = balance;   // cannot pay more than loan

        balance -= amt;
        history.add(new Transaction("LOAN PAYMENT", amt));
    }

    // Cannot withdraw from loan account
    @Override
    public void withdraw(double amt) throws Exception {
        throw new Exception("Cannot withdraw from Loan Account");
    }
}