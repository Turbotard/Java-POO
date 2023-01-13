package model;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.text.ParseException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

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

    public static Contact getRandomContact() {
        Contact contact = new Contact();
        try {
            contact.setFirstname(getRandomFirstname());
            contact.setLastname(getRandomLastname());
            contact.setNumber(getRandomNumber());
            contact.setMail(getRandomMail());
            contact.setBirthday(getRandomBirthday());

            return contact;
        } catch (ParseException e) {
            return null;
        }
    }

    public static String getRandomFirstname() {
        String[] firstnames = { "Jean", "Pierre", "Paul", "Jacques", "Marie" };

        int randomIndex = (int) (Math.random() * firstnames.length);
        return firstnames[randomIndex];
    }

    public static String getRandomLastname() {
        String[] lastnames = { "Dupont", "Durand", "Martin", "Lefebvre", "Saumon" };

        int randomIndex = (int) (Math.random() * lastnames.length);
        return lastnames[randomIndex];
    }

    public static String getRandomNumber() {
        String phoneNumber = "06";
        for (int i = 0; i < 8; i++) {
            int randomDigit = (int) (Math.random() * 10);
            phoneNumber += randomDigit;
        }
        return phoneNumber;
    }

    public static String getRandomMail() {
        String[] domains = { "gmail.com", "hotmail.com", "yahoo.com", "laposte.net", "orange.fr" };
        String[] mailChars = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
                "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "." };

        String randomString = "";
        for (int i = 0; i < 10; i++) {
            int randomCharIndex = (int) (Math.random() * mailChars.length);
            String randomChar = mailChars[randomCharIndex];
            randomString += randomChar;
        }
        randomString += "@";

        int randomIndex = (int) (Math.random() * domains.length);
        randomString += domains[randomIndex];
        return randomString;
    }

    public static String getRandomBirthday() {
        String year = Integer.toString(randint(1900, 2020));
        String month = Integer.toString(randint(1, 12));
        String day = Integer.toString(randint(1, 31));

        return day + "/" + month + "/" + year;
    }

    public static int randint(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("La valeur minimale doit être inférieure à la valeur maximale");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}