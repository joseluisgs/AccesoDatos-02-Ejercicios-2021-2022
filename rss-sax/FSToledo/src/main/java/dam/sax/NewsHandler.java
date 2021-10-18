package dam.sax;

import dam.sax.model.News;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class NewsHandler extends DefaultHandler {
    private boolean hasTitle;
    private boolean hasDescription;
    private boolean hasCreator;
    private boolean hasLink;
    private boolean hasMediaDescription;
    private boolean hasMediaTitle;
    private boolean hasMediaContent;
    private boolean hasMediaThumbnail;
    private boolean hasGuid;
    private boolean hasPubdate;
    private boolean hasCategory;
    private String attributeContent;

    private List<News> newsList = null;
    private News news = null;

    public List<News> getNews(){
        return newsList;
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
        if (newsList==null) newsList = new ArrayList<>();
        if (qName.equalsIgnoreCase("item")) news = new News();

        else if(qName.equalsIgnoreCase("title")) {
            news = new News();
            hasTitle = true;}
        else if(qName.equalsIgnoreCase("description")) hasDescription = true;
        else if(qName.equalsIgnoreCase("dc:creator")) hasCreator = true;
        else if(qName.equalsIgnoreCase("link")) hasLink = true;
        else if(qName.equalsIgnoreCase("category")) hasCategory = true;
        else if(qName.equalsIgnoreCase("media:description")) hasMediaDescription = true;
        else if(qName.equalsIgnoreCase("media:title")) hasMediaTitle = true;
        else if(qName.equalsIgnoreCase("media:content")) {
            attributeContent = attributes.getValue("url");
            hasMediaContent =true;}
        else if(qName.equalsIgnoreCase("media:thumbnail")) {
            attributeContent = attributes.getValue("url");
            hasMediaThumbnail = true;}
        else if(qName.equalsIgnoreCase("guid")) hasGuid = true;
        else if(qName.equalsIgnoreCase("pubdate")) hasPubdate = true;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equalsIgnoreCase("item")) newsList.add(news);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException{
        if (hasTitle){
            news.setTitle(new String(ch, start, length));
            hasTitle = false;
        }
        else if (hasDescription){
            news.setDescription(new String(ch, start, length));
            hasDescription = false;
        }
        else if (hasCreator){
            news.setCreator(new String(ch, start, length));
            hasCreator = false;
        }
        else if (hasLink){
            news.setLink(new String(ch, start, length));
            hasLink = false;
        }
        else if(hasMediaDescription){
            news.setMediaDescription(new String(ch, start, length));
            hasMediaDescription = false;
        }
        else if(hasMediaTitle){
            news.setMediaTitle(new String(ch, start, length));
            hasMediaTitle = false;
        }
        else if(hasMediaContent){
            news.setMediaContent(attributeContent);
            hasMediaContent = false;
        }
        else if(hasMediaThumbnail){
            news.setMediaThumbnail(attributeContent);
            hasMediaThumbnail = false;
        }
        else if(hasGuid){
            news.setGuid(new String(ch, start, length));
            hasGuid = false;
        }
        else if(hasPubdate){
            news.setPubdate(new String(ch, start, length));
            hasPubdate = false;
        }
        else if (hasCategory){
            news.setCategory(new String(ch, start, length));
            hasCategory = false;
        }
    }

}
