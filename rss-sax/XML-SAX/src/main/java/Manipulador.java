import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/*
    La clase Manipulador (HANDLER) manipula y controla los elementos
     El método startElement se llamará al inicio de un elemento
     El método characters  se llamará cuando se encuentre la información dentro de un elemento
     El método endElement se llamará al final de un elemento
 */
public class Manipulador extends DefaultHandler {
    //Declaro los elementos que van dentro del ITEM a leer y los DECLARAMOS BOOLEANOS para los metodos
    private boolean title;
    private boolean image;
    private boolean link;
    private boolean author;
    private boolean guid;
    private boolean pubDate;
    //creamos el objeto noticia para almacenarlo en una lista dependiendo de los getters y seters del pojo noticia.
    private PojoNoticia noticia = new PojoNoticia();
    private final List<PojoNoticia> item= new ArrayList<>();

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals(ElementosNoticia.ITEM.getName())) {
            title = true;
        }
        if (qName.equals(ElementosNoticia.IMAGE.getName())) {
            image = true;
        }
        if (qName.equals(ElementosNoticia.LINK.getName())) {
            link = true;
        }
        if (qName.equals(ElementosNoticia.AUTHOR.getName())) {
            author = true;
        }
        if (qName.equals(ElementosNoticia.GUID.getName())) {
            guid = true;
        }
        if (qName.equals(ElementosNoticia.PUBDATE.getName())) {
            pubDate = true;
        }
    }
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (title) {
            noticia.setTitle(new String(ch, start, length));
            title = false;
        }
        if (image) {
            noticia.setImage(new String(ch, start, length));
            image = false;
        }
        if (link) {
            noticia.setLink(new String(ch, start, length));
            link = false;
        }
        if (author) {
            noticia.setAuthor(new String(ch, start, length));
            author =false;
        }
        if (guid) {
            noticia.setGuid(new String(ch, start, length));
            guid =false;
        }
        if (pubDate) {
            noticia.setPubDate(new String(ch, start, length));
            pubDate =false;
        }
    }
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals(ElementosNoticia.ITEM.getName())) {
            item.add(noticia);
            noticia = new PojoNoticia();
        }
    }
    public List<PojoNoticia> getItem() {
        return item;
    }

}
