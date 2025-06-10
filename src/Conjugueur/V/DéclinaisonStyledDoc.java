/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.V;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.Map;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Element;
import javax.swing.text.ElementIterator;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class DéclinaisonStyledDoc extends DefaultStyledDocument {
    public static final StyleContext SC  = new StyleContext() ;                // réservoire de styles et de leurs ressources
    public static Style styledéclinaison = SC.getStyle(StyleContext.DEFAULT_STYLE);
    public static Style stylepe, stylepp, stylepr, stylera, stylesu ;
    public  static Font DéclinaisonStyledDocPolice ;


/**
 * Fonction : 
 * Algorithme :
 *  1. Obtenir les attributs de la police, attribuer la ligature, dériver une nouvelle police
 * @param policeDéclinaison
 * @param alignement
 */
    @SuppressWarnings("unchecked")
    public DéclinaisonStyledDoc(Font policeDéclinaison, int alignement) {
        if (DEBUG) System.out.println("Conjugueur.V.DéclinaisonStyledDoc::DéclinaisonStyledDoc, policeDéclinaison.getFamily() = " + policeDéclinaison.getFamily()+", alignement = " + alignement) ;
        Map attributes = policeDéclinaison.getAttributes(); attributes.put(TextAttribute.LIGATURES, TextAttribute.LIGATURES_ON); 
        DéclinaisonStyledDocPolice = policeDéclinaison.deriveFont(attributes);
        StyleConstants.setFontFamily(styledéclinaison, DéclinaisonStyledDocPolice.getFamily());
        StyleConstants.setFontSize(styledéclinaison, DéclinaisonStyledDocPolice.getSize());
        StyleConstants.setAlignment(styledéclinaison, alignement);
        stylepe = SC.addStyle("Personne", styledéclinaison);      StyleConstants.setFontFamily(stylepe, DéclinaisonStyledDocPolice.getFamily());  
        stylepp = SC.addStyle("Pronom personnel", styledéclinaison) ; StyleConstants.setForeground(stylepp, Color.BLUE);
        stylepr = SC.addStyle("Préfixe", styledéclinaison);            StyleConstants.setForeground(stylepr, Color.MAGENTA);
        stylera = SC.addStyle("Racine", styledéclinaison);        StyleConstants.setForeground(stylera, Color.BLACK);
        stylesu = SC.addStyle("Suffixe", styledéclinaison) ; StyleConstants.setForeground(stylesu, Color.RED);
    }
/**
 * Fctions appelantes : ConjTableM::BarrOblique, Copier
 * @param offset
 * @param str
 * @param a
 * @throws BadLocationException 
 */        
    @Override
    public void insertString (int offset, String str, AttributeSet a) throws BadLocationException {
        if (DEBUG) {System.out.println("Conjugueur.V.DéclinaisonStyledDoc::insertString, offset = "+ offset+", str = "+ str) ; }
        super.insertString(offset, str, a);
    }    
         
    static Font  police () {
        return DéclinaisonStyledDocPolice ;
    }
    
    /**
     * @param polidecl police 2è col
     */
    public void Polichange (String polidecl) {
        if (DEBUG) { System.out.println("Conjugueur.V.DéclinaisonStyledDoc::Polichange, polidecl = "+ polidecl) ; }
        StyleConstants.setFontFamily(DéclinaisonStyledDoc.stylepr, polidecl);
        StyleConstants.setFontFamily(DéclinaisonStyledDoc.stylepp, polidecl);            
        StyleConstants.setFontFamily(DéclinaisonStyledDoc.stylepr, polidecl);            
        StyleConstants.setFontFamily(DéclinaisonStyledDoc.stylera, polidecl);            
        StyleConstants.setFontFamily(DéclinaisonStyledDoc.stylesu, polidecl);            

        ElementIterator id = new ElementIterator(this);
        for (Element el = id.first(); el != null; el = id.next()) {
            if (DEBUG) { System.out.println("Conjugueur.V.DéclinaisonStyledDoc::Polichange, el.getAttributes().getAttribute(AttributeSet.NameAttribute) = "+el.getAttributes().getAttribute(AttributeSet.NameAttribute)) ; }
            if ("Pronom personnel".equals(el.getAttributes().getAttribute(AttributeSet.NameAttribute))) {
                if (DEBUG) System.out.println("StyleChanger::actionPerformed, el.getAttributes().getAttribute(AttributeSet.NameAttribute) = "+el.getAttributes().getAttribute(AttributeSet.NameAttribute)) ;
                this.setCharacterAttributes(0, el.getEndOffset(), DéclinaisonStyledDoc.stylepr, true);                
            }
            else if ("Racine".equals(el.getAttributes().getAttribute(AttributeSet.NameAttribute))) {
                if (DEBUG) System.out.println("StyleChanger::actionPerformed, el.getAttributes().getAttribute(AttributeSet.NameAttribute) = "+el.getAttributes().getAttribute(AttributeSet.NameAttribute)) ;
                this.setCharacterAttributes(0, el.getEndOffset(), DéclinaisonStyledDoc.stylera, true);                
            }
            else if ("Préfixe".equals(el.getAttributes().getAttribute(AttributeSet.NameAttribute))) {
                if (DEBUG) System.out.println("StyleChanger::actionPerformed,  el.getAttributes().getAttribute(AttributeSet.NameAttribute) = "+el.getAttributes().getAttribute(AttributeSet.NameAttribute)) ;
                this.setCharacterAttributes(0, el.getEndOffset(), DéclinaisonStyledDoc.stylepr, true);                
            }
            else if ("Suffixe".equals(el.getAttributes().getAttribute(AttributeSet.NameAttribute))) {
                this.setCharacterAttributes(0, el.getEndOffset(), DéclinaisonStyledDoc.stylesu, true);                
            }

        }

/*        StyleConstants.setFontFamily(DéclinaisonStyledDoc.styledéclinaison, polidecl);            


        DéclinaisonStyledDocPolice = new Font (polidecl, Font.PLAIN, TAILLEPOLICE) ;
        StyleConstants.setFontFamily(styledéclinaison, DéclinaisonStyledDocPolice.getFamily());
        StyleConstants.setFontFamily(stylepp, DéclinaisonStyledDocPolice.getFamily());
        this.styleChanged(styledéclinaison);

*/
    }

    private final boolean DEBUG = false ;
}                                                                                                       // DéclinaisonStyledDoc