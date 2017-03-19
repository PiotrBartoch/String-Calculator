package pl.calculations;

/**
 * Created by Piotrek on 14.03.2017.
 */
public class StringCalculator {

    public static int add(String string) {
        if (string.isEmpty()) {
            return 0;
        }
        String[] arrayOfNumbers = Separator.getNumbers(string);
        checkForNegativeNumbers(arrayOfNumbers);
        return sumArray(arrayOfNumbers);
    }

    private static void checkForNegativeNumbers(String[] arrayOfNumbers) {
        String negatives = "";
        for (String number : arrayOfNumbers) {
            if (number.contains("-"))
                negatives += "," + number;
        }
        if (!negatives.isEmpty())
            throw new IllegalArgumentException("Negatives not allowed: " + negatives.substring(1)); // index:0 is comma
    }

    private static int sumArray(String[] arrayOfNumbers) {
        int sum = 0;
        int parsedNumber;
        for (String number : arrayOfNumbers) {
            parsedNumber = Integer.parseInt(number);
            if (parsedNumber <= 1000)
                sum += parsedNumber;
        }
        return sum;
    }
}
