/**
 * Modèle du menu principal présentant des verbes ou des mots
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.util.IteratorIterable;

public class ModèleMenu1 extends AbstractListModel implements ComboBoxModel {
      org.jdom2.Element selection = null;
      private final SortedArrayList<org.jdom2.Element> tableau = new SortedArrayList<>();

/**
 * Fonction :  : 
 * Algorithme :
 *      1 Crée un analyseur XML en appelant
 *      2 Partir de la racine pour ensuite saisir ts les élément
 * @param xmls    nom du fichier xml du lexique SAXBuilder
 * @param élément libellé "verhm", "2è. m. s."
 * @param elerac "infinitif"
 * Fctions appelantes : Conjugueur.M.ConjugAraM, ConjugGrecAM, ConjugLat, ConjugAnFr
 */
    public ModèleMenu1(String xmls, String élément, String elerac) {
        super();
        if (DEBUG) { System.out.println ("Conjugueur.M.ModèleMenu1::ModèleMenu1, xmls = " + xmls + ", élément = " + élément + ", elerac = " + elerac) ; }
         SAXBuilder jdomBuilder = new SAXBuilder();
         try {
            Element lexique = jdomBuilder.build(xmls).getRootElement();
            IteratorIterable<Element> éléments = lexique.getDescendants(Filters.element(élément));
            while (éléments.hasNext()) {
                tableau.insertSorted(éléments.next(), elerac);
            }
         } catch (JDOMException | IOException ex) { Logger.getLogger(ModèleMenu1.class.getName()).log(Level.SEVERE, null, ex); }
    }                                                                        // ModèleMenu1
    public ModèleMenu1(String xmls, String élément, String elerac, String langue) {
        super();
        if (DEBUG) { System.out.println ("Conjugueur.M.ModèleMenu1::ModèleMenu1, xmls = " + xmls + ", élément = " + élément + ", elerac = " + elerac + ", langue = " + langue) ; }
         SAXBuilder jdomBuilder = new SAXBuilder();
         try {
            Element lexique = jdomBuilder.build(xmls).getRootElement();
            IteratorIterable<Element> éléments = lexique.getDescendants(Filters.element(élément));
            while (éléments.hasNext()) {
                tableau.insertSorted(éléments.next(), elerac, langue);
            }
         } catch (JDOMException | IOException ex) { Logger.getLogger(ModèleMenu1.class.getName()).log(Level.SEVERE, null, ex); }
    }                                                                        // ModèleMenu1
   
    /**
     * Fctions appelantes : Conjugueur.M.ConjTableLat::MAJConj, Conjugueur.M.ConjugSyri::MajDecl
     * @param indItem
     * @return 
     */
    public org.jdom2.Element get (int indItem) {
        if (DEBUG) { System.out.println ("Conjugueur.M.ModèleMenu1::get, indItem = " + indItem) ; }
        return tableau.get(indItem) ;
      }
      @Override
      public Object getElementAt(int index) {
            return tableau.get(index);
      }

      @Override
      public int getSize() {
         return tableau.size();
      }

      /**
       * Fction appelante Conjugueur.V.JFrameConjugAraM::JFrameConjugAraM, JFrameConjugLat
       */
      @Override
      public Object getSelectedItem() {
        if (DEBUG) { System.out.println ("Conjugueur.M.ModèleMenu1.getSelectedItem, selection = " + selection) ; }
         return selection; // to add the selection to the combo box
      }
    
      /**
       * Fction appelante Conjugueur.V.JFrameConjugLat::JFrameConjugLat
       * @param anItem 
       */
      @Override
      public void setSelectedItem(Object anItem) {
        selection = (org.jdom2.Element) anItem; // to select and register an
        if (DEBUG) { System.out.println ("Conjugueur.M.ModèleMenu1.setSelectedItem, selection = " + selection) ; }
      } // item from the pull-down list
      
    private final boolean DEBUG = true ;
}                                                                           // ModèleMenu1
