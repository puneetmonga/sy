package com.sy.stream;

import com.sy.domain.Char;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

/**
 * Created by pmonga on 11/2/16.
 */
public class WordStreamTest {

    public static final String WORD1 = "Hello";
    public static final String WORD2 = "World";
    public static final String SPACE = " ";
    public static final String TAB = "\t";

    @Test
    public void shouldSeparateWordsBySpace() {
        shouldIdentifyWords(SPACE);
    }

    @Test
    public void shouldSeparateWordsByTab() {
        shouldIdentifyWords(TAB);
    }

    @Test
    public void shouldIgnoreConsecutiveSpaces() {
        shouldIdentifyWords(SPACE + SPACE + SPACE);
    }

    @Test
    public void shouldIgnoreConsecutiveTabs() {
        shouldIdentifyWords(TAB + TAB + TAB);
    }

    @Test
    public void shouldIgnoreConsecutiveSeparators() {
        shouldIdentifyWords(SPACE + TAB + SPACE);
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldNotReadPastEndOfStream() {
        WordStream wordStream = getWordStream(SPACE);
        wordStream.next();
        wordStream.next();
        wordStream.next();
    }

    @Test(expected = IllegalArgumentException.class)
    public void startCharIsMandatory() {
        new WordStream(null, null);
    }

    @Test
    public void shouldReadOnlyTillProvidedRange() {
        CharacterChain chain = new CharacterChain("Hello World");
        Char start = chain.getStart();
        Char end = start.getNext().getNext().getNext();
        WordStream stream = new WordStream(start, end);
        assertEquals("Hel", stream.next().getValue());
    }


    private void shouldIdentifyWords(String separator) {
        WordStream stream = getWordStream(separator);
        assertEquals(WORD1, stream.next().getValue());
        assertEquals(WORD2, stream.next().getValue());
    }

    private WordStream getWordStream(String space) {
        CharacterChain chain = new CharacterChain(WORD1 + space + WORD2);
        return new WordStream(chain.getStart(), chain.getEnd());
    }
}