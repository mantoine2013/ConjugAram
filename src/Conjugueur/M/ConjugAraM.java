/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;

import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelListener;


public class ConjugAraM extends Conjug {
    protected EventListenerList listenerList = new EventListenerList();
 
    public ConjugAraM (short mode, short temps, short voix, int iVerbe, int iAM){
        super(mode, temps, iVerbe);
        if (DEBUG) { System.out.println("Conjugueur.M.ConjugAraM::ConjugAraM, mode = " +  Conjugueur.M.Conjug.M.i2M(mode).toString()  + ", temps = " + Conjugueur.M.ConjugAraM.ART.i2T(temps).toString() + ", iAM = " + iAM + ", iVerbe = "+ ConjugAraM.cbVerbeM.get(iVerbe).getAttributeValue(ConjugAraM.ATTRIBUT) ) ; } 
        conjTableM = new ConjTableAraM();
        declTableM = new DeclTableAraM() ;
        listeners = new EventListenerList() ;
    }
  
    /**
     * Fction appelante : Conjugueur.C.ConjugAraM::addListenersToModèle
     * @param listener 
     */
    public void addConjugAraMListener(ConjugListener listener){
        if (DEBUG) System.out.println("Conjugueur.Modèle.ConjugAraM::addConjugAraMListener") ; 
        listeners.add(ConjugListener.class, listener);
    }
    public void removeConjugAraMListener(ConjugListener l){
        listeners.remove(ConjugListener.class, l); 
    }
 
    public void fireConjugAraMChanged(){

    }
    public TreeModelListener[] getTreeModelListeners() {
        return (TreeModelListener[])listenerList.getListeners(TreeModelListener.class);
    }
    public enum ART { Inaccompli((short)0), Accompli((short)1), Futur((short)2) ; 
        private final short it ;                                                  // indice de personne 0, 1, 2,...
        ART(short it) { this.it = it ; }
        public short it() { return it; }
        public static ART i2T(short temps) { ART result = null;
            for (ART t : ART.values()) { if (temps == t.it()) { result = t ;  break; }}
            return result;
        }                                                                       //i2T
        public static ART l2T(String temps) { ART result = null;
            for (ART t : ART.values()) if (t.name().equals(temps)) { result = t;  break;  }
            return result;
        }                                                                       //l2T
    }                                                                           // ART    

    public enum ARV { Active ((short)0), Passive((short)1), Impérative((short)2);
        private final short iv;
        ARV (short ivoix) { this.iv = ivoix ;  }
        public short iv() { return iv; }    
    }                                                                           // ARV  

    public static final String ATTRIBUT = "infinitif", AAM = "forme", BAMAramM = "amaraM", BALISÉNVERBE = "varaM", LANGUE = "ar", XMLSOURCE = "ConjugAraM.xml";
    public static final ModèleMenu1 cbAutreMotM = new ModèleMenu1(XMLSOURCE, BAMAramM, AAM, LANGUE) ;
    public final static ModèleMenu1 cbVerbeM = new ModèleMenu1(XMLSOURCE, BALISÉNVERBE, ATTRIBUT, LANGUE) ; 
        
    private static final boolean DEBUG = true ;      
}
