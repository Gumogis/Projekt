package main;

import main.CustomPanels.LeftPanel;
import main.Lists.DetailsList;
import main.Lists.ShapeList;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final JPanel multiPanel;

    public MainFrame() {

        setTitle("PRO1");
        setVisible(true);
        setBackground(Color.black);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        multiPanel = new JPanel();
        add(multiPanel);


        ShapeList shapeList = new ShapeList();
        DetailsList detailsList = new DetailsList(shapeList);

        JPanel leftPanel = new LeftPanel(shapeList, detailsList);

        multiPanel.setLayout(new BorderLayout());
        multiPanel.add(leftPanel);


    }
}
