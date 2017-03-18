package pl.piotrek;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Piotrek on 14.03.2017.
 */
public class StringCalculatorTest {

    StringCalculator stringCalculator;

    @Before
    public void setUp() throws Exception {
        stringCalculator = new StringCalculator();
    }

    @Test
    public void shouldReturn0WhenPassedStringIsEmpty() {
        assertEquals("String is empty", 0, stringCalculator.add(""));
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenPassedStringIsNull() {
        stringCalculator.add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenPassedStringIsNotANumber() {
        stringCalculator.add("X");
        stringCalculator.add("1,s,3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenPassedStringHasCommaAsAFirstCharacter() {
        stringCalculator.add(",1,2,3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenPassedStringHasDuplicatedCommas() {
        stringCalculator.add("1,,3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenPassedStringHasCommaAsALastCharacter() {
        stringCalculator.add("1,2,3,");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenPassedStringHasWhiteSpaces() {
        stringCalculator.add("1,2, 3");
    }


    @Test
    public void shouldReturnTheSameNumberWhenItWasOnlyNOneNumberPassed() {
        assertEquals(3,stringCalculator.add("3"));
    }

    @Test
    public void shouldReturnSumOfEveryAmountOfNumbers() {
        assertEquals(3+2,stringCalculator.add("3,2"));
        assertEquals(3+2+6+10,stringCalculator.add("3,2,6,10"));
        assertEquals(3+2+6+10+100,stringCalculator.add("3,2,6,10,100"));
    }
}