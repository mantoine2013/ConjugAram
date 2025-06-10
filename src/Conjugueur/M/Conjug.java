/**
 * @author MichelANTOINE@hotmail.com
 * Modèle de données
 * A comme souclasses ConjugAnFr
 */
package Conjugueur.M;
import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelListener;

public class Conjug {
    protected int iAM, iVerbe ;                                                   // indice autre mot, verbe
    protected short écriture, état, mode, nb, schème, temps, thème, voix ;

    /**
     * Fction appelante : Conjugueur.M.Conjug::Conjug
     * @param mode "Indicatif",
     * @param temps "Présent"
     * @param iVerbe indice de verbe
     */
    public Conjug(short mode, short temps, int iVerbe) {
       if (DEBUG) { System.out.println ("Conjugueur.M.Conjug::Conjug, mode = " + mode + ", temps = " + temps + ", iVerbe = " + iVerbe) ; }
        this.mode = mode ; this.temps = temps ;  this.iVerbe = iVerbe ; 
        listeners = new EventListenerList();
    }
    
    /**
     * Fction appelante : Conjugueur.C.ConjugAnFr::addListenersToModèle
     * @param listener 
     */
    public void addConjugListener(ConjugListener listener){
        listeners.add(ConjugListener.class, listener);
    }
    public void removeConjugListener(ConjugListener l){
         listeners.remove(ConjugListener.class, l);
    }
    public void addVolumeListener(VolumeListener listener){  // provisoire
        listeners.add(VolumeListener.class, listener);
    }

    public void firePChanged(String p){
        if (DEBUG) { System.out.println ("Conjugueur.M.Conjug::firePChanged, p = " + p) ; }
        ConjugListener[] listenerList = (ConjugListener[])listeners.getListeners(ConjugListener.class);
 
        for(ConjugListener listener : listenerList){
            listener.PChanged(new PanelChangedEvent(this, p));
        }
    }                                                                           // firePChanged
    
    public short    getÉcriture() {  return écriture ;   }
    public short    getÉtat() {    return état ;    }
    public int      getIAutreMot() { return iAM ; }
    public int      getIndVerbe() {  return iVerbe;    }
    public short    getMode() {  return mode;  }
    public short    getNb() { return nb ;  }  
    public short    getSchème() { return schème ;  }
    public short    getTemps() {   return temps ;   }
    public short    getThème() {  return thème ; }
    public int      getVolume() {  return 0 ; /* provisoire*/    }
    public short getVoix() {     return voix ;    }
    public TreeModelListener[] getTreeModelListeners() {
        return (TreeModelListener[])listenerList.getListeners(TreeModelListener.class);
    }
    public void setÉcriture(short écriture) {
       if (DEBUG) { System.out.println ("Conjugueur.M.Conjug::setÉcriture, écriture = "+ écriture) ; }
        this.écriture = écriture;
    }     
    public void setÉtat(short état) {
       if (DEBUG) System.out.println ("ConjÉtatM::getÉtat, état = "+ état) ;
        this.état = état;
    }     
    public void setIAutreMot(int iAM) {
       if (DEBUG) { System.out.println ("Conjugueur.M.Conjug::setIAutreMot, iAM = " + iAM) ; }
        this.iAM = iAM;
        firePChanged("D");
   }   
    public void setIVerbe(int iVerbe) {
       if (DEBUG) { System.out.println ("Conjugueur.M.Conjug::setIVerbe, iVerbe = " + iVerbe) ; }
        this.iVerbe = iVerbe;
        firePChanged("G");
    }   

    public void setMode(short mode) {
       if (DEBUG) { System.out.println ("Conjugueur.M.Conjug::setMode, mode = "+ mode) ; }
        this.mode = mode ;
        firePChanged("G");
    }

    public void setNb(short indNb) {
       if (DEBUG) { System.out.println ("Conjugueur.M.Conjug::setNb, indNb = " + indNb) ; }
        this.nb = indNb ;
        firePChanged("D");
    }   
    public void setSchème(short schème) {
       if (DEBUG) { System.out.println ("Conjugueur.M.Conjug::setSchème, schème = "+ schème) ; }
        this.schème = schème;
        firePChanged("G");
    }    

    public void setTemps(short temps) {
       if (DEBUG) { System.out.println ("Conjugueur.M.Conjug::setTemps, temps = "+ temps) ; }
        this.temps = temps;
        firePChanged("G");
    }
    public void setVoix(short voix) {
       if (DEBUG) { System.out.println ("Conjugueur.M.Conjug::setVoix, voix = "+ voix) ; } 
        this.voix = voix ;
        firePChanged("G");
    }    

