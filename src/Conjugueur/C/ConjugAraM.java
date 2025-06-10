/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.C;
import Conjugueur.V.JFrameConjugAraM;

public class ConjugAraM extends Conjug {
 
    public ConjugAraM (Conjugueur.M.ConjugAraM model){
        if (DEBUG) { System.out.println("Conjugueur.C.ConjugAraM::ConjugAraM") ; }
        this.model = model; 
        fieldVue = new JFrameConjugAraM(this); 
        addListenersToModèle();
    }

 
    public void displayVues(){
        if (DEBUG) { System.out.println("Conjugueur.C.ConjugAraM::displayVues") ; }
        fieldVue.display();
    }
     private static final boolean DEBUG = false;        
}
