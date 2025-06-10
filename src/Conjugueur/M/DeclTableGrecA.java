/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;
import Conjugueur.ConjugGrecA;
import Conjugueur.V.DéclinaisonStyledDoc;
import static Conjugueur.V.JFrameConjugGrecA.LATIN;
import static Conjugueur.M.ConjTable.inbLignes;
import com.ibm.icu.text.Transliterator;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;


public class DeclTableGrecA extends ConjTable {
    
        /**
     * Genre        | f  |f |f
     * Terminaison  | α  |η |α
     * du nominatif |    |ή |
     * Terminaison  | ης |  |ας
     * du génitif   |    |  |
     * -------------|-----------
     * désinence    |ᾱηςf|ηf|ᾱαςf   
     */
    @Override
    public void MAJDecl(Conjugueur.C.Conjug contrôleur)  {
        if (DEBUG) { System.out.println ("Conjugueur.M.ConjugGrecA::MAJDecl, Mot = " + Conjugueur.M.ConjugGrecA.cbAutreMotM.get(contrôleur.model.getIAutreMot()).getAttributeValue(Conjugueur.M.ConjugGrecA.ATTRIBUT)) ;   }
        vider();                                                 
        déclinaisonChildren = Conjugueur.M.ConjugGrecA.cbAutreMotM.get(contrôleur.model.getIAutreMot()).getChildren("déclinaison");//        adjustJTableRowSizes(ConjugGrecA.jTabDecl);
        if (déclinaisonChildren.isEmpty()) {                    // verbe régulier
            StringTokenizer tokenizer = new StringTokenizer(Conjugueur.M.ConjugGrecA.cbAutreMotM.get(contrôleur.model.getIAutreMot()).getAttributeValue(Conjugueur.M.ConjugGrecA.ATTRIBUT), " ");
            forme[NOMSING] = tokenizer.nextToken() ;                        // nominatif singulier
            forme[GÉNSING] = tokenizer.nextToken() ;
            forme[GENRE] = tokenizer.nextToken() ;
            switch (forme[GENRE]) {
                case "f" -> {
                    // nom féminin
                    if("ας".equals(forme[NOMSING].substring(forme[NOMSING].length() - 2))) ConjugGrecA.itype = ConjugGrecA.GAD.PreDeclαςουf ;
                    else if("α".equals((forme[NOMSING].substring(forme[NOMSING].length() - 1)).replaceAll("[p{M}]", ""))) {
                        if("ας".equals(forme[GÉNSING])) ConjugGrecA.itype = ConjugGrecA.GAD.PreDeclᾱαςf ;
                        if("ης".equals(forme[GÉNSING])) ConjugGrecA.itype = ConjugGrecA.GAD.PreDeclᾱηςf ;
                    }
                    else if(("η".equals((forme[NOMSING].substring(forme[NOMSING].length() - 1)).replaceAll("\\p{M}", "")))||("ή".equals((forme[NOMSING].substring(forme[NOMSING].length() - 1)).replaceAll("\\p{M}", "")))) ConjugGrecA.itype = ConjugGrecA.GAD.PreDeclηf ;
                }
                case "m" -> {
                    // nom masculin
                    if(("ος".equals((forme[NOMSING].substring(forme[NOMSING].length()-2)).replaceAll("[p{M}]", "")))
                            && ("ου".equals((forme[GÉNSING]).replaceAll("[p{M}]", ""))))
                        ConjugGrecA.itype = ConjugGrecA.GAD.DeuDeclm ;
                    else if("ξ".equals((forme[NOMSING].substring(forme[NOMSING].length() - 1)).replaceAll("[p{M}]", ""))) {
                        if("ος".equals(forme[GÉNSING])) ConjugGrecA.itype = ConjugGrecA.GAD.TroisDecl ;
                    }
                    else    ConjugGrecA.itype = ConjugGrecA.GAD.PreDeclm ;
                }
                case "n" -> {
                    // nom neutre
                    if(("ον".equals((forme[NOMSING].substring(forme[NOMSING].length()-2)).replaceAll("[p{M}]", "")))
                            && ("ου".equals((forme[GÉNSING]).replaceAll("[p{M}]", ""))))
                        ConjugGrecA.itype = ConjugGrecA.GAD.DeuDecln ;
                }
            }                                                               // switch
            
            // nom féminin
            // nom masculin
            
            if (DEBUG) { System.out.println ("Conjugueur.M.ConjugGrecA::MAJDecl, Mot = "+Conjugueur.M.ConjugGrecA.cbAutreMotM.get(contrôleur.model.getIAutreMot()).getAttributeValue(Conjugueur.M.ConjugGrecA.ATTRIBUT)+", ConjugGrecA.itype = "+ConjugGrecA.itype) ;   }
        }                                                       // verbe régulier
        for (ConjugGrecA.GAC cas : ConjugGrecA.GAC.values()) {
            inbLignes = AjouterLigne(cas.ic(), cas.name(), LATIN, StyleConstants.ALIGN_RIGHT) ;
            for (char c : (forme[NOMSING]).substring(0, forme[NOMSING].length()-1).toCharArray()){
                try {
                    Copier (String.valueOf(c), DéclinaisonStyledDoc.stylera) ;
                } catch (BadLocationException ex) {
                    Logger.getLogger(DeclTableGrecA.class.getName()).log(Level.SEVERE, null, ex);
                }
            }                                                    // pour ts les caractères
            try {
                //                Conjugueur.M.ConjugGrecA.declTablemodele.Suffixer (inbLignes, cas.spip(ConjugGrecA.GAG.Féminin.ig(), ConjugGrecA.itype.id(), ConjNbM.getIndNb().in())) ;
                AjouterTranslittération(graToLatinTrans) ;
            } catch (BadLocationException ex) {
                Logger.getLogger(DeclTableGrecA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }                                                                           // MAJDecl
    public enum GAC { 
        Nominatif (0, new String [][][]{/*féminin*/{/*PreDeclᾱαςf*/{/*sing*/"ᾱ",/*duel*/"ᾱ",/*plur*/"αί"},/*PreDeclηf*/{/*sing*/"η",/*duel*/"ᾱ",/*plur*/"αι"},/*PreDeclᾱηςf*/{/*sing*/"α",/*duel*/"ᾱ",/*plur*/"ēs"},/*PreDeclαςουf*/{/*sing*/"ας",/*duel*/"",/*plur*/"ēs"},/*PreDeclm*/{/*sing*/"ας",/*duel*/"ᾱ",/*plur*/"αι"},/*DeuDeclm*/{/*sing*/"ος",/*duel*/"ᾱ",/*plur*/"οι"},/*DeuDecln*/{/*sing*/"ον",/*duel*/"ᾱ",/*plur*/"οι"},/*TroisDecl*/{/*sing*/"ξ",/*duel*/"ᾱ",/*plur*/"οι"}}}),
        Vocatif(1, new String [][][]{/*féminin*/{/*PreDeclᾱαςf*/{/*sing*/"ᾱ",/*duel*/"ᾱ",/*plur*/"αί"},/*PreDeclηf*/{/*sing*/"η",/*duel*/"ᾱ",/*plur*/"αι"},/*PreDeclᾱηςf*/{/*sing*/"α",/*duel*/"ᾱ",/*plur*/"ēs"},/*PreDeclαςουf*/{/*sing*/"α",/*duel*/"",/*plur*/"ēs"},/*PreDeclm*/{/*sing*/"α",/*duel*/"ᾱ",/*plur*/"αι"},/*DeuDeclm*/{/*sing*/"ε",/*duel*/"ᾱ",/*plur*/"οι"},/*DeuDecln*/{/*sing*/"ον",/*duel*/"ᾱ",/*plur*/"οι"},/*TroisDecl*/{/*sing*/"ξ",/*duel*/"ᾱ",/*plur*/"οι"}}}),
        Accusatif(2, new String [][][]{/*féminin*/{/*PreDeclᾱαςf*/{/*sing*/"άν",/*duel*/"ᾱ",/*plur*/"άς"},/*PreDeclηf*/{/*sing*/"ην",/*duel*/"ᾱ",/*plur*/"ας"},/*PreDeclᾱηςf*/{/*sing*/"αν",/*duel*/"ᾱ",/*plur*/"ēs"},/*PreDeclαςουf*/{/*sing*/"ίαν",/*duel*/"",/*plur*/"ēs"},/*PreDeclm*/{/*sing*/"αν",/*duel*/"ᾱ",/*plur*/"ᾱς"},/*DeuDeclm*/{/*sing*/"ον",/*duel*/"ᾱ",/*plur*/"ους"},/*DeuDecln*/{/*sing*/"ον",/*duel*/"ᾱ",/*plur*/"οι"},/*TroisDecl*/{/*sing*/"κᾰ",/*duel*/"ᾱ",/*plur*/"οι"}}}),
        Génitif(3, new String [][][]{/*féminin*/{/*PreDeclᾱαςf*/{/*sing*/"ᾶς",/*duel*/"αιν",/*plur*/"ῶν"},/*PreDeclηf*/{/*sing*/"ης",/*duel*/"αιν",/*plur*/"ῶν"},/*PreDeclᾱηςf*/{/*sing*/"ης",/*duel*/"ᾱ",/*plur*/"ium"},/*PreDeclαςουf*/{/*sing*/"ίου",/*duel*/"",/*plur*/"ēs"},/*PreDeclm*/{/*sing*/"ου",/*duel*/"αιν",/*plur*/"ῶν"},/*DeuDeclm*/{/*sing*/"ου",/*duel*/"ᾱ",/*plur*/"ων"},/*DeuDecln*/{/*sing*/"ου",/*duel*/"ᾱ",/*plur*/"οι"},/*TroisDecl*/{/*sing*/"κος",/*duel*/"ᾱ",/*plur*/"οι"}}}),
        Datif(4, new String [][][]{/*féminin*/{/*PreDeclᾱαςf*/{/*sing*/"ᾷ ",/*duel*/"αιν",/*plur*/"αῖς"},/*PreDeclηf*/{/*sing*/"ῃ",/*duel*/"αιν",/*plur*/"αις"},/*PreDeclᾱηςf*/{/*sing*/"ῃ",/*duel*/"ᾱ",/*plur*/"ibus"},/*PreDeclαςουf*/{/*sing*/"ίᾳ",/*duel*/"",/*plur*/"ēs"},/*PreDeclm*/{/*sing*/"ίᾳ",/*duel*/"αιν",/*plur*/"αις"},/*DeuDeclm*/{/*sing*/"ῳ",/*duel*/"ᾱ",/*plur*/"οις"},/*DeuDecln*/{/*sing*/"ῳ",/*duel*/"ᾱ",/*plur*/"οι"},/*TroisDecl*/{/*sing*/"κῐ",/*duel*/"ᾱ",/*plur*/"οι"}}}); 
        private final int ic;
        private final String [][][] suffixes ;                                     // préfixe et suffixe par schème
        GAC (int ic, String [][][] suffixes) { this.ic = ic ;   this.suffixes = suffixes ;}
        public int ic() { return ic; }    
        public String spip(int ig, int it, int in) {return suffixes [ig][it][in] ; }          // renvoie le suffixe d'un groupe donné   
    }                                                                           // GAC
    public enum GAD {PreDeclᾱαςf((short)0), PreDeclηf((short)1), PreDeclᾱηςf((short)2), PreDeclαςουf((short)3), PreDeclm((short)4), DeuDeclm((short)5), DeuDecln((short)6), TroisDecl((short)7);
        private final short id;
        GAD (short id) { this.id = id ;  }
        public short id() { return id; }    
    }                                                                           // GAD

    private  static String forme[] = new String [5] ;
    private final static short NOMSING = 0, GÉNSING = 1, GENRE = 3 ;
    private static final String GREEK_TO_LATIN = "Greek-Latin", NOMPOLICE = "Times New Roman";
    private static final Transliterator graToLatinTrans = Transliterator.getInstance(GREEK_TO_LATIN);
    private static final boolean DEBUG = false;    
}
