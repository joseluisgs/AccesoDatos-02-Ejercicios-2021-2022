import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class NoticiasHandler extends DefaultHandler{

    private boolean hasTitle;
    private boolean hasLink;
    private boolean hasDescription;
    private boolean hasContent;
    private boolean hasPubDate;

    private List<Noticia> noticias = null;
    private Noticia noticia = null;

    public List<Noticia> getNoticias() {
        return noticias;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(noticias == null){
            noticias = new ArrayList<>();
        }
        else if (qName.equalsIgnoreCase("link")) {
            hasLink = true;
        }
        else if (qName.equalsIgnoreCase("title")) {
            noticia = new Noticia();
            hasTitle = true;
        }
        else if (qName.equalsIgnoreCase("description")) {
            hasDescription = true;
        }
        else if (qName.equalsIgnoreCase("content:encoded")) {
            hasContent = true;
        }
        else if (qName.equalsIgnoreCase("pubDate")) {
            hasPubDate = true;
        }

    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        if (hasTitle) {
            noticia.setTitulo(new String(ch, start, length));
            hasTitle = false;
        }
        if (hasLink){
            noticia.setUri(new String(ch, start, length));
            hasLink = false;
        }
        if(hasDescription){
            noticia.setDescripcion(new String(ch,start,length));
            hasDescription = false;
        }
        if(hasContent){
            noticia.setContenido(new String(ch,start,length));
            hasContent = false;
        }
        if(hasPubDate){
            noticia.setFecha(new String(ch,start,length));
            hasPubDate = false;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("item")) {
            noticias.add(noticia);
        }
    }
}
