/**
 *
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;

import java.util.EventObject;

public class ConjugSansChangedEvent  extends EventObject {
    private int newVolume;
 
    public ConjugSansChangedEvent(Object source, int newVolume){
        super(source);
        if (DEBUG) System.out.println("Conjugueur.Vue.ConjugSansContrôleur::JFrameConjugSans, newVolume = " + newVolume) ;
 
        this.newVolume = newVolume;
    }
 
    public int getNewConjugSans(){
        return newVolume;
    }
    private static final boolean DEBUG = true;          
}
