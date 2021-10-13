package dom;

import dom.model.*;

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


public class DOMController {
    private static DOMController controller;
    private Document data;
    private String uri;

    private DOMController(String uri) {
        this.uri = uri;
    }


    //Singleton
    public static DOMController getInstance(@NonNull String uri) {
        if (controller == null) {
            controller = new DOMController(uri);
        }
        return controller;
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
        File rssFile = new File(this.uri);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        dBuilder = dbFactory.newDocumentBuilder();
        this.data = dBuilder.parse(rssFile);
        this.data.getDocumentElement().normalize();
    }

    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        if(node != null) {return node.getNodeValue();}
        else {return null;}
    }

    private Noticia getNoticia(Node node) {
        Noticia news = new Noticia();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            news.setTitulo(getTagValue("title", element));
            news.setLink(getTagValue("link", element));
            news.setDescripcion(getTagValue("description", element));
            news.setImagen(element.getAttribute("url"));
            news.setFecha(getTagValue("pubDate", element));
            news.setCategoria(getTagValue("category", element));
        }
        return news;
    }

    public List<Noticia> getNoticias() {
        List<Noticia> newsList = new ArrayList<>();
        NodeList nodeList = this.data.getElementsByTagName("item");
        for (int i = 0; i < nodeList.getLength(); i++) {
            newsList.add(getNoticia(nodeList.item(i)));
        }
        return newsList;
    }

    private Transformer preProcess() throws TransformerConfigurationException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        return transformer;
    }

    public void writeRSSFile(String uri) throws TransformerException {
        Transformer transformer= this.preProcess();
        DOMSource source = new DOMSource(this.data);
        StreamResult file = new StreamResult(new File(uri));
        transformer.transform(source, file);
        System.out.println("Fichero RSS generado con éxito");
    }

    public void printRSS() throws TransformerException {
        Transformer transformer= this.preProcess();
        DOMSource source = new DOMSource(this.data);
        StreamResult console = new StreamResult(System.out);
        transformer.transform(source, console);
    }

    private Node createNewsElements(Element element, String name, String value) {
        Element node = this.data.createElement(name);
        node.appendChild(this.data.createTextNode(value));
        return node;
    }

    private Node createNewsElement(Noticia news) {
        Element newsElement = this.data.createElement("item");
        // Establecemos los datos
        newsElement.appendChild(createNewsElements(newsElement, "title", news.getTitulo()));
        newsElement.appendChild(createNewsElements(newsElement, "link", news.getLink()));
        newsElement.appendChild(createNewsElements(newsElement, "description", news.getDescripcion()));
        newsElement.setAttribute("url", news.getImagen());
        newsElement.appendChild(createNewsElements(newsElement, "pubDate", news.getFecha()));
        newsElement.appendChild(createNewsElements(newsElement, "category", news.getCategoria()));
        return newsElement;
    }

    public void addNews(Noticia noticia) {
        Element rootElement = (Element) this.data.getElementsByTagName("rss").item(0);
        rootElement.appendChild(createNewsElement(noticia));
    }

    public void addElement(String tag, String value) {
        NodeList news = this.data.getElementsByTagName("item");
        Element emp = null;

        for (int i = 0; i < news.getLength(); i++) {
            emp = (Element) news.item(i);
            Element salaryElement = this.data.createElement(tag);
            salaryElement.appendChild(this.data.createTextNode(value));
            emp.appendChild(salaryElement);
        }
    }

    public void deleteElement(String tag) {
        NodeList newss = this.data.getElementsByTagName("item");
        Element news = null;

        for (int i = 0; i < newss.getLength(); i++) {
            news = (Element) newss.item(i);
            Node genderNode = news.getElementsByTagName(tag).item(0);
            news.removeChild(genderNode);
        }
    }
}
