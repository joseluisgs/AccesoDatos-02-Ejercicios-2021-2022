package com.madirex.jdom;

import com.madirex.jdom.model.Post;
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

    private static Element createPostElement(Post post) {
        Element postElement = new Element("item");
        postElement.addContent(new Element("pubDate").setText(post.getPubDate()));
        postElement.addContent(new Element("title").setText(post.getTitle()));
        postElement.addContent(new Element("description").setText(post.getDescription()));
        postElement.addContent(new Element("link").setText(post.getLink()));
        postElement.addContent(new Element("author").setText(post.getAuthor()));
        return postElement;
    }

    public void loadData() throws IOException, JDOMException {
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(this.uri);
        this.data = (Document) builder.build(xmlFile);
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

    public List<Post> getPosts() {
        List<Post> postList = new ArrayList<>();
        Element root = (Element) this.data.getRootElement();
        Element channel = root.getChild("channel");

            List<Element> listOfPosts = channel.getChildren("item");

            listOfPosts.forEach(postElement -> {
                Post post = new Post();
                post.setPubDate(postElement.getChildText("pubDate"));
                post.setTitle(postElement.getChildText("title"));
                post.setDescription(postElement.getChildText("description"));
                post.setLink(postElement.getChildText("link"));
                post.setAuthor(postElement.getChildText("author"));
                postList.add(post);
            });

        return postList;
    }

    public void addPost(Post post) {
        Element root = (Element) this.data.getRootElement();
        Element channel = root.getChild("channel");
        channel.addContent(createPostElement(post));
    }

    public void addAttribute(String atributoTag, String atributoValue) {
        List<Post> postList = new ArrayList<>();

        Element root = (Element) this.data.getRootElement();
        Element channel = root.getChild("channel");
        List<Element> listOfPosts = channel.getChildren("item");
        for(Element post : listOfPosts){
            post.setAttribute(new Attribute(atributoTag, atributoValue));
        }

    }

    public void addElementAndAtribute(String tag, String value, String atributoTag, String atributoValue) {
        Element root = this.data.getRootElement();
        Element channel = root.getChild("channel");
        List<Element> listOfPosts = channel.getChildren("item");
        listOfPosts.forEach(postElement -> {
            postElement.addContent(new Element(tag).setText(value)
                            .setAttribute(new Attribute(atributoTag,atributoValue))
                    );
        });
    }

    public void setPubDateFormat() {
        Element root = this.data.getRootElement();
        Element channel = root.getChild("channel");
        List<Element> listOfPosts = channel.getChildren("item");

        listOfPosts.forEach(postElement -> {
            String pubDate = postElement.getChildText("pubDate");

            LocalDateTime localDateTime = LocalDateTime.from(DateTimeFormatter.RFC_1123_DATE_TIME.parse(pubDate));

            int day = localDateTime.getDayOfMonth();
            int month = localDateTime.getMonthValue();
            int year = localDateTime.getYear();
            int hour = localDateTime.getHour();
            int minute = localDateTime.getMinute();
            int second = localDateTime.getSecond();

            //DD/MM/AAAA a las HH:MM:SS
            String newPubDate = day + "/" + month + "/" + year
                    + " a las " + hour + ":" + minute + ":" + second;

            if (pubDate != null) {
                postElement.getChild("pubDate").setText(newPubDate);
            }

        });

    }

    // XPATH
    public List<String> getAllLinks() {
        XPathFactory xpath = XPathFactory.instance();
        XPathExpression<Element> expr = xpath.compile("//item/link", Filters.element());
        List<Element> posts = expr.evaluate(this.data);
        List<String> links = new ArrayList<String>();
        posts.forEach(post -> links.add(post.getValue().trim()));
        return links;
    }

    public List<String> getAllTitles() {
        XPathFactory xpath = XPathFactory.instance();
        XPathExpression<Element> expr = xpath.compile("//item/title", Filters.element());
        List<Element> posts = expr.evaluate(this.data);
        List<String> titles = new ArrayList<String>();
        posts.forEach(post -> titles.add(post.getValue().trim()));
        return titles;
    }

}