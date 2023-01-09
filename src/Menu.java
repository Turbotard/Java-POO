import java.util.Scanner;

public class Menu {
    public static void displayMenu() {
        System.out.println("1. Ajouter un contact");
        System.out.println("2. Afficher les contacts");
        System.out.println("q. Quitter le menu");

        String input = getUserInput();

        while (!input.equals("1") && !input.equals("2") && !input.equals("q")) {
            System.out.println("Veuillez entrer une option valide");
            input = getUserInput();
        }

        if (input.equals("1")) {
            addContact();
        } else if (input.equals("2")) {
            displayContacts();
        } else if (input.equals("q")) {
            quit();
        }
    }

    public static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez une option :");
        String userInput = scanner.nextLine();
        scanner.close();
        return userInput;
    }

    public static void addContact() {
        System.out.println("Contact ajouté");
    }

    public static void displayContacts() {
        System.out.println("Contacts affichés");
    }

    public static void quit() {
        System.out.println("Au revoir");
    }
}
