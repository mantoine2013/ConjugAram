/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.M;

import static Conjugueur.M.ConjTable.inbLignes;
import static Conjugueur.M.ConjTableHebib.hebToLatinTrans;
import Conjugueur.V.JFrameConjugLat;
import Conjugueur.V.DéclinaisonStyledDoc;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;

public class DeclTableHebib extends ConjTable {
    
    /**
     * 
     * @throws BadLocationException
     * Fction appelante : Conjugueur.Vue.JFrameConjugHebib
     */
    @Override
    public void MAJDecl(Conjugueur.C.Conjug contrôleur) {
        if (DEBUG) { System.out.println ("Conjugueur.M.DeclTableHebib::MAJDecl, Mot = "+Conjugueur.M.ConjugHebib.comboBoxAutreMotM.get(contrôleur.model.getIAutreMot()).getAttributeValue(Conjugueur.M.ConjugHebib.ATTRIBUTMOT)) ;   }
        vider();                                                 
        déclinaisonChildren = Conjugueur.M.ConjugHebib.comboBoxAutreMotM.get(contrôleur.model.getIAutreMot()).getChildren("déclinaison");//        adjustJTableRowSizes(ConjugGrecA.jTabDecl);
        if (déclinaisonChildren.isEmpty()) {                    // mot régulier
                for (ConjTableHebib.HBN decl : ConjTableHebib.HBN.values()) {                             // pour ttes les personnes
                    inbLignes = AjouterLigne(decl.in(), decl.ln(), JFrameConjugLat.LATIN, StyleConstants.ALIGN_RIGHT) ;
                    for (char c : Conjugueur.M.ConjugHebib.comboBoxAutreMotM.get(contrôleur.model.getIAutreMot()).getAttributeValue(Conjugueur.M.ConjugHebib.ATTRIBUTMOT).toCharArray()) {
                        try {
                            Copier (String.valueOf(c), DéclinaisonStyledDoc.stylera) ;
                        } catch (BadLocationException ex) {
                            Logger.getLogger(DeclTableHebib.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }                                                    // pour ts les caractères
                    try {
                        Suffixer(decl.term()) ;
                    } catch (BadLocationException ex) {
                        Logger.getLogger(DeclTableHebib.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        AjouterTranslittération(hebToLatinTrans) ;
                    } catch (BadLocationException ex) {
                        Logger.getLogger(DeclTableHebib.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }                                           // ttes les déclinaisons
        }                                                       // mot régulier
    }                                                                           // MAJDecl
    private static final boolean DEBUG = false;    
}
