/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;
import javax.swing.event.EventListenerList;
 
public class VolumeModel {
    private int volume;
 
    private EventListenerList listeners;
 
    public VolumeModel(){
        this(0);
    }
 
    public VolumeModel(int volume){
        super(); 
        this.volume = volume; 
        listeners = new EventListenerList();
    }
 
    public int getVolume() {
        return volume;
    }
 
    public void setVolume(int volume) {
       if (DEBUG) { System.out.println ("Conjugueur.M.VolumeModel::setVolume, volume = "+ volume) ; }
        this.volume = volume; 
        fireVolumeChanged();
    }
 
    public void addVolumeListener(VolumeListener listener){
       if (DEBUG) { System.out.println ("Conjugueur.M.VolumeModel::addVolumeListener, listener.toString() = "+ listener.toString()) ; }
        listeners.add(VolumeListener.class, listener);
    }
 
    public void removeVolumeListener(VolumeListener l){
         listeners.remove(VolumeListener.class, l);
    }
 
    public void fireVolumeChanged(){
       if (DEBUG) { System.out.println ("Conjugueur.M.VolumeModel::fireVolumeChanged") ; }
        VolumeListener[] listenerList = (VolumeListener[])listeners.getListeners(VolumeListener.class); 
        for(VolumeListener listener : listenerList){
            listener.volumeChanged(new VolumeChangedEvent(this, getVolume()));
        }
    }
    private static final boolean DEBUG = true;      
}