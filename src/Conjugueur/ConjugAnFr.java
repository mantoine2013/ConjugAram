/* Programme principal du conjugueur arabe moderne
 * Nécessite les *.jar icu4j-67_1.jar et jdom2-2.0.6.jar polices SyrCOMEdessa.otf, SyrCOMJerusalem.otf
 * @author MichelANTOINE@hotmail.com
*/
package Conjugueur;
import Conjugueur.M.*;

public class ConjugAnFr {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (DEBUG) { System.out.println("Conjugueur.ConjugAnFr::main") ; }
        Conjugueur.M.ConjugAnFr modèle = new Conjugueur.M.ConjugAnFr(
                Conjug.M.Indicatif.im(),                                        // mode
                Conjugueur.M.ConjugAnFr.TAnFr.PasséSimple.it(),                     // temps
                    Conjugueur.M.ConjugAnFr.V.Active.iv(),
                    6/*Conjugueur.M.ConjugAnFr.cbVerbeM.getSize()/2*/,
                    Conjugueur.M.ConjugAnFr.cbAutreMotM.getSize()/2,
                    Conjugueur.M.ConjugAnFr.N.Singulier.in());
        Conjugueur.C.ConjugAnFr contrôleur = new Conjugueur.C.ConjugAnFr(modèle);
        contrôleur.displayVues(); 
    }
    private static final boolean DEBUG = true;    
}
