/* Programme principal du conjugueur arabe moderne
 * Nécessite les *.jar icu4j-67_1.jar et jdom2-2.0.6.jar polices SyrCOMEdessa.otf, SyrCOMJerusalem.otf
 * @author MichelANTOINE@hotmail.com
*/
package Conjugueur;

import Conjugueur.M.Conjug;

public class ConjugAraM {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (DEBUG) { System.out.println("Conjugueur.ConjugAraM::main") ; }
        Conjugueur.M.ConjugAraM modèle = new Conjugueur.M.ConjugAraM(
                    Conjug.M.Impératif.im(),                                    // mode
                    Conjugueur.M.ConjugAraM.ART.Inaccompli.it(),                   // temps
                    Conjugueur.M.ConjugAraM.V.Active.iv(),                      // voix
                    Conjugueur.M.ConjugAraM.cbVerbeM.getSize()/2,
                    Conjugueur.M.ConjugAraM.cbAutreMotM.getSize()/2
        );
        Conjugueur.C.ConjugAraM contrôleur = new Conjugueur.C.ConjugAraM(modèle);
        contrôleur.displayVues(); 
    }
    private static final boolean DEBUG = false;    
}
