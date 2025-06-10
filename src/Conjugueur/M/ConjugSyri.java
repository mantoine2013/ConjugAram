/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;

public class ConjugSyri extends Conjug  {

    public ConjugSyri(short mode, short temps, short voix, int iVerbe, short écriture, short schème) {
        super(mode, temps, iVerbe);
        if (DEBUG) { System.out.println("Conjugueur.M.ConjugSyri::ConjugSyri, mode = " +  Conjugueur.M.Conjug.M.i2M(mode).toString()  + ", temps = " + Conjugueur.M.Conjug.T.i2T(temps).toString()) ; } 
        conjTableM = new ConjTableSyri() ;
        declTableM = new DeclTableSyri() ;
    }    
  
    /* 
     * Polices pour le syriac
     */
    public enum SYF { Serto(((short)0), "Serto Jerusalem"), Estrangela(((short)1),"Estrangelo Edessa") ;        // écriture / police        
        private final short ifo;
        private final String lp ;
    
        SYF (short ifo, String lp) { this.ifo = ifo ; this.lp = lp ; }
        public short ifo() { return ifo; }    
    public String lp() { return lp ; }
    }                                                                           // SYF    

    /* 
     * Shèmes pour le syriac
     */
    public enum SYS {                                                           // schème syriac
        Peal((short)0, "Pe‘al", new char []{'\u0000','\u0730', '\u0000'}, new String []{""}), Etpeel ((short)1,"Etpe‘el", new char []{'\u0000','\u0736', '\u0000'}, new String []{"ܐܶܬ݂"}), Pael((short)2,"Pa‘el", new char []{'\u0730','\u0736', '\u0000'}, new String []{""}), Etpaal ((short)3,"Etpa‘al", new char []{'\u0000','\u0000', '\u0000'}, new String []{"ܐܶܬ݂"}), Aphel((short)4,"Aph‘el", new char []{'\u0000','\u0000', '\u0000'}, new String []{"ܐܰ"}) , Ettaphal((short)5,"Ettaph‘al", new char []{'\u0000','\u0000', '\u0000'}, new String []{"ܐܶܬ݂ܬܰ"}) ;
        private final String ls;
        private final short is;                                                   // indice de schème 0, 1, 2,...
        public final char[] accents ;                                        // accents sur les consonnes par schème

        SYS (short is, String lschème, char [] accents, String [] préfixe) { this.is = is ; this.ls = lschème ; this.accents = accents ; }
        public short is() { return is; }
        public String ls() { return ls; }
        public static SYS i2S(short schème) { SYS result = null;
            for (SYS s : SYS.values()) { if (schème == s.is()) { result = s ;  break; }}
            return result;
        }                                                                       //findByName
    }                                                                           // SYS      
    /* 
     * Temps pour le syriac
     */
    public enum SYT { Accompli((short)0), Inaccompli((short)1), Impératif((short)2), Participe((short)3), INFINITIF((short)4) ; 
        private final short it ;                                                  // indice de personne 0, 1, 2,...
        SYT(short it) { this.it = it ; }
        public short it() { return it; }
        public static SYT i2T(short temps) { SYT result = null;
            for (SYT t : SYT.values()) { if (temps == t.it()) { result = t ;  break; }}
            return result;
        }                                                                       //i2T
        public static SYT l2T(String temps) { SYT result = null;
            for (SYT t : SYT.values()) if (t.name().equals(temps)) { result = t;  break;  }
            return result;
        }                                                                       //l2T
    }                                                                           // SYT 
    public final static String XMLSOURCE = "ConjugSemi.xml", ATTRIBUT = "forme", BALISÉNVERBE = "versy", BALISÉNAUTREMOT = "autsymot", LANGUE = "syr" ;
    public static final ModèleMenu1 comboBoxAutreMot = new ModèleMenu1(XMLSOURCE, BALISÉNAUTREMOT, ATTRIBUT, LANGUE) ;
    public final static ModèleMenu1 comboBoxVerbeM = new ModèleMenu1(XMLSOURCE, BALISÉNVERBE, ATTRIBUT, LANGUE) ; 
    private static final boolean DEBUG = true;    
}
