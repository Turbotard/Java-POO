import java.sql.Date;


import model.Contact;
import model.Menu;

public class App {
    public static void main(String[] args) throws Exception {
        Date sm = new Date(12-12-2004);
        Contact contact1 = new Contact("123","jiji","lulu","oui@gmail.com",sm);
        Contact contact2 = new Contact("12345","c'est le JI ","LouLou","non@gmail.com",sm);
        Contact contact3 = new Contact("1234567","c'est le I ","LouLoutte","test@gmail.com",sm);

        Contact.ajouterContact(contact1);
        Contact.ajouterContact(contact2);
        Contact.ajouterContact(contact3);
        Menu.displayMenu();
        
    }
}
