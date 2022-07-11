package io.github.mcrtin.bwinfLib.example;

import io.github.mcrtin.bwinfLib.InputLine;
import lombok.Getter;

import java.util.List;
import java.util.function.Function;
@Getter
public class ListMatrixExample extends MatrixExample {

    private final InputLine firstLine;

    public ListMatrixExample(int number, List<InputLine> inputLines) {
        super(number, inputLines);
        firstLine = remove(0);
    }

    public <T> List<T> getList(Function<InputLine, T> transformer) {
        return stream()
                .map(transformer)
                .toList();
    }
}
