/**
 * Gère la variable temps de l'italien
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;

public class ConjTempsCopt {
    public static ITT temps = ITT.Présent ;
    public ConjTempsCopt() {
    }
    public static ITT getTemps() {
        return temps ;
    }
    public static void setTemps(ITT temps) {
       if (DEBUG) System.out.println ("ConjTempsM::getTemps, temps = "+ temps) ;
        ConjTempsCopt.temps = temps;
    } 

    public enum ITT { Présent((short)0), Imparfait((short)1), Futur((short)2), Impératif((short)3), Gérondif((short)4), infinitif((short)5) ; 
        private final short it ;                                                  // indice de personne 0, 1, 2,...
        ITT(short it) { this.it = it ; }
        public short it() { return it; }
    }                                                                           // ITT           
    private static final boolean DEBUG = true;    
}
