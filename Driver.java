import java.io.File;
import java.io.IOException;



public class Driver {
    public static void main(String[] args) {
        // Test 1: Polynomial Initialization
        double[] coefficients1 = {1, 3, 5};
        int[] exponents1 = {0, 2, 5};
        Polynomial p1 = new Polynomial(coefficients1, exponents1);

        System.out.println("Test 1: Polynomial Initialization");
        System.out.print("Coefficients: ");
        for (double coeff : p1.non_zero_coefficients) {
            System.out.print(coeff + " ");
        }
        System.out.println();

        System.out.print("Exponents: ");
        for (int exp : p1.exponents) {
            System.out.print(exp + " ");
        }
        System.out.println("\n");

        // Test 2: Polynomial Multiplication
        Polynomial p2 = new Polynomial(coefficients1, exponents1);
        Polynomial p3 = p1.multiply(p2);

        System.out.println("Test 2: Polynomial Multiplication");
        System.out.print("Resultant Coefficients: ");
        for (double coeff : p3.non_zero_coefficients) {
            System.out.print(coeff + " ");
        }
        System.out.println();

        System.out.print("Resultant Exponents: ");
        for (int exp : p3.exponents) {
            System.out.print(exp + " ");
        }
        System.out.println("\n");

        // Test 3: File Save
        try {
            p3.saveToFile("result.txt");
            System.out.println("Test 3: File save successful");
        } catch (IOException e) {
            System.out.println("Test 3: Error saving file");
            e.printStackTrace();
        }

        // Test 4: Adding Two Polynomials
        double[] coeff2 = {2, -4, 3};
        int[] exp2 = {0, 1, 2};
        Polynomial p4 = new Polynomial(coeff2, exp2);
        Polynomial p5 = new Polynomial(coeff2, exp2);
        Polynomial p6 = p4.add(p5);

        System.out.println("Test 4: Polynomial Addition");
        System.out.print("Resultant Coefficients: ");
        for (double coeff : p6.non_zero_coefficients) {
            System.out.print(coeff + " ");
        }
        System.out.println();

        System.out.print("Resultant Exponents: ");
        for (int exp : p6.exponents) {
            System.out.print(exp + " ");
        }
        System.out.println("\n");

        // Test 5: Polynomial Evaluation at a Point
        double xValue = 1;
        double result = p4.evaluate(xValue);
        System.out.println("Test 5: Polynomial Evaluation at x = " + xValue);
        System.out.println("Result: " + result + "\n");

        // Test 6: Check for Roots
        System.out.println("Test 6: Check for Roots");
        System.out.println("Root at x = 1: " + p4.hasRoot(1) + "\n");

        // Test 7: Multiplication with Zero Coefficient
        double[] coeff3 = {0, 3, 5};
        int[] exp3 = {0, 1, 2};
        Polynomial p7 = new Polynomial(coeff3, exp3);
        Polynomial p8 = new Polynomial(new double[]{1, 1}, new int[]{0, 1});

        Polynomial p9 = p7.multiply(p8);
        System.out.println("Test 7: Multiplication with Zero Coefficient");
        System.out.print("Resultant Coefficients: ");
        for (double coeff : p9.non_zero_coefficients) {
            System.out.print(coeff + " ");
        }
        System.out.println();

        System.out.print("Resultant Exponents: ");
        for (int exp : p9.exponents) {
            System.out.print(exp + " ");
        }
        System.out.println("\n");
    }
}

