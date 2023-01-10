package model;

import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

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

    public String getNumber() {
        return _number;
    }

    public void setNumber(String _number) throws ParseException {
        this._number = CustomUtils.matchValue(_number, PHONE_NUMBER_PATTERN, "numéro invalide");
        // Pattern pat = Pattern.compile(PHONE_NUMBER_PATTERN);
        // Matcher matcher = pat.matcher(_number);

        // if (matcher.matches()) {
        // this._number = _number;
        // } else {
        // ParseException e = new ParseException("numéro invalide", 0);
        // throw e;
        // }

    }

    public String getFirstname() {
        return _firstname;
    }

    public void setFirstname(String _prenom) throws ParseException {
        this._firstname = CustomUtils.matchValue(_prenom, NAME_PATTERN, "prénom invalide");
    }

    public String getLastname() {
        return _lastname;
    }

    public void setLastname(String _nom) throws ParseException {
        this._lastname = CustomUtils.matchValue(_nom, NAME_PATTERN, "nom invalide");
    }

    public String getMail() {
        return _mail;
    }

    public void setMail(String _mail) throws ParseException {
        this._mail = CustomUtils.matchValue(_mail, MAIL_PATTERN, "email invalide");
        // Pattern pat = Pattern.compile(MAIL_PATTERN);
        // Matcher matcher = pat.matcher(_mail);

        // if (matcher.matches()) {
        // this._mail = _mail;
        // } else {
        // ParseException e = new ParseException("email invalide", 0);
        // throw e;
        // }
    }

    public Date getBirthday() {
        return _birthday;
    }

    public void setBirthday(String _birthday) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this._birthday = dateFormat.parse(_birthday);
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
        build.append(this.getBirthday());

        return build.toString();
    }
}
