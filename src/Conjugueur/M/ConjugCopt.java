/**
 * Initialise les variables de l'italien
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;

public class ConjugCopt extends Conjug {
 
    /**
     * Fction appelante : Conjugueur.M.ConjugCopt::ConjugCopt
     * @param mode "Indicatif", 
     * @param temps "Présent"
     */
    public ConjugCopt(short mode, short temps, short voix, int iVerbe){
        super(mode, temps, iVerbe);
        if (DEBUG) { System.out.println("Conjugueur.M.ConjugCopt::ConjugCopt") ; }
        conjTableM = new ConjTableCopt();

    }    
    public enum ITM {Indicatif((short)0), Conditionnel((short)1), Subjonctif((short)2), Participe((short)3), Impératif((short)4) ;
        private final short im ;                                                  // indice de personne 0, 1, 2,...
        ITM(short im) { this.im = im ; }
        public short im() { return im; }
    }                                                                           // ITM 

    public static final String ATTRIBUTVERBE = "infinitif", BALISÉNTRÉE = "verbcopt", LANGUE = "cop", XMLSOURCE = "ConjugCopt.xml";
    public static final ModèleMenu1 comboBoxVerbeM = new ModèleMenu1(XMLSOURCE, BALISÉNTRÉE, ATTRIBUTVERBE, LANGUE) ;
    public static ConjTableCopt conjTableM ;
    private final boolean DEBUG = false;
}        

