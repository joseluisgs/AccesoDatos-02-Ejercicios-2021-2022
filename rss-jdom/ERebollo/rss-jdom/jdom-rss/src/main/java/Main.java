import java.io.File;

public class Main {
    public static void main(String[] args) {
        String rss = "http://ep00.epimg.net/rss/tags/ultimas_noticias.xml";
        String outputXML = System.getProperty("user.dir")+ File.separator+"rss-jdom"+File.separator+"ERebollo"+File.separator+"rss-jdom"+
                File.separator+"jdom-rss"+File.separator+"src"+File.separator+"data"+File.separator+"noticias.xml";

        JDOMController jd = new JDOMController();
        jd.domToXML(new RssToDom().rssReader(rss), outputXML);
        jd.leerNoticias(outputXML);
    }
}
