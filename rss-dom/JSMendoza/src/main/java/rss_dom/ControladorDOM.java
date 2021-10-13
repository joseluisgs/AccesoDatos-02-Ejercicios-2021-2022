package rss_dom;

import lombok.NonNull;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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

public class ControladorDOM {
    private final String uri;
    private static ControladorDOM controlador;
    private Document data;

    public ControladorDOM(String uri) {
        this.uri = uri;
    }

    public static ControladorDOM getInstance(@NonNull String uri) {
        if (controlador == null)
            controlador = new ControladorDOM(uri);
        return controlador;
    }

    public void initData() throws ParserConfigurationException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        dBuilder = dbFactory.newDocumentBuilder();
        this.data = dBuilder.newDocument();
        Element rootElement = this.data.createElement("rss");
        this.data.appendChild(rootElement);
    }

    public void loadData() throws ParserConfigurationException, IOException, SAXException {
        File rssFile = new File("data/rss.rss");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        dBuilder = dbFactory.newDocumentBuilder();
        this.data = dBuilder.parse(rssFile);
        this.data.getDocumentElement().normalize();
    }

    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        if (node != null) {
            return node.getNodeValue();
        } else {
            return null;
        }
    }

    private Noticias getNoticias(Node node) {
        Noticias noticias = new Noticias();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            noticias.setTitulo(getTagValue("titulo", element));
            noticias.setLink(getTagValue("link", element));
            noticias.setDescripcion(getTagValue("descripcion", element));
            noticias.setFecha(getTagValue("fecha", element));
            noticias.setCategoria(getTagValue("categoria", element));
        }
        return noticias;
    }

    public List<Noticias> getNoticias() {
        List<Noticias> noticiasList = new ArrayList<>();
        NodeList nodeList = this.data.getElementsByTagName("Noticias");
        for (int i = 0; i < nodeList.getLength(); i++) {
            noticiasList.add(getNoticias(nodeList.item(i)));
        }
        return noticiasList;
    }

    private Transformer preProcess() throws TransformerConfigurationException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        return transformer;
    }

    public void writeRSSFile(String uri) throws TransformerException {
        Transformer transformer = this.preProcess();
        DOMSource source = new DOMSource(this.data);
        StreamResult file = new StreamResult(new File(uri));
        transformer.transform(source, file);
        System.out.println("Fichero RSS generado con éxito");
    }

    public void printRSS() throws TransformerException {
        Transformer transformer = this.preProcess();
        DOMSource source = new DOMSource(this.data);
        StreamResult console = new StreamResult(System.out);
        transformer.transform(source, console);
    }

    private Node crearElementosNoticias(String name, String value) {
        Element node = this.data.createElement(name);
        node.appendChild(this.data.createTextNode(value));
        return node;
    }

    private Node crearElementosNoticias(Noticias noticias) {
        Element elementosNoticias = this.data.createElement("item");
        elementosNoticias.appendChild(crearElementosNoticias("title", noticias.getTitulo()));
        elementosNoticias.appendChild(crearElementosNoticias("link", noticias.getLink()));
        elementosNoticias.appendChild(crearElementosNoticias("description",
                noticias.getDescripcion()));
        elementosNoticias.appendChild(crearElementosNoticias("puDate", noticias.getFecha()));
        elementosNoticias.appendChild(crearElementosNoticias("category", noticias.getCategoria()));
        return elementosNoticias;
    }

    public void agregarNoticias(Noticias noticias) {
        Element rootElement = (Element) this.data.getElementsByTagName("rss").item(0);
        rootElement.appendChild(crearElementosNoticias(noticias));
    }

}
