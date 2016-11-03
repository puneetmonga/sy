package com.sy.analyzer.external;

import com.sy.analyzer.Analyzer;
import com.sy.domain.Text;


/* Create a custom analyzer */
public class CustomAnalyzer implements Analyzer<CustomStatistics> {
    @Override
    public CustomStatistics analyze(Text text) {
        /* Logic to analyze the text */
        return new CustomStatistics(50);
    }
}
