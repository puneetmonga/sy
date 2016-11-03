package com.sy.stream;

import com.sy.domain.Char;

/**
 * Single linked list to represent the text to be analyzed in a chain of {@link Char}.
 * Created by pmonga on 11/1/16.
 */
public class CharacterChain {

    private Char head;
    private Char tail;
    private int length;

    /**
     * Constructs the Chain from a given string
     * @param text string to be represented as a chain
     */
    public CharacterChain(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Text must not be null");
        }

        createTokenChain(text);
    }

    /**
     * Head of the chain
     * @return
     */
    public Char getStart() {
        return head;
    }

    /**
     * End of the linked list
     * @return
     */
    public Char getEnd() {
        return tail.getNext();
    }

    private void createTokenChain(String text) {
        Char nextChar = null;
        this.length = text.length();
        nextChar = new Char(text.charAt(length - 1), null, length - 1) ;
        this.tail = nextChar;
        for (int i = length - 2; i >= 0; i--) {
            nextChar = new Char(text.charAt(i), nextChar, i);
        }
        this.head = nextChar;
    }

    public int getLength() {
        return length;
    }
}
