/**
 *
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;

import java.util.EventObject;


public class ConjugSyriChangedEvent  extends EventObject {
    
    private int newVolume;
    public ConjugSyriChangedEvent(Object source, int newVolume){
        super(source);
        if (DEBUG) System.out.println("Conjugueur.Modèle.ConjugSyriChangedEvent::ConjugSyriChangedEvent, newVolume = " + newVolume) ; 
        this.newVolume = newVolume;
    }    
    public int getNewConjugSyri(){
        return newVolume;
    }
    private static final boolean DEBUG = false;    }
