package com.sy.domain;

import com.sy.stream.CharacterChain;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by pmonga on 11/3/16.
 */
public class SentenceTest {

    @Test
    public void shouldCreateSentenceWithWords() {
        CharacterChain chain = new CharacterChain("Hello World");
        Sentence sentence = new Sentence(chain.getStart(), chain.getEnd());
        List<Word> words = sentence.getWords();
        assertEquals(2, words.size());
        assertEquals("Hello", words.get(0).getValue());
        assertEquals("World", words.get(1).getValue());
    }
}