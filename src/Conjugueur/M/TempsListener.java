package Conjugueur.M;
import java.util.EventListener;
 
public interface TempsListener extends EventListener {
    public void tempsChanged(TempsChangedEvent event);
}