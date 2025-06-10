/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;

import java.util.EventObject;
 
public class VolumeChangedEvent extends EventObject{
    private int newVolume;
 
    public VolumeChangedEvent(Object source, int newVolume){
        super(source);
        if (DEBUG) System.out.println ("Conjugueur.M.VolumeChangedEvent::VolumeChangedEvent, newVolume = "+newVolume) ;
        this.newVolume = newVolume;
    }
 
    public int getNewVolume(){
        if (DEBUG) System.out.println ("Conjugueur.M.VolumeChangedEvent::getNewVolume") ;
        return newVolume;
    }
    private static final boolean DEBUG = true;
}