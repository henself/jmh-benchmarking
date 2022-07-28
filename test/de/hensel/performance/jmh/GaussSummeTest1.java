package de.hensel.performance.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.BenchmarkParams;
import org.openjdk.jmh.infra.Blackhole;

import static de.hensel.performance.GaussSumme.gaussSumme1;
import static java.util.stream.Collectors.joining;

/**
 * @author henself
 * @since 12.04.2022
 */
@Warmup(iterations = 20)
@Measurement(iterations = 50)
public class GaussSummeTest1 extends PerformanceTest {

    @Override
    public int getExpectedPerformance(String benchmarkName, BenchmarkParams parameter) {
        if ("benchmark_gauss1".equals(benchmarkName)) {
            switch (parameter.getParam("state")) {
                case "100":
                    return 40;
                case "200":
                    return 40;
                case "300":
                    return 50;
                case "1000":
                    return 60;
                case "10000":
                    return 70;
                default:
                    break;
            }
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
    public static void benchmark_gauss1(Parameter state, Blackhole blackhole) {
        blackhole.consume(gaussSumme1(state.state));
    }
}



