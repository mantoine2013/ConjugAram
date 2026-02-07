/**
 *
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.V;

import static Conjugueur.V.JFrameConjug.FRANÇAIS;
import static Conjugueur.V.JFrameConjug.NOMBRE;
import static Conjugueur.V.JFrameConjug.jPanelDroit;
import static Conjugueur.V.JFrameConjug.jPanelNb;
import java.awt.ComponentOrientation;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
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
        jCBAutMot = new JComboBox(Conjugueur.M.ConjugRu.cbAutreMotM);
        jPanelNb.add(jComboBoxNb) ;     jPanelDroit.add(jPanelNb, java.awt.BorderLayout.EAST);    jComboBoxNb.setActionCommand(NOMBRE) ; jComboBoxNb.addActionListener(this); jCBAutMot.setRenderer(new ComboBoxVerbeRenderer(FRANÇAIS, ComponentOrientation.LEFT_TO_RIGHT, Conjugueur.M.ConjugRu.ATTRIBUT)); 
    
    }                                                                           // JFrameConjugRu
    public static final int HAUTEUR = 380, LARGEUR = 870 ;
    private final  JComboBox<Conjugueur.M.ConjugRu.N> jComboBoxNb = new JComboBox<>(new DefaultComboBoxModel<>(Conjugueur.M.ConjugRu.N.values())) ;
    private final boolean DEBUG = true;        
}
