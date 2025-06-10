/* Programme principal du conjugueur italien
 * Nécessite les *.jar icu4j-67_1.jar et jdom2-2.0.6.jar
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur;

import Conjugueur.M.Conjug;
import static Conjugueur.M.ConjugItal.comboBoxVerbeM;

public class ConjugItal {
                           
    public static void main(String[] args) {
        if (DEBUG) { System.out.println("Conjugueur.ConjugItal::main") ;}
        Conjugueur.M.ConjugItal modèle = new Conjugueur.M.ConjugItal(
                   Conjug.M.Indicatif.im(),
                    Conjugueur.M.ConjugItal.T.Présent.it(),
                    Conjugueur.M.ConjugItal.V.Active.iv(),
                    comboBoxVerbeM.getSize()/2
        );
        Conjugueur.C.ConjugItal contrôleur = new Conjugueur.C.ConjugItal(modèle);
        contrôleur.displayVues(); 
    }                                                                         // AjouterPersonnesEtRacines

    public static final boolean DEBUG = true;
}                                                                                // ConjugAram