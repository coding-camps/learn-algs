package jacob.algos.intro;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ListOperationTest {

    Stopwatch stopwatch = null;

    @BeforeEach
    public void beforeTest() {
        stopwatch = Stopwatch.createStarted();
    }

    @AfterEach
    public void afterTest() {
        long nanos = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        System.out.println("Time nanos: " + nanos);
        stopwatch.stop();
    }

    static public Stream<Arguments> provideListAddArgs() {
        int testLen = 900_000;
        return Stream.of(Arguments.of(new LinkedList<Integer>(), testLen), Arguments.of(new ArrayList<Integer>(), testLen));
    }

    @ParameterizedTest(name = "add [{index}]: {arguments}")
    @MethodSource("provideListAddArgs")
    public void testListAdd(List<Integer> list, int testLen) {
        ListOperation.listAdd(list, testLen);
    }

    static class DataProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            List<Integer> linkedList = Lists.newLinkedList();
            List<Integer> arrayList = Lists.newArrayList();
            int max = 100_000;
            IntStream.range(1, max).forEach(value -> {
                linkedList.add(value);
                arrayList.add(value);
            });
            int target = max / 2;
            return Stream.of(new Object[]{linkedList, target}, new Object[]{arrayList, target}).map(Arguments::of);
        }
    }

    @ParameterizedTest(name = "find {index}: {1}")
    @ArgumentsSource(DataProvider.class)
    public void testFind(List<Integer> list, Integer target) {
        ListOperation.listFind(list, target);
    }


}
