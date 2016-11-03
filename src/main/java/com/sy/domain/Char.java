package com.sy.domain;

/**
 * Node like data structure that holds a character and also points to the next {@link Char} on object in {@link com.sy.stream.CharacterChain}
 * Created by pmonga on 11/1/16.
 */

public class Char {
    private char ch;
    private Char next;
    private int position;

    /**
     * @param ch character
     * @param next Next {@link Char} object in chain
     * @param position position of the character in a chain
     */
    public Char(char ch, Char next, int position) {
        this.ch = ch;
        this.next = next;
        this.position = position;
    }

    /**
     * @return char stored by the object
     */
    public char getValue() {
        return ch;
    }

    /**
     * @return next {@link Char} in chain. This will return null for last {@link Char} in a chain.
     */
    public Char getNext() {
        return next;
    }

    /**
     * @return position of the character in a chain
     */
    public int getPosition() {
        return position;
    }
}
