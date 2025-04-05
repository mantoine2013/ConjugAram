/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.C;
import Conjugueur.V.JFrameConjugAnFr;

public class ConjugAnFr extends Conjug {
 
    /**
     * Fction appelante : Conjugueur.ConjugAnFr::main
     * @param model 
     */
    public ConjugAnFr (Conjugueur.M.ConjugAnFr model){
        if (DEBUG) { System.out.println("Conjugueur.C.ConjugAnFr::ConjugAnFr") ; }
        this.model = model; 
        fieldVue = new JFrameConjugAnFr(this); 
        addListenersToModèle();
    }

 
    public void displayVues(){
        if (DEBUG) System.out.println("Conjugueur.C.ConjugAnFr::displayVues") ;
        fieldVue.display();
    }
 
    private static final boolean DEBUG = false;        
}
