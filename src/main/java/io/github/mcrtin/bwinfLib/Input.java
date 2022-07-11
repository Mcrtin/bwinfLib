package io.github.mcrtin.bwinfLib;

import lombok.Data;
import lombok.NonNull;

import java.util.function.Function;

/**
 * Represents a section without spaces and newlines in an {@link InputLine}.
 */
@Data
public class Input {

    /**
     * A section without spaces and newlines
     */
    @NonNull
    private final String string;

    /**
     * Parses this {@link #string} as an int.
     * This is equal to {@link Integer#parseInt(String)}.
     */
    public int asInt() {
        return Integer.parseInt(string);
    }

    /**
     * Parses this {@link #string} as a double.
     * This is equal to {@link Double#parseDouble(String)}.
     */
    public double asDouble() {
        return Double.parseDouble(string);
    }

    /**
     * Parses this {@link #string} as a long.
     * This is equal to {@link Long#parseLong(String)}.
     */
    public long asLong() {
        return Long.parseLong(string);
    }

    /**
     * Parses this {@link #string} as a byte.
     * This is equal to {@link Byte#parseByte(String)}.
     */
    public byte asByte() {
        return Byte.parseByte(string);
    }

    /**
     * Parses this {@link #string} as a boolean.
     * This is equal to {@link Boolean#parseBoolean(String)}.
     */
    public boolean asBoolean() {
        return Boolean.parseBoolean(string);
    }

    /**
     * This is equal to {@link String#charAt(int)}.
     * @return the first char of this {@link #string}
     */
    public char asChar() {
        return string.charAt(0);
    }

    /**
     * Transform this {@link #string} to a type of your choice.
     * <p>
     * For example:
     * <p>
     * <code>Foo::new</code>
     * @param transformer the transformer for the result type
     * @param <T> the result type
     * @return the transformed {@link #string}
     */
    public <T> T as(Function<String, T> transformer) {
        return transformer.apply(string);
    }

    @Override
    public String toString() {
        return string;
    }
}
