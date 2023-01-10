package model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.FileWriter;
import java.io.IOException;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class Contact {
    private static final String PHONE_NUMBER_PATTERN = null;
    private String _number;
    private String _prenom;
    private String _nom;
    private String _mail;
    private Date _birthday;
    private static ArrayList<Contact> contactList = new ArrayList<Contact>();

    public Contact(String number, String prenom, String nom, String mail, Date date) {
        _number = number;
        _prenom = prenom;
        _nom = nom;
        _mail = mail;
        _birthday = date;
    }

    public static void addToContactList(Contact contact) {
        try (PrintWriter bw = new PrintWriter(new BufferedWriter(new FileWriter("contacts.csv",true)))){
            String contacString = getContactToString(contact);
            bw.println(contacString);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        contactList.add(contact);
    }

    public static void deleteContact(String firstName, String lastName) {
        Contact contactToRemove = null;
        for (Contact contact : contactList) {
            if (contact._prenom.equals(firstName) && contact._nom.equals(lastName)) {
                contactToRemove = contact;
                break;
            }
        }
        if(contactToRemove != null) {
            contactList.remove(contactToRemove);
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("contacts.csv"))) {
                for (Contact contact : contactList) {
                    String contactString = getContactToString(contact);
                    bw.write(contactString);
                    bw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("The contact "+ firstName+" "+lastName +" does not exist.");
        }
    }

    public static void displaySpecificContact(String prenom, String nom) {
        Contact contact = getContact(prenom, nom);
        if (contact != null) {
            displayContact(contact);
            return;
        }
        System.out.println(
                ConsoleColors.RED + "Contact du nom de " + prenom + " " + nom + " non trouvé \n" + ConsoleColors.RESET);
    }

    static Contact getContact(String prenom, String nom) {
        for (Contact contact : contactList) {
            if (contact._prenom.equals(prenom) && contact._nom.equals(nom)) {
                return contact;
            }
        }
        return null;
    }

    public static void displayContact(Contact contact) {
        String contacString = getContactToString(contact);
        System.out.println(contacString);
    }

    public static String getContactToString(Contact contact) {
        return contact._nom + ";" + contact._prenom + ";" + contact._number + ";" + contact._mail
        + ";" + contact._birthday;
    }

    public static void displayAllContacts() throws ParseException {
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
        Menu.displayMenu();
    }
}
