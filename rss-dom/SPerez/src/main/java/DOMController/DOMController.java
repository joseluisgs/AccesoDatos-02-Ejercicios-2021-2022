package DOMController;

import org.w3c.dom.Document;
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
		this.document = docBuilder.parse(new File(this.uri));
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
}
