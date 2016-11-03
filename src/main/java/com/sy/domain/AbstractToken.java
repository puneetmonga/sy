package com.sy.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Optional;

import static com.sy.util.Utils.newToStringBuilder;

/**
 * Created by pmonga on 11/2/16.
 * Abstract implementation of {@link Token} that groups helper and common method of different Token type IE {@link Text}, {@link Sentence} and {@link Word}
 */
public abstract class AbstractToken implements Token {
    private final Char startChar;
    private final Char endChar;
    private String value;
    private Integer length;

    public AbstractToken(Char startChar, Char endChar) {
        this.startChar = startChar;
        this.endChar = endChar;
    }

    /**
     * Memoize the string value of the token
     * @return string vaue of the token
     */
    @Override
    public String getValue() {
        return Optional.ofNullable(this.value).orElseGet(() -> {
            this.value = Token.super.getValue();
            return this.value;
        });
    }

    @Override
    public Char getStartChar() {
        return startChar;
    }

    @Override
    public Char getEndChar() {
        return endChar;
    }

    /**
     * Memoizes the length of the token value
     * @return length of the token
     */
    @Override
    public int getLength() {
        return Optional.ofNullable(this.length).orElseGet(() -> {
            if (endChar != null) {
                this.length = endChar.getPosition() - startChar.getPosition();
            } else {
                this.length = this.getValue().length();
            }
            return this.length;
        });
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token that = (Token) o;

        return new EqualsBuilder()
                .append(this.getValue(), that.getValue())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.getValue())
                .toHashCode();
    }

    @Override
    public String toString() {
        return newToStringBuilder(this)
                .append(this.getValue())
                .append(getLength())
                .toString();
    }
}