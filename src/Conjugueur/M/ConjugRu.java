/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;

public class ConjugRu extends Conjug {
    
    /**
     * Fction appelante : Conjugueur.M.ConjugAnFr::ConjugAnFr
     * @param mode "Indicatif", 
     * @param temps "Présent"
     * @param iAM indice d'un autre mot
     * @param nb
     */
    public ConjugRu(short mode, short temps, short voix, int iVerbe, int iAM, short nb){
        super(mode, temps, iVerbe);
        if (DEBUG) { System.out.println("Conjugueur.M.ConjugRu::ConjugRu, mode = " +  Conjugueur.M.Conjug.M.i2M(mode).toString()  + ", temps = " + Conjugueur.M.ConjugAnFr.TAnFr.i2T(temps).toString() + ", iAM = " + iAM +", iVerbe = "+ ConjugAnFr.cbVerbeM.get(iVerbe).getAttributeValue(ConjugAnFr.ATTRIBUT) ) ; } 
    }                                                                           // ConjugRu
    public static final String ATTRIBUT = "forme", BALISÉNAUTREMOT = "autremot", BALISÉNVERBE = "verbanfr", LANGUE = "ru", XMLSOURCE = "ConjugRu.xml";
    public final static ModèleMenu1 cbAutreMotM = new ModèleMenu1(XMLSOURCE, BALISÉNAUTREMOT, ATTRIBUT, LANGUE) ;
    private static final boolean DEBUG = false;      
}
