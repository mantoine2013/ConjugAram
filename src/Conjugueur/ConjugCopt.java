/* Programme principal du conjugueur copte
 * Nécessite les *.jar icu4j-67_1.jar et jdom2-2.0.6.jar et la police Abba Antwnioc
 * @author MichelANTOINE@hotmail.com
*/
package Conjugueur;
import Conjugueur.M.Conjug;
import java.awt.GraphicsEnvironment;
import javax.swing.text.BadLocationException;

public class ConjugCopt {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws BadLocationException {
        if (DEBUG) System.out.println("Conjugueur.ConjugCopt::main") ;
        boolean found = false ;
        for(String name:GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()){
            if (name.equals(NOMPOLICOPT)) {found = true ; break ;}
        }
        if (!found) { System.out.println("la police "+NOMPOLICOPT+" n'est pas installée !") ; return ;    }
        Conjugueur.M.ConjugCopt modèle = new Conjugueur.M.ConjugCopt(
                    Conjug.M.Indicatif.im(),
                    Conjugueur.M.ConjugAnFr.TAnFr.Présent.it(),
                    Conjugueur.M.ConjugAnFr.V.Active.iv(),
                    9/*comboBoxVerbeM.getSize()/2*/
        );
        Conjugueur.C.ConjugCopt contrôleur = new Conjugueur.C.ConjugCopt(modèle);
        contrôleur.displayVues(); 
    }
    public  static final String NOMPOLICOPT = "Abba Antwnioc" ;
    private static final boolean DEBUG = true;    
    
}
