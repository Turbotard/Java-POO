package model;

import java.util.Comparator;

/**
 * Une classe qui implémente l'interface Comparator pour comparer des objets Contact en fonction de leurs dates de naissance.
 */
public class Mail implements Comparator<Contact> {

    /**
     * Compare deux objets Contact en fonction de leur email
     *
     * @param c1 le premier contact à comparer
     * @param c2 le deuxième contact à comparer
     *
     * @return un nombre entier négatif, zéro ou un nombre entier positif selon si l'email du premier est devant, identique ou derrière l'email du deuxième contact par ordre alphabétique
     */
    public int compare(Contact c1, Contact c2) {
    return c1.getMail().toLowerCase().compareTo(c2.getMail().toLowerCase());
    }
}
