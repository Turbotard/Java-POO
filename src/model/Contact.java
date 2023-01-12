package model;

import java.util.ArrayList;
import java.util.Date;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Collections;
import java.util.Comparator;

public class Contact {
    private static final String NAME_PATTERN = "^[a-zA-Z]+(([\\'\\,\\.\\-][a-zA-Z])?[a-zA-Z]*)*$";
    private static final String PHONE_NUMBER_PATTERN = "(0|\\+33|0033)[1-9][0-9]{8}";
    private static final String MAIL_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    public static final String SEPARATEUR = ";";

    public static ArrayList<Contact> contactList = new ArrayList<Contact>();

    private String _firstname;
    private String _lastname;
    private String _number;
    private String _mail;
    private Date _birthday;

    public String getFirstname() {
        return _firstname;
    }

    public void setFirstname(String _prenom) throws ParseException {
        this._firstname = CustomUtils.matchValue(_prenom, NAME_PATTERN, "prénom invalidebbbb");
    }

    public String getLastname() {
        return _lastname;
    }

    public void setLastname(String _nom) throws ParseException {
        this._lastname = CustomUtils.matchValue(_nom, NAME_PATTERN, "nom invalide");
    }

    public String getNumber() {
        return _number;
    }

    public void setNumber(String _number) throws ParseException {
        this._number = CustomUtils.matchValue(_number, PHONE_NUMBER_PATTERN, "Le format du numéro est inhfiushfiu");
    }

    public String getMail() {
        return _mail;
    }

    public void setMail(String _mail) throws ParseException {
        this._mail = CustomUtils.matchValue(_mail, MAIL_PATTERN, "email invalide");
    }

    public Date getBirthday() {
        return _birthday;
    }

    public void setBirthday(String _birthday) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this._birthday = dateFormat.parse(_birthday);
    }

    public static Comparator<Contact> FirstnameComparator = new Comparator<Contact>() {

        @Override
        public int compare(Contact e1, Contact e2) {
            return e1.getFirstname().compareTo(e2.getFirstname());
        }
    };

    public static Comparator<Contact> LastnameComparator = new Comparator<Contact>() {

        @Override
        public int compare(Contact e1, Contact e2) {
            return e1.getLastname().compareTo(e2.getLastname());
        }
    };

    public static void sortContactListByFirstnameC() {
        Collections.sort(Contact.contactList, Contact.FirstnameComparator);
        System.out.println("Vous avez choisi le tri par prénom.");
    }

    public static void sortContactListByLastnameC() {
        Collections.sort(Contact.contactList, Contact.LastnameComparator);
        System.out.println("Vous avez choisi le tri par nom.");
    }

    public static void sortContactListByMailC() {
        Mail mail = new Mail();
        Collections.sort(contactList, mail);
        System.out.println("Vous avez choisi le tri par mail.");
    };

    public static void sortContactListByBirthdayC() {
        Birthday birthday = new Birthday();
        Collections.sort(contactList, birthday);
        System.out.println("Vous avez choisi le tri par date de naissance.");
    }
    /*
     * static void sortContactListPrenom() {
     * Contact.contactList.clear();
     * Menu.csvToContactList();
     * Collections.sort(contactList, (o1, o2) ->
     * o1.getFirstname().compareTo(o2.getFirstname()));
     * System.out.println(contactList);
     * }
     * 
     * static void sortContactListNom() {
     * Contact.contactList.clear();
     * Menu.csvToContactList();
     * Collections.sort(contactList, LASTNAME_COMPARATOR);
     * }
     * 
     * static void sortContactListMail() {
     * Contact.contactList.clear();
     * Menu.csvToContactList();
     * Collections.sort(contactList, MAIL_COMPARATOR);
     * }
     * 
     * static void sortContactListBirthday() {
     * Contact.contactList.clear();
     * Menu.csvToContactList();
     * Collections.sort(contactList, (o1, o2) ->
     * o1.getBirthday().compareTo(o2.getBirthday()));
     * }
     */

    static boolean contactExist(String firstname, String lastname) {
        for (Contact contact : contactList) {
            if (contact.getFirstname().equals(firstname) && contact.getLastname().equals(lastname)) {
                return true;
            }
        }
        return false;
    }

    static boolean numberExist(String number) {
        for (Contact contact : contactList) {
            if (contact.getNumber().equals(number)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder build = new StringBuilder();
        build.append(this.getFirstname());
        build.append(SEPARATEUR);
        build.append(this.getLastname());
        build.append(SEPARATEUR);
        build.append(this.getNumber());
        build.append(SEPARATEUR);
        build.append(this.getMail());
        build.append(SEPARATEUR);

        String birthdayString = CustomUtils.dateToString(this.getBirthday());
        build.append(birthdayString);

        return build.toString();
    }
}