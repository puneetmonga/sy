package com.sy.analyzer.lib;

import com.sy.analyzer.Analyzer;
import com.sy.domain.Text;
import com.sy.stream.CharacterChain;

/**
 * Root class that is invoked to analyze a given string
 * Created by pmonga on 11/1/16.
 */
public class TextAnalyzer {
    private Config config;

    /**
     * Constructs the TextAnalzyer object with a given config.
     * @param config object to configure TextAnalzyer.
     */
    public TextAnalyzer(Config config) {
        this.config = config;
    }

    /**
     * Method to instruct the TextAnalyzer to analyze a given string
     * @param string string to be analyzed
     * @return Analysis done by the TextAnalyzer
     */
    public TextAnalysis analyze(String string) {
        CharacterChain chain = new CharacterChain(string);
        Text text = new Text(chain.getStart(), chain.getEnd());
        TextAnalysis analysis = new TextAnalysis(text);
        for (Analyzer analyzer : config.getAnalyzers()) {
            analysis.set(analyzer.analyze(text));
        }
        return analysis;
    }
}
