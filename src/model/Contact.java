package model;

import java.util.ArrayList;
import java.util.Date;

public class Contact {
    private String _number;
    private String _prenom;
    private String _nom;
    private String _mail;
    private Date _date;
    private static ArrayList<Contact> contacteListe = new ArrayList<Contact>();

    public Contact(String number, String prenom, String nom, String mail,Date date) {
        _number = number;
        _prenom = prenom;
        _nom = nom;
        _mail = mail;
        _date = date;
    }

    public static void ajouterContact(Contact contact) {
        contacteListe.add(contact);
    }

    public static void supprimerContact(Contact contact) {
        contacteListe.remove(contact);
    }

    public static void afficherContact(String prenom_, String nom_) {
        for (Contact contact : contacteListe) {
            if (contact._nom.equals(nom_) && contact._nom.equals(prenom_)) {
                System.out.println(contact._nom + " " + contact._prenom + " " + contact._number+" "+contact._mail+" "+contact._date);
                break;
            } else {
                System.out.println("Pas trouvé apprend à écrire ou recherche un contact qui existe !");
            }
        }

    }
    public static void afficherAll(){
        for (Contact contact : contacteListe) {
            Contact.afficherContact(contact._prenom, contact._nom);            
        }
    }
}
