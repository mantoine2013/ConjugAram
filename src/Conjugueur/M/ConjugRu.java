/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;
import javax.swing.event.EventListenerList;

public class ConjugRu extends Conjug {
    
    /**
     * Fction appelante : Conjugueur.M.ConjugRu::ConjugRu
     * @param mode "Indicatif", 
     * @param temps "Présent"
     * @param iAM indice d'un autre mot
     * @param nb
     */
    public ConjugRu(short mode, short temps, short voix, int iVerbe, int iAM, short nb){
        super(mode, temps, iVerbe);
        if (DEBUG) { System.out.println("Conjugueur.M.ConjugRu::ConjugRu, mode = " +  Conjugueur.M.Conjug.M.i2M(mode).toString()  + ", temps = " + Conjugueur.M.ConjugRu.RUT.i2T(temps).toString() + ", iAM = " + iAM +", iVerbe = "+ ConjugRu.cbVerbeM.get(iVerbe).getAttributeValue(ConjugRu.ATTRIBUTVERBEI) ) ; } 
        this.iAM = iAM ; this.écriture = écriture ; this.nb = nb ;
        conjTableM = new ConjTableRu();
        declTableM = new DeclTableRu() ;
        listeners = new EventListenerList() ;
    }                                                                           // ConjugRu

    public enum RUM {Indicatif((short)0), Conditionnel((short)1), Impératif((short)2), Infinitif((short)4) ;
        private final short im ;                                                  // indice de personne 0, 1, 2,...
        RUM(short im) { this.im = im ; }
        public short im() { return im; }
    }                                                                           // RUM 

    public enum RUA { Imperfectif((short)0), Perfectif((short)1) ;
        private final short ia;
        RUA (short ia) { this.ia = ia ;  }
        public short ia() { return ia; }    
        public static RUA i2A(short aspect) { RUA result = null;
            for (RUA a : RUA.values()) { if (aspect == a.ia()) { result = a ;  break; }}
            return result;
        }                                                                       //i2T
        public static RUA l2A(String aspect) { RUA result = null;
            for (RUA t : RUA.values()) if (t.name().equals(aspect)) { result = t;  break;  }
            return result;
        }                                                                       //l2T
    } 
    public enum RUC {
        PremièreConjugaison((short)0), DeuxièmeConjugaison((short)1) ;
        private final short ic;
        private RUC(short ic) {   this.ic = ic;      }
        public short ic() {      return this.ic ;     }
        public static RUC i2C(short aspect) { RUC result = null;
            for (RUC c : RUC.values()) { if (aspect == c.ic()) { result = c ;  break; }}
            return result;
        }                                                                       //i2T
    }                                                                           //RUC
    /*
     * Temps pour le russe
     */
    public enum RUT { Présent((short)0), Passé((short)1), Futur ((short)2) ;
        private final short it;
        RUT (short it) { this.it = it ;  }
        public short it() { return it; }    
        public static RUT l2T(String temps) { RUT result = null;
            for (RUT t : RUT.values()) if (t.name().equals(temps)) { result = t;  break;  }
            return result;
        }                                                                       //l2T
        public static RUT i2T(short temps) { RUT result = null;
            for (RUT t : RUT.values()) { if (temps == t.it()) { result = t ;  break; }}
            return result;
        }                                                                       //i2RUT
    } 
    public static final String ATTRIBUTMOT = "forme", ATTRIBUTVERBEI = "imperfectif", ATTRIBUTVERBEP = "perfectif", BALISÉNAUTREMOT = "autremot", BALISÉNVERBE = "verbru", LANGUE = "ru", XMLSOURCE = "ConjugRu.xml";
    public final static ModèleMenu1 cbAutreMotM = new ModèleMenu1(XMLSOURCE, BALISÉNAUTREMOT, ATTRIBUTMOT, LANGUE) ;
    public final static ModèleMenu1 cbVerbeM = new ModèleMenu1(XMLSOURCE, BALISÉNVERBE, ATTRIBUTVERBEI, ATTRIBUTVERBEP, LANGUE) ; 
    private static final boolean DEBUG = false ;      
}
