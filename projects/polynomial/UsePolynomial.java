import java.util.Arrays;

public class UsePolynomial {

    public static void main(String[] args) {

        Polynomial emptyPol = new Polynomial();
        System.out.printf("- Empty polynomial: '%s'\n", emptyPol);

        Polynomial polA = new Polynomial(new int[] {1});
        System.out.printf("- Polynomial %s: '%s'\n", Arrays.toString(polA.coefficients()), polA);

        Polynomial polB = new Polynomial(new int[] {1, -1, 3, 5, 0});
        System.out.printf("- Polynomial '%s':\n", Arrays.toString(polB.coefficients()));
        System.out.printf("\t * toString(): %s\n", polB);
        System.out.printf("\t * Degree: %d\n", polB.degree());
        System.out.printf("\t * Coefficient `1`: %d\n", polB.coefficient(1));
        System.out.printf("\t * Evaluate with x=1: %.2f\n", polB.value(1F));
        polB.coefficient(1, 1);
        System.out.printf("\t * Change coefficient of X^1 from -1 to 1: %s\n",
                Arrays.toString(polB.coefficients()));
        polB.coefficient(6, 0);
        System.out.printf("\t * Set coefficient of X^6 to 0: %s\n", Arrays.toString(polB.coefficients()));
        polB.coefficient(6, 2);
        System.out.printf("\t * Set coefficient of X^6 to 2: %s\n", Arrays.toString(polB.coefficients()));


        polB = new Polynomial(new int[] {1, -1, 3, 5, 0});
        Polynomial polC = new Polynomial(new int[] {1, -1, -3, -5});
        System.out.printf("- Sum of `%s` and `%s` is: %s\n", polB, polC, polB.add(polC));
        System.out.printf("- Difference of `%s` and `%s` is: %s\n", polB, polC, polB.subtract(polC));

        Polynomial polX = new Polynomial(new int[] {1});
        Polynomial polY = new Polynomial(new int[] {1});
        System.out.printf("- Sum of `%s` and `%s` is: %s\n", polX, polY, polX.add(polY));
        System.out.printf("- Difference of `%s` and `%s` is: %s\n", polX, polY, polX.subtract(polY));
    }
}
