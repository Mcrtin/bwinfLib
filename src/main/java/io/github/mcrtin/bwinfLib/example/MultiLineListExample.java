package io.github.mcrtin.bwinfLib.example;

import io.github.mcrtin.bwinfLib.InputLine;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MultiLineListExample extends ListExample {

    public MultiLineListExample(int number, List<InputLine> inputLines) {
        super(number, inputLines);
    }

    public <T> List<T> getList(Function<List<InputLine>, T> transformer, int lines) {
        List<T> result = new ArrayList<>();
        while(size() > lines) {
            List<InputLine> subList = subList(0, lines + 1);
            result.add(transformer.apply(subList));
            subList.clear();
        }
        return result;
    }
}
