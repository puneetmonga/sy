package com.sy.analyzer.lib;

import com.sy.analyzer.Analysis;
import com.sy.domain.Sentence;
import com.sy.domain.Text;
import com.sy.domain.Word;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.util.Collections.unmodifiableList;

/**
 * Class that represents the anaysis/output of TextAnalyzer
 * Created by pmonga on 11/2/16.
 */
public class TextAnalysis {
    private Text text;
    private Map<Class<? extends Analysis>, Analysis> results = new HashMap<>();

    /**
     *
     * @param text
     */
    public TextAnalysis(Text text) {
        this.text = text;
    }


    public Text getText() {
        return text;
    }

    public List<Sentence> sentences() {
        return text.getSentences();
    }

    public List<Word> words() {
        List<Word> words = new LinkedList<>();
        for (Sentence sentence : sentences()) {
            words.addAll(sentence.getWords());
        }
        return unmodifiableList(words);

    }

    public void set(Analysis analysis) {
        results.put(analysis.getClass(), analysis);
    }

    public <T> T get(Class<? extends Analysis> clazz) {
        return (T) results.get(clazz);
    }
}
