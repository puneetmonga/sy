package com.sy.domain;

import com.sy.stream.WordStream;

import java.util.LinkedList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

/**
 * Data structure to represent a given sentence within a {@link Text}. It holds the list of words ({@link Word})
 * that makes up a Sentence object
 * Created by pmonga on 11/2/16.
 */
public class Sentence extends AbstractToken {
    private List<Word> words = new LinkedList<>();

    /**
     * @param startChar starting {@link Char} of the Sentence
     * @param endChar   ending {@link Char} of the Sentence
     */
    public Sentence(Char startChar, Char endChar) {
        super(startChar, endChar);
        WordStream wordStream = new WordStream(startChar, endChar);
        while (wordStream.hasNext()) {
            words.add(wordStream.next());
        }
    }

    /**
     * @return list of words that make up the Sentence.
     */
    public List<Word> getWords() {
        return unmodifiableList(words);
    }
}
