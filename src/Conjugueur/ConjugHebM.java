/* Programme principal du conjugueur hébreu moderne
 * Nécessite les *.jar icu4j-67_1.jar et jdom2-2.0.6.jar et la police Dana Yad AlefAlefAlef Normal installée
 */
package Conjugueur;
import Conjugueur.M.Conjug;
import static Conjugueur.M.ConjugGrecA.cbVerbeM;
import java.awt.*;


public final class ConjugHebM {                    
            
    public static void main(String[] args) {
        if (DEBUG) { System.out.println("Conjugueur.ConjugHebM::main") ; }
        boolean found = false ;
        for(String name:GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()){
            if (name.equals("Dana Yad AlefAlefAlef Normal")) {found = true ; break ;}
        }
        if (!found) { System.out.println("ConjugHebM : la police Dana Yad AlefAlefAlef Normal n'est pas installée !") ; return ;    }
        javax.swing.SwingUtilities.invokeLater(() -> {
            Conjugueur.M.ConjugHebM modèle = new Conjugueur.M.ConjugHebM (
                    Conjug.M.Indicatif.im(),                                    // mode
                    Conjugueur.M.ConjugHebM.HMT.Présent.it(),                 // temps
                    Conjugueur.M.ConjugHebM.V.Active.iv(),                  // voix
                    Conjug.S.Paal.is(),                                         // thème
                    cbVerbeM.getSize()/2,                                       // iVerbe
                    Conjugueur.M.ConjugHebM.É.Droite.ié()                       //écriture
            );
            Conjugueur.C.ConjugHebM contrôleur = new Conjugueur.C.ConjugHebM(modèle);
            contrôleur.displayVues();
        });
    }                                                                           // main

    public static final boolean DEBUG = false;
}                                                                                // ConjugHebM