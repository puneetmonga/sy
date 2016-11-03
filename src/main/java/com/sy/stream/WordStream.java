package com.sy.stream;

import com.sy.domain.Char;
import com.sy.domain.Word;

/**
 * Parses words ({@link Word}) one by one from a given starting {@link Char} and ending {@link Char}.
 * It is an extension of {@link AbstractStream} that maintains state and helper methods to iterate over words one by one.
 * Created by pmonga on 11/1/16.
 */
public class WordStream extends AbstractStream<Word> {
    private static final int SPACE = ' ';
    private static final int TAB = '\t';

    public WordStream(Char start, Char end) {
        super(start, end);
    }

    /**
     * Separator of the word character
     * @return true if the last read character is a word separator IE SPACE or TAB
     */
    @Override
    public boolean isSeparator() {
        return getLastReadCharacter() == SPACE || getLastReadCharacter() == TAB;
    }

    /**
     * Factory method implementation to create a word object given starting {@link Char } and ending {@link Char}
     * @param startChar
     * @param endChar
     * @return
     */
    @Override
    public Word newTokenInstance(Char startChar, Char endChar) {
        Word word = new Word(startChar, endChar);
        return word;
    }
}