import java.sql.Date;


import model.Contact;
import model.Menu;

public class App {
    public static void main(String[] args) throws Exception {
        Menu.displayMenu();
        Date sm = new Date(12-12-2004);
        Contact contact1 = new Contact("123","jiji","lulu","oui@gmail.com",sm);
        Contact.ajouterContact(contact1);
        Contact.afficherContact("jiji","lulu");
    }
}
