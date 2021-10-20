package model;

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

public class JDomController {
    private static JDomController controller;
    private Document data;
    private String uri;

    public JDomController(String uri) {
        this.uri = uri;
    }

    public static JDomController getInstance(@NonNull String uri) {
        if (controller == null)
            controller = new JDomController(uri);
        return controller;
    }

    /*
        Rellenamos los atributos con los campos del xml
     */
    public static Element createPersonaElements(Persona persona) {
        Element personaElement = new Element("Persona");
        personaElement.setAttribute(new Attribute("id", Integer.toString(persona.getId())));
        personaElement.addContent(new Element("firstName").setText(persona.getFirstName()));
        personaElement.addContent(new Element("lastName").setText(persona.getLastName()));
        personaElement.addContent(new Element("age").setText(Integer.toString(persona.getAge())));
        personaElement.addContent(new Element("direccion").setText(persona.getDireccion()));
        return personaElement;
    }

    public void loadData() throws IOException, JDOMException {
//Trabajamos con Sax
        SAXBuilder builder = new SAXBuilder();
        File file = new File(this.uri);
        this.data = (Document) builder.build(file);
    }

    public void initData() {
        // Documento vacío
        data = new Document();
        // Nodo raíz
        this.data.setRootElement(new Element("Personas"));
    }

    private XMLOutputter preProcess() {
        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());
        return xmlOutput;
    }

    public void writeXMLFile(String uri) throws IOException {
        XMLOutputter xmlOutput = this.preProcess();
        xmlOutput.output(this.data, new FileWriter(uri));
        System.out.println("Fichero XML generado con éxito");
    }

    public void printXML() throws IOException {
        XMLOutputter xmlOutput = this.preProcess();
        xmlOutput.output(this.data, System.out);
    }

    public List<Persona> getPersonas() {
        Element root = (Element) this.data.getRootElement();
        List<Element> listPer = root.getChildren("Personas");

        //  inicializamos la lista de usuarios
        List<Persona> personaList = new ArrayList<>();

        listPer.forEach(userElement -> {
            Persona persona = new Persona();
            persona.setId(Integer.parseInt(userElement.getAttributeValue("id")));
            persona.setAge(Integer.parseInt(userElement.getChildText("age")));
            persona.setFirstName(userElement.getChildText("firstName"));
            persona.setLastName(userElement.getChildText("lastName"));
            persona.setDireccion(userElement.getChildText("direccion"));
            personaList.add(persona);
        });
        return personaList;
    }

    public void addPersona(Persona persona) {
        Element root = (Element) this.data.getRootElement();
        root.addContent(createPersonaElements(persona));
    }


    public void addElement(String tag, String value) {
        Element rootElement = this.data.getRootElement();
        List<Element> listElem = rootElement.getChildren("Personas");
        listElem.forEach(userElement -> {
            userElement.addContent(new Element(tag).setText(value));
        });
    }


    public void updateElementValue(String tag) {
        Element rootElement = this.data.getRootElement();
        List<Element> listUserElement = rootElement.getChildren("Personas");
        listUserElement.forEach(userElement -> {
            String name = userElement.getChildText("firstName");
            if (name != null)
                userElement.getChild("firstName").setText(name.toUpperCase());

        });
    }


    public void deleteElement(String tag) {
        Element rootElement = this.data.getRootElement();
        List<Element> listUserElement = rootElement.getChildren("Personas");
        listUserElement.forEach(userElement -> {
            userElement.removeChild(tag);
        });
    }


    public void updateID() {
        Element rootElement = this.data.getRootElement();
        List<Element> listUserElement = rootElement.getChildren("Personas");
        listUserElement.forEach(userElement -> {
            //String gender = userElement.getChildText("gender");
            //int edad = userElement.getChild("age");
            int edad = Integer.parseInt(((String) userElement.getChildText("age")));
            if (edad <= 40) {
                String id = userElement.getAttributeValue("id");
                userElement.getAttribute("id").setValue(id + "Joven");

            } else {
                String id = userElement.getAttributeValue("id");
                userElement.getAttribute("id").setValue(id + "Menos joven");
            }
        });
    }

    // Consultas con XPATH


    public List<String> getAllNames() {
        XPathFactory xpath = XPathFactory.instance();
        XPathExpression<Element> expr = xpath.compile("//Persona/firstName", Filters.element());
        List<Element> users = expr.evaluate(this.data);
        List<String> names = new ArrayList<String>();
        users.forEach(user -> names.add(user.getValue().trim()));
        return names;
    }


    public List<String> getAllIds() {
        XPathFactory xpath = XPathFactory.instance();
        XPathExpression<Attribute> expr = xpath.compile("//Persona/@id", Filters.attribute());
        List<Attribute> users = expr.evaluate(this.data);
        List<String> ids = new ArrayList<String>();
        users.forEach(user -> ids.add(user.getValue().trim()));
        return ids;
    }


    public String getLastName(int index) {
        XPathFactory xpath = XPathFactory.instance();
        XPathExpression<Element> expr = xpath.compile("//Persona[" + index + "]/lastname", Filters.element());
        Element name = expr.evaluateFirst(this.data);
        return name.getValue();
    }


    public String getFirstNameById(String id) {
        XPathFactory xpath = XPathFactory.instance();
        XPathExpression<Element> expr = xpath.compile("//Persona[@id='" + id + "']/firstName", Filters.element());
        Element name = expr.evaluateFirst(this.data);
        return name.getValue();
    }
}
