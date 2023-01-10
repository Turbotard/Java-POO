package model;

import java.util.ArrayList;
import java.util.Date;

public class Contact {
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
        contactList.add(contact);
    }

    public static void deleteContact(Contact contact) {
        contactList.remove(contact);
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

    static void displayContact(Contact contact) {
        System.out.println(contact._nom + " " + contact._prenom + " " + contact._number + " " + contact._mail
                + " " + contact._birthday);
    }

    public static void displayAllContacts() {
        for (Contact contact : contactList) {
            Contact.displayContact(contact);
        }
        Menu.displayMenu();
    }
}
