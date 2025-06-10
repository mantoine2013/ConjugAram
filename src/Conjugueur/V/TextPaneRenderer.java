package Conjugueur.V;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author MichelANTOINE@hotmail.com
 */
public class TextPaneRenderer extends JTextPane implements TableCellRenderer {
    Font police ;

    public TextPaneRenderer(Font FontUtilisateur, ComponentOrientation or) {
        this.police = FontUtilisateur ; 
        setEditable(false);
        setComponentOrientation(or);
        setFont(FontUtilisateur); 
    }                                                                       // TextPaneRenderer                          
  
      @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                  boolean isSelected, boolean hasFocus,
                                  int row, int column)      {
//        if (DEBUG) System.out.println("TextPaneRenderer::getTableCellRendererComponent, row = " + row +", column = "+column) ;
        setText((String) value) ;
        table.setRowHeight(row, police.getSize() + 13);
        return this;
    }                                                                         // getTableCellRendererComponent
    public static final boolean DEBUG = true;
}                                                                            // TextPaneRenderer 