/**
 *
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;

import java.util.EventObject;

public class VerbeChangedEvent extends EventObject {
    private int newVerbe;
 
    public  VerbeChangedEvent(Object source, int newVerbe){
        super(source);
        if (DEBUG) System.out.println ("VerbeChangedEvent::VerbeChangedEvent, newVerbe = "+ newVerbe) ;
 
        this.newVerbe = newVerbe;
    }
 
    public int getNewVerbe(){
        if (DEBUG) System.out.println ("VerbeChangedEvent::getNewVerbe") ;
        return newVerbe;
    }
    private static final boolean DEBUG = true;
}
