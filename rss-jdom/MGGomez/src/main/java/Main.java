import org.jdom2.Element;
import org.jdom2.JDOMException;
import util.Noticias;
import util.Procesador;
import util.XmlBuilder;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, JDOMException {
        XmlBuilder builder = new XmlBuilder();
        Procesador pr = new Procesador();
        List <Element> list = builder.leerXml("http://ep00.epimg.net/rss/tags/ultimas_noticias.xml");
        Noticias news = pr.procesarXml(list);
        news.imprimirNoticias(news.getNews());

    }
}
