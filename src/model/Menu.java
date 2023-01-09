package model;
import java.util.Scanner;

public class Menu {
    public static void displayMenu() {
        System.out.println("1. Ajouter un contact");
        System.out.println("2. Afficher les contacts");
        System.out.println("q. Quitter le menu");

        String input = getUserInput();
        boolean isInputValid = true;

        do {
            switch (input) {
                case "1":
                    addContact();
                    break;
                case "2":
                    displayContacts();
                    break;
                case "q":
                    quit();
                    break;
                default:
                    System.out.println("Veuillez entrer une option valide");
                    isInputValid = false;
                    break;
            }
        } while (!isInputValid);
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
