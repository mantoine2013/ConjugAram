/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.C;
import Conjugueur.V.JFrameConjugLat;

public class ConjugLat extends Conjug {
 
    /**
     * @param model 
     * Fction appelante : Conjugueur.ConjugLat::main
     */
    public ConjugLat (Conjugueur.M.ConjugLat model){
        if (DEBUG) { System.out.println("Conjugueur.C.ConjugLat::ConjugLat") ; }
        this.model = model; 
        fieldVue = new JFrameConjugLat(this); 
        addListenersToModèle();
    }
  
    private  final boolean DEBUG = false ;    
    
}
