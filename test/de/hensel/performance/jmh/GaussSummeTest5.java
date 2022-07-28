package de.hensel.performance.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.BenchmarkParams;
import org.openjdk.jmh.infra.Blackhole;

import static de.hensel.performance.GaussSumme.gaussSumme5;
import static java.util.stream.Collectors.joining;

/**
 * @author henself
 * @since 12.04.2022
 */
@Warmup(iterations = 20)
@Measurement(iterations = 50)
public class GaussSummeTest5 extends PerformanceTest {

    @Override
    public int getExpectedPerformance(String benchmarkName, BenchmarkParams parameter) {
        if ("benchmark_gauss5".equals(benchmarkName)) {
            return 1;
        }
        String message = benchmarkName + parameter.getParamsKeys().stream().map(x -> x + ":" + parameter.getParam(x)).collect(joining(" "));
        throw new UnsupportedOperationException(message);
    }

    @State(Scope.Thread)
    public static class Parameter {
        @Param({"100", "200", "300", "1000", "10000"})
        int state;
    }


    @Benchmark
    public static void benchmark_gauss5(Parameter state, Blackhole blackhole) {
        blackhole.consume(gaussSumme5(state.state));
    }

}



