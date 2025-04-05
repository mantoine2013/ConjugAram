/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.C;
import Conjugueur.V.*;
import javax.swing.text.BadLocationException;


public class ConjugCopt extends Conjug {
    

    public ConjugCopt (Conjugueur.M.ConjugCopt model) throws BadLocationException{
        if (DEBUG) { System.out.println("Conjugueur.C.ConjugCopt::ConjugCopt") ;  }
        this.model = model; 
        fieldVue = new JFrameConjugCopt(this); 
//        addListenersToModèle();
    }

/*    private void addListenersToModèle() {
        if (DEBUG) System.out.println("Conjugueur.Contrôleur.ConjugHebibContrôleur::addListenersToModèle") ;
//        model.addConjugCoptListener(fieldVue);
    }
    public void notifyConjugCopthanged(int volume){
    }
    
/*     public void notifyVerbeChanged(int verbe){
       if (DEBUG) System.out.println ("AppliMVCC::notifyVerbeChanged, verbe = "+verbe) ;
        model.setVerbe(verbe);
    }*/
    
    public void displayVues(){
        if (DEBUG) System.out.println("Conjugueur.C.ConjugCopt::displayVues") ;
        fieldVue.display();
    }
    private final boolean DEBUG = true;    
}
