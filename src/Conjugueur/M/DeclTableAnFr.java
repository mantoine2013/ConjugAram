/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;
import Conjugueur.V.*;
import java.util.StringTokenizer;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;

public class DeclTableAnFr extends ConjTable {
    
    /**
     * Fction appelante : Conjugueur.V.JFrameConjugAnFr::JFrameConjugAnFr
     * Algorithme
     *  Si la déclinaison est régulière
     * 
     */
    @Override
    public void MAJDecl(Conjugueur.C.Conjug contrôleur) {
        if (DEBUG) { System.out.println ("Conjugueur.M.DeclTableAnFr::MAJDecl, Mot = " + Conjugueur.M.ConjugAnFr.cbAutreMotM.get(contrôleur.model.getIAutreMot()).getAttributeValue(Conjugueur.M.ConjugAnFr.ATTRIBUT) + ", Nombre "+ contrôleur.model.getNb()) ;   }
        vider();                                                 
        try {
            déclinaisonChildren = Conjugueur.M.ConjugAnFr.cbAutreMotM.get(contrôleur.model.getIAutreMot()).getChildren("déclinaison");
            if (déclinaisonChildren.isEmpty()) {                    // verbe régulier
                itype = DéterminerDéclinaison(contrôleur) ; 
                for (var cas : LAC.values()) {
                    inbLignes = AjouterLigne(cas.ic(), cas.lc(), JFrameConjugAnFr.FRANÇAIS, StyleConstants.ALIGN_RIGHT) ;
/* provisoire               if ((((cas == LAC.CSujet)||(cas == LAC.CRégim)) && (DonnéesAnFr.getNb() == LAN.Singulier))
                        ||((itype == AFD.TroiDeclParisyllabiquesNF.id())&&(cas == LAC.Accusa))
                        ||(itype == AFD.TroiDeclImparisyllabiquesMEF.id())) {        // nominatif vocatif singulier
                    for (char c : (forme[AFF.Racine.ifo()]).substring(0, forme[AFF.Racine.ifo()].length()).toCharArray()){
                        Copier (inbLignes, String.valueOf(c), DéclinaisonStyledDoc.stylera) ;
                    }                                                    // pour ts les caractères
                    if (itype == AFD.TroiDeclImparisyllabiquesMEF.id()) Suffixer (inbLignes, cas.spip(itype, DonnéesAnFr.getNb().in())) ;
                }                                                               // nominatif singulier
                else { */
                    for (char c : (forme[AFF.Racine.ifo()]).toCharArray()){
                        Copier (String.valueOf(c), DéclinaisonStyledDoc.stylera) ;
                    }                                                    // pour ts les caractères
                    if (itype == AFD.PreDecl) {
                        Suffixer (cas.spip(itype.id(), contrôleur.model.getNb())) ;                         
                    }
// provisoire               }                                           // ttes les cas
            }
            }                                                       // verbe régulier
        } catch (BadLocationException ble) {        }
//        update(jTabDecl);
    }                                                                           // updateTextPaneDecl
    /**
     * Fonction : Détermine le type de déclinaison
     * Algorithme
     *  1. Prendre le nominatif singulier (equus, ...)
     *  2. Prendre le génitif (æ, ..)
     *  3. Prendre le genre (f, m ou n)
     */    
    private AFD DéterminerDéclinaison(Conjugueur.C.Conjug contrôleur)  {
        if (DEBUG)  System.out.println ("Conjugueur.M.DeclTableAnFr::DéterminerDéclinaison, Mot = " + Conjugueur.M.ConjugAnFr.cbAutreMotM.get(contrôleur.model.getIAutreMot()).getAttributeValue(Conjugueur.M.ConjugAnFr.ATTRIBUT) + ", Nombre = " + Conjugueur.M.Conjug.N.i2N(contrôleur.model.getNb()).toString()) ;   
        StringTokenizer tokenizer = new StringTokenizer(Conjugueur.M.ConjugAnFr.cbAutreMotM.get(contrôleur.model.getIAutreMot()).getAttributeValue(Conjugueur.M.ConjugAnFr.ATTRIBUT), " ");
        forme[AFF.Racine.ifo()] = tokenizer.nextToken() ;
        forme[AFF.Genre.ifo()] = tokenizer.nextToken() ;
        if ("re".equals(forme[AFF.Racine.ifo()].substring(forme[AFF.Racine.ifo()].length() - 2))) { JFrameConjug.jLabelDéclinaison.setText("2ème déclinaison") ; return AFD.DeuDecl ; } 
        else if ("m".equals(forme[AFF.Genre.ifo()])) { JFrameConjug.jLabelDéclinaison.setText("1ère déclinaison") ; return AFD.PreDecl ; } 
        else { JFrameConjug.jLabelDéclinaison.setText("3ème déclinaison") ; return AFD.TroiDecl ; }                                                 // switch
    }                                                                           // DéterminerDéclinaison

