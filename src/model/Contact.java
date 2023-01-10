package model;

import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Contact {
    private static final String PHONE_NUMBER_PATTERN = null;
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
        Pattern pat = Pattern.compile(
                "(0|\\+33|0033)[1-9][0-9]{8}");
        Matcher matcher = pat.matcher(_number);

        if (matcher.matches()) {
            this._number = _number;
        } else {
            ParseException e = new ParseException("numéro invalide", 0);
            throw e;
        }
    }

    public String getFirstname() {
        return _firstname;
    }

    public void setFirstname(String _prenom) {
        this._firstname = _prenom;
    }

    public String getLastname() {
        return _lastname;
    }

    public void setLastname(String _nom) {
        this._lastname = _nom;
    }

    public String getMail() {
        return _mail;
    }

    public void setMail(String _mail) throws ParseException {
        Pattern pat = Pattern.compile(
                "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pat.matcher(_mail);

        if (matcher.matches()) {
            this._mail = _mail;
        } else {
            ParseException e = new ParseException("email invalide", 0);
            throw e;
        }
    }

    public Date getBirthday() {
        return _birthday;
    }

    public void setBirthday(String _birthday) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this._birthday = dateFormat.parse(_birthday);
    }
}
