package DOMController;

import model.RSSItem;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DOMController {
	private String uri;
	private Document document;
	private static DOMController instance;
	private DOMController (String uri) {
		this.uri = uri;
	}

	public static DOMController getInstance(String uri) {
		if (instance == null) {
			instance = new DOMController(uri);
		}
		return instance;
	}

	public void loadData() throws ParserConfigurationException, IOException, SAXException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = factory.newDocumentBuilder();
		this.document = docBuilder.parse(this.uri);
	}

	private Transformer getPreprocessor() throws TransformerConfigurationException {
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		return transformer;
	}

	public void printXML(PrintStream out) throws TransformerException {
		this.getPreprocessor().transform(new DOMSource(this.document), new StreamResult(out));
	}

	public ArrayList<RSSItem> getRSSItems() {
		ArrayList<RSSItem> items = new ArrayList<>();
		NodeList nodes = this.document.getElementsByTagName("item");
		for (int i = 0; i < nodes.getLength(); i++) {
			RSSItem item = new RSSItem();
			for (Node node = nodes.item(i).getFirstChild(); node != null; node = node.getNextSibling()) {
				if (node.getNodeName().equals("title")) {
					item.setTitle(node.getTextContent());
				}
				if (node.getNodeName().equals("link")) {
					item.setLink(node.getTextContent());
				}
				if (node.getNodeName().equals("description")) {
					item.setDescription(node.getTextContent());
				}
				if (node.getNodeName().equals("pubDate")) {
					item.setPubDate(LocalDateTime.parse(node.getTextContent(), DateTimeFormatter.RFC_1123_DATE_TIME));
				}
			}
			items.add(item);
		}
		return items;
	}
}
