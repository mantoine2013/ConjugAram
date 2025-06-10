/**
 * Initialise les variables du Gtec Ancien
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;
import static Conjugueur.M.ConjugGrecA.conjTableM;
import javax.swing.event.EventListenerList;

public class ConjugGrecA extends Conjug {    
 
    /**
     * Fction appelante : Conjugueur.M.ConjugGrecA::ConjugGrecA
     * @param iAM indice d'un autre mot
     * @param nb
     */
    public ConjugGrecA(short mode, short thème, short voix, int iVerbe, int iAM, short nb){
        super(mode, thème, iVerbe);
        if (DEBUG) { System.out.println("Conjugueur.M.ConjugGrecA::ConjugGrecA, mode = " + Conjugueur.M.Conjug.M.i2M(mode).toString() + ", thème = " + Conjugueur.M.ConjugGrecA.GAT.i2T(thème).toString() + ", iAM = " + iAM + ", iVerbe ="+ ConjugGrecA.cbVerbeM.get(iVerbe).getAttributeValue(ConjugAnFr.ATTRIBUT)) ; } 
        this.thème = thème ; this.iAM = iAM ;  this.nb = nb ; 
        conjTableM = new ConjTableGrecA();
        declTableM = new DeclTableGrecA();
        listeners = new EventListenerList() ;
    }
    public void fireThèmeChanged(){
        ThèmeListener[] listenerList = (ThèmeListener[])listeners.getListeners(ThèmeListener.class);
 
        for(ThèmeListener listener : listenerList){
            listener.thèmeChanged(new ThèmeChangedEvent(this, getThème()));
        }
    }
    public short getThème() {  return thème ; }
    public void setThème(short thème) {
       if (DEBUG) { System.out.println ("Conjugueur.M.Conjug::setThème, thème = "+ thème) ; }
        this.thème = thème;
        fireThèmeChanged();
    }

    public enum GAM { Indicatif((short)0), Impératif((short)1), Subjonctif((short)2), Optatif((short)3), Participe((short)4), Infinitif((short)5) ;
        private final short im;
        GAM (short im) { this.im = im ;  }
        public short im() { return im; }    
        public static GAM i2M(short mode) { GAM result = null;
            for (GAM m : GAM.values()) { if (mode == m.im()) { result = m ;  break; }}
            return result;
        }                                                                       //findByName
    }                                                                           // GAM
    //  constantes définissant les temps
    public enum GAN { Singulier((short)0), Duel((short)1), Pluriel((short)2) ;
        private final short in;
        GAN (short in) { this.in = in ;  }
        public short in() { return in; }    
    }                                                                           // GAN
    public enum GAT { Imperfectif((short)0), Futur((short)1), Aoriste((short)2), Parfait((short)3) ;
        private final short it;
        GAT (short it) { this.it = it ;  }
        public short it() { return it; }    
         public static GAT i2T(short temps) { GAT result = null;
            for (GAT t : GAT.values()) { if (temps == t.it()) { result = t ;  break; }}
            return result;
        }                                                                       //i2T
   } 
    public enum GAV { Active ((short)0), Passive((short)1), Moyenne((short)2);
        private final short iv;
        GAV (short ivoix) { this.iv = ivoix ;  }
        public short iv() { return iv; }    
    }                                                                           // GAV

    public enum N { Singulier((short)0), Pluriel((short)1) ;
        private final short in;
        N (short in) { this.in = in ;  }
        public short in() { return in; }    
        public static N i2N(short nb) { N result = null;
            for (N n : N.values()) { if (nb == n.in()) { result = n ;  break; }}
            return result;
        }                                                                       //findByName
    } 
    public static final String XMLSOURCE = "ConjugGrecA.xml", ATTRIBUT = "forme", BALISÉNTRÉE = "verbgra", BALISÉNAUTREMOT = "autremot" ;
    public static final ModèleMenu1 cbAutreMotM = new ModèleMenu1(XMLSOURCE, BALISÉNAUTREMOT, ATTRIBUT) ;
    public static final ModèleMenu1 cbVerbeM = new ModèleMenu1(XMLSOURCE, BALISÉNTRÉE, ATTRIBUT) ;
    private static final boolean DEBUG = false;    
}
