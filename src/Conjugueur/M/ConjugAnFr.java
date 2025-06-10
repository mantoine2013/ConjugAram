/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;
import javax.swing.event.EventListenerList;

public class ConjugAnFr extends Conjug  {
 
    /**
     * Fction appelante : Conjugueur.M.ConjugAnFr::ConjugAnFr
     * @param mode "Indicatif", 
     * @param temps "Présent"
     * @param iAM indice d'un autre mot
     * @param nb
     */
    public ConjugAnFr(short mode, short temps, short voix, int iVerbe, int iAM, short nb){
        super(mode, temps, iVerbe);
        if (DEBUG) { System.out.println("Conjugueur.M.ConjugAnFr::ConjugAnFr, mode = " +  Conjugueur.M.Conjug.M.i2M(mode).toString()  + ", temps = " + Conjugueur.M.ConjugAnFr.TAnFr.i2T(temps).toString() + ", iAM = " + iAM +", iVerbe = "+ ConjugAnFr.cbVerbeM.get(iVerbe).getAttributeValue(ConjugAnFr.ATTRIBUT) ) ; } 
        this.iAM = iAM ; this.écriture = écriture ; this.nb = nb ;
        conjTableM = new ConjTableAnFr();
        declTableM = new DeclTableAnFr() ;
        listeners = new EventListenerList() ;
    }
  
    /*
     * Temps pour l'ancien français
     */
    public enum TAnFr { Présent((short)0), Imparfait((short)1), PasséSimple ((short)2), Participe((short)3), Futur ((short)5), Gérondif((short)6), Infinitif((short)7), Impératif((short)7) ;
        private final short it;
        TAnFr (short it) { this.it = it ;  }
        public short it() { return it; }    
        public static TAnFr l2T(String temps) { TAnFr result = null;
            for (TAnFr t : TAnFr.values()) if (t.name().equals(temps)) { result = t;  break;  }
            return result;
        }                                                                       //l2T
        public static TAnFr i2T(short temps) { TAnFr result = null;
            for (TAnFr t : TAnFr.values()) { if (temps == t.it()) { result = t ;  break; }}
            return result;
        }                                                                       //i2TAnFr
    } 

    public static final String ATTRIBUT = "forme", BALISÉNAUTREMOT = "autremot", BALISÉNVERBE = "verbanfr", LANGUE = "fr", XMLSOURCE = "ConjugAnFr.xml";
    public final static ModèleMenu1 cbVerbeM = new ModèleMenu1(XMLSOURCE, BALISÉNVERBE, ATTRIBUT, LANGUE) ; 
    public final static ModèleMenu1 cbAutreMotM = new ModèleMenu1(XMLSOURCE, BALISÉNAUTREMOT, ATTRIBUT, LANGUE) ;
        
    private static final boolean DEBUG = false;      
}
