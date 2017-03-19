package pl.calculations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Separator {
    private static Pattern customDelimitersValidator = Pattern.compile("//(\\[(\\D+)\\])+\n.*");
    private static Matcher customDelimitersMatcher;
    private static int startIndex;

    protected static String[] getNumbers(String string) {
        String delimiters = getDelimiters(string);
        String[] separatedNumbers = separateNumbers(string, delimiters);
        return separatedNumbers;
    }

    private static String[] separateNumbers(String string, String delimiters) {
        if (areCustomDelimitersValid(string)) {
            string = string.substring(startIndex);
        }
        return string.split(delimiters);
    }

    private static String getDelimiters(String string) {
        String delimiters = ",|\n";
        if (areCustomDelimitersValid(string))
            delimiters += "|" + getEscapedChars().substring(1);
        return delimiters;
    }

    private static String getEscapedChars() {
        String bracketsRemoved = getCustomDelimiters();
        String[] delimitersToEscape = bracketsRemoved.split("\\|");
        String multipleDelimiters = "";
        for (String delimiter : delimitersToEscape) {
            multipleDelimiters += "|" + Pattern.quote(delimiter);
        }
        return multipleDelimiters;
    }

    private static String getCustomDelimiters() {
        String customDelimiters = customDelimitersMatcher.group(1);
        setStartIndex(customDelimiters);
        return customDelimiters.replaceAll("\\[", "|").replaceAll("\\]", "").substring(1);
    }

    private static void setStartIndex(String customDelimiters) {
        startIndex = customDelimiters.length() + 3;
    }

    private static boolean areCustomDelimitersValid(String string) {
        customDelimitersMatcher = customDelimitersValidator.matcher(string);
        return customDelimitersMatcher.matches();
    }
}