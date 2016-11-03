package com.sy.stream;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by pmonga on 11/3/16.
 */
public class SentenceStreamTest {

    /* Need to test only sentence separation as other cases have been covered in WordStreamTest.
    *  WordStream and SentenceStream share the same parent */
    @Test
    public void shouldIdentifySentences() {
        String text = "Hello World.Hi World";
        CharacterChain chain = new CharacterChain(text);
        SentenceStream stream = new SentenceStream(chain.getStart(), chain.getEnd());
        assertEquals("Hello World", stream.next().getValue());
        assertEquals("Hi World", stream.next().getValue());
    }
}