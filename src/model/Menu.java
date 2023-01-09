package model;

import java.util.Scanner;

public class Menu {
    public static void displayMenu() {
        do {
            System.out.println("1. Ajouter un contact");
            System.out.println("2. Afficher les contacts");
            System.out.println("q. Quitter le menu");

            String input = getUserInput();

            switch (input) {
                case "1":
                    addContact();
                    return;
                case "2":
                    displayContacts();
                    return;
                case "q":
                    quit();
                    return;
                default:
                    System.out.println("Veuillez entrer une option valide \n");
            }
        } while (true);
    }

    public static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez une option :");
        String userInput = scanner.nextLine();
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
