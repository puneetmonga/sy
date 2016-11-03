package com.sy.analyzer.lib;

import com.sy.analyzer.external.CustomAnalyzer;
import com.sy.analyzer.external.CustomStatistics;
import com.sy.analyzer.word.WordCountStatistics;
import com.sy.domain.Word;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

/**
 * Created by pmonga on 11/1/16.
 */
public class TextAnalyzerTest {

    public static final String TEXT = "The quick brown fox jumped over the lazy brown dog's back";
    private TextAnalyzer analyzer;
    private TextAnalysis analysis;

    enum Position {
        FIRST, SECOND, THIRD
    }

    @Test
    public void shouldAnalyzeText() {
        analyzer = new TextAnalyzer(new Config());
        analysis = analyzer.analyze(TEXT);
        assertEquals(1, analysis.sentences().size());
        assertEquals(11, analysis.words().size());
        assertEquals("quick", wordAt(Position.SECOND).getValue());
    }

    @Test
    public void shouldCountWord() {
        analyzer = new TextAnalyzer(new Config().enableWordCount());
        analysis = analyzer.analyze(TEXT);
        WordCountStatistics wordCountStatistics = analysis.get(WordCountStatistics.class);
        assertEquals((Integer) 2, wordCountStatistics.get(wordAt(Position.THIRD)));
    }

    @Test
    public void wordCountInAppropriateOrder() {
        analyzer = new TextAnalyzer(new Config().enableWordCount());
        analysis = analyzer.analyze(TEXT);
        WordCountStatistics wordCountStatistics = analysis.get(WordCountStatistics.class);
        Map<Word, Integer> countMap = wordCountStatistics.getCountMap();
        List<String> expectedOrder = asList(new String[]{"The", "fox", "the","back","lazy","over","brown","dog's","quick","jumped"});
        List<String> actualOrder = countMap.entrySet().stream().map(wordCountEntry -> wordCountEntry.getKey().getValue()).collect(toList());
        assertEquals(expectedOrder, actualOrder);
    }

    @Test
    public void countsAreCorrect() {
        analyzer = new TextAnalyzer(new Config().enableWordCount());
        analysis = analyzer.analyze(TEXT);
        WordCountStatistics wordCountStatistics = analysis.get(WordCountStatistics.class);
        Map<Word, Integer> countMap = wordCountStatistics.getCountMap();
        List<Integer> expectecCount = asList(new Integer[]{1,1,1,1,1,1,2,1,1,1});
        List<Integer> actualCount = countMap.entrySet().stream().map(wordCountEntry -> wordCountEntry.getValue()).collect(toList());
        assertEquals(expectecCount, actualCount);
    }

    @Test
    public void shouldInvokedRegisteredCustomAnalyzer() {
        Config config = new Config().registerNewAnalyzer(new CustomAnalyzer());
        analyzer = new TextAnalyzer(config);
        analysis = analyzer.analyze(TEXT);
        CustomStatistics customStatistics = analysis.get(CustomStatistics.class);
        int hardCodedCustomAnalyzerOutput = 50;
        assertEquals(hardCodedCustomAnalyzerOutput,customStatistics.getValue());
    }

    private Word wordAt(Position position) {
        return analysis.words().get(position.ordinal());
    }
}
