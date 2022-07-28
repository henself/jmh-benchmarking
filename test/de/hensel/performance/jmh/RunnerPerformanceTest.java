package de.hensel.performance.jmh;

import org.assertj.core.presentation.StandardRepresentation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openjdk.jmh.results.Result;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.VerboseMode;
import org.reflections.Reflections;

import java.util.Collection;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Runs all Performance Tests.
 *
 * @author henself
 * @since 12.04.2022
 */
public class RunnerPerformanceTest {

    private static final StandardRepresentation INSTANCE = new StandardRepresentation() {
        @Override
        public String toStringOf(Object object) {
            if (object instanceof RunResult) {
                RunResult runResult = (RunResult) object;
                Result<?> result = runResult.getPrimaryResult();
                String bezeichner = runResult.getParams().getParamsKeys().stream()
                        .map(x -> x + ":" + runResult.getParams().getParam(x))
                        .collect(joining(", ", result.getLabel() + " ", " "));
                return bezeichner + result.getScore() + " " + result.getScoreUnit();
            }
            return super.toStringOf(object);
        }
    };

    private static Stream<Class<? extends PerformanceTest>> performanceTests() {
        String packageName = RunnerPerformanceTest.class.getPackage().getName();
        Reflections thisPackage = new Reflections(packageName);
        return thisPackage.getSubTypesOf(PerformanceTest.class).stream()
                .filter(c -> packageName.equals(c.getPackage().getName()));
    }

    @ParameterizedTest
    @MethodSource("performanceTests")
    public void runBenchmarkTests(Class<? extends PerformanceTest> clazz) throws RunnerException, ReflectiveOperationException {
        PerformanceTest benchmarkInstance = clazz.getConstructor().newInstance();
        Options opt = new OptionsBuilder()
                .include(clazz.getName() + ".*")
                .shouldFailOnError(true)
                .verbosity(VerboseMode.NORMAL)
                .build();

        Collection<RunResult> results = new Runner(opt).run();

        assertFalse(results.isEmpty());
        assertThat(results)
                .withRepresentation(INSTANCE)
                .allMatch(r -> r.getPrimaryResult().getScore() < benchmarkInstance.getExpectedPerformance(r.getPrimaryResult().getLabel(), r.getParams()),
                        "Benchmark is fast as expected.");
    }

}
