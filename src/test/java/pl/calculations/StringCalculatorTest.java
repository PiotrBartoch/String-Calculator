package pl.calculations;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Piotrek on 14.03.2017.
 */
public class StringCalculatorTest {

    StringCalculator stringCalculator;

    @Before
    public void setUp() {
        stringCalculator = new StringCalculator();
    }

    @Test
    public void add_ShouldReturn0_WhenPassedStringIsEmpty() {
        assertEquals("String is empty", 0, stringCalculator.add(""));
    }

    @Test(expected = IllegalArgumentException.class)
    public void add_ShouldThrowIllegalArgumentException_WhenPassedStringIsNotANumber() {
        stringCalculator.add("1,s,3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void add_ShouldThrowIllegalArgumentException_WhenPassedStringHasCommaAsAFirstCharacter() {
        stringCalculator.add(",1,2,3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void add_ShouldThrowIllegalArgumentException_WhenPassedStringHasDuplicatedCommas() {
        stringCalculator.add("1,,3");
    }

    @Test
    public void add_ShouldReturnSum_WhenPassedStringHasCommaAsALastCharacter() {
        assertEquals(1 + 2 + 3, stringCalculator.add("1,2,3,"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void add_ShouldThrowIllegalArgumentException_WhenPassedStringHasSpaceCharacter() {
        stringCalculator.add("1,2, 3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void add_ShouldThrowIllegalArgumentException_WhenPassedStringHasTabulationCharacter() {
        stringCalculator.add("1,2   ,3");
    }

    @Test
    public void add_ShouldReturnTheSameNumber_WhenItWasOnlyNOneNumberPassed() {
        assertEquals(3, stringCalculator.add("3"));
    }

    @Test
    public void add_ShouldReturnSumOfEveryAmountOfNumbers_WhenDelimiterIsOnlyComma() {
        assertEquals(3 + 2, stringCalculator.add("3,2"));
        assertEquals(3 + 2 + 6 + 10, stringCalculator.add("3,2,6,10"));
        assertEquals(3 + 2 + 6 + 10 + 100, stringCalculator.add("3,2,6,10,100"));
    }

    @Test
    public void add_ShouldReturnSumOfEveryAmountOfNumbers_WhenDelimiterIsOnlyNewLine() {
        assertEquals(3 + 2, stringCalculator.add("3\n2"));
        assertEquals(3 + 2 + 6 + 10, stringCalculator.add("3\n2\n6\n10"));
        assertEquals(3 + 2 + 6 + 10 + 100, stringCalculator.add("3\n2\n6\n10\n100"));
    }

    @Test
    public void add_ShouldReturnSumOfEveryAmountOfNumbers_WhenDelimitersAreNewLinesAndCommas() {
        assertEquals(3 + 2 + 6 + 10, stringCalculator.add("3\n2,6\n10"));
        assertEquals(3 + 2 + 6 + 10 + 100, stringCalculator.add("3\n2,6\n10,100"));
    }

    @Test
    public void add__ShouldReturnSum_WhenSingleCustomDelimiterIsSpecified() {
        assertEquals(3 + 2 + 4, stringCalculator.add("//[;]\n3;2;4"));
        assertEquals(3 + 2 + 4, stringCalculator.add("//[*]\n3*2*4"));
    }

    @Test
    public void add__ShouldReturnSum_WhenSingleType_AndAnyLength_CustomDelimiterIsSpecified() {
        assertEquals(3 + 2 + 4, stringCalculator.add("//[ddd]\n3ddd2ddd4"));
        assertEquals(3 + 2 + 4, stringCalculator.add("//[***]\n3***2***4"));
    }

    @Test
    public void add__ShouldReturnSum_WhenMultipleType_AndAnyLength_CustomDelimitersAreSpecified() {
        assertEquals(3 + 2 + 4, stringCalculator.add("//[ddd][fff]\n3ddd2fff4"));
        assertEquals(3 + 2 + 4, stringCalculator.add("//[***][%]\n3***2%4"));
        assertEquals(3 + 2 + 4 + 5 + 6 + 7, stringCalculator.add("//[***][%][$$]\n3***2%4***5$$6%7"));
    }


    @Test(expected = NumberFormatException.class)
    public void add_ShouldThrowNumberFormatException_WhenDelimiterSpecificationIsSeparatedByEveryCharButNotNewLine() {
        stringCalculator.add("//;\t3;2;4");
    }

    @Test(expected = NumberFormatException.class)
    public void add_ShouldThrowNumberFormatException_WhenDelimiterSpecificationIsNotSeparatedByNewLine() {
        stringCalculator.add("//;3;2;4");
    }

    @Test
    public void add_ShouldThrowIllegalArgumentException_WhenNegativeNumbersArePassed() {
        try {
            assertEquals(0, stringCalculator.add("-2,3,-4"));
        } catch (IllegalArgumentException e) {
            assertEquals("Negatives not allowed: -2,-4", e.getMessage());
        }
    }

    @Test
    public void add_ShouldIgnoreNumbersBiggerThan1000() {
        assertEquals(2, stringCalculator.add("2,1001"));
    }
}