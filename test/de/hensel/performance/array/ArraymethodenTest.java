package de.hensel.performance.array;

import org.apache.commons.lang3.ArrayUtils;
import org.openjdk.jmh.annotations.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.All)
@Warmup(iterations = 100)
@Measurement(iterations = 5000)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Threads(4)
public class ArraymethodenTest {

    private static final String v = "A";

    @State(Scope.Thread)
    public static class ArrayState {

        private Random random = new Random(42);
        private int index = 0;
        private final String[] array = new String[4000];

        @Setup(Level.Trial)
        public void doSetupRandom() {
            random = new Random(42);
            for (int i = 0; i < array.length; i++) {
                array[i] = "v" + i;
            }
            random = new Random(42);
        }

        @Setup(Level.Invocation)
        public void doSetupTabellen() {
            index = random.nextInt(array.length);
        }
    }

    @Benchmark
    public String[] insert_arrayUtils(ArrayState array) {
        return ArrayUtils.insert(array.index, array.array, v);
    }

//    @Benchmark
//    public String[] insert_marnej(ArrayState array) {
//        return (String[]) MnjUtil.insert(array.array, v, array.index);
//    }
}
