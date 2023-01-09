public class App {
    public static void main(String[] args) throws Exception {
        Menu.displayMenu();
        Contact contact1 = new Contact("123","jiji","lulu");
        Contact.ajouterContact(contact1);
        Contact.afficherContact("jiji","lulu");
    }
}
