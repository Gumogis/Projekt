package main.Lists;

import main.ShapeDetails.EllipseDetails;
import main.ShapeDetails.LineSegmentDetails;
import main.ShapeDetails.RectangleDetails;
import main.Shapes.Ellipse;
import main.Shapes.LineSegment;
import main.Shapes.Rectangle;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class DetailsList {

    private List<AbstractTableModel> detaily = new ArrayList<>();

    public DetailsList(ShapeList list) {
        for(int i = 0; i < list.getList().size();i++){
            switch (list.getList().get(i).getClass().getSimpleName()){
                case "Ellipse":
                    detaily.add(new EllipseDetails((Ellipse) list.getList().get(i)));
                    break;
                case "LineSegment":
                    detaily.add(new LineSegmentDetails((LineSegment) list.getList().get(i)));
                    break;
                case "Rectangle":
                    detaily.add(new RectangleDetails((Rectangle) list.getList().get(i)));
                    break;
                default:
                    throw new IllegalArgumentException("Unknown shape");
            }
        }
        System.out.println(detaily.getFirst().getValueAt(1,1));
    }

    public List<AbstractTableModel> getDetaily() {
        return detaily;
    }

    public void setDetaily(List<AbstractTableModel> detaily) {
        this.detaily = detaily;
    }
}
