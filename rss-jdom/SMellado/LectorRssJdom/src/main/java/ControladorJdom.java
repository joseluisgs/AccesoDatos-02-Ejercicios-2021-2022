
import lombok.Getter;
import lombok.NonNull;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ControladorJdom {
    private static ControladorJdom controller;
    private final String uri;
    private Document data;

    private ControladorJdom(String uri) {
        this.uri = uri;
    }

    public static ControladorJdom getInstance(@NonNull String uri) {
        if (controller == null)
            controller = new ControladorJdom(uri);
        return controller;
    }

    /**
     * Metodo que añade un elemento Alumno
     * @param alumno
     * @return Elemento alumno
     */
    private Element createUserElement(Alumno alumno) {
        Element alumnoElement = new Element("alumno");
        alumnoElement.setAttribute(new Attribute("id", Integer.toString(alumno.getId())));
        alumnoElement.addContent(new Element("nombre").setText(alumno.getNombre()));
        alumnoElement.addContent(new Element("apellido").setText(alumno.getApellido()));
        alumnoElement.addContent(new Element("dni").setText(alumno.getDni()));
        alumnoElement.addContent(new Element("comentario").setText(alumno.getComentario()));
        return alumnoElement;
    }

    /**
     * Metodo que carga los datos de nuestro fichero, trabajando con DOM, SAX y STAX Parser Builder
     * @throws IOException
     * @throws JDOMException
     */
    public void loadData() throws IOException, JDOMException {
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(this.uri);
        this.data = (Document) builder.build(xmlFile);
    }

    /**
     * Metodo que nos permite crear alumnos de 0
     */
    public void initData() {
        this.data = new Document();
        this.data.setRootElement(new Element("alumnos"));
    }

    private XMLOutputter preProcess() {
        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());
        return xmlOutput;
    }

    /**
     * Metodo que escribe nuestro fichero
     * @param uri
     * @throws IOException
     */

    public void writeXMLFile(String uri) throws IOException {
        XMLOutputter xmlOutput = this.preProcess();
        xmlOutput.output(this.data, new FileWriter(uri));
        System.out.println("Fichero XML generado con éxito");
    }

    /**
     * Metodo que saca por consola el fichero
     * @throws IOException
     */
    public void printXML() throws IOException {
        XMLOutputter xmlOutput = this.preProcess();
        xmlOutput.output(this.data, System.out);
    }

    /**
     * Metodo que nos saca por consola los alumnos que hay en nuestro fichero obteniendo todos sus datos
     * @return
     */
    public List<Alumno> getAlumnos() {
        Element root = (Element) this.data.getRootElement();
        List<Element> listOfUsers = root.getChildren("alumno");
        List<Alumno> alumnoList = new ArrayList<>();

        listOfUsers.forEach(alumnoElement -> {
            Alumno alumno = new Alumno();
            alumno.setId(Integer.parseInt(alumnoElement.getAttributeValue("id")));
            alumno.setNombre(alumnoElement.getChildText("nombre"));
            alumno.setApellido(alumnoElement.getChildText("apellido"));
            alumno.setDni(alumnoElement.getChildText("dni"));
            alumno.setComentario(alumnoElement.getChildText("comentario"));
            alumnoList.add(alumno);
        });
        return alumnoList;
    }

    /**
     * Metodo que nos permite añadir nuevos alumnos
     * @param alumno
     */
    public void addUser(Alumno alumno) {
        Element root = (Element) this.data.getRootElement();
        root.addContent(createUserElement(alumno));
    }

    /**
     * Metodo que añade un nuevo elemento al alumno con una determinada tag
     * @param tag
     * @param value
     */
    public void addElement(String tag, String value) {
        Element rootElement = this.data.getRootElement();
        List<Element> listUserElement = rootElement.getChildren("alumno");
        listUserElement.forEach(userElement -> {
            userElement.addContent(new Element(tag).setText(value));
        });
    }

    /**
     * Metodo que borra un elemento de alumno mediante una tag
     * @param tag
     */
    public void deleteElement(String tag) {
        Element rootElement = this.data.getRootElement();
        List<Element> listaAlumnoElement = rootElement.getChildren("alumno");
        listaAlumnoElement.forEach(alumnoElement -> {
            alumnoElement.removeChild(tag);
        });
    }

    /**
     * Metodo que saca todos los nombres del fichero
     * @return
     */
    public List<String> getNombres() {
        XPathFactory xpath = XPathFactory.instance();
        XPathExpression<Element> expr = xpath.compile("//alumno/nombre", Filters.element());
        List<Element> alumnos = expr.evaluate(this.data);
        List<String> nombres = new ArrayList<String>();
        alumnos.forEach(alumno -> nombres.add(alumno.getValue().trim()));
        return nombres;
    }
}
