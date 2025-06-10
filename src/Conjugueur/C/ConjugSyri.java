/**
 *
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.C;
import Conjugueur.V.JFrameConjugSyri;

public class ConjugSyri extends Conjugueur.C.Conjug {

    /**
     * Fction appelante : Conjugueur.ConjugSyri::main
     * @param model 
     */
    public ConjugSyri (Conjugueur.M.ConjugSyri model){
        if (DEBUG) System.out.println("Conjugueur.C.ConjugSyri::ConjugSyri") ;
        this.model = model; 
        fieldVue = new JFrameConjugSyri(this); 
        addListenersToModèle();
    }
    public void displayVues(){
        if (DEBUG) System.out.println("Conjugueur.C.ConjugSyri::displayVues") ;
        fieldVue.display();
    }
    public void notifyConjugSyrihanged(int volume){
//        model.setConjugSyri(volume);
    }
    private static final boolean DEBUG = true;    
     
}
