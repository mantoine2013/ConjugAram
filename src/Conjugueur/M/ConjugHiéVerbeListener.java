/**
 *
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;
import Conjugueur.M.ConjugHiéVerbeChangedEvent;
import java.util.EventListener;


public interface ConjugHiéVerbeListener extends EventListener {    
    public void ConjugHiéVerbeChanged(ConjugHiéVerbeChangedEvent event);
    
}
