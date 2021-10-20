import DOMController.DOMController;

public class Main {
	public static final String XML_URI = "";
	public static void main (String[]args) {
		DOMController xmlParser = DOMController.getInstance(XML_URI);
		try{
			xmlParser.loadData();
			xmlParser.printXML(System.out);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
