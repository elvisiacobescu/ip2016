package parser;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.w3c.dom.Node;

public class ParserXMLString {

	private List<String> paragraphs;

	public ParserXMLString (String xmlString){
		paragraphs = new ArrayList<String>();
		this.init(xmlString);
	}

	private void init(String xmlString) {
		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new InputSource( new StringReader( xmlString )) );
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("p");
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				TextNormalizer sanitizedText = new TextNormalizer(nNode.getTextContent());
				paragraphs.add(sanitizedText.getClearedText());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the paragraphs
	 */
	public List<String> getParagraphs() {
		return paragraphs;
	}

	/**
	 * @param paragraphs the paragraphs to set
	 */
	public void setParagraphs(List<String> paragraphs) {
		this.paragraphs = paragraphs;
	}
}
