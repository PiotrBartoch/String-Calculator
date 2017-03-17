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
    public void shouldReturn0WhenPassedStringIsEmpty()  {
        assertEquals("String is empty",0,stringCalculator.add(""));
    }

    @Test
    public void shouldReturn1WhenPassedStringIsNotEmpty() {
        assertEquals("String is Not Empty", 1, stringCalculator.add("1,x,1"));
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowRuntimeExceptionWhenPassedStringIsNotNumber() {
        stringCalculator.add("X");
    }
}