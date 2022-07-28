package de.hensel.performance.plainJava;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.LongStream;

import static de.hensel.performance.GaussSumme.*;
import static de.hensel.performance.GaussSumme.gaussSumme6;

/**
 * @author henself
 * @since 05.05.2022
 */
public class PlainJavaTest {

    public static List<Integer> params() {
        return List.of(100, 200, 300, 1000, 10000);
    }

    @ParameterizedTest
    @MethodSource("params")
    public void benchmark_gauss1(int i) {
        long start = System.currentTimeMillis();
        gaussSumme1(i);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    @ParameterizedTest
    @MethodSource("params")
    public void benchmark_gauss2(int i) {
        long start = System.nanoTime();
        gaussSumme2(i);
        long end = System.nanoTime();
        System.out.println(end - start);
    }

    @ParameterizedTest
    @MethodSource("params")
    public void benchmark_gauss3(int i) {
        long[] avg = new long[50];
        for (int j = 0; j < avg.length; j++) {
            long start = System.nanoTime();
            gaussSumme3(i);
            long end = System.nanoTime();
            avg[j] = end - start;
        }
        System.out.println(LongStream.of(avg).summaryStatistics());
    }

    @ParameterizedTest
    @MethodSource("params")
    public void benchmark_gauss4(int i) {
        long[] avg = new long[50];
        int k = 0;
        for (int j = 0; j < avg.length; j++) {
            long start = System.nanoTime();
            k += gaussSumme4(i);
            long end = System.nanoTime();
            avg[j] = end - start;
        }
        System.out.println(k);
        System.out.println(LongStream.of(avg).summaryStatistics());
    }

    @ParameterizedTest
    @MethodSource("params")
    public void benchmark_gauss5(int i) {
        long[] avg = new long[50];
        int k = 0;
        for (int j = 0; j < 20; j++) {
            k += gaussSumme5(i);
        }
        k = 0;
        for (int j = 0; j < avg.length; j++) {
            long start = System.nanoTime();
            k += gaussSumme5(i);
            long end = System.nanoTime();
            avg[j] = end - start;
        }
        System.out.println(k);
        System.out.println(LongStream.of(avg).summaryStatistics());
    }

    @ParameterizedTest
    @MethodSource("params")
    public void benchmark_gauss6(int i) {
        long[] avg = new long[50];
        int k = 0;
        for (int j = 0; j < 20; j++) {
            k += gaussSumme6(i);
        }
        k = 0;
        for (int j = 0; j < avg.length; j++) {
            long start = System.nanoTime();
            k += gaussSumme6(i);
            long end = System.nanoTime();
            avg[j] = end - start;
        }
        System.out.println(k);
        System.out.println(LongStream.of(avg).summaryStatistics());
    }

}
