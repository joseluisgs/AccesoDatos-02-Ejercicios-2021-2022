package org.alozano;

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

public class DOM {
    private final String uri;
    private static DOM controller;
    private Document data;

    private DOM(String uri) {   //COMENTAR
        this.uri = uri;
    }

    public static DOM getInstance(@NonNull String uri) {
        if (controller == null)
            controller = new DOM(uri);
        return controller;
    }


    public void añadirDatos() throws ParserConfigurationException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        dBuilder = dbFactory.newDocumentBuilder();
        this.data = dBuilder.newDocument();
        Element rootElement = this.data.createElement("Noticias");
        this.data.appendChild(rootElement);
    }


    public void cargarDatos() throws ParserConfigurationException, IOException, SAXException {
        File xmlFile = new File(this.uri);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        this.data = dBuilder.parse(uri); //this.data = dBuilder.parse(xmlFile);
        this.data.getDocumentElement().normalize();
    }


    private String obteneValores(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        if(node != null)
            return node.getNodeValue();
        else
            return null;
    }


    private Noticia getNoticia(@NonNull Node node) {
        Noticia noticias = new Noticia();
        for(Node n = node.getFirstChild(); n != null; n = n.getNextSibling()){  //IMPORTANTE
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                if (n.getNodeName().equals("title"))
                    noticias.setTitulo(n.getTextContent());
                if (n.getNodeName().equals("category"))
                    noticias.setCategoria(n.getTextContent());
                if (n.getNodeName().equals("description"))
                    noticias.setDescripcion(n.getTextContent());
                if (n.getNodeName().equals("dc:creator"))
                    noticias.setAuthor(n.getTextContent());
                if (n.getNodeName().equals("pubDate"))
                    noticias.setFecha(n.getTextContent());
                if (n.getNodeName().equals("link"))
                    noticias.setEnlace(n.getTextContent());
            }
        }

        return noticias;
    }


    public List<Noticia> getNoticias() {
        List<Noticia> noticiaList = new ArrayList<Noticia>();
        NodeList nodeList = this.data.getElementsByTagName("item");
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


    public void writeXMLFile(String uri) throws TransformerException {
        Transformer transformer= this.preProcess();
        DOMSource source = new DOMSource(this.data);
        StreamResult file = new StreamResult(new File(uri));
        transformer.transform(source, file);
        System.out.println("Fichero XML generado con éxito");
    }


    public void printXML() throws TransformerException {
        Transformer transformer= this.preProcess();
        DOMSource source = new DOMSource(this.data);
        StreamResult console = new StreamResult(System.out);
        transformer.transform(source, console);
    }

    private Node nodosNoticia(String name, String value){
        Element node = this.data.createElement(name);
        node.appendChild(this.data.createTextNode(value));
        return node;
    }

    private Node nuevaNoticia(Noticia noticia){
        Element element = this.data.createElement("item");
        element.appendChild(nodosNoticia("title",noticia.getTitulo()));
        element.appendChild(nodosNoticia("category",noticia.getCategoria()));
        element.appendChild(nodosNoticia("description",noticia.getDescripcion()));
        element.appendChild(nodosNoticia("creator",noticia.getAuthor()));
        element.appendChild(nodosNoticia("pubDate",noticia.getFecha()));
        element.appendChild(nodosNoticia("link",noticia.getEnlace()));
        return element;
    }

    public void añadirNoticia(Noticia noticia){
        Element rootElement = (Element) this.data.getElementsByTagName("Noticias").item(0);
        rootElement.appendChild(nuevaNoticia(noticia));
    }


}
