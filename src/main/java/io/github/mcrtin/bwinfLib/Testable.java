package io.github.mcrtin.bwinfLib;

import io.github.mcrtin.bwinfLib.example.Example;

import java.util.function.Supplier;

/**
 *
 * @param <T> type of {@link io.github.mcrtin.bwinfLib.example example}
 */
public interface Testable<T extends Example> {

    /**
     * This method is executed in {@link ExampleTester#test(Supplier, boolean)}.
     * @param example the example to test
     * @return the result
     */
    Object test(T example);
}
