import java.util.Arrays;

public class Vector {

    private double[] components;

    // Constructor
    public Vector(double[] newV) throws VectorException {

        if (newV.length != 2 && newV.length != 3) {
            throw new VectorException("Only 2D or 3D vectors are allowed.");
        }

        this.components = Arrays.copyOf(newV, newV.length);
    }

    public double[] getComponents() {
        return components;
    }

    // ADDITION
    public static Vector add(Vector v1, Vector v2) throws VectorException {

        checkDimensions(v1, v2);

        double[] res = new double[v1.components.length];

        for (int i = 0; i < res.length; i++) {
            res[i] = v1.components[i] + v2.components[i];
        }

        return new Vector(res);
    }

    // SUBTRACTION
    public static Vector subtract(Vector v1, Vector v2) throws VectorException {

        checkDimensions(v1, v2);

        double[] res = new double[v1.components.length];

        for (int i = 0; i < res.length; i++) {
            res[i] = v1.components[i] - v2.components[i];
        }

        return new Vector(res);
    }

    // DOT PRODUCT
    public static double dotProduct(Vector v1, Vector v2) throws VectorException {

        checkDimensions(v1, v2);

        double res = 0;

        for (int i = 0; i < v1.components.length; i++) {
            res += v1.components[i] * v2.components[i];
        }

        return res;
    }

    // CHECK DIMENSIONS
    public static void checkDimensions(Vector v1, Vector v2) throws VectorException {

        if (v1.components.length != v2.components.length) {

            throw new VectorException(
                    "Vector dimensions must match. Found "
                            + v1.components.length + "D and "
                            + v2.components.length + "D");
        }
    }

    // DISPLAY METHOD
    public void display(String label) {
        System.out.println(label + " = " + Arrays.toString(components));
    }
}