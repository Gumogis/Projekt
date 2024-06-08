package main.CustomPanels;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class DetailsPanel extends JTable {

    private DefaultTableModel table;

    public DetailsPanel(List<AbstractTableModel> detaily) {
        table = new DefaultTableModel(0,0);
        setModel(table);
    }

    public void setPanel(int x, List<AbstractTableModel> detaily) {
        table.setRowCount(detaily.get(x).getRowCount());
        table.setColumnCount(detaily.get(x).getColumnCount());
        setModel(table);
        for(int i = 0; i < detaily.get(x).getRowCount();i++) {
            setValueAt(detaily.get(x).getValueAt(i,0),i,0);
            setValueAt(detaily.get(x).getValueAt(i,1),i,1);
        }
    }

    public void nullPanel(){
        table.setRowCount(0);
        setModel(table);
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
