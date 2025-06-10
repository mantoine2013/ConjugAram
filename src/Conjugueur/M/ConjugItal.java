/**
 * Initialise les variables de l'italien
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;

public class ConjugItal extends Conjug {
 
    public ConjugItal(short mode, short temps, short voix, int iVerbe){
        super(mode, temps, iVerbe);
        if (DEBUG) System.out.println("Conjugueur.Modèle.ConjugItal::ConjugItal") ;
        conjTableM = new ConjTableItal();

    }    

    private static final String XMLSOURCE = "ConjugItal.xml";
    public static final ModèleMenu1 comboBoxVerbeM = new ModèleMenu1(XMLSOURCE, "verbe", "infinitif") ;
    private final boolean DEBUG = false;
}        

