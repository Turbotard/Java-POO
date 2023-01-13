package model;

import java.util.Comparator;
import java.util.Date;

/**
 * Une classe qui implémente l'interface Comparator pour comparer des objets Contact en fonction de leurs dates de naissance.
 */
public class Birthday implements Comparator<Contact> {

    /**
     * Compare deux contact en fonction de leurs dates de naissance.
     *
     * @param c1 le premier contact à comparer
     * @param c2 le deuxième contact à comparer
     * @return un nombre entier négatif, zéro ou un nombre entier positif selon que le premier contact est plus jeune, de même âge ou plus âgé que le deuxième contact
     */
    public int compare(Contact c1, Contact c2) {
        Date b1 = c1.getBirthday();
        Date b2 = c2.getBirthday();

        String b1String = CustomUtils.dateToString(b1);
        String b2String = CustomUtils.dateToString(b2);

        String[] date1 = b1String.split("/");
        String[] date2 = b2String.split("/");

        int day1 = Integer.parseInt(date1[0]);
        int day2 = Integer.parseInt(date2[0]);

        int month1 = Integer.parseInt(date1[1]);
        int month2 = Integer.parseInt(date2[1]);

        int year1 = Integer.parseInt(date1[2]);
        int year2 = Integer.parseInt(date2[2]);

        if (year1 != year2) {
            return year1 - year2;
        } else if (month1 != month2) {
            return month1 - month2;
        } else {
            return day1 - day2;
        }
    }
}
