package sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import sax.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserHandler extends DefaultHandler {
    private boolean hasPubDate = false;
    private boolean hasTitle = false;
    private boolean hasDescription = false;
    private boolean hasLink = false;
    private boolean hasAuthor = false;
    private boolean hasCategory=false;

    //Donde vamos a almacenar nuestros objetos
    private List<User> postList = null;
    private User news = null;

    private boolean enEntry = false;

    /**
     * Getter de Lista de usuarios
     *
     * @return
     */
    public List<User> getNews() {
        return postList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("item")) {
            enEntry = true;
        }


        // Iniciar lista
        if (postList == null) {
            postList = new ArrayList<>();
        }

        if (enEntry) {
            if (qName.equalsIgnoreCase("item")) {
                news = new User();
            } else if (qName.equalsIgnoreCase("pubDate")) {
                hasPubDate = true;
                } else if (qName.equalsIgnoreCase("category")) {
                 hasCategory = true;
            } else if (qName.equalsIgnoreCase("title")) {
                hasTitle = true;
            } else if (qName.equalsIgnoreCase("description")) {
                hasDescription = true;
            } else if (qName.equalsIgnoreCase("link")) {
                hasLink = true;
            } else if (qName.equalsIgnoreCase("author")) {
                hasAuthor = true;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("item")) {
            postList.add(news);
            enEntry = false;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        if (hasPubDate) {
            news.setPubDate(new String(ch, start, length));
            hasPubDate = false;
        } else if (hasTitle) {
            news.setTitle(new String(ch, start, length));
            hasTitle = false;
        } else if (hasDescription) {
            news.setDescription(new String(ch, start, length));
            hasDescription = false;
        } else if (hasLink) {
            news.setLink(new String(ch, start, length));
            hasLink = false;
        } else if (hasAuthor) {
            news.setAuthor(new String(ch, start, length));
            hasAuthor = false;
        } else if (hasCategory){
            news.setCategory(new String(ch, start, length));
            hasCategory = false;
        }
    }
}
