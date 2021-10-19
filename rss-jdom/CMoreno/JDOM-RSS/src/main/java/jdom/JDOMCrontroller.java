package jdom;

import jdom.model.Noticia;
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


public class JDOMCrontroller {
    private static JDOMCrontroller controller;
    private Document data;
    private String uri;

    private JDOMCrontroller(String uri) {
        this.uri = uri;
    }


    //Singleton
    public static JDOMCrontroller getInstance(@NonNull String uri) {
        if (controller == null) {
            controller = new JDOMCrontroller(uri);
        }
        return controller;
    }

    public void loadData() throws IOException, JDOMException {

        SAXBuilder builder = new SAXBuilder();
        File rssFile = new File(this.uri);
        this.data = (Document) builder.build(rssFile);
    }

    private static Element createNewsElement(Noticia news) {
        Element newsElement = new Element("Item");

        newsElement.addContent(new Element("title").setText(news.getTitulo()));
        newsElement.addContent(new Element("link").setText(news.getLink()));
        newsElement.addContent(new Element("description").setText(news.getDescripcion()));
        newsElement.addContent(new Element("url").setText(news.getImagen()));
        newsElement.addContent(new Element("pubDate").setText(news.getFecha()));
        newsElement.addContent(new Element("category").setText(news.getCategoria()));
        return newsElement;
    }

    public void initData() {
        this.data = new Document();
        this.data.setRootElement(new Element("rss"));
    }

    private XMLOutputter  preProcess() {
        XMLOutputter rssOutput = new XMLOutputter();
        rssOutput.setFormat(Format.getPrettyFormat());
        return rssOutput;
    }

    public void writeRSSFile(String uri) throws IOException {
        XMLOutputter rssOutput = this.preProcess();
        rssOutput.output(this.data, new FileWriter(uri));
        System.out.println("Fichero RSS generado con éxito");
    }

    public void printRSS() throws IOException {
        XMLOutputter rssOutput = this.preProcess();
        rssOutput.output(this.data, System.out);
    }

    public List<Noticia> getNoticias() {
        Element root = (Element) this.data.getRootElement();
        List<Element> listOfNews = root.getChildren("Item");

        List<Noticia> newsList = new ArrayList<>();

        listOfNews.forEach(newsElement -> {
            Noticia news = new Noticia();
            news.setTitulo((newsElement.getChildText("title")));
            news.setLink((newsElement.getChildText("link")));
            news.setDescripcion((newsElement.getChildText("description")));
            news.setImagen((newsElement.getAttributeValue("url")));
            news.setFecha((newsElement.getChildText("pubDate")));
            news.setCategoria((newsElement.getChildText("category")));
            newsList.add(news);
        });
        return newsList;
    }

    public void addNews(Noticia news) {
        Element root = (Element) this.data.getRootElement();
        root.addContent(createNewsElement(news));
    }

    public void addElement(String tag, String value) {
        Element rootElement = this.data.getRootElement();
        List<Element> listNewsElement = rootElement.getChildren("Item");
        listNewsElement.forEach(newsElement -> {
            newsElement.addContent(new Element(tag).setText(value));
        });
    }

    public void updateElementValue(String tag) {
        Element rootElement = this.data.getRootElement();
        List<Element> listNewsElement = rootElement.getChildren("Item");
        listNewsElement.forEach(newsElement -> {
            String name = newsElement.getChildText("firstName");
            if (name != null)
                newsElement.getChild("title").setText(name.toUpperCase());
        });
    }

    public void deleteElement(String tag) {
        Element rootElement = this.data.getRootElement();
        List<Element> listNewsElement = rootElement.getChildren("//item/title");
        listNewsElement.forEach(newsElement -> {
            newsElement.removeChild(tag);
        });
    }

    public List<String> getAllTitle() {
        XPathFactory xpath = XPathFactory.instance();
        XPathExpression<Element> expr = xpath.compile("//item/title", Filters.element());
        List<Element> news = expr.evaluate(this.data);
        List<String> titles = new ArrayList<String>();
        news.forEach(newss -> titles.add(newss.getValue().trim()));
        return titles;
    }


    public List<String> getAllImages() {
        XPathFactory xpath = XPathFactory.instance();
        XPathExpression<Attribute> expr = xpath.compile("//item/enclosure/@url", Filters.attribute());
        List<Attribute> news = expr.evaluate(this.data);
        List<String> urls = new ArrayList<String>();
        news.forEach(newss -> urls.add(newss.getValue().trim()));
        return urls;
    }

    public String getFirstTitle(int index) {
        XPathFactory xpath = XPathFactory.instance();
        XPathExpression<Element> expr = xpath.compile("//item["+index+"]/title", Filters.element());
        Element title = expr.evaluateFirst(this.data);
        return title.getValue();
    }

}
