import java.util.Scanner;

public class Calculator {

    public int n1, n2;

    public Calculator() {
        this.n1 = 0;
        this.n2 = 0;
    }

    public Calculator(int x, int y) {
        this.n1 = x;
        this.n2 = y;
    }

    public long addNums(int x, int y) {
        return x + y;
    }

    public long subNums(int x, int y) {
        return x - y;
    }

    public long prodNums(int x, int y) {
        return x * y;
    }

    public double divNums(int x, int y) {
        if (y == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        return (double) x / y;
    }

    public long modNums(int x, int y) {
        if (y == 0) {
            throw new ArithmeticException("Modulus by zero is not allowed");
        }
        return x % y;
    }

    public void displayNums(int x, int y) {
        System.out.println("First num: " + x + ", Second num: " + y + "\n");
    }

    public void enterNums(int x, int y) {
        this.n1 = x;
        this.n2 = y;
    }

    public static void main(String[] args) {

        Calculator user = new Calculator();
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to Calculator");

        System.out.print("Enter first number: ");

        while (!scan.hasNextInt()) {
            System.out.print("Invalid input. Enter an integer: ");
            scan.next();
        }
        int x = scan.nextInt();

        System.out.print("Enter second number: ");

        while (!scan.hasNextInt()) {
            System.out.print("Invalid input. Enter an integer: ");
            scan.next();
        }
        int y = scan.nextInt();

        user.enterNums(x, y);
        user.displayNums(user.n1, user.n2);

        boolean exit = false;

        while (!exit) {

            String menu =
                    "\nCalculator Menu\n" +
                    "1. Add\n" +
                    "2. Subtract\n" +
                    "3. Multiply\n" +
                    "4. Divide\n" +
                    "5. Modulus\n" +
                    "6. Exit\n" +
                    "Enter your choice: ";

            System.out.print(menu);

            while (!scan.hasNextInt()) {
                System.out.print("Invalid choice. Enter a number (1–6): ");
                scan.next();
            }

            int choice = scan.nextInt();

            try {
                switch (choice) {
                    case 1 -> System.out.println("Sum: " + user.addNums(user.n1, user.n2));
                    case 2 -> System.out.println("Difference: " + user.subNums(user.n1, user.n2));
                    case 3 -> System.out.println("Product: " + user.prodNums(user.n1, user.n2));
                    case 4 -> System.out.println("Quotient: " + user.divNums(user.n1, user.n2));
                    case 5 -> System.out.println("Remainder: " + user.modNums(user.n1, user.n2));
                    case 6 -> {
                        System.out.println("Thank you for using Calculator!");
                        exit = true;
                    }
                    default -> System.out.println("Invalid choice. Try again!");
                }
            } catch (ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scan.close();
    }
}
