import rss.Noticia;
import rss.RSSController;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("*** LECTOR DE NOTICIAS RSS ***");
        List<Noticia> noticias = RSSController.getInstance().getNoticias("https://www.europapress.es/rss/rss.aspx?ch=00066");

        if (noticias.size() > 0) {
            System.out.println("Se han encontrado: " + noticias.size() + " noticias");
            noticias.forEach(System.out::println);
        } else {
            System.out.println("No se han encontrado noticias");
        }
    }
}