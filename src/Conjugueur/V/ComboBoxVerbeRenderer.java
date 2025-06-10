/**
 * @author MichelANTOINE@hotmail.com
 * Fonction : Assure le rendu de la liste déroulante des entrées et leur groupe
 */
package Conjugueur.V;

import java.awt.ComponentOrientation;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public  class ComboBoxVerbeRenderer extends JLabel implements ListCellRenderer  {
    
    public ComboBoxVerbeRenderer(Font FontUtilisateur, ComponentOrientation or, String elerac) {
        if (DEBUG) { System.out.println("ComboBoxVerbeRenderer::ComboBoxVerbeRenderer, FontUtilisateur.getFontName() = "+ FontUtilisateur.getFontName() + ", elerac = " + elerac) ; }
            setOpaque(true);
            setComponentOrientation(or);
            setFont(FontUtilisateur); 
            this.elerac = elerac ;
    }

    @Override
    public ComboBoxVerbeRenderer getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if(null == value ) return this ;
        if (DEBUG) { System.out.println("ComboBoxVerbeRenderer::getListCellRendererComponent, ((org.jdom2.Element)value).getAttributeValue("+elerac+") = "+ ((org.jdom2.Element)value).getAttributeValue(elerac)) ; }
        setText(((org.jdom2.Element)value).getAttributeValue(elerac));
        return this;
    }                                                                       // ComboBoxVerbeRenderer 
    
          
    public void Polichange (Font Police) {
        if (DEBUG) { System.out.println("ComboBoxVerbeModel::Polichange, Police = "+ Police) ; }  
            setFont(Police); 
    }
    private final String elerac ;

    private static final boolean DEBUG = false ;
}                                                                           // ComboBoxVerbeRenderer