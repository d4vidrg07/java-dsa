import java.util.Arrays;

/**
 * Polynomial with integer coefficients plus an independent term.
 *
 * <p>
 * For example, a polynomial made only of an independent term equal to zero:
 * </p>
 * <pre>{@code
 *     Polynomial pol = new Polynomial();
 * }</pre>
 *
 * <p>
 * A polynomial with the terms {@code 3x^2 + 4x + 5}:
 * </p>
 *
 * <pre>{@code
 *     Polynomial pol = new Polynomial(new int[] {5, 4, 3});
 * }</pre>
 *
 * @version 1.0
 */
public class Polynomial {

    private int[] coefs;

    /**
     * Creates a polynomial of degree zero and independent term zero.
     */
    public Polynomial() {
        this.coefs = new int[]{0};
    }

    /**
     * Initializes the polynomial from an array of integers holding the independent
     * term and the coefficients. The degree of the polynomial is the highest index
     * with a value different from zero.
     *
     * @param coefs coefficients and independent term of the polynomial
     */
    public Polynomial(int[] coefs) {
        if ((coefs == null) || (coefs.length == 0)) {
            this.coefs = new int[]{0};
            return;
        }

        this.coefs = Arrays.copyOf(coefs, coefs.length);
        fixZeros();
    }

    /**
     * Removes the higher-order coefficients that are equal to zero.
     */
    private void fixZeros() {
        int pos = this.coefs.length - 1;
        if (coefs[pos] != 0) return;

        while ((pos > 0) && (this.coefs[pos] == 0)) {
            pos--;
        }
        this.coefs = Arrays.copyOf(this.coefs, pos + 1);
    }

    /**
     * Degree of the polynomial.
     *
     * @return degree of the polynomial
     */
    public int degree() {
        return this.coefs.length - 1;
    }

    /**
     * Returns the value of a coefficient. If {@code i} is zero, the independent
     * term is returned. If the index {@code i} is not valid, zero is returned.
     *
     * @param i index of the coefficient
     * @return value of coefficient {@code i}; {@code 0} if the index is not valid
     */
    public int coefficient(int i) {
        if ((i < 0) || (i >= this.coefs.length)) {
            return 0;
        }

        return this.coefs[i];
    }

    /**
     * Sets the value {@code v} of the coefficient at position {@code i}.
     * <p>
     * If the index is greater than the length of the polynomial, new terms are
     * created and the degree grows. If the index is negative, the method returns
     * silently.
     * </p>
     *
     * @param i index of the coefficient
     * @param v value of the coefficient
     */
    public void coefficient(int i, int v) {
        if (i < 0) {
            return;
        } else if (i < this.coefs.length) {
            this.coefs[i] = v;
            fixZeros();
            return;
        }

        // Position i exceeds the current size of the array
        if (v == 0) return;
        this.coefs = Arrays.copyOf(this.coefs, i + 1);
        this.coefs[i] = v;
    }

    /**
     * Returns an independent copy of the array of coefficients of the polynomial.
     *
     * @return array of integers with the terms of the polynomial
     */
    public int[] coefficients() {
        return Arrays.copyOf(this.coefs, this.coefs.length);
    }

    /**
     * Returns the value of the polynomial when {@code x = v}.
     *
     * @param v value of the variable {@code x}
     * @return result of evaluating the polynomial with {@code x = v}
     */
    public float value(float v) {
        float result = this.coefs[0];
        for (int n = 1; n < this.coefs.length; n++) {
            result += this.coefs[n] * Math.pow(v, n);
        }

        return result;
    }

    /**
     * Adds another polynomial to the current one.
     *
     * @param other polynomial to add
     * @return result of adding both polynomials
     */
    public Polynomial add(Polynomial other) {
        return this.operation(other, 1);
    }

    /**
     * Subtracts another polynomial from the current one.
     *
     * @param other polynomial to subtract
     * @return result of subtracting both polynomials
     */
    public Polynomial subtract(Polynomial other) {
        return this.operation(other, -1);
    }

    /*
     * Adds the coefficients of the current polynomial to the coefficients of a
     * second polynomial. The latter are multiplied by `k` at the moment of the
     * sum. The factor `k` can be used to change the sign of the coefficients of
     * the second polynomial (using `k=-1`).
     *
     * The goal of this method is to share the same code base between the
     * addition and the subtraction of polynomials.
     *
     * @param other polynomial combined with the current one
     * @param k factor applied to the coefficients of the second polynomial
     * @return polynomial with the result of the operation
     */
    private Polynomial operation(Polynomial other, int k) {
        if ((other == null) || (other.coefs.length == 0)) return this;

        // Use two coefficient arrays of the same size to avoid an `if` inside the
        // loop below.
        int[] coefsA = this.coefs;
        int[] coefsB = other.coefs;
        int nItems = this.coefs.length;
        if (this.coefs.length > other.coefs.length) {
            coefsB = Arrays.copyOf(other.coefs, this.coefs.length);
        } else if (this.coefs.length < other.coefs.length) {
            nItems = other.coefs.length;
            coefsA = Arrays.copyOf(this.coefs, other.coefs.length);
        }

        int[] result = new int[nItems];
        for (int n = 0; n < nItems; n++) {
            result[n] = coefsA[n] + (k * coefsB[n]);
        }

        return new Polynomial(result);
    }

    /**
     * Representation of the polynomial as a string such as:
     * <code>
     *     3x^4 + 2x^3 + 4x + 1
     * </code>
     *
     * @see java.lang.Object#toString()
     * @see java.lang.StringBuilder
     */
    @Override
    public String toString() {
        if (this.coefs.length == 1) {
            return Integer.toString(this.coefs[0]);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = this.coefs.length - 1; i >= 0; i--) {
            final int value = coefs[i];
            if (value == 0) {
                continue;
            }

            if ((value > 0) && (i < (this.coefs.length - 1))) {
                sb.append('+');
            }

            if (i == 0) {
                sb.append(value);
                continue;
            }

            if ((value != 1) && (value != -1)) {
                sb.append(value);
            } else if (value == -1) {
                sb.append("-");
            }

            sb.append('x');
            if (i > 1) {
                sb.append('^');
                sb.append(i);
            }
        }
        return sb.toString();
    }
}
