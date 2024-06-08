package main.Lists;

import main.Shapes.Ellipse;
import main.Shapes.GraphicShapes;
import main.Shapes.LineSegment;
import main.Shapes.Rectangle;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "svg")
@XmlSeeAlso({Rectangle.class, Ellipse.class, LineSegment.class})
public class ShapeList {
    private List<GraphicShapes> list = new ArrayList<>();
    @XmlAttribute
    private String viewBox = "0 0 1000 1000";


    public ShapeList() {
        list.add(new Ellipse(10,50,150,200,10,"#FF5733"));
        list.add(new Ellipse(10,50,150,200,10,"#FF5733"));
        list.add(new LineSegment(55,100,500,100,10,"#FF5733"));
        list.add(new Rectangle(300,100,250,50,10,"#FF5733"));
    }

    @XmlElementWrapper(name = "g")
    @XmlAnyElement(lax = true)
    public List<GraphicShapes> getList(){
        return list;
    }
    public void addValueToList(GraphicShapes g) {
        list.add(g);
    }
    public void setPaintList(ShapeList list) {
        this.list = list.getList();
    }

    public void setValueToList(GraphicShapes g, int column){
        list.set(column, g);
    }
}
