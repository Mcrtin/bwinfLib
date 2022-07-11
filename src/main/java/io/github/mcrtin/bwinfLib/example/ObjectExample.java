package io.github.mcrtin.bwinfLib.example;

import io.github.mcrtin.bwinfLib.InputLine;

import java.util.List;
import java.util.function.Function;

public class ObjectExample extends ListExample {

    public ObjectExample(int number, List<InputLine> inputLines) {
        super(number, inputLines);
    }

    public <T> List<T> getNext(Function<InputLine, T> toObject) {
        List<InputLine> inputLines = subList(0, getFirstLine().remove(0).asInt());
        List<T> result = inputLines.stream()
                .map(toObject)
                .toList();
        inputLines.clear();
        return result;
    }
}
