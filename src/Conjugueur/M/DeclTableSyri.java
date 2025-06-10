/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;
import Conjugueur.V.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;

public class DeclTableSyri extends ConjTable {

    @Override
    public void MAJDecl(Conjugueur.C.Conjug contrôleur) {
        if (0 == Conjugueur.M.ConjugSyri.comboBoxAutreMot.getSize()) return ;
        if (DEBUG) { System.out.println ("Conjugueur.M.ConjugSyri::MajDecl, Mot = " + Conjugueur.M.ConjugSyri.comboBoxAutreMot.get(contrôleur.model.getIAutreMot()).getAttributeValue(Conjugueur.M.ConjugSyri.ATTRIBUT)) ;   }
        vider();
        try {
            déclinaisonChildren = Conjugueur.M.ConjugSyri.comboBoxAutreMot.get(contrôleur.model.getIAutreMot()).getChildren("déclinaison");
            for (Conjugueur.ConjugSyri.SYÉ cas : Conjugueur.ConjugSyri.SYÉ.values()) {
                inbLignes = AjouterLigne(cas.ié(), cas.lc(), JFrameConjugSyri.SYRIAC, StyleConstants.ALIGN_RIGHT) ;
                for (char c : Conjugueur.M.ConjugSyri.comboBoxAutreMot.get(contrôleur.model.getIAutreMot()).getAttributeValue(Conjugueur.M.ConjugSyri.ATTRIBUT).toCharArray()){
                   Copier (inbLignes, String.valueOf(c), DéclinaisonStyledDoc.stylera) ;
                }                                                    // pour ts les caractères
               Suffixer (inbLignes, cas.spip()) ;
//                AjouterTranslittération(inbLignes, ConjTableSyri.syriacToLatinTrans) ;
            }
        } catch (BadLocationException ble) {        }
    }                                                                           // updateTextPaneDecl
    private final boolean DEBUG = false;
    
}
