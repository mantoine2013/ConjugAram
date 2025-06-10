/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;
import javax.swing.event.EventListenerList;

public class ConjugHebM extends Conjug {
 
    public ConjugHebM (short mode, short temps, short voix, short thème, int iVerbe, short écriture){
        super(mode, temps, iVerbe);
        if (DEBUG) { System.out.println("Conjugueur.M.ConjugHebM::ConjugHebM, mode = " +  Conjugueur.M.Conjug.M.i2M(mode).toString()+ ", temps = " + Conjugueur.M.ConjugHebM.HMT.i2T(temps).toString()+ ", iVerbe = "+ iVerbe + ", écriture = " + écriture) ; }
        setÉcriture(écriture) ;
        conjTableM = new ConjTableHebM();
        listeners = new EventListenerList() ;
    }
 
    public enum HMS {
        Paal ((short)0), Piel ((short)1), Hifil ((short)2), Hitpael ((short)3), Huphal ((short)4), Pual ((short)5), Nifal((short)6) ;
        private final short is;
        private boolean enabled ;

        HMS (short ischème) { this.is = ischème ; }
        public short is() { return is; }
        public boolean GetEnabled () { return enabled ; }
        public void SetEnabled (boolean e) { this.enabled = e ; }
        public static HMS i2S(short schème) { HMS result = null;
            for (HMS s : HMS.values()) { if (schème == s.is()) { result = s ; break ; }}
            return result;
        }                                                                       //i2T
    }                                                                           // HMS   

    public enum HMT { Infinitif((short)0), Présent((short)1), Passé((short)2), Futur((short)3), Impératif((short)4) ;
        private final short it ;                                                  // indice de personne 0, 1, 2,...
        HMT(short it) { this.it = it ; }
        public short it() { return it; }
        public static HMT i2T(short temps) { HMT result = null;
            for (HMT t : HMT.values()) { if (temps == t.it()) { result = t ;  break; }}
            return result;
        }                                                                       //i2T
    }                                                                           // HMT   

    public final static String ATTRIBUTVERBE = "infinitif", BALISÉNTRÉE = "verhm", LANGUE = "heb", XMLSOURCE = "ConjugSemi.xml" ;
    public static final ModèleMenu1 cbVerbeM = new ModèleMenu1(XMLSOURCE, BALISÉNTRÉE, ATTRIBUTVERBE, LANGUE) ;
    
    private static final boolean DEBUG = true;        
}
