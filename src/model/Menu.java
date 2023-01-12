package model;

import java.text.ParseException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class Menu {
    public static Map<String, String> MONTHS = new HashMap<String, String>() {
        {
            put("Jan", "1");
            put("Feb", "2");
            put("Mar", "3");
            put("Apr", "4");
            put("May", "5");
            put("Jun", "6");
            put("Jul", "7");
            put("Aug", "8");
            put("Sep", "9");
            put("Oct", "10");
            put("Nov", "11");
            put("Dec", "12");
        }
    };

    public static void displayMenu() throws ParseException {
        csvToContactList();
        do {
            System.out.println(ConsoleColors.GREEN + "  -- Menu --");
            System.out.println("1. Ajouter un contact");
            System.out.println("2. Afficher les contacts");
            System.out.println("3. Trier les contacts");
            System.out.println("4. Modifier un contact");
            System.out.println("5. Rechercher les contacts");
            System.out.println("6. Supprimer un contact");
            System.out.println("q. Quitter le menu" + ConsoleColors.DEFAULT);

            String input = CustomUtils.getUserInput();

            switch (input) {
                case "1":
                    createContact();
                    break;
                case "2":
                    displayAllContacts();
                    break;
                case "3":
                    displayMenuTri();
                    break;
                case "4":
                    edit();
                    break;
                case "5":
                    displayMenuSearch();
                    break;
                case "6":
                    delete();
                    break;
                case "q":
                    quit();
                    return;
                default:
                    System.out.println(ConsoleColors.RED + "Veuillez entrer une option valide" + ConsoleColors.DEFAULT);
                    break;
            }
        } while (true);
    }

    public static void displayEditMenu(Contact contactToEdit) {
        do {
            displayContact(contactToEdit);
            System.out.println(ConsoleColors.GREEN + "  -- Quel valeur voulez-vous modifier ? --");
            System.out.println("1. Prénom");
            System.out.println("2. Nom");
            System.out.println("3. Numéro");
            System.out.println("4. Mail");
            System.out.println("5. Date de naissance");
            System.out.println("q. Quitter le menu d'édition" + ConsoleColors.DEFAULT);

            String input = CustomUtils.getUserInput();
            switch (input) {
                case "1":
                    System.out.println("Entrez le nouveau prénom :");
                    try {
                        contactToEdit.setFirstname(CustomUtils.getUserInput());
                        if (!Contact.contactExist(contactToEdit.getFirstname(), contactToEdit.getLastname())) {
                            System.out
                                    .println(ConsoleColors.YELLOW + "Prénom modifié avec succès !"
                                            + ConsoleColors.DEFAULT);
                        } else {
                            System.out.println(ConsoleColors.RED + "Le contact " + contactToEdit.getFirstname() + " "
                                    + contactToEdit.getLastname() + " existe déjà." + ConsoleColors.DEFAULT);
                        }
                    } catch (ParseException e) {
                        System.out.println(
                                ConsoleColors.RED + "Le format du prénom est incorrect" + ConsoleColors.DEFAULT);
                    }
                    break;
                case "2":
                    System.out.println("Entrez le nouveau nom :");
                    try {
                        contactToEdit.setLastname(CustomUtils.getUserInput());

                        if (!Contact.contactExist(contactToEdit.getFirstname(), contactToEdit.getLastname())) {
                            System.out
                                    .println(ConsoleColors.YELLOW + "Nom modifié avec succès !"
                                            + ConsoleColors.DEFAULT);
                        } else {
                            System.out.println(ConsoleColors.RED + "Le contact " + contactToEdit.getFirstname() + " "
                                    + contactToEdit.getLastname() + " existe déjà." + ConsoleColors.DEFAULT);
                        }
                    } catch (ParseException e) {
                        System.out
                                .println(ConsoleColors.RED + "Le format du nom est incorrect" + ConsoleColors.DEFAULT);
                    }
                    break;
                case "3":
                    System.out.println("Entrez le nouveau numéro de téléphone :");
                    try {
                        contactToEdit.setNumber(CustomUtils.getUserInput());
                        if (!Contact.numberExist(contactToEdit.getNumber())) {
                            System.out
                                    .println(ConsoleColors.YELLOW + "Numéro de téléphone modifié avec succès !"
                                            + ConsoleColors.DEFAULT);
                        } else {
                            System.out
                                    .println(ConsoleColors.RED + "Un contact a déjà ce numéro" + ConsoleColors.DEFAULT);
                        }

                    } catch (ParseException e) {
                        System.out.println(ConsoleColors.RED + "Le format du numéro de téléphone est incorrect"
                                + ConsoleColors.DEFAULT);
                    }
                    break;

                case "4":
                    System.out.println("Entrez la nouvelle adresse email :");
                    try {
                        contactToEdit.setMail(CustomUtils.getUserInput());
                        System.out.println(
                                ConsoleColors.YELLOW + "Adresse email modifiée avec succès !" + ConsoleColors.DEFAULT);
                    } catch (ParseException e) {
                        System.out.println(ConsoleColors.RED + "Le format de l'adresse email est incorrect"
                                + ConsoleColors.DEFAULT);
                        break;
                    }
                    break;
                case "5":
                    System.out.println("Entrez la nouvelle date de naissance :");
                    try {
                        contactToEdit.setBirthday(CustomUtils.getUserInput());
                        System.out.println(ConsoleColors.YELLOW + "Date de naissance modifiée avec succès !"
                                + ConsoleColors.DEFAULT);
                    } catch (ParseException e) {
                        System.out.println(ConsoleColors.RED + "Le format de la date de naissance est incorrect"
                                + ConsoleColors.DEFAULT);
                        break;
                    }
                    break;
                case "q":
                    System.out.println(ConsoleColors.RED + "Fermeture du menu d'édition..." + ConsoleColors.DEFAULT);
                    contactListToCsv();
                    return;
                default:
                    System.out.println(ConsoleColors.RED + "Veuillez entrer une option valide" + ConsoleColors.DEFAULT);
                    break;
            }
        } while (true);
    }

    public static void edit() {
        System.out.println("Entrez le prénom du contact à modifier :");
        String firstnameInput = CustomUtils.getUserInput();

        System.out.println("Entrez le nom du contact à modifier :");
        String lastnameInput = CustomUtils.getUserInput();

        editContact(firstnameInput, lastnameInput);
    }

    public static void editContact(String firstName, String lastName) {
        Contact contactToEdit = getContact(firstName, lastName);

        if (contactToEdit != null) {
            displayEditMenu(contactToEdit);
        } else {
            System.out.println(ConsoleColors.RED + "Le contact du nom de " + firstName + " | " + lastName
                    + " n'existe pas" + ConsoleColors.DEFAULT);
        }
    }

    public static void createContact() {
        Contact newContact = new Contact();

        do {
            do {
                try {
                    System.out.println("Entrez le prénom du contact :");
                    String firsnameInput = CustomUtils.getUserInput();
                    newContact.setFirstname(firsnameInput);

                    break;
                } catch (ParseException e) {
                    System.out.println(ConsoleColors.RED + "Le format du prénom est incorrect" + ConsoleColors.DEFAULT);
                }
            } while (true);

            do {
                try {
                    System.out.println("Entrez le nom du contact :");
                    String lastnameInput = CustomUtils.getUserInput();
                    newContact.setLastname(lastnameInput);

                    break;
                } catch (ParseException e) {
                    System.out.println(ConsoleColors.RED + "Le format du nom est incorrect" + ConsoleColors.DEFAULT);
                }
            } while (true);

            if (!Contact.contactExist(newContact.getFirstname(), newContact.getLastname())) {
                break;
            }
            System.out.println(ConsoleColors.RED + "Le contact " + newContact.getFirstname() + " "
                    + newContact.getLastname() + " existe déjà." + ConsoleColors.DEFAULT);
        } while (true);

        do {
            try {
                System.out.println("Entrez le numéro du contact :");
                String numberInput = CustomUtils.getUserInput();
                newContact.setNumber(numberInput);

                if (!Contact.numberExist(newContact.getNumber())) {
                    break;
                }
                System.out.println(ConsoleColors.RED + "Un contact a déjà ce numéro" + ConsoleColors.DEFAULT);

            } catch (ParseException e) {
                System.out.println(ConsoleColors.RED + "Le format du numéro est incorrect" + ConsoleColors.DEFAULT);
            }
        } while (true);

        do {
            try {
                System.out.println("Entrez le mail du contact :");
                String mailInput = CustomUtils.getUserInput();
                newContact.setMail(mailInput);

                break;
            } catch (ParseException e) {
                System.out.println(ConsoleColors.RED + "Le format du mail est incorrect" + ConsoleColors.DEFAULT);
            }
        } while (true);

        do {
            try {
                System.out.println("Entrez la date de naissance du contact dans le format jj/mm/aaaa :");
                String birthdayInput = CustomUtils.getUserInput();
                newContact.setBirthday(birthdayInput);

                break;
            } catch (ParseException e) {
                System.out.println(ConsoleColors.RED + "Le format de la date est incorrect" + ConsoleColors.DEFAULT);
            }
        } while (true);

        addToContactList(newContact);
    }

    public static void quit() {
        System.out.println(ConsoleColors.RED + "Fermeture du menu..." + ConsoleColors.DEFAULT);
    }

    public static void addToContactList(Contact contact) {
        try (PrintWriter bw = new PrintWriter(new BufferedWriter(new FileWriter("contacts.csv", true)))) {
            String contacString = contact.toString();
            bw.println(contacString);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Contact.contactList.add(contact);
    }

    public static void delete() {
        Contact.contactList.clear();
        csvToContactList();
        

        System.out.println("Entrez le prénom du contact à supprimer :");
        String firsnameInput = CustomUtils.getUserInput();
        System.out.println("Entrez le nom du contact à supprimer :");
        System.out.println("test");
        String lastnameInput = CustomUtils.getUserInput();
        deleteContact(firsnameInput, lastnameInput);
    }

    public static void deleteContact(String firstName, String lastName) {
        Contact contactToRemove = getContact(firstName, lastName);
        if (contactToRemove != null) {
            Contact.contactList.remove(contactToRemove);
            contactListToCsv();

            System.out.println(ConsoleColors.RED + "Le contact du nom de " + firstName + " | " + lastName
                    + " a été supprimé !" + ConsoleColors.DEFAULT);

        } else {
            System.out.println(ConsoleColors.RED + "Le contact du nom de " + firstName + " | " + lastName
                    + " n'existe pas." + ConsoleColors.DEFAULT);
        }
    }

    public static void displaySpecificContact(String prenom, String nom) {
        Contact contact = getContact(prenom, nom);
        if (contact != null) {
            displayContact(contact);
            return;
        }
        System.out.println(
                ConsoleColors.RED + "Contact du nom de " + prenom + " " + nom + " non trouvé \n"
                        + ConsoleColors.DEFAULT);
    }

    static Contact getContact(String prenom, String nom) {
        for (Contact contact : Contact.contactList) {
            if (contact.getFirstname().equals(prenom) && contact.getLastname().equals(nom)) {
                return contact;
            }
        }
        return null;
    }

    public static void displayContact(Contact contactList) {
        String contactString = contactList.toString();
        contactString = contactString.replace(Contact.SEPARATEUR, " | ");
        System.out.println(contactString);
    }

    public static void displayAllContacts() throws ParseException {

        for (Contact contact : Contact.contactList) {
            displayContact(contact);
        }

    }

    static void contactListToCsv() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("contacts.csv"))) {
            for (Contact contact : Contact.contactList) {
                String contactString = contact.toString();
                bw.write(contactString);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void csvToContactList() {
        try (BufferedReader br = new BufferedReader(new FileReader("contacts.csv"))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {

                String[] fields = line.split(Contact.SEPARATEUR);
                String firstName = fields[0];
                String lastName = fields[1];
                String number = fields[2];
                String mail = fields[3];
                String birthday = fields[4];

                try {
                    Contact contact = new Contact();
                    contact.setFirstname(firstName);
                    contact.setLastname(lastName);
                    contact.setNumber(number);
                    contact.setMail(mail);
                    contact.setBirthday(birthday);

                    Contact.contactList.add(contact);

                } catch (ParseException e) {
                    System.out.println(ConsoleColors.RED + "Le contact ligne " + lineNumber + " n'a pas pu être lu"
                            + ConsoleColors.DEFAULT);
                }
                lineNumber++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void displayMenuTri() throws ParseException {
        do {
            System.out.println(ConsoleColors.GREEN + "  -- Menu tri --");
            System.out.println("1. Trier par prénom");
            System.out.println("2. Trier par nom");
            System.out.println("3. Trier par mail");
            System.out.println("4. Trier par date de naissance");
            System.out.println("q. Quitter menu tri" + ConsoleColors.DEFAULT);

            String input = CustomUtils.getUserInput();
            switch (input) {
                case "1":
                    Contact.sortContactListByFirstname();
                    displayMenuTriOrder();
                    break;
                case "2":
                    Contact.sortContactListByLastname();
                    displayMenuTriOrder();
                    break;
                case "3":
                    Contact.sortContactListByMail();
                    displayMenuTriOrder();
                    break;
                case "4":
                    Contact.sortContactListByBirthday();
                    displayMenuTriOrder();
                    break;
                case "q":
                    System.out.println(ConsoleColors.RED + "Fermeture du menu de tri" + ConsoleColors.DEFAULT);
                    contactListToCsv();
                    return;
                default:
                    System.out.println(ConsoleColors.RED + "Veuillez entrer un choix valide" + ConsoleColors.DEFAULT);
                    break;
            }
        } while (true);
    }

    public static void findContactByFirstName() {
        System.out.println("Entrez le début du prénom du contact à chercher :");
        String firstNameInput = CustomUtils.getUserInput();

        ArrayList<Contact> filteredList = Contact.contactList.stream()
                .filter(o -> o.getFirstname().toLowerCase().startsWith(firstNameInput.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));

        if (filteredList.size() > 0) {

            System.out.println(ConsoleColors.DARK_GREEN + "Voici le/les contacts trouvés :" + ConsoleColors.DEFAULT);
            System.out.println(ConsoleColors.YELLOW);
            for (Contact c : filteredList) {
                displayContact(c);
            }
            System.out.println(ConsoleColors.DEFAULT);

        } else {
            System.out.println(
                    ConsoleColors.RED + "Aucun contact ne correspond à votre recherche !" + ConsoleColors.DEFAULT);
        }
    }

    public static void findContactByNumber() {
        System.out.println("Entrez le début du numéro de téléphone du contact à chercher :");
        String numberInput = CustomUtils.getUserInput();

        ArrayList<Contact> filteredList = Contact.contactList.stream()
                .filter(o -> o.getNumber().startsWith(numberInput))
                .collect(Collectors.toCollection(ArrayList::new));

        if (filteredList.size() > 0) {

            System.out.println(ConsoleColors.DARK_GREEN + "Voici le/les contacts trouvés :" + ConsoleColors.DEFAULT);
            System.out.println(ConsoleColors.YELLOW);
            for (Contact c : filteredList) {
                displayContact(c);
            }
            System.out.println(ConsoleColors.DEFAULT);

        } else {
            System.out.println(
                    ConsoleColors.RED + "Aucun contact ne correspond à votre recherche !" + ConsoleColors.DEFAULT);
        }
    }

    public static void findContactByLastname() {
        System.out.println("Entrez le début du prénom du contact à chercher :");
        String lastNameInput = CustomUtils.getUserInput();

        ArrayList<Contact> filteredList = Contact.contactList.stream()
                .filter(o -> o.getLastname().toLowerCase().startsWith(lastNameInput.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));

        if (filteredList.size() > 0) {

            System.out.println(ConsoleColors.DARK_GREEN + "Voici le/les contacts trouvés :" + ConsoleColors.DEFAULT);
            System.out.println(ConsoleColors.YELLOW);
            for (Contact c : filteredList) {
                displayContact(c);
            }
            System.out.println(ConsoleColors.DEFAULT);

        } else {
            System.out.println(
                    ConsoleColors.RED + "Aucun contact ne correspond à votre recherche !" + ConsoleColors.DEFAULT);
        }
    }
    
    public static void findContactByMail() {
        System.out.println("Entrez le début du mail du contact à chercher :");
        String mailInput = CustomUtils.getUserInput();

        ArrayList<Contact> filteredList = Contact.contactList.stream()
                .filter(o -> o.getMail().toLowerCase().startsWith(mailInput.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));

        if (filteredList.size() > 0) {

            System.out.println(ConsoleColors.DARK_GREEN + "Voici le/les contacts trouvés :" + ConsoleColors.DEFAULT);
            System.out.println(ConsoleColors.YELLOW);
            for (Contact c : filteredList) {
                displayContact(c);
            }
            System.out.println(ConsoleColors.DEFAULT);

        } else {
            System.out.println(
                    ConsoleColors.RED + "Aucun contact ne correspond à votre recherche !" + ConsoleColors.DEFAULT);
        }
    }

    public static void displayMenuTriOrder() throws ParseException {
        do {
            System.out.println(ConsoleColors.GREEN + "  -- Menu tri --");
            System.out.println("1. Trier par ordre croissant");
            System.out.println("2. Trier par ordre décroisant" + ConsoleColors.DEFAULT);

            String input = CustomUtils.getUserInput();
            switch (input) {
                case "1":
                    contactListToCsv();
                    displayOrderMessage();
                    return;
                case "2":
                    Contact.reverseContactList();
                    contactListToCsv();
                    displayOrderMessage();
                    return;
                default:
                    System.out.println(ConsoleColors.RED + "Veuillez entrer un choix valide" + ConsoleColors.DEFAULT);
                    break;
            }
        } while (true);
    }

    public static void displayOrderMessage() {
        String orderText = null;
        if (Contact.isReversed) {
            orderText = "décroissant";
        } else {
            orderText = "croissant";
        }
        
        System.out.println(ConsoleColors.YELLOW + "Vous avez choisi le tri par " + Contact.contactListSort + " par ordre " + orderText + ConsoleColors.DEFAULT);
    }

    public static void displayMenuSearch() throws ParseException {
        do {
            System.out.println(ConsoleColors.GREEN + "  -- Menu de Recherche --");
            System.out.println("1. Rechercher par prénom");
            System.out.println("2. Rechercher par nom");
            System.out.println("3. Rechercher par mail");
            System.out.println("4. Rechercher par numéro de téléphone");
            System.out.println("q. Quitter Menu de Recherche" + ConsoleColors.DEFAULT);

            String input = CustomUtils.getUserInput();
            switch (input) {
                case "1":
                    findContactByFirstName();
                    break;
                case "2":
                    findContactByLastname();
                    break;
                case "3":
                    findContactByMail();
                    break;
                case "4":
                    findContactByNumber();
                    break;
                case "q":
                    System.out.println(ConsoleColors.RED + "Fermeture du menu de tri" + ConsoleColors.DEFAULT);
                    contactListToCsv();
                    return;
                default:
                    System.out.println(ConsoleColors.RED + "Veuillez entrer un choix valide" + ConsoleColors.DEFAULT);
                    break;
            }
        } while (true);
    }
}
