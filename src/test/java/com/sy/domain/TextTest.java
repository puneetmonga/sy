package com.sy.domain;

import com.sy.stream.CharacterChain;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by pmonga on 11/3/16.
 */
public class TextTest {

    @Test
    public void shouldCreateTextWithSentences() {
        CharacterChain chain = new CharacterChain("Hello World.Hi World");
        Text text = new Text(chain.getStart(), chain.getEnd());
        List<Sentence> sentences = text.getSentences();
        assertEquals(2, sentences.size());
        assertEquals("Hello World", sentences.get(0).getValue());
        assertEquals("Hi World", sentences.get(1).getValue());
    }

}