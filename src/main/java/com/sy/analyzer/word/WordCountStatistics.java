package com.sy.analyzer.word;

import com.sy.analyzer.Analysis;
import com.sy.domain.Word;
import com.sy.util.BinaryMap;

import java.util.Comparator;
import java.util.Optional;

import static com.sy.util.Utils.newToStringBuilder;
import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;

/**
 * Class represent the output of {@link WordCountAnalyzer}.
 * It relies on {@link BinaryMap} to store the word counts sorted (first on word length and then on alphabetical/natural order).
 * Created by pmonga on 11/2/16.
 */
public class WordCountStatistics implements Analysis {
    private BinaryMap<Word, Integer> countMap = new BinaryMap<>(WORD_COMPARATOR);
    private static final Comparator<Word> WORD_COMPARATOR = comparingInt(Word::getLength).thenComparing(comparing(Word::getValue));

    public Integer get(Word word) {
        return Optional.ofNullable(countMap.get(word)).orElse(0);
    }

    void set(Word word, int count) {
        countMap.put(word, count);
    }

    public BinaryMap<Word, Integer> getCountMap() {
        return countMap;
    }

    @Override
    public String toString() {
        return newToStringBuilder(this).append(countMap).toString();
    }
}
