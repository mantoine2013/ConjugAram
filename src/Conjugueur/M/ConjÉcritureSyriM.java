/**
 * Gère la variable écriture du syriaque
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;

public class ConjÉcritureSyriM {
    private static SYF écriture = SYF.Serto ;
    public ConjÉcritureSyriM() {
       if (DEBUG) System.out.println ("Conjugueur.Modèle.ConjÉcritureSyriM::ConjÉcritureSyriM") ;
    }
    public static void setÉcriture(SYF écriture) {
       if (DEBUG) System.out.println ("Conjugueur.Modèle.ConjÉcritureM::getÉcriture, écriture = "+ écriture) ;
        ConjÉcritureSyriM.écriture = écriture;
    }     
    public enum SYF {                                                          // écriture / police
        Serto("Serto Jerusalem"), Estrangela("Estrangelo Edessa") ;
        private final String lp ;

        SYF (String lp) { this.lp = lp ; }
        public String lp() { return lp ; }
    }                                                                           // SYF    
    private static final boolean DEBUG = true;    
}
