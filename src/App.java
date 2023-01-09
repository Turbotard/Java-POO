import java.sql.Date;

import model.Contact;
import model.Menu;

public class App {
    public static void main(String[] args) throws Exception {
        Date sm = new Date(12 - 12 - 2004);
        Contact contact1 = new Contact("123", "jiji", "lulu", "oui@gmail.com", sm);
        Contact contact2 = new Contact("456", "pierre", "polo", "test@yahoo.fr", sm);

        Contact.addContact(contact1);
        Contact.addContact(contact2);

        Contact.displaySpecificContact("bob", "lulu");

        Menu.displayMenu();
    }
}
