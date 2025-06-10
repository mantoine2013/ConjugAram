/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;

import java.util.EventObject;
 
public class ThèmeChangedEvent extends EventObject{
    private int newThème;
 
    public ThèmeChangedEvent(Object source, int newThème){
        super(source);
        if (DEBUG) System.out.println ("ThèmeChangedEvent::ThèmeChangedEvent, newThème = "+newThème) ;

        this.newThème = newThème;
    }
 
    public int getNewThème(){
        if (DEBUG) System.out.println ("ThèmeChangedEvent::getNewThème") ;
        return newThème;
    }
    private static final boolean DEBUG = true;
}