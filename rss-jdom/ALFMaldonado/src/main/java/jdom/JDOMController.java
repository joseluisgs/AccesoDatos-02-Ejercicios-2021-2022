package jdom;

import jdom.model.News;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    private static Element createUserElement(News news) {
        Element userElement = new Element("item");
        userElement.addContent(new Element("pubDate").setText(news.getPubDate()));
        userElement.addContent(new Element("title").setText(news.getTitle()));
        userElement.addContent(new Element("description").setText(news.getDescription()));
        userElement.addContent(new Element("author").setText(news.getAuthor()));
        userElement.addContent(new Element("link").setText(news.getLink()));


        return userElement;
    }

    public void loadData() throws IOException, JDOMException {
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(this.uri);
        this.data = builder.build(xmlFile);
    }

    private XMLOutputter preProcess() {
        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());
        return xmlOutput;
    }

    public void writeXMLFile(String uri) throws IOException {
        XMLOutputter xmlOutput = this.preProcess();
        xmlOutput.output(this.data, new FileWriter(uri));
    }

    public void printXML() throws IOException {
        XMLOutputter xmlOutput = this.preProcess();
        xmlOutput.output(this.data, System.out);
    }



    public void addNews(News news) {
        Element root = this.data.getRootElement();
        Element channel = root.getChild("channel");
        channel.addContent(createUserElement(news));
    }

    public void addAttribute(String atributteTag, String atributteValue) {
        List<News> newsList = new ArrayList<>();

        Element root = this.data.getRootElement();
        Element channel = root.getChild("channel");
        List<Element> listOfPosts = channel.getChildren("item");
        for(Element news : listOfPosts){
            news.setAttribute(new Attribute(atributteTag, atributteValue));
        }

    }
    public List<News> getNews() {
        List<News> NewsList = new ArrayList<>();
        Element root = this.data.getRootElement();
        Element channel = root.getChild("channel");

        List<Element> listaDenews = channel.getChildren("item");

        listaDenews.forEach(newsElements -> {
            News news = new News();
            news.setTitle(newsElements.getChildText("title"));
            news.setPubDate(newsElements.getChildText("pubDate"));
            news.setDescription(newsElements.getChildText("description"));
            news.setLink(newsElements.getChildText("link"));
            news.setAuthor(newsElements.getChildText("author"));

            NewsList.add(news);
        });

        return NewsList;
    }
    

    public void PubDate() {
        Element root = this.data.getRootElement();
        Element channel = root.getChild("channel");
        List<Element> listaDeNews = channel.getChildren("item");

        listaDeNews.forEach(newsElement -> {
            String pubDate = newsElement.getChildText("pubDate");

            LocalDateTime localDateTime = LocalDateTime.from(DateTimeFormatter.RFC_1123_DATE_TIME.parse(pubDate));

            int day = localDateTime.getDayOfMonth();
            int month = localDateTime.getMonthValue();
            int year = localDateTime.getYear();
            int hour = localDateTime.getHour();
            int minute = localDateTime.getMinute();
            int second = localDateTime.getSecond();


            String newPubDate = day
                    + "/"
                    + month + "/"
                    + year + "--"
                    + hour + ":"
                    + minute + ":"
                    + second;

            if (pubDate != null) {
                newsElement.getChild("pubDate").setText(newPubDate);
            }

        });

    }

    /**
     * Obtiene las descripciones del rss
     * @return
     */
    public List<String> Description() {
        XPathFactory xpath = XPathFactory.instance();
        XPathExpression<Element> expr = xpath.compile("//item/description", Filters.element());
        List<Element> news = expr.evaluate(this.data);
        List<String> description = new ArrayList<String>();
        news.forEach(post -> description.add(post.getValue().trim()));
        return description;
    }

}
