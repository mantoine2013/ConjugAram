/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.C;

import Conjugueur.V.JFrameConjugHebM;

public class ConjugHebM extends Conjug {
 
    public ConjugHebM (Conjugueur.M.ConjugHebM model) {
        if (DEBUG) System.out.println("Conjugueur.Contrôleur.ConjugHebM::ConjugHebM") ;
        this.model = model; 
        fieldVue = new JFrameConjugHebM(this); 
        addListenersToModèle();
    }
 
     private  final boolean DEBUG = false;        
}
