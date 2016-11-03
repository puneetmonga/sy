package com.sy.analyzer.word;

import com.sy.domain.Text;
import com.sy.stream.CharacterChain;
import com.sy.stream.WordStream;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by pmonga on 11/3/16.
 */
public class WordCountAnalyzerTest {

    @Test
    public void shouldCountWords() {
        WordCountAnalyzer analyzer = new WordCountAnalyzer();
        CharacterChain chain = new CharacterChain("Hello World Hello");
        WordStream wordStream = new WordStream(chain.getStart(), chain.getEnd());
        WordCountStatistics count = analyzer.analyze(new Text(chain.getStart(), chain.getEnd()));
        Integer helloWordCount = count.get(wordStream.next());
        assertEquals((Integer) 2, helloWordCount);
        Integer worldWordCount = count.get(wordStream.next());
        assertEquals((Integer) 1, worldWordCount);
    }

}