package model;

import java.util.Comparator;

public class Mail implements Comparator<Contact> {
    public int compare(Contact c1, Contact c2) {
    return c1.getMail().compareTo(c2.getMail());
    }
}
