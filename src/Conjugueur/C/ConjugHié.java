/**
 *
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.C;
import Conjugueur.V.*;

public class ConjugHié extends Conjug {
 
 
    public ConjugHié (Conjugueur.M.ConjugHié model){
        if (DEBUG) System.out.println("Conjugueur.Contrôleur.ConjugHié::ConjugHié") ;
        this.model = model;
 
        fieldVue = new JFrameConjugHié(this, model.getConjugHié());
 
        addListenersToModèle();
    }
 
 
 
    private static final boolean DEBUG = true;    
    
}
