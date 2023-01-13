package model;

import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;

/**
 * Représente un contact avec un nom, prénom, numéro de téléphone, email et date de naissance
 * Et ensemble de méthodes permettant de manipuler un contact
 */
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

    /**
     * Retourne le prénom du contact
     *
     * @return Le prénom du contact
     */
    public String getFirstname() {
        return _firstname;
    }

    /**
     * Défini le prénom du contact par le prénom passé en paramètre
     *
     * @param _prenom Le prénom du contact à définir
     *
     * @throws ParseException Si le prénom ne correspond pas au patterne de prénom
     */
    public void setFirstname(String _prenom) throws ParseException {
        this._firstname = CustomUtils.matchValue(_prenom, NAME_PATTERN, "prénom invalidebbbb");
    }

    /**
     * Retourne le nom du contact
     *
     * @return Le nom du contact
     */
    public String getLastname() {
        return _lastname;
    }

    /**
     * Défini le nom du contact par le nom passé en paramètre
     *
     * @param _nom Le nom du contact à définir
     *
     * @throws ParseException Si le nom ne correspond pas au patterne de nom
     */
    public void setLastname(String _nom) throws ParseException {
        this._lastname = CustomUtils.matchValue(_nom, NAME_PATTERN, "nom invalide");
    }

    /**
     * Retourne le numéro de téléphone du contact
     *
     * @return Le numéro de téléphone du contact
     */
    public String getNumber() {
        return _number;
    }

    /**
     * Défini le numéro de téléphone du contact par le numéro de téléphone passé en paramètre
     *
     * @param _number Le numéro de téléphone du contact à définir
     *
     * @throws ParseException Si le numéro de téléphone ne correspond pas au patterne de numéro de téléphone
     */
    public void setNumber(String _number) throws ParseException {
        this._number = CustomUtils.matchValue(_number, PHONE_NUMBER_PATTERN, "Le format du numéro est inhfiushfiu");
    }

    /**
     * Retourne l'email du contact
     *
     * @return L'email du contact
     */
    public String getMail() {
        return _mail;
    }

    /**
     * Défini l'email du contact par l'email passé en paramètre
     *
     * @param _mail L'email du contact à définir
     *
     * @throws ParseException Si l'email ne correspond pas au patterne d'email
     */
    public void setMail(String _mail) throws ParseException {
        this._mail = CustomUtils.matchValue(_mail, MAIL_PATTERN, "email invalide");
    }

    /**
     * Retourne la date de naissance du contact
     *
     * @return La date de naissance du contact
     */
    public Date getBirthday() {
        return _birthday;
    }

    /**
     * Défini la date de naissance du contact par la date de naissance passée en paramètre
     *
     * @param _birthday La date de naissance du contact à définir
     *
     * @throws ParseException Si la date de naissance n'est pas au format jj/mm/aaaa
     */
    public void setBirthday(String _birthday) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this._birthday = dateFormat.parse(_birthday);
    }

    public static Comparator<Contact> FirstnameComparator = new Comparator<Contact>() {

        @Override
        public int compare(Contact e1, Contact e2) {
            return e1.getFirstname().toLowerCase().compareTo(e2.getFirstname().toLowerCase());
        }
    };

    public static Comparator<Contact> LastnameComparator = new Comparator<Contact>() {

        @Override
        public int compare(Contact e1, Contact e2) {
            return e1.getLastname().toLowerCase().compareTo(e2.getLastname().toLowerCase());
        }
    };

    /**
     * Tri la liste de contact par ordre alphabétique des prénoms
     */
    public static void sortContactListByFirstname() {
        Collections.sort(Contact.contactList, Contact.FirstnameComparator);
        isReversed = false;
        contactListSort = "prénom";
    }

    /**
     * Tri la liste de contact par ordre alphabétique des noms
     */
    public static void sortContactListByLastname() {
        Collections.sort(Contact.contactList, Contact.LastnameComparator);
        isReversed = false;
        contactListSort = "nom";
    }

    /**
     * Tri la liste de contact par ordre alphabétique des mails
     */
    public static void sortContactListByMail() {
        Mail mail = new Mail();
        Collections.sort(contactList, mail);
        isReversed = false;
        contactListSort = "mail";
    };

    /**
     * Tri la liste de contact du plus jeune au plus agé
     */
    public static void sortContactListByBirthday() {
        Birthday birthday = new Birthday();
        Collections.sort(contactList, birthday);
        isReversed = false;
        contactListSort = "date de naissance";
    }

    public static String contactListSort = null;
    public static boolean isReversed = false;

    /**
     * Inverse l'ordre de la liste de contact
     */
    public static void reverseContactList() {
        Collections.reverse(Contact.contactList);
        isReversed = true;
    }

    /**
     * Vérifie si un contact du nom et prénom passé en paramètre existe déjà dans la liste de contact
     *
     * @param firstname Le prénom du contact à vérifier
     * @param lastname Le nom du contact à vérifier
     * @return true si le contact existe déjà, false sinon
     */
    static boolean contactExist(String firstname, String lastname) {
        for (Contact contact : contactList) {
            if (contact.getFirstname().toLowerCase().equals(firstname.toLowerCase())
                    && contact.getLastname().toLowerCase().equals(lastname.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Vérifie si un contact du numéro de téléphone passé en paramètre existe déjà dans la liste de contact
     *
     * @param number Le numéro de téléphone du contact à vérifier
     * @return true si le contact existe déjà, false sinon
     */
    static boolean numberExist(String number) {
        for (Contact contact : contactList) {
            if (contact.getNumber().equals(number)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retourne une chaine de caractère contenant les informations du contact, séparées par un séparateur
     *
     * @return Une chaine de caractère contenant les informations du contact, séparées par un séparateur
     */
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