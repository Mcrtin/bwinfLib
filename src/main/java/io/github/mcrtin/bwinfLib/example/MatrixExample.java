package io.github.mcrtin.bwinfLib.example;

import io.github.mcrtin.bwinfLib.Input;
import io.github.mcrtin.bwinfLib.InputLine;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Getter
public class MatrixExample extends Example {

    private final InputLine firstLine;

    public MatrixExample(int number, List<InputLine> inputLines) {
        super(number, inputLines);
        firstLine = remove(0);
    }

    public <T> List<List<T>> getMatrix(Function<Input, T> transformer) {
        return stream()
                .map(line -> line.stream()
                        .map(transformer)
                        .toList())
                .toList();
    }

    public void swapRowsAndColumns() {
        List<InputLine> newInputLines = new ArrayList<>();
        for (InputLine line : this)
            for (int column = 0; column < get(0).size(); column++) {
                if (newInputLines.size() <= column)
                    newInputLines.add(new InputLine(new ArrayList<>()));
                newInputLines.get(column).add(line.get(column));
            }

        clear();
        addAll(newInputLines);
    }
}
