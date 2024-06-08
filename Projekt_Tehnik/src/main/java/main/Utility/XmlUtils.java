package main.Utility;

import main.Lists.ShapeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class XmlUtils

{

    public static String getXml(ShapeList image)

    {

        JAXBContext ctx = null;

        try
        {
            ctx = JAXBContext.newInstance(ShapeList.class);
            Marshaller marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();
            marshaller.marshal(image, sw);
            String xmlString = sw.toString();
            return xmlString;

        } catch (JAXBException e)
        {
            throw new RuntimeException(e);
        }

    }



    public static ShapeList getImage(String xml)

    {

        try

        {
            JAXBContext ctx = JAXBContext.newInstance(ShapeList.class);
            Unmarshaller unmarshaller = ctx.createUnmarshaller();
            StringReader sr = new StringReader(xml);
            ShapeList svgImage = (ShapeList) unmarshaller.unmarshal(sr);
            return svgImage;

        } catch (JAXBException e)
        {
            throw new RuntimeException(e);
        }
    }

}