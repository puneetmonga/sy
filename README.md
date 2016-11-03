# sy

TextAnalyzer takes in a string and return a TextAnalysis. It creates a Text data structure to represent given string. 
Every Text object is made up of sentence objects and every sentence object is made up of word objects. 

TextAnalayzer provides a WordCountAnalyzer that returns count of every word in a given string. 

### Usage:


##### Get all sentences and words
```java
        String TEXT = "The quick brown fox jumped over the lazy brown dog's back";
        /* Create a TextAnalyzer with a given config */
        TextAnalyzer analyzer = new TextAnalyzer(new Config());
        /* Analyze the text */
        TextAnalysis analysis = analyzer.analyze(TEXT);
        /* Print all sentences */
        System.out.println(analysis.sentences());
        /* Print all words */
        System.out.println(analysis.words());
```

##### Get all words for a sentence
```java
        /* Create a TextAnalyzer with a given config */
        TextAnalyzer analyzer = new TextAnalyzer(new Config());
        /* Analyze the text */
        TextAnalysis analysis = analyzer.analyze(TEXT);
        /* Print all sentences */
        System.out.println(analysis.sentences());
        /* Get first sentence */
        Sentence firstSentence = analysis.sentences().get(0);
        /* Get all words for the sentence */
        List<Word> words = sentence.getWords();
```

##### Word Count Example:
```java
        /* Create a config object and enable word count in TextAnalyzer */
        Config config = new Config().enableWordCount();
        /* Create a TextAnalyzer with a given config */
        TextAnalyzer analyzer = new TextAnalyzer(config);
        /* Analyze the text */
        TextAnalysis analysis = analyzer.analyze(TEXT);
        /* Retreieve Word Count Statistics */
        WordCountStatistics wordCountStatistics = analysis.get(WordCountStatistics.class);
        /* Get the first words from list of words */
        Word firstWord = analysis.words().get(0);
        /* Get the word count */ 
        System.out.println("Count =" + wordCountStatistics.get(firstWord));
```

##### Register custom analyzer
```java
        /* Create a custom statistics class */
        public class CustomStatistics implements Analysis {
                private int value;
                public CustomStatistics(int value) {
                        this.value = value;
                }
                public int getValue() {
                        return value;
                }
        }

        /* Create a custom analyzer */
        public class CustomAnalyzer implements Analyzer<CustomStatistics> {
            @Override
            public CustomStatistics analyze(Text text) {
                /* Logic to analyze the text */
                return new CustomStatistics(50);
            }
        }
        
        /* Register and invoke custom analyzer */
        Config config = new Config().registerNewAnalyzer(new CustomAnalyzer());
        TextAnalyzer analyzer = new TextAnalyzer(config);
        TextAnalysis analysis = analyzer.analyze(TEXT);
        CustomStatistics customStatistics = analysis.get(CustomStatistics.class);
        int hardCodedCustomAnalyzerOutput = 50;
        assertEquals(hardCodedCustomAnalyzerOutput,customStatistics.getValue());
        
```
