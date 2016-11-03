package com.sy.stream;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by pmonga on 11/3/16.
 */
public class CharacterChainTest {
    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateChainWithoutText() {
        new CharacterChain(null);
    }

    @Test
    public void shouldCreateChain() {
        CharacterChain chain = new CharacterChain("Hi");
        assertEquals('H', chain.getStart().getValue());
        assertEquals('i', chain.getStart().getNext().getValue());
        assertEquals(2, chain.getLength());
    }

}