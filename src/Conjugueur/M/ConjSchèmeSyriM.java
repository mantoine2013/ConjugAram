/**
 * Gère la variable schème du Syriaque
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;

public class ConjSchèmeSyriM   {
    private static SYS schème = SYS.Peal ;

   
    public enum SYS {
        Peal((short)0, "Pe‘al", new char []{'\u0000','\u0730', '\u0000'}, new String []{""}), Etpeel ((short)1,"Etpe‘el", new char []{'\u0000','\u0736', '\u0000'}, new String []{"ܐܶܬ݂"}), Pael((short)2,"Pa‘el", new char []{'\u0730','\u0736', '\u0000'}, new String []{""}), Etpaal ((short)3,"Etpa‘al", new char []{'\u0000','\u0000', '\u0000'}, new String []{"ܐܶܬ݂"}), Aphel((short)4,"Aph‘el", new char []{'\u0000','\u0000', '\u0000'}, new String []{"ܐܰ"}) , Ettaphal((short)5,"Ettaph‘al", new char []{'\u0000','\u0000', '\u0000'}, new String []{"ܐܶܬ݂ܬܰ"}) ;
        private final String ls;
        private final short is;                                                   // indice de schème 0, 1, 2,...
        public final char[] accents ;                                        // accents sur les consonnes par schème

        SYS (short is, String lschème, char [] accents, String [] préfixe) { this.is = is ; this.ls = lschème ; this.accents = accents ; }
        public short is() { return is; }
        public String ls() { return ls; }
    }                                                                           // SYS      
    private static final boolean DEBUG = false;
}
