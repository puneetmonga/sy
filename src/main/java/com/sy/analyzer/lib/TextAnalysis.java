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
 * Class that represents the analysis/output of TextAnalyzer
 * Created by pmonga on 11/2/16.
 */
public class TextAnalysis {
    private Text text;
    private Map<Class<? extends Analysis>, Analysis> results = new HashMap<>();


    public TextAnalysis(Text text) {
        this.text = text;
    }


    /**
     * @return parsed {@link Text} object
     */
    public Text getText() {
        return text;
    }

    /**
     * @return all sentences identified by {@link TextAnalyzer}
     */
    public List<Sentence> sentences() {
        return text.getSentences();
    }

    /**
     * @return all words identified by {@link TextAnalyzer}
     */
    public List<Word> words() {
        List<Word> words = new LinkedList<>();
        for (Sentence sentence : sentences()) {
            words.addAll(sentence.getWords());
        }
        return unmodifiableList(words);

    }

    /**
     *  Method invoked by {@link TextAnalyzer} to set Analysis objects received from individual analyzers({@link com.sy.analyzer.Analyzer})
     */
    public void set(Analysis analysis) {
        results.put(analysis.getClass(), analysis);
    }

    /**
     * Getter to receive the analysis/results back
     * @param clazz Type of analysis you want to retrieve
     * @return Analysis object back
     */
    public <T> T get(Class<? extends Analysis> clazz) {
        return (T) results.get(clazz);
    }
}
