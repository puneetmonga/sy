package com.sy.analyzer.word;

import com.sy.analyzer.Analyzer;
import com.sy.domain.Text;
import com.sy.domain.Word;
import com.sy.stream.WordStream;

/**
 * Implementation of {@link Analyzer} that counts every word and return {@link WordCountStatistics}
 * Created by pmonga on 11/2/16.
 */
public class WordCountAnalyzer implements Analyzer<WordCountStatistics> {

    @Override
    public WordCountStatistics analyze(Text text) {
        WordStream stream = new WordStream(text.getStartChar(), text.getEndChar());
        WordCountStatistics wordCountStatistics = new WordCountStatistics();
        while (stream.hasNext()) {
            Word word = stream.next();
            Integer count = wordCountStatistics.get(word);
            wordCountStatistics.set(word, count + 1);
        }
        return wordCountStatistics;
    }
}
