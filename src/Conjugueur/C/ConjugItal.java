/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.C;
import Conjugueur.V.JFrameConjugItal;

public class ConjugItal extends Conjug {
    
    /**
     * @param model 
     * Fction appelante : Conjugueur.ConjugItal::main
     */
    public ConjugItal (Conjugueur.M.ConjugItal model){
        if (DEBUG) { System.out.println("Conjugueur.Contrôleur.ConjugItal::ConjugItal") ; }
        this.model = model ; 
        fieldVue = new JFrameConjugItal(this); 
        addListenersToModèle();
    }
    
    private final boolean DEBUG = true;    
}
