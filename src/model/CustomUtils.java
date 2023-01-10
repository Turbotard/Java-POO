package model;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.text.ParseException;

public class CustomUtils {
    public static String matchValue(String value, String pattern, String errorMessage) throws ParseException {
        Pattern pat = Pattern.compile(pattern);
        Matcher matcher = pat.matcher(value);

        if (matcher.matches()) {
            return value;
        } else {
            ParseException e = new ParseException(errorMessage, 0);
            throw e;
        }
    }

    public static String setValue(String value, String pattern, String errorMessage) {
        try {
            return matchValue(value, pattern, errorMessage);
        } catch (ParseException e) {
            System.out.println(ConsoleColors.RED + errorMessage + ConsoleColors.DEFAULT);
            return null;
        }
    }
}
