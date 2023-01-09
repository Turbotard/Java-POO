import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Contact {
    String number;
    String prenom;
    String nom;
    ArrayList<Contact> contacteListe = new ArrayList<Contact>();

    public void Contact(String number_, String prenom_, String nom_){
        number = number_;
        prenom = prenom_;
        nom = nom_;
        contacteListe.add(this);
        
    }
}
