/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;

public class ConjugLat extends Conjug {

    /**
     * Fction appelante Conjugueur::main
     * @param mode "Indicatif", 
     * @param temps "Présent"
     */
    public ConjugLat(short mode, short temps, short voix, int iVerbe, int iAM, short nb) {
        super(mode, temps, iVerbe);
        if (DEBUG) { System.out.println("Conjugueur.M.ConjugLat::ConjugLat, mode = " + Conjugueur.M.Conjug.M.i2M(mode).toString() + ", temps = " + Conjugueur.M.ConjugLat.T.i2T(temps).toString() + ", voix = " + Conjugueur.M.ConjugLat.V.i2V(voix).toString() + ", iAM = " + iAM +", iVerbe = "+ ConjugLat.cbVerbeM.get(iVerbe).getAttributeValue(ConjugLat.ATTRIBUT)+", Mot = " + Conjugueur.M.ConjugLat.cbAutreMotM.get(iAM).getAttributeValue(Conjugueur.M.ConjugLat.ATTRIBUT)+", nb = "+nb) ; } 
        this.iAM = iAM ; this.nb = nb ; setVoix(voix) ;
        conjTableM = new ConjTableLat() ;
        declTableM = new DeclTableLat() ;
    }

    /*
     * Temps pour l'ancien français
     */
    public enum LAT { Présent((short)0), Parfait((short)1), Imparfait((short)2), Futur((short)3), PlusQueParfait((short)4) ;
        private final short it;
        LAT (short it) { this.it = it ;  }
        public short it() { return it; }    
        public static LAT l2T(String temps) { LAT result = null;
            for (LAT t : LAT.values()) if (t.name().equals(temps)) { result = t;  break;  }
            return result;
        }                                                                       //l2T
        public static LAT i2T(short temps) { LAT result = null;
            for (LAT t : LAT.values()) { if (temps == t.it()) { result = t ;  break; }}
            return result;
        }                                                                       //i2LAT
    } 
    
    public static final String XMLSOURCE = "ConjugLat.xml", ATTRIBUT = "forme", BALISÉNVERBE = "verblat", BALISÉNAUTREMOT = "autremot", LANGUE = "la" ;
    public final static ModèleMenu1 cbAutreMotM = new ModèleMenu1(XMLSOURCE, BALISÉNAUTREMOT, ATTRIBUT, LANGUE) ;
    public final static ModèleMenu1 cbVerbeM = new ModèleMenu1(XMLSOURCE, BALISÉNVERBE, ATTRIBUT, LANGUE) ;
    private final boolean DEBUG = false ;      
}
