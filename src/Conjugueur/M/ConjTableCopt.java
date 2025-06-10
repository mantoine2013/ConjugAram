/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;
import Conjugueur.V.DéclinaisonStyledDoc;
import Conjugueur.V.JFrameConjugLat;
import com.ibm.icu.text.Transliterator;
import java.util.StringTokenizer;
import javax.swing.text.StyleConstants;
import javax.swing.text.BadLocationException;

public class ConjTableCopt  extends ConjTable {
    public void MAJConj(Conjugueur.C.ConjugCopt contrôleur) throws BadLocationException {
        if (DEBUG) { System.out.println ("Conjugueur.M.ConjTableCopt::MAJConj, Verbe = " + Conjugueur.M.ConjugCopt.comboBoxVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue(Conjugueur.M.ConjugCopt.ATTRIBUTVERBE)+", selectedIndMode = "+Conjugueur.M.Conjug.M.i2M(contrôleur.model.getMode()).toString()) ;   }
            vider ();
             déclinaisonChildren =  ConjugCopt.comboBoxVerbeM.get(contrôleur.model.getIndVerbe()).getChildren("déclinaison");
            switch (Conjugueur.M.ConjugCopt.M.i2M(contrôleur.model.getMode())) {                                             // mode
                case Indicatif -> {
                    switch (ConjTempsCopt.getTemps()) {                                       // temps
                        case Présent -> {
                            for (COP personne : COP.values()) {
                                if (déclinaisonChildren.isEmpty()) {                    // verbe régulier
                                    StringTokenizer tokenizer = new StringTokenizer(Conjugueur.M.ConjugCopt.comboBoxVerbeM.get(contrôleur.model.getIndVerbe()).getAttributeValue(ConjugCopt.ATTRIBUTVERBE), " ");
                                    forme[FABSOLU] = tokenizer.nextToken() ;
                                    inbLignes = AjouterLigne(personne.ip(), personne.lp(), JFrameConjugLat.LATIN, StyleConstants.ALIGN_RIGHT) ;
                                    DéclinerRégulièrement (personne, contrôleur) ;    
                                }                                                       // verbe régulier
                                AjouterTranslittération(copToLatinTrans) ;
                            }                                           // ttes les personnes
                        }                                                       // Présent
                    }                                                           // switch (ConjTempsItalM.getTemps())
                }                                                                // Indicatif
            }                                                                   // switch (Données.M.i2M(contrôleur.model.getMode()))
    }                                                                           // MAJConj
    /**
     * @param itype indice de groupe 1, 2 ou 3
    * Algorithme
    * 1. Copier le libellé du Pronom Personnel
    * 2. Si le préfixe existe, l'ajouter
    */
    private void DéclinerRégulièrement(COP personne, Conjugueur.C.ConjugCopt contrôleur) throws BadLocationException {
        if (DEBUG) System.out.println("Conjugueur.M.ConjTableLat::DéclinerRégulièrement, personne = " + personne.lp+", forme = "+itype+", Mode = " + contrôleur.model.getMode()+", Données.getTemps() = "+Conjugueur.M.ConjugCopt.T.i2T(contrôleur.model.getTemps()).toString()+", Données.getVoix() = "+ Conjugueur.M.ConjugAnFr.V.i2V(contrôleur.model.getTemps()).toString()+", itype = "+itype) ;
        CopierPersonne (personne.lip()) ;

        for (char c : (forme[FABSOLU]).toCharArray()){
    //        if (Consonne(c)) {                                // le caractère courant est une consonne
      //          nbcons++ ;
                Copier (String.valueOf(c), DéclinaisonStyledDoc.stylera) ;
//                this.Copier (inbLignes, Character.toString(personne.accent(temps, mode, (short)(nbcons-1))), DéclinaisonStyledDoc.stylesu) ;
        }                                                    // pour ts les caractères

     } 

    // constantes définissant les personnes   
    public enum COP {
        CO1S((short)0, "1è. s.","Ϯ"),
        CO2MS((short)1, "2è. m. s.","Ⲭ"),
        CO2FS((short)2, "2è. f. s.","Ⲧ"),
        CO3MS((short)3, "3è. m. s.","Ϥ"),
        CO3FS((short)4, "3è. f. s.","Ⲥ"),
        CO1P((short)5, "1è. p.","ⲧⲉⲛ"),
        CO2P((short)6, "2è. p.","ⲧⲉⲧ"),
        CO3P((short)7, "3è. p.","ⲥⲉ") ;
        private int ip ;                                                  // indice de personne 0, 1, 2,...
        private final String lip;
        private String lp;
        COP(int ipers, String lp, String lip) {
            this.ip     = ipers ; this.lp     = lp ; this.lip = lip ;
        }
        public int ip() { return ip; }
        public String lip() { return lip; }
        public String lp() { return lp; }
    }                                                                           // COP            
static String copToLatinRules = "ⲁ > a ; ⲃ > b ; ⲅ > g ; ⲇ > d ; ⲉ > e ; ⲍ > z ; ⲏ > ē ; ⲑ > th ; ⲓ > i ; ⲕ > k ; ⲗ > l ; ⲙ > m ; ⲛ > n ; ⲝ > x ; ⲟ > o ; ⲡ > p ; ⲣ > r ; ⲥ > s ; ⲧ > t ; ⲩ > u ; ⲫ > ph ; ⲭ > kh ; ⲯ > ps ; ⲱ > ō ; ϣ > sh ; ϥ > f ; ϧ > H ; ϩ > h ; ϫ > ḏ ; ϭ > ch ;  ϯ > ti ;" ;
;

    static Transliterator copToLatinTrans = Transliterator.createFromRules("any-hexAndName", copToLatinRules, Transliterator.FORWARD);
    private static short itype = 0 ;                                                      // N° de groupe
    private final static short FABSOLU = 0 ;
    String forme[] = new String [5] ;

    private static final boolean DEBUG = true;        
}
