import java.util.ArrayList;

public class Contact {
    public String number;
    public String prenom;
    public String nom;
    static ArrayList<Contact> contacteListe = new ArrayList<Contact>();

    Contact(String number_, String prenom_, String nom_) {
        number = number_;
        prenom = prenom_;
        nom = nom_;
    }

    public static void ajouterContact(Contact contact) {
        contacteListe.add(contact);
    }

    public static void supprimerContact(Contact contact) {
        contacteListe.remove(contact);
    }

    public static void afficherContact(String prenom_, String nom_) {
        for (Contact contact : contacteListe) {
            if (contact.nom.equals(nom_) || contact.nom.equals(prenom_)) {
                System.out.println(contact.nom + " " + contact.prenom + " " + contact.number);
                break;
            } else {
                System.out.println("Pas trouvé apprend à écrire ou recherche un contact qui existe !");
            }
        }

    }
}
