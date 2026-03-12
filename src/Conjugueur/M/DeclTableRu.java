/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;
import static Conjugueur.M.ConjTable.inbLignes;
import Conjugueur.V.*;
import com.ibm.icu.text.Transliterator;
import java.util.StringTokenizer;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;

public class DeclTableRu  extends ConjTable {
    
    /**
     * Fction appelante : Conjugueur.V.JFrameConjugRu::JFrameConjugRu
     * Algorithme
     *  Si la déclinaison est régulière
     * 
     */
    @Override
    public void MAJDecl(Conjugueur.C.Conjug contrôleur) {
        if (DEBUG) { System.out.println ("Conjugueur.M.DeclTableRu::MAJDecl, Mot = " + Conjugueur.M.ConjugRu.cbAutreMotM.get(contrôleur.model.getIAutreMot()).getAttributeValue(Conjugueur.M.ConjugRu.ATTRIBUTMOT) + ", Nombre "+ contrôleur.model.getNb()) ;   }
        vider();                                                 
        try {
            déclinaisonChildren = Conjugueur.M.ConjugRu.cbAutreMotM.get(contrôleur.model.getIAutreMot()).getChildren("déclinaison");
            if (déclinaisonChildren.isEmpty()) {                    // mot régulier
                itype = DéterminerDéclinaison(contrôleur) ; 
                for (var cas : RUC.values()) {
                    inbLignes = AjouterLigne(cas.ic(), cas.lc(), JFrameConjugAnFr.FRANÇAIS, StyleConstants.ALIGN_RIGHT) ;
                    for (char c : (forme[RUF.Racine.ifo()]).toCharArray()){
                        Copier (String.valueOf(c), DéclinaisonStyledDoc.stylera) ;
                    }                                                    // pour ts les caractères
                    AjouterTranslittération(inbLignes, cyToLatinTrans) ;
                }
            }                                                       // mot régulier
        } catch (BadLocationException ble) {        }
    }                                                                           // MAJDecl
    /**
     * Fonction : Détermine le type de déclinaison
     * Algorithme
     *  1. Prendre le nominatif singulier (equus, ...)
     *  2. Prendre le génitif (æ, ..)
     *  3. Prendre le genre (f, m ou n)
     */    
    private RUD DéterminerDéclinaison(Conjugueur.C.Conjug contrôleur)  {
        if (DEBUG)  System.out.println ("Conjugueur.M.DeclTableRu::DéterminerDéclinaison, Mot = " + Conjugueur.M.ConjugRu.cbAutreMotM.get(contrôleur.model.getIAutreMot()).getAttributeValue(Conjugueur.M.ConjugRu.ATTRIBUTMOT) + ", Nombre = " + Conjugueur.M.Conjug.N.i2N(contrôleur.model.getNb()).toString()) ;   
        StringTokenizer tokenizer = new StringTokenizer(Conjugueur.M.ConjugRu.cbAutreMotM.get(contrôleur.model.getIAutreMot()).getAttributeValue(Conjugueur.M.ConjugRu.ATTRIBUTMOT), " ");
        forme[RUF.Racine.ifo()] = tokenizer.nextToken() ;
        if ("a".equals(forme[RUF.Racine.ifo()].substring(forme[RUF.Racine.ifo()].length() - 1))) { JFrameConjug.jLabelDéclinaison.setText("1ère déclinaison") ; return RUD.PreDecl ; } 
        else { JFrameConjug.jLabelDéclinaison.setText("2ème déclinaison") ; return RUD.DeuDecl ; }                                                 // switch
    }                                                                           // DéterminerDéclinaison
    public enum RUC { 
        CNominatif ((short)0, "Nominatif", new String [][] {/*1è déc*/{/*sing*/"s",/*plur*/""},/*2è déc*/{/*sing*/"us",/*plur*/"ī"},/*3è déc Parisyllabiques masculins et féminins*/{/*sing*/"",/*plur*/"ēs"},/*3è déc Parisyllabiques neutres*/{/*sing*/"",/*plur*/"ēs"},/*3è déc Imparisyllabiques masculins et féminins*/{/*sing*/"",/*plur*/"ēs"}}),
        CGénitif((short)1, "Génitif", new String [][] {/*1è déc*/{/*sing*/"",/*plur*/"s"},/*2è déc*/{/*sing*/"ĕ",/*plur*/"ī"},/*3è déc Parisyllabiques masculins et féminins*/{/*sing*/"",/*plur*/"ēs"},/*3è déc Parisyllabiques neutres*/{/*sing*/"",/*plur*/"ēs"},/*3è déc Imparisyllabiques masculins et féminins*/{/*sing*/"",/*plur*/"ēs"}}),
        CDatif ((short)2, "Datif", new String [][] {/*1è déc*/{/*sing*/"s",/*plur*/""},/*2è déc*/{/*sing*/"us",/*plur*/"ī"},/*3è déc Parisyllabiques masculins et féminins*/{/*sing*/"",/*plur*/"ēs"},/*3è déc Parisyllabiques neutres*/{/*sing*/"",/*plur*/"ēs"},/*3è déc Imparisyllabiques masculins et féminins*/{/*sing*/"",/*plur*/"ēs"}}),
        CInstrumental((short)3, "Instrumental", new String [][] {/*1è déc*/{/*sing*/"",/*plur*/"s"},/*2è déc*/{/*sing*/"ĕ",/*plur*/"ī"},/*3è déc Parisyllabiques masculins et féminins*/{/*sing*/"",/*plur*/"ēs"},/*3è déc Parisyllabiques neutres*/{/*sing*/"",/*plur*/"ēs"},/*3è déc Imparisyllabiques masculins et féminins*/{/*sing*/"",/*plur*/"ēs"}}),
        CLocatif((short)4, "Locatif", new String [][] {/*1è déc*/{/*sing*/"",/*plur*/"s"},/*2è déc*/{/*sing*/"ĕ",/*plur*/"ī"},/*3è déc Parisyllabiques masculins et féminins*/{/*sing*/"",/*plur*/"ēs"},/*3è déc Parisyllabiques neutres*/{/*sing*/"",/*plur*/"ēs"},/*3è déc Imparisyllabiques masculins et féminins*/{/*sing*/"",/*plur*/"ēs"}});
        private final int ic;
        private final String lc ;                                               // libellé de personne "1è. s.", "2è. m. s."
        private final String [][] suffixes ;                                     // préfixe et suffixe par schème
        RUC (int ic, String lc, String [][] suffixes) { this.ic = ic ;  this.lc = lc ; this.suffixes = suffixes ;}
        public int ic() { return ic; }    
        public String lc() { return lc; }
        public String spip(int it, int in) { 
            return suffixes [it][in] ; }          // renvoie le suffixe d'un groupe donné   
    }
    public enum RUD {PreDecl((short)0), DeuDecl((short)1), TroiDecl((short)2) ;
        private final short id;
        RUD (short id) { this.id = id ;  }
        public short id() { return id; }    
    }
    public enum RUF {
        Racine((short)0), Genre((short)1);
        private final short ifo;
        private RUF(short ifo) {  this.ifo = ifo;  }
        public short ifo() {   return this.ifo;   }        
    }                                                                           // RUF
    private static final String CYRILLIC_TO_LATIN = "Cyrillic-Latin", NOMPOLICE = "Times New Roman" ;
    private static final Transliterator cyToLatinTrans = Transliterator.getInstance(CYRILLIC_TO_LATIN);
    private static RUD itype ;                                            // N° de groupe
    String forme[] = new String [5] ;
    private final boolean DEBUG = false ;
}                                                                               // DeclTableRu
