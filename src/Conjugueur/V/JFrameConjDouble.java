/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.V;

import static Conjugueur.V.JFrameConjug.frame;
import java.awt.BorderLayout;

public class JFrameConjDouble extends JFrameConjug {
    public JFrameConjDouble(Conjugueur.C.Conjug contrôleur) {
        super(contrôleur);
        if (DEBUG) { System.out.println("Conjugueur.V.JFrameConjDouble::JFrameConjDouble, contrôleur.toString() = "+contrôleur.toString()) ; }          
        jPanelDroit.add(jPanelAutreMot, java.awt.BorderLayout.NORTH) ;
        jPanelGauche.add(jPanelVerbe, java.awt.BorderLayout.NORTH);        
        frame.add(splitPane, BorderLayout.CENTER);
    }                                                                           // JFrameConjDouble   

    private static final boolean DEBUG = false ;    
}                                                                               // JFrameConjDouble
