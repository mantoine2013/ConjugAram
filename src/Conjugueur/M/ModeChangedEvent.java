package Conjugueur.M;

import java.util.EventObject;
 
public class ModeChangedEvent extends EventObject{
    private short newMode;
 
    public ModeChangedEvent(Object source, short newMode){
        super(source);
        if (DEBUG) System.out.println ("ModeChangedEvent::ModeChangedEvent, newMode = "+ newMode) ;
        this.newMode = newMode;
    }
 
    public short getNewMode(){
        if (DEBUG) System.out.println ("ModeChangedEvent::getNewMode") ;
        return newMode;
    }
    private static final boolean DEBUG = true;
}