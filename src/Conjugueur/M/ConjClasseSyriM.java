/**
 * Gère la variable classe du syriaque
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;

public class ConjClasseSyriM {
    public static SYC classe = SYC.Fort  ;                                                      // N° de groupe
    public ConjClasseSyriM() {
       if (DEBUG) System.out.println ("ConjClasseSyriM::ConjClasseSyriM") ;
    }
    public static SYC getClasse() {
        return classe ;
    }
    public static void setClasse(SYC classe) {
       if (DEBUG) System.out.println ("ConjClasseSyriM::getClasse, classe = "+ classe) ;
        ConjClasseSyriM.classe = classe;
    }     
    public enum SYC { Fort((short)0), P_ōlaf((short)1), P_yud((short)2), P_nun((short)3), e_ōlaf ((short)4), e_waw((short)5), e_e((short)6), lōmadh_ōlaf((short)7), lōmadh_yud ((short)8) ; 
        private final short ic ;                                                  // indice de classe 0, 1, 2,...
        SYC(short ic) { this.ic = ic ; }
        public short ic() { return ic; }
    }                                                                           // SYC     
    private static final boolean DEBUG = false;    
}
