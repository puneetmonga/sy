package com.sy.stream;

import com.sy.domain.Char;
import com.sy.domain.Token;

import java.util.NoSuchElementException;

/**
 * Abstract Stream implementation that maintains state and helper methods to iterate over tokens ({@link Token}) one by one
 * Created by pmonga on 11/2/16.
 */
public abstract class AbstractStream<T extends Token> {
    private Char endChar;
    private Char currentChar;
    private int lastReadCharacter;

    public AbstractStream(Char start, Char end) {
        if (start == null) {
            throw new IllegalArgumentException("Head token must not be null");
        }
        this.currentChar = start;
        this.endChar = end;
        this.lastReadCharacter = currentChar.getValue();
    }

    /**
     * @return true if there is an available token ({@link Token} to read otherwise false
     */
    public boolean hasNext() {
        return currentChar != null && (endChar == null ? true : currentChar != endChar);
    }

    private void moveToNextToken() {
        currentChar = currentChar.getNext();
        if (hasNext()) {
            lastReadCharacter = currentChar.getValue();
        }
    }

    /**
     * @return next available {@link Token}
     */
    public T next() {
        if (!hasNext()) throw new NoSuchElementException();

        Char startChar = currentChar;
        Char endChar = null;
        StringBuilder builder = new StringBuilder();
        while (hasNext() && !isSeparator()) {
            builder.append((char) lastReadCharacter);
            moveToNextToken();
        }
        endChar = currentChar;

        while (isSeparator()) {
            moveToNextToken();
        }

        return newTokenInstance(startChar, endChar);
    }

    /**
     * Hook for child methods to find out the last read character.
     */
    protected int getLastReadCharacter() {
        return lastReadCharacter;
    }

    /**
     * Hook for child classes to specify what is treated as a separator. For example, {@link WordStream} treats space and tab
     * as separator and {@link SentenceStream} treats DOT as a separator
     * @return
     */
    public abstract boolean isSeparator();

    /**
     * Hook for child classes to provide a factory method to instantiate tokens ({@link Token)}
     * @param startChar
     * @param endChar
     * @return
     */
    public abstract T newTokenInstance(Char startChar, Char endChar);
}
