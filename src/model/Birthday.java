package model;

import java.util.Comparator;
import java.util.Date;

public class Birthday implements Comparator<Contact> {
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
            return year2 - year1;
        } else if (month1 != month2) {
            return month2 - month1;
        } else {
            return day2 - day1;
        }
    }
}