    // Déclinaisons https://fr.wikipedia.org/wiki/D%C3%A9clinaisons_en_latin
    public enum LAC { 
        CSujet ((short)0, "Cas sujet", new String [][] {/*1è déc*/{/*sing*/"s",/*plur*/""},/*2è déc*/{/*sing*/"us",/*plur*/"ī"},/*3è déc Parisyllabiques masculins et féminins*/{/*sing*/"",/*plur*/"ēs"},/*3è déc Parisyllabiques neutres*/{/*sing*/"",/*plur*/"ēs"},/*3è déc Imparisyllabiques masculins et féminins*/{/*sing*/"",/*plur*/"ēs"}}),
        CRégim((short)1, "Cas régime", new String [][] {/*1è déc*/{/*sing*/"",/*plur*/"s"},/*2è déc*/{/*sing*/"ĕ",/*plur*/"ī"},/*3è déc Parisyllabiques masculins et féminins*/{/*sing*/"",/*plur*/"ēs"},/*3è déc Parisyllabiques neutres*/{/*sing*/"",/*plur*/"ēs"},/*3è déc Imparisyllabiques masculins et féminins*/{/*sing*/"",/*plur*/"ēs"}});
        private final int ic;
        private final String lc ;                                               // libellé de personne "1è. s.", "2è. m. s."
        private final String [][] suffixes ;                                     // préfixe et suffixe par schème
        LAC (int ic, String lc, String [][] suffixes) { this.ic = ic ;  this.lc = lc ; this.suffixes = suffixes ;}
        public int ic() { return ic; }    
        public String lc() { return lc; }
        public String spip(int it, int in) { 
            return suffixes [it][in] ; }          // renvoie le suffixe d'un groupe donné   
    }

    private static AFD itype ;                                            // N° de groupe
    public enum AFD {PreDecl((short)0), DeuDecl((short)1), TroiDecl((short)2) ;
        private final short id;
        AFD (short id) { this.id = id ;  }
        public short id() { return id; }    
    }
    public enum AFF {
        Racine((short)0), Genre((short)1);
        private final short ifo;
        private AFF(short ifo) {  this.ifo = ifo;  }
        public short ifo() {   return this.ifo;   }        
    }                                                                           // AFF
    private static LAG igenre ;                                            // N° de groupe
    public enum LAG {
        Masculin((short)0, "m"), Féminin((short)1, "f"), Neutre((short)2, "n");
        private final short ig;
        private final String lg ;
        private LAG(short ig, String lg) {  this.ig = ig; this.lg     = lg ; }
        public short ig() {   return this.ig;   }        
        public String lg() { return lg; }
        public static LAG findGenreByName(String genre) {
            LAG result = null;
            for (LAG g : LAG.values()) if (g.lg().equals(genre)) { result = g;  break;  }
            return result;
        }                                                                       //findByName
    }                                                                           // LAG
    String forme[] = new String [5] ;
    private final boolean DEBUG = false;
}
