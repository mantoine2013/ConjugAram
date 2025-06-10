package Conjugueur.M;

import java.util.EventObject;
 
public class TempsChangedEvent extends EventObject{
    private short newTemps;
 
    public TempsChangedEvent(Object source, short newTemps){
        super(source);
        if (DEBUG) System.out.println ("TempsChangedEvent::TempsChangedEvent, newTemps = "+ newTemps) ;
        this.newTemps = newTemps;
    }
 
    public short getNewTemps(){
        if (DEBUG) System.out.println ("TempsChangedEvent::getNewTemps") ;
        return newTemps;
    }
    private static final boolean DEBUG = true;
}