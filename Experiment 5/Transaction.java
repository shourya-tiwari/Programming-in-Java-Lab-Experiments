import java.util.Date;

public class Transaction {
    String type;
    double amount;
    String time;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
        this.time = new Date().toString();
    }

    public void show() {
        System.out.printf("%-25s %-12s %.2f\n", time, type, amount);
    }
}