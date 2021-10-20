package util;

import org.jdom2.Element;

import java.util.ArrayList;
import java.util.List;

public class Procesador {

    public Noticias procesarXml(List<Element> channel) {
        Noticias news = new Noticias();
        List<Noticia> list = new ArrayList<>();

        for (int i = 0; i < channel.size(); i++) {

            Element item = channel.get(i);
            List<Element> items = item.getChildren("item");

            for (int j = 0; j < items.size(); j++) {
                Noticia noticia = new Noticia();
                Element campo = items.get(j);

                noticia.setTitle(campo.getChildTextTrim("title"));
                //noticia.setAuthor(campo.getChildTextTrim("dc:creator"));
                noticia.setPubDate(campo.getChildTextTrim("pubDate"));
                noticia.setBody(campo.getChildTextTrim("content:encoded"));
                noticia.setBodyopt(campo.getChildTextTrim("description"));
                noticia.setLink(campo.getChildTextTrim("link"));
                list.add(noticia);
            }
        }
        news.setNews(list);

        return news;
    }
}
