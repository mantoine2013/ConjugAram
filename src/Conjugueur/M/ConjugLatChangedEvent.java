/**
 *
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;

import java.util.EventObject;

public class ConjugLatChangedEvent  extends EventObject {
    private int newVolume;
 
    public ConjugLatChangedEvent(Object source, int newVolume){
        super(source);
        if (DEBUG) System.out.println("Conjugueur.Vue.ConjugLatContrôleur::JFrameConjugLat, newVolume = " + newVolume) ;
 
        this.newVolume = newVolume;
    }
 
    public int getNewConjugLat(){
        return newVolume;
    }
    private static final boolean DEBUG = true;          
}
