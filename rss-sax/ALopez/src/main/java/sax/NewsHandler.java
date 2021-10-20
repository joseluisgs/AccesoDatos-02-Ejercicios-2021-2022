package sax;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;


public class NewsHandler extends DefaultHandler {

    private boolean hasguide = false;
    private boolean hasTitle = false;
    private boolean hasPubDate = false;
    private boolean haslink = false;
    private boolean hasCategory = false;
    private boolean hasContent = false;

    private List<News> nuevaList= null;
    private News newsaux= null;

    private boolean enEntry= false;
    public List<News> getNews(){return nuevaList;}

    @Override
    public void startElement(String urii, String localName, String tname, Attributes attributes) throws SAXException {
        if (tname.equalsIgnoreCase("item")){
            enEntry = true;
        }

        if (enEntry) {
            if (nuevaList == null)
                nuevaList = new ArrayList<>();
            if (tname.equalsIgnoreCase("item")) {
                newsaux = new News();
            } else if (tname.equalsIgnoreCase("guid")) {
                hasguide = true;
            } else if (tname.equalsIgnoreCase("title")) {
                hasTitle = true;
            } else if (tname.equalsIgnoreCase("pubDate")) {
                hasPubDate = true;
            } else if (tname.equalsIgnoreCase("link")) {
                haslink= true;
            } else if (tname.equalsIgnoreCase("category")) {
                hasCategory = true;
            } else if (tname.equalsIgnoreCase("content")) {
                String img = attributes.getValue("url");

            }
        }
    }

    @Override
    public void endElement(String urii, String localName, String tname) throws SAXException {
        if (tname.equalsIgnoreCase("item")) {
            nuevaList.add(newsaux);
            enEntry = false;
        }

    }



}
