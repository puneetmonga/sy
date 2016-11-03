package com.sy.domain;

import com.sy.stream.SentenceStream;

import java.util.LinkedList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

/**
 * Data structure that holds the entire structural representation of the string.  Text has sentences ({@link Sentence}) and every sentence
 * in turn has words ({@link Word}). Text, Sentence and Word are generally termed as Tokens {@link Token}.
 * Created by pmonga on 11/1/16.
 */
public class Text extends AbstractToken {
    private List<Sentence> sentences;

    /**
     * Creates a Text object with starting {@link Char} and ending {@link Char}
     * @param start starting {@link Char} of the Text.
     * @param end ending {@link Char} of the text.
     */
    public Text(Char start, Char end) {
        super(start, end);
        this.sentences = collectSentences();
    }

    /**
     * @return list of sentences part of the Text object.
     */
    public List<Sentence> getSentences() {
        return unmodifiableList(sentences);
    }

    private List<Sentence> collectSentences() {
        List<Sentence> sentences = new LinkedList<>();
        SentenceStream sentenceStream = new SentenceStream(getStartChar(), getEndChar());
        while (sentenceStream.hasNext()) {
            sentences.add(sentenceStream.next());
        }
        return sentences;
    }
}
