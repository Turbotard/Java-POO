package model;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.text.ParseException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        return userInput;
    }

    public static String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = formatter.format(date);
        return strDate;
    }
}