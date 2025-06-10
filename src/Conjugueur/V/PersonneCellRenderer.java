package Conjugueur.V;

import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author MichelANTOINE@hotmail.com
 */
public class PersonneCellRenderer extends DefaultTableCellRenderer {
    private final Font policePersonne ;
    
    public PersonneCellRenderer(Font PolicePersonne){
        this.policePersonne = PolicePersonne ; 
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        setFont(policePersonne);
        table.setRowHeight(row, policePersonne.getSize() + 13);
       return this;
    }        
}