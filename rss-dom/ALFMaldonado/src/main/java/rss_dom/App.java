package rss_dom;

import java.io.PrintStream;
import java.util.List;
import java.util.Objects;

public class App {
    public App() {
    }

    public static void main(String[] args) {
        System.out.println("*** LECTOR DE NOTICIAS RSS ***");
        List<News> noticias = Controlador.getInstance().getNews("src/main/resources/noticia.rss");
        PrintStream var10001 = System.out;
        Objects.requireNonNull(var10001);
        noticias.forEach(var10001::println);
    }
}
