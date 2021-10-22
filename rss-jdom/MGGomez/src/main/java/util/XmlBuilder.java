package util;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlBuilder {


    public List<Element> leerXml(String ruta) throws IOException, JDOMException {
        SAXBuilder builder = new SAXBuilder();
        Document document = builder.build(ruta);
        Element root = document.getRootElement();
        List<Element>channel = root.getChildren("channel");
        return channel;
    }
}
