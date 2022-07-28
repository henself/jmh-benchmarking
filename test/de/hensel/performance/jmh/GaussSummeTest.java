package de.hensel.performance.jmh;

import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static de.hensel.performance.GaussSumme.*;

/**
 * @author henself
 * @since 12.04.2022
 */
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Threads(2)
@Warmup(iterations = 20, time = 100, timeUnit = TimeUnit.NANOSECONDS)
@Measurement(iterations = 50, time = 100, timeUnit = TimeUnit.NANOSECONDS)
@Timeout(time = 2)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class GaussSummeTest {
    public static void main(String[] args) throws IOException {
        Main.main(new String[]{});
    }

    @State(Scope.Thread)
    public static class Parameter {
        @Param({"100", "200", "300", "1000", "10000"})
        int state;
    }

    @Benchmark
    public static void benchmark_gauss1(Parameter state, Blackhole blackhole) {
        blackhole.consume(gaussSumme1(state.state));
    }

    @Benchmark
    public static void benchmark_gauss2(Parameter state, Blackhole blackhole) {
        blackhole.consume(gaussSumme2(state.state));
    }

    @Benchmark
    public static void benchmark_gauss3(Parameter state, Blackhole blackhole) {
        blackhole.consume(gaussSumme3(state.state));
    }

    @Benchmark
    public static void benchmark_gauss4(Parameter state, Blackhole blackhole) {
        blackhole.consume(gaussSumme4(state.state));
    }

    @Benchmark
    public static void benchmark_gauss5(Parameter state, Blackhole blackhole) {
        blackhole.consume(gaussSumme5(state.state));
    }

    @Benchmark
    public static void benchmark_gauss6(Parameter state, Blackhole blackhole) {
        blackhole.consume(gaussSumme6(state.state));
    }

    @Benchmark
    public static void benchmark_gauss7(Parameter state, Blackhole blackhole) {
        blackhole.consume(gaussSumme7(state.state));
    }
}



