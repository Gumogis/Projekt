package main.Shapes;

import javax.sound.sampled.Line;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.awt.*;

@XmlRootElement(name = "LineSegment")
@XmlAccessorType(XmlAccessType.FIELD)
public class LineSegment implements GraphicShapes {
    @XmlAttribute
    private int x;
    @XmlAttribute
    private int y;
    @XmlAttribute
    private int x2;
    @XmlAttribute
    private int y2;
    @XmlAttribute
    private int thickness;
    @XmlAttribute
    private String strokeColor;

    public LineSegment(int x, int y, int x2, int y2, int thickness, String strokeColor) {
        this.x = x;
        this.x2 = x2;
        this.y = y;
        this.y2 = y2;
        this.thickness = thickness;
        this.strokeColor = strokeColor;
    }

    public LineSegment() {

    }

    @Override
    public void Draw(Graphics2D g) {
    g.setStroke(new BasicStroke(thickness));
    g.setColor(Color.decode(strokeColor));
    g.drawLine(x,y,x2,y2);
    }

    public int getX1() {
        return x;
    }

    public void setX1(int x1) {
        this.x = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY1() {
        return y;
    }

    public void setY1(int y1) {
        this.y = y1;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public int getThickness() {
        return thickness;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public String getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(String strokeColor) {
        this.strokeColor = strokeColor;
    }

}
