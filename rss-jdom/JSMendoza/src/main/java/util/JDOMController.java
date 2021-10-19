package util;

import lombok.Getter;
import lombok.NonNull;
import model.Band;
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

public class JDOMController {
    private static JDOMController controlador;
    private final String uri;
    private Document data;

    private JDOMController(String uri) {
        this.uri = uri;
    }

    public static JDOMController getInstance(@NonNull String uri) {
        if (controlador == null)
            controlador = new JDOMController(uri);
        return controlador;
    }

    private Element createBandElement(Band band) {
        org.jdom2.Element bandElement = new org.jdom2.Element("band");
        bandElement.setAttribute(new Attribute("id", Integer.toString(band.getId())));
        bandElement.addContent(new org.jdom2.Element("name").setText(band.getName()));
        bandElement.addContent(new org.jdom2.Element("origin").setText(band.getOrigin()));
        bandElement.addContent(
                new org.jdom2.Element("periodActivity").setText(Integer.toString(band.getPeriodActivity())));
        bandElement.addContent(new org.jdom2.Element("genre").setText(band.getGenre()));
        return bandElement;
    }

    public void loadData() throws IOException, JDOMException {
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(this.uri);
        this.data = builder.build(xmlFile);
    }

    public void initData() {
        this.data = new Document();
        this.data.setRootElement(new Element("bands"));
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

    public List<Band> getBands() {
        Element root = this.data.getRootElement();
        List<Element> listOfBands = root.getChildren("band");

        List<Band> bandList = new ArrayList<>();

        listOfBands.forEach( bandElement -> {
            Band band = new Band();
            band.setId(Integer.parseInt(bandElement.getAttributeValue("id")));
            band.setName(bandElement.getChildText("name"));
            band.setOrigin(bandElement.getChildText("origin"));
            band.setPeriodActivity(Integer.parseInt(bandElement.getChildText("periodActivity")));
            band.setGenre(bandElement.getChildText("genre"));
            bandList.add(band);
        });
        return bandList;
    }

    public void addBand(Band band) {
        Element root = this.data.getRootElement();
        root.addContent(createBandElement(band));
    }

    public void addElement(String tag, String value) {
        Element rootElement = this.data.getRootElement();
        List<Element> listBandElement = rootElement.getChildren("band");
        listBandElement.forEach( bandElement -> {
            bandElement.addContent(new Element(tag).setText(value));
        });
    }

    public void updateElementValue(String tag) {
        Element rootElement = this.data.getRootElement();
        List<Element> listBandElement = rootElement.getChildren("band");
        listBandElement.forEach(bandElement -> {
            String name = bandElement.getChildText("name");
            if (name != null)
                bandElement.getChild("name").setText(name.toUpperCase());
        });
    }

    public void deleteElement(String tag) {
        Element rootElement = this.data.getRootElement();
        List<Element> listBandElement = rootElement.getChildren("band");
        listBandElement.forEach( bandElement -> {
            bandElement.removeChild(tag);
        });
    }

    public void updateID(){
        Element rootElement = this.data.getRootElement();
        List<Element> listBandElement = rootElement.getChildren("band");
        listBandElement.forEach((Element bandElement) -> {
            String genre = bandElement.getChildText("genre");
            if (genre != null && genre.equalsIgnoreCase("Heavy Metal")){
                String id = bandElement.getAttributeValue("id");
                bandElement.getAttribute("id").setValue(id+"HM");
            } else {
                String id = bandElement.getAttributeValue("id");
                bandElement.getAttribute("id").setValue(id+"TM");
            }
        });
    }

   public List<String> getAllnames(){
        XPathFactory xpath = XPathFactory.instance();
        XPathExpression<Element> expr = xpath.compile("bands/band/name", Filters.element());
        List<Element> bands = expr.evaluate(this.data);
        List<String> names = new ArrayList<>();
        bands.forEach(bandElement ->names.add(bandElement.getValue().trim()));
        return names;
    }

   public List<String> getAllIds() {
        XPathFactory xpath = XPathFactory.instance();
        XPathExpression<Attribute> expr = xpath.compile("//band/@id", Filters.attribute());
        List<Attribute> users = expr.evaluate(this.data);
        List<String> ids = new ArrayList<>();
        users.forEach(bandElement -> ids.add(bandElement.getValue().trim()));
        return ids;
    }

    public String getGenre(int index) {
        XPathFactory xpath = XPathFactory.instance();
        XPathExpression<Element> expr = xpath.compile("//band["+index+"]/genre", Filters.element());
        Element genre = expr.evaluateFirst(this.data);
        return genre.getValue();
    }

    public String getNameById(String id) {
        XPathFactory xpath = XPathFactory.instance();
        XPathExpression<Element> expr = xpath.compile("//band[@id='"+id+"']/name", Filters.element());
        Element name = expr.evaluateFirst(this.data);
        return name.getValue();
    }
}
