package main.CustomPanels;

import main.Shapes.GraphicShapes;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Canvas extends JPanel {
    private List<GraphicShapes> list;


    public Canvas(List<GraphicShapes> list) {
        this.list = list;
        setBackground(Color.white);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(GraphicShapes shape : list){
            shape.Draw((Graphics2D) g);
        }
    }
}
