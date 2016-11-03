package com.sy.domain;

/**
 * Data structure to represent a word within a {@link Sentence}.
 * For now this doesn't have  any specific method of its own. However, it will serve a useful place holder
 * for future enhancements.
 * Created by pmonga on 11/1/16.
 */
public class Word extends AbstractToken {
    /**
     * @param startChar staring {@link Char} of the word
     * @param endChar ending @{@link Char} of the word
     */
    public Word(Char startChar, Char endChar) {
        super(startChar, endChar);
    }
}
