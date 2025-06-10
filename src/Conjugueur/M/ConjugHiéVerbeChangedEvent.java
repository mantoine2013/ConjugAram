/**
 *
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;
import java.util.EventObject;

public class ConjugHiéVerbeChangedEvent extends EventObject{
    private int newConjugHié;
 
    public ConjugHiéVerbeChangedEvent(Object source, int newConjugHié){
        super(source);
 
        this.newConjugHié = newConjugHié;
    }
 
    public int getNewConjugHié(){
        return newConjugHié;
    }
}
