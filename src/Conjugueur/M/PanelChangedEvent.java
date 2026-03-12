/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;
import java.util.EventObject;

public class PanelChangedEvent extends EventObject {
    private String newPanel;
 
    /**
     * Fction appelante : Conjugueur.M.Conjug::firePChanged
     */
        public  PanelChangedEvent(Object source, String panel){
        super(source);
        if (DEBUG) {System.out.println ("Conjugueur.M.PanelChangedEvent::PanelChangedEvent, panel = "+ panel) ; }
        this.newPanel = panel;
    }                                                                           // PanelChangedEvent
 
    public String getNewPanel(){
        if (DEBUG) {System.out.println ("Conjugueur.M.PanelChangedEvent::getNewPanel, newPanel = "+newPanel) ;}
        return newPanel;
    }
    private static final boolean DEBUG = false ;
}
