package io.github.mcrtin.bwinfLib.example;

import io.github.mcrtin.bwinfLib.InputLine;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Delegate;

import java.util.List;

/**
 * Represents an example from <a href= "https://bwinf.de/bundeswettbewerb/">bwinf</a>.
 * <p>
 * It is build like a <code>String[][]</code>, where <code>String[n][]</code> row n is
 * and <code>String[][n]</code> column n.
 * <p>
 * The <code>String[][]</code> is wrapped using {@link io.github.mcrtin.bwinfLib.Input Input} and lists.
 */
@Data
public class Example implements List<InputLine> {

    /**
     * the web-number
     */
    private final int number;

    @Delegate
    @NonNull
    private final List<InputLine> inputLines;
}


