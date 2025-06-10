package Conjugueur.M;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Locale;
import java.text.Collator;

/**
 * Fonction : crée  un tableau de noeud trié alphabétiquement sur l'infinitif
 * @author MichelANTOINE@hotmail.com
 *  Fonction : Crée un tableau d'éléments <entrée  racine=""></entrée> trié sur l'attribut "racine"
     * @param <T> 
 */
public class SortedArrayList<T> extends ArrayList<org.jdom2.Element> {

    /**
     * 
     * @param value
     * @param elerac 
     * Fctions appelantes : Conjugueur.M.ModèleMenu1::ModèleMenu1
     */
    @SuppressWarnings("unchecked")
    public void insertSorted(org.jdom2.Element value, String elerac) {
        if (DEBUG) { System.out.println ("Conjugueur.M.SortedArrayList::insertSorted, value.getAttributeValue(" + elerac + ") = " + value.getAttributeValue(elerac)) ; }
        add(value);
        Comparable<String> cmp = value.getAttributeValue(elerac) ;
        for (int i = size() - 1; i > 0 && cmp.compareTo(get(i - 1).getAttributeValue(elerac)) < 0; i--) {
            Collections.swap(this, i, i - 1);
        }
    }                                                                            // insertSorted
    @SuppressWarnings("unchecked")
    public void insertSorted(org.jdom2.Element value, String elerac, String langue) {
        if (DEBUG) { System.out.println ("Conjugueur.M.SortedArrayList::insertSorted, value.getAttributeValue(" + elerac + ") = " + value.getAttributeValue(elerac) + ", langue = " + langue) ; }
        add(value);
        for (int i = size() - 1; i > 0 && Collator.getInstance(new Locale(langue)).compare(value.getAttributeValue(elerac), get(i - 1).getAttributeValue(elerac)) < 0; i--) {
            Collections.swap(this, i, i - 1);
        }
    }                                                                            // insertSorted
    private static final boolean DEBUG = false ;
}                                                                             // class SortedArrayList


