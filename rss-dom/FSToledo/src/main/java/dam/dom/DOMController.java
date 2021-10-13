package dam.dom;
import dam.dom.model.News;
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
    private final String uri;
    private static DOMController controller;
    private Document data;

    private DOMController(String uri){
        this.uri = uri;
    }
    public static DOMController getInstance(@NonNull String uri){
        if (controller == null)
            controller = new DOMController(uri);
        return controller;
    }
    public void initData() throws ParserConfigurationException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        dBuilder = dbFactory.newDocumentBuilder();
        this.data = dBuilder.newDocument();

        Element rootElement = this.data.createElement("News");

        this.data.appendChild(rootElement);
    }
    public void loadData() throws ParserConfigurationException, IOException, SAXException {
        File xmlFile = new File(this.uri);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        this.data = dBuilder.parse(xmlFile);
        this.data.getDocumentElement().normalize();
    }

    private String getTagValue(String tag, Element element){
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        if(node != null) return node.getNodeValue();
        else return null;
    }

    private News getNewsItem(@NonNull Node node) {
        News news = new News();
        for (Node n = node.getFirstChild(); n != null; n = n.getNextSibling()) {

            if (n.getNodeType() == Node.ELEMENT_NODE) {
                if (n.getNodeName().equals("title"))
                    news.setTitle(n.getTextContent());
                if (n.getNodeName().equals("description"))
                    news.setDescription(n.getTextContent());
                if (n.getNodeName().equals("dc:creator"))
                    news.setCreator(n.getTextContent());
                if (n.getNodeName().equals("link"))
                    news.setLink(n.getTextContent());
                if (n.getNodeName().equals("category"))
                    news.setCategory(n.getTextContent());
                if (n.getNodeName().equals("guid"))
                    news.setGuid(n.getTextContent());
            }
        }
        return news;
    }
    public List<News> getNewsList(){
        List<News> newsList = new ArrayList<>();
        NodeList nodeList = this.data.getElementsByTagName("item");
        for (int i = 0; i< nodeList.getLength();i++){
            newsList.add(getNewsItem(nodeList.item(i)));
        }
        return newsList;
    }

    private Transformer preProcess() throws TransformerConfigurationException{
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
        return transformer;
    }

    public void writeXMLFile(String uri) throws TransformerException{
        Transformer transformer = this.preProcess();
        DOMSource source = new DOMSource(this.data);
        StreamResult result = new StreamResult(new File(uri));
        transformer.transform(source, result);
        System.out.println("XML File generated sucessfully");
    }
    public void printXML() throws TransformerException{
        Transformer transformer = this.preProcess();
        DOMSource source = new DOMSource(this.data);
        StreamResult console = new StreamResult(System.out);
        transformer.transform(source, console);
    }
    private Node createNewsElements(String name, String value){
        Element node = this.data.createElement(name);
        node.appendChild(this.data.createTextNode(value));
        return node;
    }
    private Node createNewsElement(News news){
            Element element = this.data.createElement("item");
            element.appendChild(createNewsElements("title",news.getTitle()));
            element.appendChild(createNewsElements("description",news.getDescription()));
            element.appendChild(createNewsElements("creator",news.getCreator()));
            element.appendChild(createNewsElements("link",news.getLink()));
            element.appendChild(createNewsElements("category",news.getCategory()));
            element.appendChild(createNewsElements("guid",news.getGuid()));
            return element;
    }

    public void addNewsItem(News news){
        Element rootElement = (Element) this.data.getElementsByTagName("News").item(0);
        rootElement.appendChild(createNewsElement(news));
    }
}
