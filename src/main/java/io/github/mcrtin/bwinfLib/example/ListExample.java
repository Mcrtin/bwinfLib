package io.github.mcrtin.bwinfLib.example;

import io.github.mcrtin.bwinfLib.InputLine;
import lombok.Getter;

import java.util.List;
import java.util.function.Function;

@Getter
public class ListExample extends Example {

    private final InputLine firstLine;

    /**
     * Removes the first line.
     * You can get it with {@link #getFirstLine()}.
     */
    public ListExample(int number, List<InputLine> inputLines) {
        super(number, inputLines);
        firstLine = remove(0);
    }

    /**
     * Transforms each line to a
     */
    public <T> List<T> getList(Function<InputLine, T> transformer) {
        return stream()
                .map(transformer)
                .toList();
    }
}
