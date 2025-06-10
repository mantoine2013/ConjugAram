/**
 * @author MichelANTOINE@hotmail.com
 */
package Conjugueur.V;
import Conjugueur.M.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumnModel;


public class JTableau extends JTable {

    /**
     * Fction appelante : JFrameConjug
     * @param conjTableM 
     */
    public JTableau (ConjTable conjTableM) {
        super();
        if (DEBUG) System.out.println("Conjugueur.Vue.JTableau::JTableau") ;
        this.setModel(conjTableM) ;
    }                                                                           // JTableau    
    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
           Component component = super.prepareRenderer(renderer, row, column);
           int rendererWidth = component.getPreferredSize().width;
           TableColumn tableColumn = getColumnModel().getColumn(column);
           tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
           return component;
        }
    public void adjustColumnSizes(JTable table, int column, int margin) {
        DefaultTableColumnModel colModel = (DefaultTableColumnModel) table.getColumnModel();
        TableColumn col = colModel.getColumn(column);
        int width;

        TableCellRenderer renderer = col.getHeaderRenderer();
        if (renderer == null) {
            renderer = table.getTableHeader().getDefaultRenderer();
        }
        Component comp = renderer.getTableCellRendererComponent(table, col.getHeaderValue(), false, false, 0, 0);
        width = comp.getPreferredSize().width;

        for (int r = 0; r < table.getRowCount(); r++) {
            renderer = table.getCellRenderer(r, column);
            comp = renderer.getTableCellRendererComponent(table, table.getValueAt(r, column), false, false, r, column);
            int currentWidth = comp.getPreferredSize().width;
            width = Math.max(width, currentWidth);
        }

        width += 2 * margin;

        col.setPreferredWidth(width);
        col.setWidth(width);
    }
    
    // Calculates the given column's width based on header name
    private int getColumnHeaderWidth(int column) {

        TableColumn tableColumn = this.getColumnModel().getColumn(column);
        Object value = tableColumn.getHeaderValue();
        TableCellRenderer renderer = this.getColumnModel().getColumn(column).getCellRenderer();

        if (renderer == null)
            renderer = this.getTableHeader().getDefaultRenderer();

        Component c = renderer.getTableCellRendererComponent(this, value, false, false, -1, column);
        return c.getPreferredSize().width;
    }
    
    // Calculates the width based on the widest cell renderer for the given
    // column.
    private int getColumnDataWidth(int column) {
        int preferredWidth = 0;
        for (int row = 0 ; row < this.getRowCount(); row++)
            preferredWidth = Math.max(preferredWidth,  getCellDataWidth(row, column));
        return preferredWidth;
    }

    
        // Gets the preferred width for the specified cell
    private int getCellDataWidth(int row, int column) {
        // Invoke the renderer for the cell to calculate the preferred width
        TableCellRenderer cellRenderer = this.getCellRenderer(row, column);
        Object value = this.getValueAt(row, column);
        Component c = cellRenderer.getTableCellRendererComponent(this, value, false, false, row, column);
        return c.getPreferredSize().width + this.getIntercellSpacing().width ;
    }
    

    public void updateRowHeights() {
       if (DEBUG) System.out.println ("JTableau::updateRowHeights") ;
        for (int row = 0; row < this.getRowCount(); row++)  {
            int rowHeight = this.getRowHeight();

            for (int column = 0; column < this.getColumnCount(); column++)        {
                Component comp = this.prepareRenderer(this.getCellRenderer(row, column), row, column);
                rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
            }
//       if (DEBUG) System.out.println ("ConjugSyri::updateRowHeights, rowHeight = "+rowHeight) ;
            this.setRowHeight(row, rowHeight);
        }                                                                       // pour ttes les rangées
    }                                                                           // updateRowHeights
        
        public void resizeColumnWidth() {
        final TableColumnModel columnModel = this.getColumnModel();
        for (int column = 0; column < this.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < this.getRowCount(); row++) {
                TableCellRenderer renderer = this.getCellRenderer(row, column);
                Component comp = this.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width +1 , width);
            }                                                                   //pour ttes les rangées
            if(width > 300)  width=300;
            columnModel.getColumn(column).setPreferredWidth(width);
        }                                                                       // pour ttes les col
    }                                                                           // resizeColumnWidth
    public static final boolean DEBUG = false;
}                                                                               // JTableau
