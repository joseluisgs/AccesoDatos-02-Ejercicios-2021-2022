package dom;

import dom.model.Noticia;
import lombok.Data;
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


@Data

public class DOMController {
    public static DOMController controlador;
    private String uri;
    private Document data;

//Haciendo privado el constructor de la clase creamos un singleton. No se pueden crear instancias de esta clase. Sólo las que se creen dentro de ella.

    private DOMController(String uri) {
        this.uri = uri;
    }


    public static DOMController getInstance(@NonNull String uri) {
        if (controlador == null) {
            controlador = new DOMController(uri);

        }
        return controlador;

    }


    public void initData() throws ParserConfigurationException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        dBuilder = dbFactory.newDocumentBuilder();
        this.data = dBuilder.newDocument();
        // Creamos el elemento raíz
        Element rootElement = this.data.createElement("channel");
        // Lo añadimos al ducumento
        this.data.appendChild(rootElement);
    }


    //DocumentBuilderFactory permite la generación de un documento XML vacío almacenándolo en memoria
    //newInstance obtiene una nueva instancia de DocumentBuilderFactory.


    public void cargarDatos() throws ParserConfigurationException, IOException, SAXException {

        File xmlFile = new File(this.uri);
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        docBuilder = docFactory.newDocumentBuilder();
        this.data = docBuilder.parse(xmlFile);
        this.data.getDocumentElement().normalize();


    }


    //Obtiene el valor de un elemento según su tag

    private String getValorTag(String tag, Element element) {
        NodeList listaNodos = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node nodo = listaNodos.item(0);
        if (nodo != null)
            return nodo.getNodeValue();
        else
            return null;
    }


    private Noticia getNoticia(Node node) {
        Noticia noticia = new Noticia();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            // Leemos los elementos en base a su tag
            noticia.setTitulo(getValorTag("title", element));
            noticia.setLink(getValorTag("link", element));
            noticia.setFecha(getValorTag("pubDate", element));
            noticia.setCreador(getValorTag("dc:creator", element));
            noticia.setDescripcion(getValorTag("description", element));
            noticia.setContenido(getValorTag("content:encoded", element));

        }
        return noticia;
    }


    public List<Noticia> getNoticias() {
        List<Noticia> listaNoticias = new ArrayList<Noticia>();
        NodeList nodeList = this.data.getElementsByTagName("item");
        for (int i = 0; i < nodeList.getLength(); i++) {
            listaNoticias.add(getNoticia(nodeList.item(i)));
        }
        return listaNoticias;
    }

    //preproceso del XML para imprimirlo

    private Transformer preProcess() throws TransformerConfigurationException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        return transformer;
    }

    //Guarda el DOM en un fichero XML indicado por la URI
    public void writeXMLFile(String uri) throws TransformerException, TransformerConfigurationException {
        Transformer transformer = this.preProcess();
        DOMSource source = new DOMSource(this.data);
        StreamResult file = new StreamResult(new File(uri));
        transformer.transform(source, file);
        System.out.println("Fichero XML generado con éxito");
    }

    public void printXML() throws TransformerException {
        Transformer transformer = this.preProcess();
        DOMSource source = new DOMSource(this.data);
        StreamResult console = new StreamResult(System.out);
        transformer.transform(source, console);
    }


}
