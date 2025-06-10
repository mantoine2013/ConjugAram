/**
 *
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.C;
import Conjugueur.V.JFrameConjugSans;
import Conjugueur.M.*;

public class ConjugSans extends Conjug {

    public ConjugSans (Conjugueur.M.ConjugSans model){
        if (DEBUG) { System.out.println("Conjugueur.C.ConjugSans::ConjugSans") ; }
        this.model = model; 
        fieldVue = new JFrameConjugSans(this); 
        addListenersToModèle();
    }
 
 
    public void displayVues(){
        if (DEBUG) System.out.println("Conjugueur.Contrôleur.ConjugSans::displayVues") ;
        fieldVue.display();
    }
 
 
    public void notifyConjugSansChanged(int volume){
//        model.setConjugSans(volume);
    }
    private static final boolean DEBUG = false;    
    
}
