package pl.piotrek;

import java.util.regex.Pattern;

/**
 * Created by Piotrek on 14.03.2017.
 */
public class StringCalculator {

    public int add(String string) {
        if (string == null) throw new NullPointerException("String can't be null");
        if (string.length() == 0) {
            return 0;
        }
        if (!Pattern.matches("(\\d+){1}([,\\.]\\d+)*", string))
            throw new IllegalArgumentException("Input have to be numeric type, separated by comma(,)");
        if (Pattern.matches("\\d+", string)) {
            int singleNum = Integer.parseInt(string);
            return singleNum;
        }
        String[] arrayOfNumbers = string.split(",");
        int sum = 0;
        int parsedNumber;
        for (String number: arrayOfNumbers){
            parsedNumber = Integer.parseInt(number);
            sum +=parsedNumber;
        }
        return sum;
    }
}