    public enum É { Droite(((short)0), "Times New Roman"), Cursive(((short)1),"Dana Yad AlefAlefAlef Normal") ;
        private final short ié;
        private final String lp ;

        É (short ié, String lp) { this.ié = ié ; this.lp = lp ; }
        public short ié() { return ié; }    
        public String lp() { return lp ; }
        public static É i2É(short nb) { É result = null;
            for (É é : É.values()) { if (nb == é.ié()) { result = é ;  break; }}
            return result;
        }                                                                       //findByName
        public  String i2LP(short nb) { String result = null;
            for (É é : É.values()) {if (nb == é.ié()) { result = this.lp ;  break; }}
            return result;
        }                                                                       //findByName
    }                                                                           // É 
    // constantes définissant les modes
    public enum M { Indicatif((short)0), Subjonctif((short)1), Impératif((short)2), Optatif((short)3), Participe((short)3), Conditionnel((short)4), Infinitif((short)5), Supin((short)5) ;
        private final short im;
        M (short im) { this.im = im ;  }
        public short im() { return im; }    
        public static M l2M(String mode) { M result = null;
            for (M m : M.values()) {if (m.name().equals(mode)) { result = m ;  break; }}
            return result;
        }                                                                       //findByName
        public static M i2M(short mode) { M result = null;
            for (M m : M.values()) { if (mode == m.im()) { result = m ;  break; }}
            return result;
        }                                                                       //findByName
    }
    public enum N { Singulier((short)0), Pluriel((short)1) ;
        private final short in;
        N (short in) { this.in = in ;  }
        public short in() { return in; }    
        public static N i2N(short nb) { N result = null;
            for (N n : N.values()) { if (nb == n.in()) { result = n ;  break; }}
            return result;
        }                                                                       //findByName
    } 
    public enum S {
        Paal ((short)0, "Paal"), Piel ((short)1, "Piel"), Hifil ((short)2, "Hifil"), Hitpael ((short)3, "Hitpael"), Huphal ((short)4, "Huphal"), Pual ((short)5, "Pual"), Nifal((short)6, "Nifal") ;
        private final short is;
        private final String lm;
        private boolean enabled ;

        S (short ischème, String lm) { this.is = ischème ; this.lm = lm ;}
        @Override
        public String toString() {  return lm ;  }
        public short is() { return is; }
        public static S i2S(short schème) { S result = null;
            for (S s : S.values()) { if (schème == s.is()) { result = s ;  break; }}
            return result;
        }                                                                       //findByName
        public boolean GetEnabled () { return enabled ; }
        public void SetEnabled (boolean e) { this.enabled = e ; }
    }                                                                           // S   


    public enum T { Présent((short)0), Imparfait((short)1), Parfait((short)1), Aoriste((short)2), Futur((short)3), Passé ((short)2), Participe((short)3), FuturSimple((short)5), Gérondif((short)6), Infinitif((short)7), Impératif((short)7) ;
        private final short it;
        T (short it) { this.it = it ;  }
        public short it() { return it; }    
        public static T l2T(String temps) { T result = null;
            for (T t : T.values()) if (t.name().equals(temps)) { result = t;  break;  }
            return result;
        }                                                                       //l2T
        public static T i2T(short temps) { T result = null;
            for (T t : T.values()) { if (temps == t.it()) { result = t ;  break; }}
            return result;
        }                                                                       //i2T
    } 
    public enum V { Active ((short)0), Passive((short)1);
        private final short iv;
        V (short ivoix) { this.iv = ivoix ;  }
        public short iv() { return iv; }    
        public static V i2V(short voix) { V result = null;
            for (V v : V.values()) { if (voix == v.iv()) { result = v ;  break; } }
            return result;
        }                                                                       //i2T
        public static V l2V(String voix) { V result = null;
            for (V v : V.values()) if (v.name().equals(voix)) { result = v;  break;  }
            return result;
        }                                                                       //l2V
    }                                                                           // V
    public static ConjTable conjTableM, declTableM ;
    protected Conjugueur.M.Conjug model = null;
    protected EventListenerList listeners;  
    protected EventListenerList listenerList = new EventListenerList();
    private static final boolean DEBUG = false ;      
}                                                                               // Conjug
