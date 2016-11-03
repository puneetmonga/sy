package com.sy.analyzer;

import com.sy.domain.Text;

/**
 * Interface to represent an individual Analyzer that in invoked by {@link com.sy.analyzer.lib.TextAnalyzer}.
 * {@link com.sy.analyzer.word.WordCountAnalyzer} is one implementation.
 * New Analyzers can be plugged in by implementing this interface which can then be regsitered with {@link com.sy.analyzer.lib.Config}
 *
 * Created by pmonga on 11/2/16.
 */
public interface Analyzer<T extends Analysis> {
    T analyze(Text text);
}
