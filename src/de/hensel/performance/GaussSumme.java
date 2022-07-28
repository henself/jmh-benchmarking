package de.hensel.performance;

import java.util.stream.IntStream;

public class GaussSumme {

    /**
     * Implementing Gaußsum with for-loop.
     */
    public static int gaussSumme1(int i) {
        int k = 0;
        for (int j = 1; j <= i; j++) {
            k += j;
        }
        return k;
    }

    /**
     * Implementing Gaußsum with fancy java8 stream.
     */
    public static int gaussSumme2(int i) {
        return IntStream.range(1, i + 1).sum();
    }

    /**
     * Implementing Gaußsum with recursion.
     */
    public static int gaussSumme3(int i) {
        return i == 1 ? 1 : i + gaussSumme3(--i);
    }

    /**
     * Implementing Gaußsum the correct way, with potency.
     */
    public static double gaussSumme4(int i) {
        return (Math.pow(i, 2) + i) / 2;
    }

    /**
     * Implementing Gaußsum the correct way, with potency by multiplication.
     */
    public static int gaussSumme5(int i) {
        return (i * i + i) / 2;
    }

    /**
     * Implementing Gaußsum the correct way, with brackets.
     */
    public static int gaussSumme6(int i) {
        return (i * (i + 1)) / 2;
    }

    public static int gaussSumme7(int i) {
        return (i++ * i) >> 1;
    }
}
