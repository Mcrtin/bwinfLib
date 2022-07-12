package io.github.mcrtin.bwinfLib;

import lombok.*;
import lombok.experimental.Delegate;

import java.util.List;
import java.util.function.Function;

/**
 * Represents a line of an {@link io.github.mcrtin.bwinfLib.example.Example Example}.
 * @see io.github.mcrtin.bwinfLib.example.Example
 */
@Data
public class InputLine implements List<Input> {

    @Delegate
    @NonNull
    private final List<Input> line;

    /**
     * @see List#get(int)
     * @see Input#asInt()
     */
    public int getAsInt(int index) {
        return get(index).asInt();
    }

    /**
     * @see List#get(int)
     * @see Input#asDouble()
     */
    public double getAsDouble(int index) {
        return get(index).asDouble();
    }

    /**
     * @see List#get(int)
     * @see Input#asLong()
     */
    public long getAsLong(int index) {
        return get(index).asLong();
    }

    /**
     * @see List#get(int)
     * @see Input#asByte()
     */
    public byte getAsByte(int index) {
        return get(index).asByte();
    }

    /**
     * @see List#get(int)
     * @see Input#asBoolean()
     */
    public boolean getAsBoolean(int index) {
        return get(index).asBoolean();
    }

    /**
     * @see List#get(int)
     * @see Input#asChar()
     */
    public char getAsChar(int index) {
        return get(index).asChar();
    }

    /**
     * @see List#get(int)
     */
    public String getString(int index) {
        return get(index).getString();
    }

    /**
     * @see List#get(int)
     * @see Input#as(Function)
     */
    public <T> T getAs(int index, Function<String, T> transformer) {
        return transformer.apply(getString(index));
    }

    @Override
    public String toString() {
        return stream()
                .map(Input::toString)
                .reduce("", (s, s2) -> s + " " + s2);
    }
}
