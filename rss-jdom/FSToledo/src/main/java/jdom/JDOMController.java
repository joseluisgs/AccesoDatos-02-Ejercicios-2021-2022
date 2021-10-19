package jdom;

import jdom.model.News;
import lombok.NonNull;

import org.jdom2.*;
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
    private static JDOMController controller;
    private final String uri;
    private Document data;

    private JDOMController(String uri){
        this.uri = uri;
    }

    public static JDOMController getInstance(@NonNull String uri){
        if (controller == null) controller = new JDOMController(uri);
        return controller;
    }
    private Element createNewsElement(News news){
        Element newsElement = new Element("newsItem");
        newsElement.setAttribute(new Attribute("id",Integer.toString(news.getId())));
        newsElement.addContent(new Element("title").setText(news.getTitle()));
        newsElement.addContent(new Element("description").setText(news.getDescription()));
        newsElement.addContent(new Element("creator").setText(news.getCreator()));
        newsElement.addContent(new Element("link").setText(news.getLink()));
        newsElement.addContent(new Element("mediacontent").setText(news.getMediaContent()));
        newsElement.addContent(new Element("mediathumbnail").setText(news.getMediaThumbnail()));
        newsElement.addContent(new Element("guid").setText(news.getGuid()));
        newsElement.addContent(new Element("pubdate").setText(news.getPubdate()));
        if(!news.getCategories().isEmpty()){
            for (String category : news.getCategories()) {
                newsElement.addContent(new Element("category").setText(category));
            }
        }
        return newsElement;
    }
    public void loadData() throws IOException, JDOMException {
        SAXBuilder saxBuilder = new SAXBuilder();
        File xmlFile = new File(this.uri);
        this.data = saxBuilder.build(xmlFile);
    }
    public void initData(){
        this.data = new Document();
        this.data.setRootElement(new Element("news"));
    }
    private XMLOutputter preProcess(){
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        return xmlOutputter;
    }
    public void writeXMLFile(String uri) throws IOException {
        XMLOutputter xmlOutputter = this.preProcess();
        xmlOutputter.output(this.data, new FileWriter(uri));
        System.out.println("XML File generated successfully");
    }
    public void printXML() throws IOException {
        XMLOutputter xmlOutputter = this.preProcess();
        xmlOutputter.output(this.data,System.out);
    }
    public List<News> getNewsList(){
        Element root = this.data.getRootElement().getChild("channel");
        List<Element> listOfNews = root.getChildren("item");

        List<News> newsList = new ArrayList<>();

        listOfNews.forEach(newsElement ->{
            News news = new News();
            for (Element element: newsElement.getChildren()) {
                if (element.getName().equalsIgnoreCase("title"))
                    news.setTitle(element.getText());
                if (element.getName().equalsIgnoreCase("description"))
                    news.setDescription(element.getText());
                if (element.getName().equalsIgnoreCase("creator"))
                    news.setCreator(element.getText());
                if (element.getName().equalsIgnoreCase("link"))
                    news.setLink(element.getText());
               if (element.getName().equalsIgnoreCase("content"))
                    news.setMediaContent(element.getAttributeValue("url"));
                if (element.getName().equalsIgnoreCase("thumbnail"))
                    news.setMediaThumbnail(element.getAttributeValue("url"));
                if (element.getName().equalsIgnoreCase("guid"))
                    news.setGuid(element.getText());
                if (element.getName().equalsIgnoreCase("pubdate"))
                    news.setPubdate(element.getText());
                if (element.getName().equalsIgnoreCase("category"))
                    news.setCategory(element.getText());
            }
            newsList.add(news);
        });
        return newsList;
    }
    public void addNewsItem(News news){
        Element root = this.data.getRootElement();
        root.addContent(createNewsElement(news));
    }
    public void addElement(String tag, String value){
        Element root = this.data.getRootElement();
        List<Element> listNewsElement = root.getChildren("item");
        listNewsElement.forEach(newsElement -> newsElement.addContent(new Element(tag).setText(value)));
    }
    public void updateElementValue(String tag,String value){
        Element root = this.data.getRootElement();
        List<Element> listNewsElement = root.getChildren("item");
        listNewsElement.forEach(newsElement->{
            String name = newsElement.getChildText(tag);
            if (name != null && tag != "id" && tag != "category") newsElement.getChild(tag).setText(value);
        });
    }
    public void deleteElement(String tag){
        Element root = this.data.getRootElement();
        List<Element> listNewsElement = root.getChildren("item");
        listNewsElement.forEach(newsElement-> newsElement.removeChild(tag));
    }
    public void updateID(int newID){
        Element root = this.data.getRootElement();
        List<Element> listNewsElement = root.getChildren("item");
        listNewsElement.forEach(newsElement-> newsElement.getAttribute("id").setValue(String.valueOf(newID)));
    }

    public List<String> getAllTitles(){
        XPathFactory xpath = XPathFactory.instance();
        XPathExpression<Element> xpathExpression = xpath.compile("//item/title", Filters.element());
        List<Element> newsList = xpathExpression.evaluate(this.data);
        List<String> titles = new ArrayList<>();
        newsList.forEach(news->titles.add(news.getValue().trim()));
        return titles;
    }
    public List<String> getAllIds(){
        XPathFactory xpath = XPathFactory.instance();
        XPathExpression<Attribute> xpathExpression = xpath.compile("//item/@id", Filters.attribute());
        List<Attribute> newsList = xpathExpression.evaluate(this.data);
        List<String> ids = new ArrayList<>();
        newsList.forEach(news -> ids.add(news.getValue().trim()));
        return ids;
    }
    public List<String> getAllImagesUrl(){
        XPathFactory xpath = XPathFactory.instance();
        XPathExpression<Attribute> xpathExpression = xpath.compile("//item/content/@url", Filters.attribute());
        List<Attribute> newsList = xpathExpression.evaluate(this.data);
        List<String> images = new ArrayList<>();
        newsList.forEach(news -> images.add(news.getValue().trim()));
        return images;
    }
    public String getTitle(int index) {
        XPathFactory xpath = XPathFactory.instance();
        XPathExpression<Element> xpathExpression = xpath.compile("//item["+index+"]/title", Filters.element());
        Element title = xpathExpression.evaluateFirst(this.data);
        return title.getValue();
    }
    public String getFirstTitleById(String id){
        XPathFactory xpath = XPathFactory.instance();
        XPathExpression<Element> xpathExpression = xpath.compile("//item[@"+id+"]/title", Filters.element());
        Element title = xpathExpression.evaluateFirst(this.data);
        return title.getValue();
    }
}
