package main.Shapes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.awt.*;

@XmlRootElement(name = "Rectangle")
@XmlAccessorType(XmlAccessType.FIELD)
public class Rectangle implements GraphicShapes {
    @XmlAttribute
    private int x;
    @XmlAttribute
    private int y;
    @XmlAttribute
    private int width;
    @XmlAttribute
    private int height;
    @XmlAttribute(name = "stroke-width")
    private int thickness;
    @XmlAttribute(name = "stroke-color")
    private String strokeColor;

    public Rectangle(int x, int y, int height, int width, int thickness, String strokeColor) {
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
        this.thickness = thickness;
        this.strokeColor = strokeColor;
    }

    public Rectangle() {

    }

    @Override
    public void Draw(Graphics2D g) {
    g.setStroke(new BasicStroke(thickness));
    g.setColor(Color.decode(strokeColor));
    g.drawRect(x,y,width,height);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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
