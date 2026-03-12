/**
 * Modèle du menu principal présentant des verbes ou des mots
 * @author MichelANTOINE@hotmail.com
 */
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
     * @param élément
     * @param elerac 
     * Fctions appelantes : Conjugueur.M.ModèleMenu1::ModèleMenu1
     */
    @SuppressWarnings("unchecked")
    public void insertSorted(org.jdom2.Element élément, String elerac) {
        if (DEBUG) { System.out.println ("Conjugueur.M.SortedArrayList::insertSorted, élément.getAttributeValue(" + elerac + ") = " + élément.getAttributeValue(elerac)) ; }
        add(élément);
        Comparable<String> cmp = élément.getAttributeValue(elerac) ;
        for (int i = size() - 1; i > 0 && cmp.compareTo(get(i - 1).getAttributeValue(elerac)) < 0; i--) {
            Collections.swap(this, i, i - 1);
        }
    }                                                                            // insertSorted
    /**
     * 
     * @param élément
     * @param elerac 
     * Fctions appelantes : Conjugueur.M.ModèleMenu1::ModèleMenu1
     */
    @SuppressWarnings("unchecked")
    public void insertSorted(org.jdom2.Element élément, String elerac, String langue) {
        if (DEBUG) { System.out.println ("Conjugueur.M.SortedArrayList::insertSorted, élément.getAttributeValue(" + elerac + ") = " + élément.getAttributeValue(elerac) + ", langue = " + langue + ", size = " + size()) ; }
        add(élément);
        for (int i = size() - 1; i > 0 && Collator.getInstance(new Locale(langue)).compare(élément.getAttributeValue(elerac), get(i - 1).getAttributeValue(elerac)) < 0; i--) {
            Collections.swap(this, i, i - 1);
        }
    }                                                                            // insertSorted
    /**
     * Fonction :  insertSorted 
     * 
     * @param élément
     * @param atimp 
     * Fctions appelantes : Conjugueur.M.ModèleMenu1::ModèleMenu1
     */
    @SuppressWarnings("unchecked")
    public void insertSorted(org.jdom2.Element élément, String atimp, String atpe, String langue) {
        if (DEBUG) {
            System.out.println ("Conjugueur.M.SortedArrayList::insertSorted, élément.getAttributeValue(" + (élément.getAttributeValue(atimp) != null?atimp:atpe) + ") = " + (élément.getAttributeValue(atimp) != null?élément.getAttributeValue(atimp):élément.getAttributeValue(atpe)) + ", langue = " + langue + ", size = " + size()) ; }
        add(élément);
        for (int i = size() - 1; i > 0 && Collator.getInstance(new Locale(langue)).compare((élément.getAttributeValue(atimp) != null?élément.getAttributeValue(atimp):élément.getAttributeValue(atpe)), (get(i - 1).getAttributeValue(atimp) != null?get(i - 1).getAttributeValue(atimp):get(i - 1).getAttributeValue(atpe))) < 0; i--) {
            Collections.swap(this, i, i - 1);
        }
    }                                                                            // insertSorted
    private static final boolean DEBUG = false ;
}                                                                             // class SortedArrayList


