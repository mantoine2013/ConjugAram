/**
 * Initialise les variables de l'hébreu moderne
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.EventListenerList;

public class ConjugHebib extends Conjug {
     
    public ConjugHebib (short mode, short shème, int iVerbe){
        super(mode, shème, iVerbe);
        if (DEBUG) System.out.println("Conjugueur.M.ConjugHebib::ConjugHebib, mode = " +  Conjugueur.M.ConjugHebib.HBM.i2M(mode).toString()  + ", shème = " + Conjugueur.M.ConjugHebib.HBS.i2S(shème).toString() + ", iAM = " + iAM + ", iVerbe = " + cbVerbeM.get(iVerbe).getAttributeValue(ConjugHebib.ATTRIBUTVERBE)) ;
        setSchème(shème) ;
        conjTableM = new ConjTableHebib();
        declTableM = new DeclTableHebib();
        listeners = new EventListenerList() ;
    }

    /*
     * Shèmes pour l'hébreu biblique
     */
    public enum HBS {
        Qal((short)0, new char []{'\u05B8','\u05B7','\u0000'}, new String []{"", ""}), Piel ((short)1, new char []{'\u05B4','\u05BC','\u0000'}, new String []{"", ""}), Hifil((short)2, new char []{'\u05B0','\u05B4','\u0000'}, new String []{"הִ", ""}), Pual ((short)3,new char []{'\u05BB','\u05BC','\u0000'}, new String []{"הִ", ""}), Hofal ((short)4, new char []{'\u05B0','\u05B7','\u0000'}, new String []{"הִ", ""}), Nifal ((short)5, new char []{'\u05B0','\u05B7','\u0000'}, new String []{"נִ", ""}), Hitpael ((short)6, new char []{'\u05B7','\u05BC','\u0000'}, new String []{"הִת", ""}) ;
        private final short is;                                                   // indice de schème 0, 1, 2,...
        public final char[] accents ;                                        // accents sur les consonnes
        public final String [] pairePS;

        HBS (short is, char [] accents, String [] pairePS) { this.is = is ; this.accents = accents ;this.pairePS = pairePS ; }
        public char accent (short i) {  return accents[i] ;    }
        public short is() { return is; }
        public static HBS i2S(short schème) { HBS result = null;
            for (HBS s : HBS.values()) { if (schème == s.is()) { result = s ;  break; }}
            return result;
        }                                                                       //i2T
    }                                                                           // HBS 
    /*
     * Modes pour l'hébreu biblique
     */
    public enum HBM { Qatal ((short)0, "Qatal"), Yiqtol ((short)1, "Yiqtol"), Volitif((short)2, "Volitif"), Cohortatif ((short)3, "Cohortatif"), Impératif ((short)4, "Impératif"), Jussif((short)5, "Jussif"), InfCons((short)6, "Infinitif construit"), InfAbs((short)7, "Infinitif absolu"), Participatif ((short)8, "Participatif");
        //Instance variable
        private final short im;                                                   // indice de personne 0, 1, 2,...
        public final String lm;

        @Override
        public String toString() {  return lm ;  }
 
        HBM (short im, String lm) { this.im = im ; this.lm = lm ; }
        public short im() { return im; }
        public String lm() { return lm; }
        public static HBM i2M(short mode) { HBM result = null;
            for (HBM m : HBM.values()) { if (mode == m.im()) { result = m ;  break; }}
            return result;
        }                                                                       //findByName
    }                                                                           // HBM
    public final static String ATTRIBUTMOT="forme", ATTRIBUTVERBE = "infinitif", BALISÉNTRÉE = "verhb", BALISAUTREMOT = "authbmot", LANGUE = "heb", NOMPOLICE = "Times New Roman", XMLSOURCE = "ConjugSemi.xml" ;

    public static final DefaultComboBoxModel<HBM> cModelModes = new DefaultComboBoxModel<>(HBM.values()) ; 
    public static final DefaultComboBoxModel<HBS> cModelSchème = new DefaultComboBoxModel<>(HBS.values()) ; 
    public static final ModèleMenu1 cbVerbeM = new ModèleMenu1(XMLSOURCE, BALISÉNTRÉE, ATTRIBUTVERBE, LANGUE) ; 
    public static final ModèleMenu1 comboBoxAutreMotM = new ModèleMenu1(XMLSOURCE, BALISAUTREMOT, ATTRIBUTMOT, LANGUE) ;
    private static final boolean DEBUG = false;    
}


    

