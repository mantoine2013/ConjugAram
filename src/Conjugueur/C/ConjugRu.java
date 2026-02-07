/**
 *
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.C;
import Conjugueur.V.JFrameConjugRu;

public class ConjugRu  extends Conjug {
    /**
     * Fction appelante : Conjugueur.ConjugRu::main
     * @param model 
     */
    public ConjugRu (Conjugueur.M.ConjugRu model){
        if (DEBUG) { System.out.println("Conjugueur.C.ConjugRu::ConjugRu") ; }
        fieldVue = new JFrameConjugRu(this); 
    }                                                                           // ConjugRu
    
    private static final boolean DEBUG = false;        
}
