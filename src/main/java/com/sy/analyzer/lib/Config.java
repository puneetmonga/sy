package com.sy.analyzer.lib;

import com.sy.analyzer.Analyzer;
import com.sy.analyzer.word.WordCountAnalyzer;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

/**
 * Created by pmonga on 11/2/16.
 * This class is used to configure the TextAnalyzer. For now, it only supports word count feature.
 */
public class Config {
    private List<Analyzer> analyzers = new ArrayList<>();

    /**
     * Enables the word count feature in the config
     * @returns the same config object back for enabling features using fluent-api.
     */
    public Config enableWordCount() {
        analyzers.add(new WordCountAnalyzer());
        return this;
    }

    /**
     * Registers an external analyzer with Config object
     * @param analyzer
     * @return the same config object back
     */
    public Config registerNewAnalyzer(Analyzer analyzer) {
        analyzers.add(analyzer);
        return this;
    }

    public List<Analyzer> getAnalyzers() {
        return unmodifiableList(analyzers);
    }
}
