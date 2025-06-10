/**
 * Gère la variable état du syriaque
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;

public class ConjÉtatSyriM {
    private static SYÉT état = SYÉT.Emphatique ;
    public ConjÉtatSyriM() {
       if (DEBUG) System.out.println ("ConjÉtatSyriM::ConjÉtatSyriM, état = "+ état) ;
    }
    public static SYÉT getÉtat() {
        return état ;
    }
    public static void setÉtat(SYÉT état) {
       if (DEBUG) System.out.println ("ConjÉtatM::getÉtat, état = "+ état) ;
        ConjÉtatSyriM.état = état;
    }     
    public enum SYÉT { Emphatique((short)0), Construit((short)1), Absolu((short)2) ;
        private final short iét;
        SYÉT (short iét) { this.iét = iét ;  }
            public short iét() { return iét; }    
    }     
   private static final boolean DEBUG = true;    
}
