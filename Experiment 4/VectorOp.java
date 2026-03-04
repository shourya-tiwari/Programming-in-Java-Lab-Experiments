import java.util.Scanner;

public class VectorOp {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        try {

            System.out.println("---- Vector Operations ----");

            Vector v1 = acceptVector("First");
            Vector v2 = acceptVector("Second");

            Vector sum = Vector.add(v1, v2);
            sum.display("Sum");

            Vector diff = Vector.subtract(v1, v2);
            diff.display("Difference");

            double dot = Vector.dotProduct(v1, v2);
            System.out.println("Dot Product = " + dot);

        }

        catch (VectorException e) {
            System.out.println("Vector Error: " + e.getMessage());
        }

        catch (Exception e) {
            System.out.println("Invalid numeric input.");
        }
    }

    public static Vector acceptVector(String name) throws VectorException {

        System.out.print("Enter number of components for " + name + " vector: ");
        int size = sc.nextInt();

        double[] temp = new double[size];

        System.out.println("Enter " + size + " values:");

        for (int i = 0; i < size; i++) {
            temp[i] = sc.nextDouble();
        }

        return new Vector(temp);
    }
}