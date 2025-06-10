/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;
import java.util.EventListener;
 
public interface ThèmeListener extends EventListener {
    public void thèmeChanged(ThèmeChangedEvent event);
}