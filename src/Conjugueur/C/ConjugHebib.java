/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.C;

import Conjugueur.V.JFrameConjugHebib;
import javax.swing.text.BadLocationException;

public class ConjugHebib  extends Conjug {
    
    /**
     * Fction appelante : Conjugueur.ConjugHebib::main
     * @param model 
     */
    public ConjugHebib (Conjugueur.M.ConjugHebib model) throws BadLocationException{
        if (DEBUG) { System.out.println("Conjugueur.C.ConjugHebib::ConjugHebib, model = " + model) ; }
        this.model = model; 
        fieldVue = new JFrameConjugHebib(this); 
        addListenersToModèle();
    }
     
    private  final boolean DEBUG = false;          
}
