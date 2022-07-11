package io.github.mcrtin.bwinfLib;

import io.github.mcrtin.bwinfLib.example.Example;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
@AllArgsConstructor
public class ExampleTester<T extends Example>  {
    private final List<T> examples;

    public static ExampleTester<Example> readExamples(String name, int max) {
        return readExamples(name, 0, max);
    }

    public static ExampleTester<Example> readExamples(String name, int min, int max) {
        return readExamples(name, min, max, Example::new);
    }

    public static ExampleTester<Example> readExamples(String name, int min, int max, int bwinfNumber) {
        return readExamples(name, min, max, bwinfNumber, Example::new);
    }

    public static <T extends Example> ExampleTester<T> readExamples(String name, int min, int max, BiFunction<Integer, List<InputLine>, T> exampleClass) {
        List<T> examples = IntStream.rangeClosed(min, max)
                .mapToObj(i -> readExample(name, i, exampleClass))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
        return new ExampleTester<>(examples);
    }

    public static <T extends Example> ExampleTester<T> readExamples(String name, int min, int max, int bwinfNumber, BiFunction<Integer, List<InputLine>, T> exampleClass) {
        List<T> examples = IntStream.rangeClosed(min, max)
                .mapToObj(i -> readExample(name, i, bwinfNumber, exampleClass))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
        return new ExampleTester<>(examples);
    }


    public void test(Supplier<Testable<T>> testable) {
        test(testable, false);
    }

    public void test(Supplier<Testable<T>> testable, boolean onlyOutput) {
        for (T example : examples) {
            Testable<T> instance = testable.get();
            print(example, instance, instance.test(example), onlyOutput);
        }
    }

    private static void print(Example example, Testable<?> instance, Object output, boolean onlyOutput) {
        if (onlyOutput)
            System.out.println("no. " + example.getNumber() + ":\n" + output);
        else
            System.out.println(example + "\n\nInput:\n" + getFieldsToString(instance) + "\n\nOutput:\n" + output);
    }


    private static List<String> getFieldsToString(Testable<?> instance) {
        return Arrays.stream(instance.getClass().getDeclaredFields())
                .map(field -> {
                    field.setAccessible(true);
                    try {
                        return field.get(instance);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } })
                .map(Object::toString)
                .collect(Collectors.toList());
    }

    /**
     * Only for the first round
     */
    private static <T extends Example> Optional<T> readExample(String name, int number, BiFunction<Integer, List<InputLine>, T> exampleClass) {
        Optional<BufferedReader> connection = openConnection(name, number, "user_upload");
        return connection.map(in -> readExample(in, number, exampleClass));
    }

    private static <T extends Example> Optional<T> readExample(String name, int number, int bwinfNumber, BiFunction<Integer, List<InputLine>, T> exampleClass) {
        Optional<BufferedReader> connection = openConnection(name, number, "bundeswettbewerb/" + bwinfNumber);
        return connection.map(in -> readExample(in, number, exampleClass));
    }

    private static <T extends Example> T readExample(BufferedReader in, int number, BiFunction<Integer, List<InputLine>, T> exampleClass) {
        List<InputLine> inputLines = in.lines()
                .map(line -> new InputLine(Arrays.stream(line.split(" "))
                        .map(Input::new)
                        .collect(Collectors.toList())))
                .collect(Collectors.toList());
        return exampleClass.apply(number, inputLines);
    }

    private static Optional<BufferedReader> openConnection(String name, int number, String extra) {
        try {
            URL url = new URL("https://bwinf.de/fileadmin/" + extra + "/" + name + number + ".txt");
            return Optional.of(new BufferedReader(new InputStreamReader(url.openStream())));
        } catch (FileNotFoundException ignored) {
            System.err.println("no example found with number " + number);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

}
