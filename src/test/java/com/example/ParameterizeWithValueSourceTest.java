package com.example;

import com.example.test.WordCounter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParameterizeWithValueSourceTest {

    private WordCounter wordCounter=new WordCounter();

    @ParameterizedTest
    @CsvFileSource(resources = "/word_counter.csv")
    void testWordsInSentenceCsv(int expected,String sentences){
        assertEquals(expected,wordCounter.countWords(sentences));
    }

    @ParameterizedTest
    @CsvSource({"2, Unit testing", "3, JUnit in Action",
                "4, Write solid Java code"} )
    void testWordsInSentence(int expected,String sentences){
        assertEquals(expected,wordCounter.countWords(sentences));
    }

    @ParameterizedTest
    @EnumSource(Sentences.class)
    void testWordsInSentence(Sentences sentences){
        assertEquals(3,wordCounter.countWords(sentences.value()));
    }

    @ParameterizedTest
    @EnumSource(value=Sentences.class,
            names = { "JUNIT_IN_ACTION", "THREE_PARAMETERS" })
    void testSelectedWordsInSentence(Sentences sentence) {
        assertEquals(3, wordCounter.countWords(sentence.value()));
    }

    @ParameterizedTest
    @EnumSource(value=Sentences.class, mode = EnumSource.Mode.EXCLUDE, names =
            { "THREE_PARAMETERS" })
    void testExcludedWordsInSentence(Sentences sentence) {
        assertEquals(3, wordCounter.countWords(sentence.value()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Ch sd",
                  "JUnit in Action"})
    void testWordsInSentence(String sentence){
        assertEquals(3,wordCounter.countWords(sentence));
    }

    enum Sentences{
        JUNIT_IN_ACTION("JUnit in Action"),
        SOME_PARAMETERS("Check some parameters"),
        THREE_PARAMETERS("Check three parameters");

        private final String sentence;

        Sentences(String sentence) {
            this.sentence = sentence;
        }

        public String value() {
            return sentence;
        }

    }
}
