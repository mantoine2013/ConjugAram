/**
 *
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.C;

import Conjugueur.V.JFrameConjugGrecA;
import javax.swing.text.BadLocationException;

public class ConjugGrecA extends Conjug {
     
    public ConjugGrecA (Conjugueur.M.ConjugGrecA model) throws BadLocationException{
        if (DEBUG) System.out.println("Conjugueur.C.ConjugGrecA::ConjugGrecA") ;
        this.model = model;
        fieldVue = new JFrameConjugGrecA(this); 
        addListenersToModèle();
    }
 
     private static final boolean DEBUG = false;            
}
