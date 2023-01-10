package model;

import java.util.Scanner;
import java.text.ParseException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Menu {
    public static void displayMenu() throws ParseException {
        do {
            System.out.println(ConsoleColors.GREEN + "  -- Menu --");
            System.out.println("1. Ajouter un contact");
            System.out.println("2. Afficher les contacts");
            System.out.println("q. Quitter le menu" + ConsoleColors.DEFAULT);

            String input = getUserInput();

            switch (input) {
                case "1":
                    createContact();
                    break;
                case "2":
                    displayAllContacts();
                    break;
                case "q":
                    quit();
                    return;
                default:
                    System.out.println(ConsoleColors.RED + "Veuillez entrer une option valide" + ConsoleColors.DEFAULT);
                    break;
            }
        } while (true);
    }

    public static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        return userInput;
    }

    public static void createContact() {
        Contact contact = new Contact();

        System.out.println("Entrez le prénom du contact :");
        String firsnameInput = getUserInput();
        contact.setFirstname(firsnameInput);

        System.out.println("Entrez le nom du contact :");
        String lastnameInput = getUserInput();
        contact.setLastname(lastnameInput);

        do {
            try {
                System.out.println("Entrez le numéro du contact :");
                String numberInput = getUserInput();
                contact.setNumber(numberInput);

                break;
            } catch (ParseException e) {
                System.out.println(ConsoleColors.RED + "Le format du numéro est incorrect" + ConsoleColors.DEFAULT);
            }
        } while (true);

        do {
            try {
                System.out.println("Entrez le mail du contact :");
                String mailInput = getUserInput();
                contact.setMail(mailInput);

                break;
            } catch (ParseException e) {
                System.out.println(ConsoleColors.RED + "Le format du mail est incorrect" + ConsoleColors.DEFAULT);
            }
        } while (true);

        do {
            try {
                System.out.println("Entrez la de naissance du contact dans le format jj/mm/aaaa :");
                String birthdayInput = getUserInput();
                contact.setBirthday(birthdayInput);

                break;
            } catch (ParseException e) {
                System.out.println(ConsoleColors.RED + "Le format de la date est incorrect" + ConsoleColors.DEFAULT);
            }
        } while (true);

        addToContactList(contact);
    }

    public static void quit() {
        System.out.println(ConsoleColors.RED + "Fermeture du menu..." + ConsoleColors.DEFAULT);
    }

    public static void addToContactList(Contact contact) {
        try (PrintWriter bw = new PrintWriter(new BufferedWriter(new FileWriter("contacts.csv", true)))) {
            String contacString = contact.toString();
            bw.println(contacString);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Contact.contactList.add(contact);
    }

    public static void deleteContact(String firstName, String lastName) {
        Contact contactToRemove = null;
        for (Contact contact : Contact.contactList) {
            if (contact.getFirstname().equals(firstName) && contact.getLastname().equals(lastName)) {
                contactToRemove = contact;
                break;
            }
        }
        if (contactToRemove != null) {
            Contact.contactList.remove(contactToRemove);
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("contacts.csv"))) {
                for (Contact contact : Contact.contactList) {
                    String contactString = contact.toString();
                    bw.write(contactString);
                    bw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("The contact " + firstName + " " + lastName + " does not exist.");
        }
    }

    public static void displaySpecificContact(String prenom, String nom) {
        Contact contact = getContact(prenom, nom);
        if (contact != null) {
            displayContact(contact);
            return;
        }
        System.out.println(
                ConsoleColors.RED + "Contact du nom de " + prenom + " " + nom + " non trouvé \n"
                        + ConsoleColors.DEFAULT);
    }

    static Contact getContact(String prenom, String nom) {
        for (Contact contact : Contact.contactList) {
            if (contact.getFirstname().equals(prenom) && contact.getLastname().equals(nom)) {
                return contact;
            }
        }
        return null;
    }

    public static void displayContact(Contact contact) {
        String contacString = contact.toString();
        System.out.println(contacString);
    }

    public static void displayAllContacts() {
        try (BufferedReader br = new BufferedReader(new FileReader("contacts.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(";");
                String lastName = fields[0];
                String firstName = fields[1];
                System.out.println("Nom : " + lastName + ", Prénom : " + firstName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
