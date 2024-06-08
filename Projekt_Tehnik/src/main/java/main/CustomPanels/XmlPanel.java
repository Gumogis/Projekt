package main.CustomPanels;

import main.Lists.ShapeList;
import main.Utility.XmlUtils;

import javax.swing.*;

public class XmlPanel extends JTextArea {
    ShapeList data;
    public XmlPanel(ShapeList data){
        this.data = data;
        setRows(100);
        setColumns(100);
        append(XmlUtils.getXml(this.data));
    }
    public void setData(ShapeList data) { setText(XmlUtils.getXml(data)); }
}
