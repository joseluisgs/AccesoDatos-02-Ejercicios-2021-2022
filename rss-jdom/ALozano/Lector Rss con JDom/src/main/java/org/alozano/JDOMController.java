package org.alozano;


import lombok.Getter;
import lombok.NonNull;
import org.alozano.model.Noticia;
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
public class JDOMController {
    private static JDOMController controller;
    private final String uri;
    private Document data;

    private JDOMController(String uri) {
        this.uri = uri;
    }

    public static JDOMController getInstance(@NonNull String uri) {
        if (controller == null)
            controller = new JDOMController(uri);
        return controller;
    }

    private static Element createNoticiaElement(Noticia noticia) {
        Element noticiaElement = new Element("noticia");
        noticiaElement.addContent(new Element("title").setText(noticia.getTitulo()));
        noticiaElement.addContent(new Element("category").setText(noticia.getCategoria()));
        noticiaElement.addContent(new Element("description").setText(noticia.getDescripcion()));
        noticiaElement.addContent(new Element("pubDate").setText(noticia.getFecha()));
        noticiaElement.addContent(new Element("link").setText(noticia.getEnlace()));
        noticiaElement.addContent(new Element("creator").setText(noticia.getAuthor()));
        return noticiaElement;
    }

    public void loadData() throws IOException, JDOMException {
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(this.uri);
        this.data = (Document) builder.build(uri);
    }

    public void initData() {
        this.data = new Document();
        this.data.setRootElement(new Element("noticias"));
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


    public List<Noticia> getNoticias() {
        Element root = (Element) this.data.getRootElement();
        Element channel = root.getChild("channel");
        List<Element> listOfNoticias = channel.getChildren("item");
        List<Noticia> noticiaList = new ArrayList<>();

        listOfNoticias.forEach(noticiaElement -> {
            Noticia noticia = new Noticia();
            noticia.setTitulo(noticiaElement.getChildText("title"));
            noticia.setCategoria(noticiaElement.getChildText("category"));
            noticia.setDescripcion(noticiaElement.getChildText("description"));
            noticia.setFecha(noticiaElement.getChildText("pubDate"));
            noticia.setEnlace(noticiaElement.getChildText("link"));
            noticia.setAuthor(noticiaElement.getChildText("dc:creator"));
            noticiaList.add(noticia);
        });
        return noticiaList;
    }

    public void addNoticia(Noticia noticia) {
        Element root = (Element) this.data.getRootElement();
        root.addContent(createNoticiaElement(noticia));
    }

    public void addElement(String tag, String value) {
        Element rootElement = this.data.getRootElement();
        List<Element> listNoticiasElement = rootElement.getChildren("noticia");
        listNoticiasElement.forEach(noticiaElement -> {
            noticiaElement.addContent(new Element(tag).setText(value));
        });

    }

    public void ValorEnMayuscula(String tag) {
        Element rootElement = this.data.getRootElement();
        List<Element> listNoticiasElement = rootElement.getChildren("noticia");
        listNoticiasElement.forEach(noticiaElement -> {
            String name = noticiaElement.getChildText("title");
            if (name != null)
                noticiaElement.getChild("title").setText(name.toUpperCase());

        });
    }

    public void deleteElement(String tag) {
        Element rootElement = this.data.getRootElement();
        List<Element> listNoticiasElement = rootElement.getChildren("noticia");
        listNoticiasElement.forEach(noticiaElement -> {
            String name = noticiaElement.getChildText("creator");
            noticiaElement.removeChild(tag);
        });
    }

    public List<String> getAllTitulos() {
        XPathFactory xpath = XPathFactory.instance();
        XPathExpression<Element> expr = xpath.compile("//item/title", Filters.element());
        List<Element> noticias = expr.evaluate(this.data);
        List<String> titulos = new ArrayList<String>();
        noticias.forEach(noticia -> titulos.add(noticia.getValue().trim()));
       return titulos;
    }

    //Obtiene el nombre del elemento con indice indicado El primero en este caso
    public String getIndexTitulo(int index) {
        XPathFactory xpath = XPathFactory.instance();
        XPathExpression<Element> expr = xpath.compile("//item["+index+"]/title", Filters.element());
        Element title = expr.evaluateFirst(this.data);
        //Element titulo = expr.evaluateFirst(uri);
        return title.getValue();
    }

}
