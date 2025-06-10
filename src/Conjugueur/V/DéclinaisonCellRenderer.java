package Conjugueur.V;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.SwingConstants;

/**
 *
 * @author MichelANTOINE@hotmail.com
 */
    public class DéclinaisonCellRenderer extends DefaultTableCellRenderer {
        Font policeDéclinaison ;
        private JTextPane textpane   ;
        private ComponentOrientation co ;

    public DéclinaisonCellRenderer(Font PoliceDéclinaison, ComponentOrientation or) {
        if (DEBUG) System.out.println("DéclinaisonCellRenderer::DéclinaisonCellRenderer, PoliceDéclinaison = "+PoliceDéclinaison+", or.isLeftToRight() = " + or.isLeftToRight()) ;
            this.co = or ; this.policeDéclinaison = PoliceDéclinaison ;
    }                                                                         // DéclinaisonCellRenderer

    /**
     * Fction appelante : Conjugueur.V.JTableau.prepareRenderer
     * @param table
     * @param value
     * @param isSelected
     * @param hasFocus
     * @param row
     * @param column
     * @return 
     */
      @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (DEBUG) System.out.println("DéclinaisonCellRenderer::getTableCellRendererComponent, table.getName() = " + table.getName()) ;
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        setFont(policeDéclinaison); 
        setComponentOrientation(co);
        textpane = new JTextPane((DefaultStyledDocument)value) ;
        textpane.applyComponentOrientation(co) ;
        setVerticalAlignment(SwingConstants.TOP);
        setLayout(new BorderLayout()); // set layout on parent
        add(textpane, BorderLayout.CENTER);        return textpane ;
    }                                                                           // getTableCellRendererComponent  

    private static final boolean DEBUG = false;
}                                                                               // DéclinaisonCellRenderer
