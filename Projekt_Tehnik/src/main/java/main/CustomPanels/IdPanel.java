package main.CustomPanels;

import main.Lists.ShapeList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class IdPanel extends JTable {

    private DefaultTableModel table;

    public IdPanel(ShapeList list) {

        table = new DefaultTableModel(list.getList().size(),2);

        for(int i = 0 ; i < list.getList().size() ;i++){
            table.setValueAt(i,i,0);
            table.setValueAt(list.getList().get(i).getClass().getSimpleName(),i,1);
        }
        setModel(table);
    }

    public void setPanel(ShapeList list) {

        table.setRowCount(list.getList().size());
        table.setColumnCount(2);
        for(int i = 0 ; i < list.getList().size() ;i++){
            table.setValueAt(i,i,0);
            table.setValueAt(list.getList().get(i).getClass().getSimpleName(),i,1);
        }
        setModel(table);
    }

    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
