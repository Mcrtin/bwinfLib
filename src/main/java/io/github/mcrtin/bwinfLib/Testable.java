package io.github.mcrtin.bwinfLib;

import io.github.mcrtin.bwinfLib.example.Example;

/**
 *
 * @param <T> type of {@link io.github.mcrtin.bwinfLib.example example}
 */
public interface Testable<T extends Example> {

    /**
     *
     * @param example
     * @return
     */
    Object test(T example);
}
