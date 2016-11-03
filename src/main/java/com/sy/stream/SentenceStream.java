package com.sy.stream;

import com.sy.domain.Sentence;
import com.sy.domain.Char;

/**
 * Parses sentences ({@link Sentence} one by one from a given starting {@link Char} and ending {@link Char}.
 * It is an extension of {@link AbstractStream} that maintains state and helper methods to iterate over words one by one.
 * Created by pmonga on 11/1/16.
 */
public class SentenceStream extends AbstractStream<Sentence> {

    private static final int DOT = '.';

    public SentenceStream(Char start, Char end) {
        super(start, end);
    }

    /**
     * @return Returns true if the last read character is a sentence separator IE DOT
     */
    @Override
    public boolean isSeparator() {
        return getLastReadCharacter() == DOT;
    }

    /**
     * Factory method implementation to create a sentence object given starting {@link Char } and ending {@link Char}
     * @param startChar
     * @param endChar
     * @return
     */
    @Override
    public Sentence newTokenInstance(Char startChar, Char endChar) {
        return new Sentence(startChar, endChar);
    }
}
