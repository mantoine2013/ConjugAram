/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;

import static Conjugueur.M.ConjTable.inbLignes;
import static Conjugueur.M.ConjTableAraM.araMToLatinTrans;
import Conjugueur.V.JFrameConjugLat;
import Conjugueur.V.DéclinaisonStyledDoc;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;

public class DeclTableAraM extends ConjTable {
    
    /**
     * 
     * Fction appelante : Conjugueur.Vue.JFrameConjugAraM
     */
    @Override
    public void MAJDecl(Conjugueur.C.Conjug contrôleur) {
        if (DEBUG) { System.out.println ("Conjugueur.M.DeclTableAraM::MAJDecl, Mot = "+Conjugueur.M.ConjugAraM.cbAutreMotM.get(contrôleur.model.getIAutreMot()).getAttributeValue(Conjugueur.M.ConjugAraM.AAM)) ;   }
        vider();                                                 
        déclinaisonChildren = Conjugueur.M.ConjugAraM.cbAutreMotM.get(contrôleur.model.getIAutreMot()).getChildren("déclinaison");//        adjustJTableRowSizes(ConjugGrecA.jTabDecl);
        if (déclinaisonChildren.isEmpty()) {                    // mot régulier
                for (var cas : AMC.values()) {                             // pour ttes les personnes
                    inbLignes = AjouterLigne(cas.ic(), cas.lc(), JFrameConjugLat.LATIN, StyleConstants.ALIGN_RIGHT) ;
                    for (char c : Conjugueur.M.ConjugAraM.cbAutreMotM.get(contrôleur.model.getIAutreMot()).getAttributeValue(Conjugueur.M.ConjugAraM.AAM).toCharArray()) {
                        try {
                            Copier (String.valueOf(c), DéclinaisonStyledDoc.stylera) ;
                        } catch (BadLocationException ex) {
                            Logger.getLogger(DeclTableAraM.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }                                                    // pour ts les caractères
                    try {
                        AjouterTranslittération(araMToLatinTrans) ;
                    } catch (BadLocationException ex) {
                        Logger.getLogger(DeclTableAraM.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }                                           // ttes les déclinaisons
        }                                                       // mot régulier
    }                                                                           // MAJDecl
    public enum AMC { 
        Nomina (0, "Nominatif", new String [][][] {/*masc*/{/*1è déc*/{/*sing*/"a",/*plur*/"æ"},/*2è déc*/{/*sing*/"us",/*plur*/"ī"},/*2è déc er*/{/*sing*/"",/*plur*/"ī"},/*3è déc Parisyllabiques masculins et féminins*/{/*sing*/"is",/*plur*/"ēs"},/*3è déc Parisyllabiques neutres*/{/*sing*/"",/*plur*/"ēs"},/*3è déc Imparisyllabiques masculins et féminins*/{/*sing*/"",/*plur*/"ēs"},/*3è déc Imparisyllabiques neutres*/{/*sing*/"",/*plur*/"ēs"}, /*4è déc */{/*sing*/"us",/*plur*/"us"}}, /*fem*/{/*1è déc*/{/*sing*/"um",/*plur*/"æ"},/*2è déc*/{/*sing*/"um",/*plur*/"æ"},{},{},{},{},{},/*4è déc*/{/*sing*/"us",/*plur*/"us"}}, /*neu*/{/*1è déc*/{/*sing*/"um",/*plur*/"æ"},/*2è déc*/{/*sing*/"um",/*plur*/"a"},/*3è déc Imparisyllabiques masculins et féminins*/{/*sing*/"",/*plur*/"ēs"},/*3è déc Imparisyllabiques neutres*/{/*sing*/"",/*plur*/"ēs"}, {}, {}, {}, /*4è déc */{/*sing*/"u",/*plur*/"ua"}}}),
        Géni(1, "Vocatif", new String [][][] {/*masc*/{/*1è déc*/{/*sing*/"a",/*plur*/"æ"},/*2è déc*/{/*sing*/"ĕ",/*plur*/"ī"},/*2è déc er*/{/*sing*/"",/*plur*/"ī"},/*3è déc Parisyllabiques masculins et féminins*/{/*sing*/"is",/*plur*/"ēs"},/*3è déc Parisyllabiques neutres*/{/*sing*/"",/*plur*/"ēs"},/*3è déc Imparisyllabiques masculins et féminins*/{/*sing*/"",/*plur*/"ēs"},/*3è déc Imparisyllabiques neutres*/{/*sing*/"",/*plur*/"ēs"}, /*4è déc */{/*sing*/"us",/*plur*/"us"}}, /*fem*/{/*1è déc*/{/*sing*/"um",/*plur*/"æ"},/*2è déc*/{/*sing*/"um",/*plur*/"æ"},{},{},{},{},{},/*4è déc*/{/*sing*/"us",/*plur*/"us"}}, /*neu*/{/*1è déc*/{/*sing*/"um",/*plur*/"æ"},/*2è déc*/{/*sing*/"um",/*plur*/"a"},/*3è déc Imparisyllabiques masculins et féminins*/{/*sing*/"",/*plur*/"ēs"},/*3è déc Imparisyllabiques neutres*/{/*sing*/"",/*plur*/"ēs"}, {}, {}, {}, /*4è déc */{/*sing*/"u",/*plur*/"ua"}}}),
        Accusa(3, "Génitif", new String [][][] {/*masc*/{/*1è déc*/{/*sing*/"æ",/*plur*/"ārum"},/*2è déc*/{/*sing*/"ī",/*plur*/"ōrum"},/*2è déc er*/{/*sing*/"i",/*plur*/"orum"},/*3è déc Parisyllabiques masculins et féminins*/{/*sing*/"is",/*plur*/"ium"},/*3è déc Parisyllabiques neutres*/{/*sing*/"is",/*plur*/"ēs"},/*3è déc Imparisyllabiques masculins et féminins*/{/*sing*/"is",/*plur*/"ēs"},/*3è déc Imparisyllabiques neutres*/{/*sing*/"",/*plur*/"ēs"}, /*4è déc */{/*sing*/"us",/*plur*/"uum"}}, /*fem*/{/*1è déc*/{/*sing*/"um",/*plur*/"æ"},/*2è déc*/{/*sing*/"um",/*plur*/"æ"},{},{},{},{},{},/*4è déc*/{/*sing*/"us",/*plur*/"uum"}}, /*neu*/{/*1è déc*/{/*sing*/"ī",/*plur*/"æ"},/*2è déc*/{/*sing*/"i",/*plur*/"orum"},/*3è déc Imparisyllabiques masculins et féminins*/{/*sing*/"",/*plur*/"ēs"},/*3è déc Imparisyllabiques neutres*/{/*sing*/"",/*plur*/"ēs"}, {}, {}, {}, /*4è déc */{/*sing*/"us",/*plur*/"uum"}}});
        private final int ic;
        private final String lc ;                                               // libellé de personne "1è. s.", "2è. m. s."
        private final String [][][] suffixes ;                                     // préfixe et suffixe par schème
        AMC (int ic, String lc, String [][][] suffixes) { this.ic = ic ;  this.lc = lc ; this.suffixes = suffixes ;}
        public int ic() { return ic; }    
        public String lc() { return lc; }
        public String spip(int ig, int id, int in) { 
            return suffixes [ig][id][in] ; }          // renvoie le suffixe d'un groupe donné   
    }
    private static final boolean DEBUG = false ;    
}
