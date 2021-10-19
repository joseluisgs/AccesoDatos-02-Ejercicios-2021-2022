package sax;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import sax.model.News;

import java.util.ArrayList;
import java.util.List;

public class NewsHandler extends DefaultHandler {

    private boolean hasTitulo = false;
    private boolean hasLink = false;
    private boolean hasDescripcion = false;
    private boolean hasFecha = false;
    private boolean hasCategoria = false;

    private List<News> newsList = null;
    private News news = null;

    private boolean enEntry = false;

    public List<News> getNews() {
        return newsList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equalsIgnoreCase("item")) {
            enEntry = true;
        }

        if (enEntry) {
            if (newsList == null)
                newsList = new ArrayList<>();
            if (qName.equalsIgnoreCase("item")) {
                news = new News();
            } else if (qName.equalsIgnoreCase("title")) {
                hasTitulo = true;
            } else if (qName.equalsIgnoreCase("link")) {
                hasLink = true;
            } else if (qName.equalsIgnoreCase("description")) {
                hasDescripcion = true;
            } else if (qName.equalsIgnoreCase("pubDate")) {
                hasFecha = true;
            } else if (qName.equalsIgnoreCase("category")) {
                hasCategoria = true;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equalsIgnoreCase("item")) {
            newsList.add(news);
            enEntry = false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {

        if (hasTitulo) {
            news.setTitulo(new String(ch, start, length));
            hasTitulo = false;
        } else if (hasLink) {
            news.setLink(new String(ch, start, length));
            hasLink = false;
        } else if (hasDescripcion) {
            news.setDescripcion(new String(ch, start, length));
            hasDescripcion = false;
        } else if (hasFecha) {
            news.setFecha(new String(ch, start, length));
            hasFecha = false;
        } else if (hasCategoria) {
            news.setCategoria(new String(ch, start, length));
            hasCategoria = false;
        }
    }
}
