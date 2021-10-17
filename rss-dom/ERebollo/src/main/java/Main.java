public class Main {
    public static void main(String[] args) {
        String rss = "http://ep00.epimg.net/rss/tags/ultimas_noticias.xml";

        RSSReader rr = RSSReader.getInstance(rss);

        System.out.println("Recuento de las ultimas noticias");
        rr.leerRSS();
        System.out.println("fin de las ultimas noticias");
    }
}
