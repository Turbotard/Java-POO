package model;

import java.util.Scanner;
import java.text.ParseException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Menu {
    public static Map<String, String> MONTHS = new HashMap<String, String>() {
        {
            put("Jan", "1");
            put("Feb", "2");
            put("Mar", "3");
            put("Apr", "4");
            put("May", "5");
            put("Jun", "6");
            put("Jul", "7");
            put("Aug", "8");
            put("Sep", "9");
            put("Oct", "10");
            put("Nov", "11");
            put("Dec", "12");
        }
    };

    public static void displayMenu() throws ParseException {
        do {
            System.out.println(ConsoleColors.GREEN + "  -- Menu --");
            System.out.println("1. Ajouter un contact");
            System.out.println("2. Afficher les contacts");
            System.out.println("q. Quitter le menu" + ConsoleColors.DEFAULT);

            String input = CustomUtils.getUserInput();

            switch (input) {
                case "1":
                    createContact();
                    break;
                case "2":
                    displayAllContacts();
                    break;
                case "5":
                    delete();
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

    public static void createContact() {
        Contact contact = new Contact();

        do {
            try {
                System.out.println("Entrez le prénom du contact :");
                String firsnameInput = CustomUtils.getUserInput();
                contact.setFirstname(firsnameInput);

                break;
            } catch (ParseException e) {
                System.out.println(ConsoleColors.RED + "Le format du prénom est incorrect" + ConsoleColors.DEFAULT);
            }
        } while (true);

        do {
            try {
                System.out.println("Entrez le nom du contact :");
                String lastnameInput = CustomUtils.getUserInput();
                contact.setLastname(lastnameInput);

                break;
            } catch (ParseException e) {
                System.out.println(ConsoleColors.RED + "Le format du nom est incorrect" + ConsoleColors.DEFAULT);
            }
        } while (true);

        do {
            try {
                System.out.println("Entrez le numéro du contact :");
                String numberInput = CustomUtils.getUserInput();
                contact.setNumber(numberInput);

                break;
            } catch (ParseException e) {
                System.out.println(ConsoleColors.RED + "Le format du numéro est incorrect" + ConsoleColors.DEFAULT);
            }
        } while (true);

        do {
            try {
                System.out.println("Entrez le mail du contact :");
                String mailInput = CustomUtils.getUserInput();
                contact.setMail(mailInput);

                break;
            } catch (ParseException e) {
                System.out.println(ConsoleColors.RED + "Le format du mail est incorrect" + ConsoleColors.DEFAULT);
            }
        } while (true);

        do {
            try {
                System.out.println("Entrez la date de naissance du contact dans le format jj/mm/aaaa :");
                String birthdayInput = CustomUtils.getUserInput();
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

    public static void delete() {
        csvToContactList();

        System.out.println("Entrez le prénom du contact à supprimer :");
        String firsnameInput = CustomUtils.getUserInput();
        System.out.println("Entrez le nom du contact à supprimer :");
        String lastnameInput = CustomUtils.getUserInput();
        deleteContact(firsnameInput, lastnameInput);
    }

    public static void deleteContact(String firstName, String lastName) {
        Contact contactToRemove = getContact(firstName, lastName);
        if (contactToRemove != null) {
            Contact.contactList.remove(contactToRemove);
            contactListToCsv();

            System.out.println(ConsoleColors.RED + "Le contact du nom de " + firstName + " " + lastName
                    + " a été supprimé !" + ConsoleColors.DEFAULT);

        } else {
            System.out.println(ConsoleColors.RED + "Le contact du nom de " + firstName + " " + lastName
                    + " n'existe pas." + ConsoleColors.DEFAULT);
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
        String contactString = contact.toString();
        contactString = contactString.replace(Contact.SEPARATEUR, " | ");
        System.out.println(contactString);
    }

    public static void displayAllContacts() {
        csvToContactList();

        for (Contact contact : Contact.contactList) {
            displayContact(contact);
        }
    }

    static void contactListToCsv() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("contacts.csv"))) {
            for (Contact contact : Contact.contactList) {
                String contactString = contact.toString();
                bw.write(contactString);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void csvToContactList() {
        try (BufferedReader br = new BufferedReader(new FileReader("contacts.csv"))) {
            Contact.contactList.clear();
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {

                String[] fields = line.split(Contact.SEPARATEUR);
                String firstName = fields[0];
                String lastName = fields[1];
                String number = fields[2];
                String mail = fields[3];
                String birthday = fields[4];

                try {
                    Contact contact = new Contact();
                    contact.setFirstname(firstName);
                    contact.setLastname(lastName);
                    contact.setNumber(number);
                    contact.setMail(mail);
                    contact.setBirthday(birthday);

                    Contact.contactList.add(contact);

                } catch (ParseException e) {
                    System.out.println(ConsoleColors.RED + "Le contact ligne " + lineNumber + " n'a pas pu être lu"
                            + ConsoleColors.DEFAULT);
                }
                lineNumber++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
