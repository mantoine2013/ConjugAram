/**
 *
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.V;

import java.awt.event.ActionListener;
import javax.swing.JComboBox;

public class JFrameConjugRu  extends JFrameConjDouble implements ActionListener {
    /**
     * Fction appelante : Conjugueur.C.ConjugRu::ConjugRu
     * @param contrôleur 
     */
    @SuppressWarnings({"unchecked", "unchecked", "unchecked"})
    public JFrameConjugRu(Conjugueur.C.ConjugRu contrôleur) {
        super(contrôleur); 
        if (DEBUG) { System.out.println("Conjugueur.V.JFrameConjugRu::JFrameConjugRu, contrôleur.toString() ="+contrôleur.toString()) ; }
        frame.setTitle("Conjugueur russe V0.8"); 
        largeur = LARGEUR ; hauteur = HAUTEUR ;
        jCBAutMot = new JComboBox(Conjugueur.M.ConjugAnFr.cbAutreMotM);
    
    }                                                                           // JFrameConjugRu
    public static final int HAUTEUR = 380, LARGEUR = 870 ;
    private final boolean DEBUG = true;        
}
