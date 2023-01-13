package model;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.text.ParseException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Ensemble de méthodes utilitaires
 */
public class CustomUtils {

    /**
     * Vérifie si la valeur passée en paramètre correspond au patterne passé en paramètre
     * Sinon expulse une exception
     *
     * @param value La valeur à vérifier
     * @param pattern Le patterne regex auqeul la valeur doit correspondre
     * @param errorMessage le message d'erreur à afficher si la valeur ne correspond pas au patterne
     * @return Le message d'erreur de l'exception si la valeur ne correspond pas au patterne
     * @throws ParseException Si la valeur ne correspond pas au patterne
     */
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

    /**
     * Récupère la chaine de caractères saisie par l'utilisateur
     *
     * @return La chaine de caractères saisie par l'utilisateur
     */
    public static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        return userInput;
    }

    /**
     * Converti une date rentrée par l'utilisateur en chaine de caractères au format jj/mm/aaaa
     *
     * @param date La date à convertir
     * @return La date convertie en chaine de caractères
     */
    public static String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = formatter.format(date);
        return strDate;
    }

    /**
     * Retourne un contact avec un nom, prénom, numéro de téléphone, email et date de naissance aléatoires
     * Si il y a une erreur lors de la génération d'un champ, renvoie null
     *
     * @return Un contact avec un nom, prénom, numéro de téléphone, email et date de naissance aléatoires, ou null si il y a une erreur lors de la génération d'un champ
     */
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

    /**
     * Retourne un prénom aléatoire parmi une liste de prénoms
     *
     * @return Un prénom aléatoire
     */
    public static String getRandomFirstname() {
        String[] firstnames = { "Jean", "Pierre", "Paul", "Jacques", "Marie" };

        int randomIndex = (int) (Math.random() * firstnames.length);
        return firstnames[randomIndex];
    }

    /**
     * Retourne un nom aléatoire parmi une liste de noms
     *
     * @return Un nom aléatoire
     */
    public static String getRandomLastname() {
        String[] lastnames = { "Dupont", "Durand", "Martin", "Lefebvre", "Saumon" };

        int randomIndex = (int) (Math.random() * lastnames.length);
        return lastnames[randomIndex];
    }

    /**
     * Retourne un numéro de téléphone aléatoire commençant par 06
     *
     * @return Un numéro de téléphone aléatoire commençant par 06
     */
    public static String getRandomNumber() {
        String phoneNumber = "06";
        for (int i = 0; i < 8; i++) {
            int randomDigit = (int) (Math.random() * 10);
            phoneNumber += randomDigit;
        }
        return phoneNumber;
    }

    /**
     * Retourne un email aléatoire qui commence par une chaine de caractères aléatoire et finit par un domaine parmi une liste de domaines
     *
     * @return Un email aléatoire
     */
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

    /**
     * Retourne une date de naissance aléatoire entre 1900 et 2020
     *
     * @return Une date de naissance aléatoire entre 1900 et 2020
     */
    public static String getRandomBirthday() {
        String year = Integer.toString(randint(1900, 2020));
        String month = Integer.toString(randint(1, 12));
        String day = Integer.toString(randint(1, 31));

        return day + "/" + month + "/" + year;
    }

    /**
     * Retourne un entier aléatoire entre min et max
     *
     * @param min La valeur minimale du nombre pouvant être généré
     * @param max La valeur maximale du nombre pouvant être généré
     * @return Un entier aléatoire entre min et max
     */
    public static int randint(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("La valeur minimale doit être inférieure à la valeur maximale");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}