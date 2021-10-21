public class App {
    public static void main(String[] args) {
        String uri = "http://ep00.epimg.net/rss/tags/ultimas_noticias.xml";

        SaxReader reader = new SaxReader();
        reader.rssToList(uri);
        reader.getNoticias().forEach(System.out::println);
    }
}
