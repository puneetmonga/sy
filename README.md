# sy

TextAnalyzer takes in a string and return a TextAnalysis. It creates a Text data structure to represent given string. 
Every Text object is made up of sentence objects and every sentence object is made up of word objects. 

TextAnalayzer provides a WordCountAnalyzer that returns count of every word in a given string. 

### Usage:

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
