package com.sy.domain;

/**
 * Interface to represent different types of tokens within a string. Token can be of different types {@link Text}, {@link Sentence}, {@link Word}
 * Created by pmonga on 11/2/16.
 */
public interface Token {

    /**
     * @return starting {@link Char} of the token
     */
    Char getStartChar();

    /**
     * @return ending {@link Char} of the token
     */
    Char getEndChar();

    /**
     * Default method that reconstructs the value back from starting char and ending char
     * @return string value of the token
     */
    default String getValue() {
        Char aChar = getStartChar();
        StringBuilder value = new StringBuilder();
        while (aChar != getEndChar()) {
            value.append(aChar.getValue());
            aChar = aChar.getNext();
        }
        return value.toString();
    }

    int getLength();
}
