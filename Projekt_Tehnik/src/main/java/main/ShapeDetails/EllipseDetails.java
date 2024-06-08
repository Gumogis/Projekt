package main.ShapeDetails;

import main.Shapes.Ellipse;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Field;

import static javax.swing.JOptionPane.showMessageDialog;

public class EllipseDetails extends AbstractTableModel {
    private Ellipse details;


    public EllipseDetails(Ellipse details){
        this.details = details;
    }

    @Override
    public int getRowCount() {
        return 6;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Field[] fields = Ellipse.class.getDeclaredFields();
        if(columnIndex == 0){
            return fields[rowIndex].getName();
        }
        else {
            switch (rowIndex){
                case 0:
                    return details.getX();
                case 1:
                    return details.getY();
                case 2:
                    return details.getWidth();
                case 3:
                    return details.getHeight();
                case 4:
                    return details.getThickness();
                case 5:
                    return details.getStrokeColor();
                case 10:
                    return details;
                default:
                    throw new IllegalArgumentException("Neplatný index řádku:" + rowIndex);
            }
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(columnIndex == 1){
            try {
            switch (rowIndex){
                case 0:
                    details.setX(Integer.parseInt(aValue.toString()));
                    break;
                case 1:
                    details.setY(Integer.parseInt(aValue.toString()));
                    break;
                case 2:
                    details.setWidth(Integer.parseInt(aValue.toString()));
                    break;
                case 3:
                    details.setHeight(Integer.parseInt(aValue.toString()));
                    break;
                case 4:
                    details.setThickness(Integer.parseInt(aValue.toString()));
                    break;
                case 5:
                    details.setStrokeColor(aValue.toString());
                    break;
                default:
                    throw new IllegalArgumentException("Neplatný index řádku:" + rowIndex);
                }
            }
            catch (Exception e) {
                showMessageDialog(null, "Špatně zadaný vstup!");
            }
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(columnIndex == 1){
            return true;
        }
        return false;
    }
}
