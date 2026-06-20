/* Programme principal du conjugueur langue turque
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur;

import Conjugueur.M.Conjug;
import javax.swing.JPanel;

public final class ConjugTurc  extends JPanel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (DEBUG) {System.out.println("Conjugueur.ConjugTurc::main") ;}
        Conjugueur.M.ConjugTurc modèle = new Conjugueur.M.ConjugTurc(
                Conjug.M.Indicatif.im(),                                        // mode
                Conjugueur.M.ConjugTurc.TTurc.Présent.it(),                     // temps
                    Conjugueur.M.ConjugTurc.V.Active.iv(),
                    Conjugueur.M.ConjugTurc.cbVerbeM.getSize()/2,
                    Conjugueur.M.ConjugTurc.cbAutreMotM.getSize()/2,
                    Conjugueur.M.ConjugTurc.N.Singulier.in());    }
    public static final boolean DEBUG = true ;
    
}
