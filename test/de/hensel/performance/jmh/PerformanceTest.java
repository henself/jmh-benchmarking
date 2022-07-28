package de.hensel.performance.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.BenchmarkParams;

import java.util.concurrent.TimeUnit;

/**
 * Dieses abstrakte Klasse stellt den Einstiegspunkt für automatisierte PerformanceTests bei uns dar. Liegt ein Test in
 * diesem package und implementiert dieses Interface, so wird sie automatisch als Integrationstest erkannt und in den
 * Testprozess integriert. Es sind keine weiteren speziellen JUnit-Konfigurationen mehr nötig, sodass sich rein aufs
 * Benchmarking konzentriert werden kann.
 * <br>
 * Außerdem setzt sie sinnvolle default-Werte für die Benchmarkparameter, sodass diese ggf. nicht gesetzt werden müssen,
 * sie können jedoch einfach überschrieben werden, falls benötigt.
 * <p>
 * Die Benchmark-Konfigurationen müssen wie bei einem nicht automatisch Benachmarktest mit jmh vorgenommen werden. Für
 * ein Beispiel betrachte die Klasse {@link GaussSummeTest1}.
 * </p>
 * <p>
 * Um die Ergebniswerte automatisiert in den Unittests auszuwerten, müssen die erwarteten maximal Laufzeiten angegeben
 * werden. Hierfür ist die Methode {@link #getExpectedPerformance(String, BenchmarkParams)} zu verwenden. Diese wird
 * automatisch im Test abgefragt.
 * </p>
 *
 * @author henself
 * @implNote Die default Messeinheit ist {@link TimeUnit#MILLISECONDS}, der default-Output {@link Mode#AverageTime} in
 * {@link TimeUnit#MILLISECONDS}.
 * @see GaussSummeTest1
 * @since 12.04.2022
 */
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Threads(2)
@Warmup(iterations = 10, time = 100, timeUnit = TimeUnit.NANOSECONDS)
@Measurement(iterations = 10, time = 100, timeUnit = TimeUnit.NANOSECONDS)
@Timeout(time = 2)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public abstract class PerformanceTest {

    /**
     * Liefert für einen angefragten Test, die maximale Laufzeit in der Zeiteinheit die für diesen Benchmark-Test
     * eingestellt ist.
     *
     * @param benchmarkName der Methodenname des Benchmarks
     * @param parameter     Das Parameterobjekt des Benchmarks. Wurden im State keine Parameter verwendet oder sind
     *                      diese für die Laufzeit nicht relevant, so kann es ignoriert werden. Sonst kann mittels
     *                      {@link BenchmarkParams#getParam(String)} der entsprechende Parameter aus dem State-Objekt
     *                      angefragt werden.
     * @return die maximal zulässig Größe, meist Laufzeit (vorgegeben durch {@link @BenchmarkMode}) in der Einheit die
     * in {@link @OutputTimeUnit} vorgegeben ist.
     */
    public abstract int getExpectedPerformance(String benchmarkName, BenchmarkParams parameter);
}
