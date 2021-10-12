import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class DOMReader {

    private String uri;
    private Document documento;
    private static DOMReader dr=null;

    private DOMReader(String uri){
        this.uri=uri;
        abrirXML();
    }

    public static DOMReader getInstance( String uri) {
        if (dr == null)
            dr = new DOMReader(uri);
        return dr;
    }

    /**
     * abrimos el archivo XML a partir de la uri almacenada al crearse una instacia de esta clase,
     * almacenamos en la variable documento el dom del xml y lo normalizamos para que este bien estructurado
     * en caso de que hubiese algun problema
     */
    private void abrirXML(){
        File xml = new File(uri);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;

        try {
            dBuilder = dbFactory.newDocumentBuilder();
            this.documento = dBuilder.parse(xml); //metemos en el documento el dom del xml
            this.documento.getDocumentElement().normalize(); //normalizamos el dom para que este bien estructurado
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    /**
     * Aniadimos un nuevo libro a la biblioteca, creamos para ello un rootElement que recojera el nodo raiz del xml
     * y lo usara para saber sobre cual metera el nuevo libro, y, con appendChild metemos el libro creado en crearLibro()
     *
     * @param libro el objeto que tiene los datos del libro que queremos aniadir a nuestra biblioteca
     */
    public void aniadirLibro(Libro libro) {
        Element rootElement = (Element) this.documento.getElementsByTagName("Biblioteca").item(0);
        rootElement.appendChild(crearLibro(libro));
    }

    /**
     * creamos el libro a partir de la informacion recibida a partir de nuestro lobro parametro, para ello
     * creamos un elemento libro y le metemos elementos hijos con appendChild().
     *
     * @param libro el objeto que tiene los datos del libro que queremos aniadir a nuestra biblioteca
     * @return el nodo libro que aniadiremos a la biblioteca mediante aniadirLibro()
     */
    private Node crearLibro(Libro libro) {

        Element elementoLibro = this.documento.createElement("libro");

        elementoLibro.appendChild(crearNodo(elementoLibro, "titulo", libro.getTitulo()));
        elementoLibro.appendChild(crearNodo(elementoLibro, "autor", libro.getAutor()));
        elementoLibro.appendChild(crearNodo(elementoLibro, "numeroHojas", libro.getNumHojas()));
        return elementoLibro;
    }

    /**
     *
     * creamos el nodo especificando sobre que elemento se aniadira (cual es su elemento padre),
     * el nombre que tiene y cual es el valor que alberga.
     *
     * @param element es el elemento padre del nodo que vamos a crear
     * @param name es el nombre del nodo que hemos creado
     * @param value es el valor que albergara
     * @return el nodo hijo a partir de los valores pasados por parametro
     */
    private Node crearNodo(Element element, String name, String value) {
        Element node = this.documento.createElement(name);
        node.appendChild(this.documento.createTextNode(value));
        return node;
    }

    /**
     * creamos un tranformador y un DOMSource, que se encargaran de leer y transformar el DOM de documento
     * y combertirlo en XML, que se almacenara el la direccion paada por parametro, que habremos creado un archivo
     * XML gracias a StreamResult
     *
     * @param uri es la direccion donde se creara el nuevo xml
     */
    public void crearXML(String uri){

        try {
            Transformer transformer= this.parametrosTransformer();
            DOMSource ds = new DOMSource(this.documento);
            StreamResult sr = new StreamResult(new File(uri));
            transformer.transform(ds, sr);

        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    /**
     * creamos un transformador con los elementos necesarios para que funcione correctamente
     *
     * @return el transformador creado con los parametros deseados
     */
    private Transformer parametrosTransformer(){

        Transformer transformer=null;

        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");


        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        return transformer;
    }

}
