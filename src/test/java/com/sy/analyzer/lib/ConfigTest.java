package com.sy.analyzer.lib;

import com.sy.analyzer.word.WordCountAnalyzer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by pmonga on 11/2/16.
 */
public class ConfigTest {

    @Test
    public void shouldHaveNoAnalyzers() {
        Config config = new Config();
        assertEquals(0, config.getAnalyzers().size());
    }

    @Test
    public void shouldHaveAnalyzer() {
        Config config = new Config().enableWordCount();
        assertEquals(1, config.getAnalyzers().size());
        assertEquals(WordCountAnalyzer.class, config.getAnalyzers().get(0).getClass());
    }
}
