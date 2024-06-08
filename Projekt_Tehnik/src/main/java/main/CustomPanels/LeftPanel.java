package main.CustomPanels;

import main.Lists.DetailsList;
import main.Lists.ShapeList;
import main.Shapes.GraphicShapes;
import main.Shapes.LineSegment;
import main.Utility.JsonUtils;
import main.Utility.XmlUtils;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LeftPanel extends JPanel {
    private int selectedRow;
    private IdPanel idPanel;
    private DetailsPanel detailsPanel;
    private SelectionTable selectionTable;
    private XmlPanel xmlPanel;
    private DetailsList detaily;
    private ShapeList list;
    private JScrollPane scrollPane;
    private JPanel canvasPanel;
    private JPanel tablePanel;
    private JPanel topPanel;
    private JPanel rightPanel;
    private JButton segmentBtn;
    private JButton importBtn;
    private JButton exportBtn;
    private Canvas canvas;
    private String file;
    private Scanner sc;
    private JComboBox<String> xmlJsonSwitch;
    private String[] pickOne = {"XML", "JSON"};
    private int selectedIndex;
    private boolean listenToMeXml = true;
    private boolean makeBrokenSegment = false;
    private boolean makeBrokenSecondSegment = false;
    private boolean listenToMeClick = false;
    private Point firstPoint;
    private Point secondPoint;
    private Point thirdPoint;
    private boolean listenToMePanel = true;
    public LeftPanel(ShapeList list1, DetailsList detaily1 ) {

        this.list = list1;
        this.detaily = detaily1;

        tablePanel = new JPanel();
        tablePanel.setLayout(new GridLayout(2,1));
        tablePanel.setBorder(BorderFactory.createLineBorder(Color.black));

        idPanel = new IdPanel(list);
        tablePanel.add(idPanel);

        detailsPanel = new DetailsPanel(detaily.getDetaily());
        tablePanel.add(detailsPanel);

        setLayout(new BorderLayout());
        add(tablePanel,BorderLayout.WEST);

        canvasPanel = new JPanel();
        canvasPanel.setLayout(new GridLayout(0,1));
        add(canvasPanel,BorderLayout.CENTER);

        canvas = new Canvas(list.getList());
        canvasPanel.add(canvas, BorderLayout.CENTER);

        topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        add(topPanel, BorderLayout.NORTH);
        topPanel.setBackground(Color.WHITE);

        rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(200,100));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        rightPanel.setLayout(new FlowLayout(FlowLayout.CENTER,200,0));
        add(rightPanel,BorderLayout.EAST);
        rightPanel.setBackground(Color.WHITE);

        segmentBtn = new JButton("Broken Segment");
        segmentBtn.setPreferredSize(new Dimension(200,100));
        rightPanel.add(segmentBtn);

        selectionTable = new SelectionTable();
        rightPanel.add(selectionTable);

        /*Změna Xml*/
        xmlPanel = new XmlPanel(list);
        xmlPanel.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(listenToMeXml) {
                    System.out.println("vlozeny data");
                    list.setPaintList(XmlUtils.getImage(xmlPanel.getText()));
                    detaily = new DetailsList(list);
                    if(idPanel.getSelectedRow()!=-1 && list.getList().size() == idPanel.getRowCount()) {
                        detailsPanel.setPanel(idPanel.getSelectedRow(), detaily.getDetaily());
                    } else {
                        detailsPanel.nullPanel();
                    }
                    if(list.getList().size()!=idPanel.getRowCount()) {
                        listenToMePanel = false;
                        idPanel.clearSelection();
                        idPanel.setPanel(list);
                        listenToMePanel = true;
                    }
                    canvasPanel.remove(0);
                    canvas = new Canvas(list.getList());
                    canvasPanel.add(canvas, BorderLayout.CENTER);
                    revalidate();
                    repaint();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(listenToMeXml) {
                    System.out.println("odstranena data");
                    list.setPaintList(XmlUtils.getImage(xmlPanel.getText()));
                    detaily = new DetailsList(list);
                    if(idPanel.getSelectedRow()!=-1 && list.getList().size() == idPanel.getRowCount()) {
                        detailsPanel.setPanel(idPanel.getSelectedRow(), detaily.getDetaily());
                    } else {
                        detailsPanel.nullPanel();
                    }
                    if(list.getList().size()!=idPanel.getRowCount()) {
                        listenToMePanel = false;
                        idPanel.clearSelection();
                        idPanel.setPanel(list);
                        listenToMePanel = true;
                    }
                    canvasPanel.remove(0);
                    canvas = new Canvas(list.getList());
                    canvasPanel.add(canvas, BorderLayout.CENTER);
                    revalidate();
                    repaint();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if(listenToMeXml) {
                    System.out.println("aktualizovany data");
                    list.setPaintList(XmlUtils.getImage(xmlPanel.getText()));
                    detaily = new DetailsList(list);
                    if(idPanel.getSelectedRow()!=-1 && list.getList().size() == idPanel.getRowCount()) {
                        detailsPanel.setPanel(idPanel.getSelectedRow(), detaily.getDetaily());
                    } else {
                        detailsPanel.nullPanel();
                    }
                    if(list.getList().size()!=idPanel.getRowCount()) {
                        listenToMePanel = false;
                        idPanel.clearSelection();
                        idPanel.setPanel(list);
                        listenToMePanel = true;
                    }
                    canvasPanel.remove(0);
                    canvas = new Canvas(list.getList());
                    canvasPanel.add(canvas, BorderLayout.CENTER);
                    revalidate();
                    repaint();
                }
            }
        });

        scrollPane = new JScrollPane( xmlPanel);
        scrollPane.setPreferredSize(new Dimension(200,200));
        add(scrollPane,BorderLayout.SOUTH);

        /*Import Buťon*/
        importBtn = new JButton("Import");
        topPanel.add(importBtn);
        importBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listenToMeXml = false;
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("XML","xml");
                fileChooser.setFileFilter(filter);
                int returnValue = fileChooser.showOpenDialog(null);
                if(returnValue == JFileChooser.APPROVE_OPTION){
                    File selectedFile = fileChooser.getSelectedFile();
                    file = "";
                    try {
                        sc = new Scanner(selectedFile);
                        while (sc.hasNextLine()) {
                            file += sc.nextLine();
                        }
                        list.setPaintList(XmlUtils.getImage(file));
                        detaily = new DetailsList(list);
                        detailsPanel.nullPanel();
                        if(list.getList().size()!=idPanel.getRowCount()) {
                            listenToMePanel = false;
                            idPanel.clearSelection();
                            idPanel.setPanel(list);
                            listenToMePanel = true;
                        }
                        xmlPanel.setData(list);
                        canvasPanel.remove(0);
                        canvas = new Canvas(list.getList());
                        canvasPanel.add(canvas, BorderLayout.CENTER);
                        revalidate();
                        repaint();
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                listenToMeXml = true;
            }
        });

        /*Export Buťon*/
        exportBtn = new JButton("Export");
        topPanel.add(exportBtn);
        exportBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("XML OR JSON", "xml", "json");
                fileChooser.setFileFilter(filter);
                int userSelection = fileChooser.showSaveDialog(canvas);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    String fileName = fileToSave.getName();
                    String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

                    if (!extension.equalsIgnoreCase("xml") && !extension.equalsIgnoreCase("json")) {
                        JOptionPane.showMessageDialog(null, "Špatný formát!");
                        return;
                    }
                    try {
                        FileWriter myWriter = new FileWriter(fileToSave.getAbsolutePath());
                        if (selectedIndex == 0) {
                            myWriter.write(XmlUtils.getXml(list));
                        } else {
                            myWriter.write(JsonUtils.getJson(list));
                        }
                        myWriter.close();
                        JOptionPane.showMessageDialog(null, "Uloženo!");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        /*Zapnutí Lomené čáry*/
        segmentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(makeBrokenSegment == false && makeBrokenSecondSegment == false){
                    makeBrokenSegment = true;
                    makeBrokenSecondSegment = true;
                    listenToMeClick = true;
                    segmentBtn.setText("Click to cancel");
                } else {
                    makeBrokenSegment = false;
                    makeBrokenSecondSegment = false;
                    listenToMeClick = false;
                    segmentBtn.setText("Broken Segment");
                }
            }
        });

        xmlJsonSwitch = new JComboBox<String>(pickOne);
        topPanel.add(xmlJsonSwitch);
        xmlJsonSwitch.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                selectedIndex = xmlJsonSwitch.getSelectedIndex();
            }
        });

        /*Výběr objektu v Id*/
        idPanel.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(listenToMePanel) {
                    selectedRow = idPanel.getSelectedRow();
                    switch (list.getList().get(selectedRow).getClass().getSimpleName()) {
                        case "Rectangle", "Ellipse", "LineSegment":
                            detailsPanel.setPanel(selectedRow, detaily.getDetaily());
                            break;
                    }
                }
            }
        });

        /*Změna Detailů*/
        DefaultCellEditor dce = (DefaultCellEditor)detailsPanel.getDefaultEditor(Object.class);
        dce.addCellEditorListener(new CellEditorListener() {
            @Override
            public void editingStopped(ChangeEvent e) {
                int idRow = idPanel.getSelectedRow();
                int detRow = detailsPanel.getSelectedRow();
                int detCol = detailsPanel.getSelectedColumn();
                detaily.getDetaily().get(idRow).setValueAt(detailsPanel.getValueAt(detRow,detCol),detRow,detCol);
                list.setValueToList((GraphicShapes) detaily.getDetaily().get(idRow).getValueAt(10,1), idRow);
                remove(canvas);
                listenToMeXml = false;
                xmlPanel.setData(list);
                listenToMeXml = true;
                canvasPanel.remove(0);
                canvas = new Canvas(list.getList());
                canvasPanel.add(canvas, BorderLayout.CENTER);
                revalidate();
                repaint();
            }

            @Override
            public void editingCanceled(ChangeEvent e) {
                int idRow = idPanel.getSelectedRow();
                int detRow = detailsPanel.getSelectedRow();
                int detCol = detailsPanel.getSelectedColumn();
                detaily.getDetaily().get(idRow).setValueAt(detailsPanel.getValueAt(detRow,detCol),detRow,detCol);
                list.setValueToList((GraphicShapes) detaily.getDetaily().get(idRow).getValueAt(10,1), idRow);
                remove(canvas);
                listenToMeXml = false;
                xmlPanel.setData(list);
                listenToMeXml = true;
                canvasPanel.remove(0);
                canvas = new Canvas(list.getList());
                canvasPanel.add(canvas, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });


        /* Lomená čára*/
        canvasPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (listenToMeClick) {
                    if (makeBrokenSegment == true && makeBrokenSecondSegment == true) {
                        firstPoint = e.getPoint();
                        makeBrokenSegment = false;
                        System.out.println(firstPoint);
                        System.out.println("První");
                    } else if (makeBrokenSegment == false && makeBrokenSecondSegment == true) {
                        secondPoint = e.getPoint();
                        makeBrokenSecondSegment = false;
                        System.out.println(secondPoint);
                        System.out.println("Druhý");
                    } else if (makeBrokenSecondSegment == false && makeBrokenSegment == false) {
                        thirdPoint = e.getPoint();
                        System.out.println(thirdPoint);
                        System.out.println("Třetí");
                        selectionTable.repaint();
                        System.out.println(selectionTable.getValueAt(0,1));
                        list.addValueToList(new LineSegment(firstPoint.x, firstPoint.y, secondPoint.x, secondPoint.y,  Integer.parseInt(selectionTable.getValueAt(0,1).toString()), (String) selectionTable.getValueAt(1,1)));
                        list.addValueToList(new LineSegment(secondPoint.x, secondPoint.y, thirdPoint.x, thirdPoint.y, Integer.parseInt(selectionTable.getValueAt(0,1).toString()), (String) selectionTable.getValueAt(1,1)));
                        detaily = new DetailsList(list);
                        detailsPanel.nullPanel();
                        if (list.getList().size() != idPanel.getRowCount()) {
                            listenToMePanel = false;
                            idPanel.clearSelection();
                            idPanel.setPanel(list);
                            listenToMePanel = true;
                        }
                        remove(canvas);
                        listenToMeXml = false;
                        xmlPanel.setData(list);
                        listenToMeXml = true;
                        canvasPanel.remove(0);
                        canvas = new Canvas(list.getList());
                        canvasPanel.add(canvas, BorderLayout.CENTER);
                        revalidate();
                        repaint();
                        listenToMeClick = false;
                        segmentBtn.setText("Broken Segment");
                    }
                }
            }
        });
        
    }

}
