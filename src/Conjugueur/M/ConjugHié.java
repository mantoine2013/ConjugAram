/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;

import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelListener;

public class ConjugHié extends Conjug {
    
 
    public ConjugHié(){
        super((short)0, (short)0, (short)0);
        if (DEBUG) System.out.println("Conjugueur.Modèle.ConjugHié::ConjugHié") ; 
        Verbe = 0;
        listeners = new EventListenerList() ;
    }
 
    public int getConjugHié() {
        return Verbe;
    }
 
    public void setConjugHié(int Verbe) {
        this.Verbe = Verbe;
    }

    /**
     * Fction appelante : Conjugueur.C.ConjugHié::addListenersToModèle
     * @param listener 
     */
 
    public void fireConjugHiéChanged(){
        VerbeListener[] listenerList = (VerbeListener[])listeners.getListeners(VerbeListener.class); 
        for(VerbeListener listener : listenerList){
//            listener.VerbeChanged(new VerbeChangedEvent(this, getConjugHié()));
        }
    }
    public TreeModelListener[] getTreeModelListeners() {
        return (TreeModelListener[])listenerList.getListeners(TreeModelListener.class);
    }
    
    public static int Verbe = 0 ;
    
    private static final boolean DEBUG = true;    
}