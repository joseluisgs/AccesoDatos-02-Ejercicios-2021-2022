import DOMController.DOMController;

public class Main {
	public static final String XML_URI = "http://ep00.epimg.net/rss/tags/ultimas_noticias.xml";
	public static void main (String[]args) {
		DOMController xmlParser = DOMController.getInstance(XML_URI);
		try{
			xmlParser.loadData();
			xmlParser.getRSSItems().forEach(System.out::println);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
