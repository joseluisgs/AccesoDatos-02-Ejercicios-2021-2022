import lombok.NonNull;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControladorRss {
    private final String uri;
    private static ControladorRss controller;
    private Document data;

    private ControladorRss(String uri) {
        this.uri = uri;
    }

    public static ControladorRss getInstance(@NonNull String uri) {
        if (controller == null)
            controller = new ControladorRss(uri);
        return controller;
    }

    public void loadData() throws ParserConfigurationException, IOException, SAXException {
        File xmlFile = new File(this.uri);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;

        dBuilder = dbFactory.newDocumentBuilder();
        this.data = dBuilder.parse(xmlFile);
        this.data.getDocumentElement().normalize();
    }

    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        if(node != null)
            return node.getNodeValue();
        else
            return null;
    }

    private Noticia getNoticia(Node node) {
        Noticia noticia = new Noticia();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            noticia.setNombre(getTagValue("firstName", element));
            noticia.setApellido(getTagValue("lastName", element));
            noticia.setDni(getTagValue("gender", element));
        }
        return noticia;
    }

    public List<Noticia> getNoticia() {
        List<Noticia> noticiaList = new ArrayList<Noticia>();
        NodeList nodeList = this.data.getElementsByTagName("Alumnos");
        for (int i = 0; i < nodeList.getLength(); i++) {
            noticiaList.add(getNoticia(nodeList.item(i)));
        }
        return noticiaList;
    }

    private Transformer preProcess() throws TransformerConfigurationException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        return transformer;
    }

    public void printXML() throws TransformerException {
        Transformer transformer= this.preProcess();
        DOMSource source = new DOMSource(this.data);
        StreamResult console = new StreamResult(System.out);
        transformer.transform(source, console);
    }
}
