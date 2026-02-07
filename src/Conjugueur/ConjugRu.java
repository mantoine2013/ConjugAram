/* Programme principal du conjugueur russe
 * Nécessite les *.jar icu4j-67_1.jar et jdom2-2.0.6.jar polices ParaType
 * @author MichelANTOINE@hotmail.com
*/
package Conjugueur;

import Conjugueur.M.Conjug;
import java.awt.GraphicsEnvironment;


public class ConjugRu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (DEBUG) { System.out.println("Conjugueur.ConjugRu::main") ; }
        boolean found = false ;
        for(String name:GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()){
            if (name.equals(NOMPOLICY)) {found = true ; break ;}
        }
        if (!found) { System.out.println("la police " + NOMPOLICY + " n'est pas installée !") ; return ;    }
        Conjugueur.M.ConjugRu modèle = new Conjugueur.M.ConjugRu(
                Conjug.M.Indicatif.im(),                                        // mode
                Conjugueur.M.ConjugAnFr.TAnFr.PasséSimple.it(),                     // temps
                Conjugueur.M.ConjugAnFr.V.Active.iv(),                          // voix
                    6/*Conjugueur.M.ConjugAnFr.cbVerbeM.getSize()/2*/,          // iVerbe
                    Conjugueur.M.ConjugAnFr.cbAutreMotM.getSize()/2,
                Conjugueur.M.ConjugAnFr.N.Singulier.in());
        Conjugueur.C.ConjugRu contrôleur = new Conjugueur.C.ConjugRu(modèle);
        contrôleur.displayVues(); 
    }    
    public  static final String NOMPOLICY = "PT Mono" ;
    private static final boolean DEBUG = true;    
}
