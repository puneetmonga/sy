package com.sy.domain;

import com.sy.stream.CharacterChain;
import com.sy.stream.WordStream;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by pmonga on 11/3/16.
 */
public class WordTest {

    @Test
    public void shouldCreateWord() {
        CharacterChain chain = new CharacterChain("Hello");
        Word word = new Word(chain.getStart(), chain.getEnd());
        assertEquals("Hello", word.getValue());
        assertEquals(5, word.getLength());

    }

    public void testWordEquality() {
        CharacterChain chain = new CharacterChain("Hello Hello");
        WordStream stream = new WordStream(chain.getStart(), chain.getEnd());
        Word word1 = stream.next();
        Word word2 = stream.next();
        assertEquals(word1, word2);
        assertEquals(word1.hashCode(), word2.hashCode());
    }

    public void testWordInequality() {
        CharacterChain chain = new CharacterChain("Hello hello");
        WordStream stream = new WordStream(chain.getStart(), chain.getEnd());
        Word word1 = stream.next();
        Word word2 = stream.next();
        assertFalse(word1.equals(word2));
        assertFalse(word1.hashCode() == word2.hashCode());
    }
}