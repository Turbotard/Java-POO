package model;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.ParseException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Menu {
    public static void displayMenu() throws ParseException {
        do {
            System.out.println("  -- Menu --");
            System.out.println("1. Ajouter un contact");
            System.out.println("2. Afficher les contacts");
            System.out.println("q. Quitter le menu");

            String input = getUserInput();

            switch (input) {
                case "1":
                    createContact();
                case "2":
                    Contact.displayAllContacts();
                    return;
                case "q":
                    quit();
                    return;
                default:
                    System.out.println("Veuillez entrer une option valide \n");
            }
        } while (true);
    }

    public static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        return userInput;
    }

    public static void createContact() throws ParseException {
        Pattern pat = Pattern.compile("^(?:(?:\\+|00)33|0)\\s*[1-9](?:[\\s.-]*\\d{2}){4}$");
        Date currentDate = new Date(12 - 12 - 2004);
        System.out.println("Entrez le prenom du contact :");
        String prenom = getUserInput();
        System.out.println("Entrez le nom du contact :");
        String nom = getUserInput();
        System.out.println("Entrez le numero du contact :");
        String numero = getUserInput();
        Matcher matcher = pat.matcher(numero);
        if (matcher.matches()){
            numero = numero;
        }else{
            ParseException e =new ParseException("Le format est incorect", 0);
            throw e;
        }


        
        System.out.println("Entrez le mail du contact :");
        String mail = getUserInput();

        do {
            try {
                System.out.println("Entrez la date de naissance du contact :");
                String date = getUserInput();
                Date birthday = getDate(date);

                Contact contact = new Contact(numero, prenom, nom, mail, birthday);
                Contact.addToContactList(contact);
                break;
            } catch (ParseException e) {
                System.out.println("La date doit avoir le format jj/mm/aaaa");
            }
        } while (true);

    }

    public static void quit() {
        System.out.println("Au revoir");
    }

    public static Date getDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(dateString);

        return date;
    }
}
