package main.CustomPanels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SelectionTable extends JTable {
    public SelectionTable() {
        setModel(new DefaultTableModel(2,2));
        setValueAt("thickness",0,0);
        setValueAt("color",1,0);
        setValueAt("10",0,1);
        setValueAt("#FF5733",1,1);
    }

    public boolean isCellEditable(int row, int column) {
        if(column == 0){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        super.setValueAt(aValue, row, column);
    }
}
