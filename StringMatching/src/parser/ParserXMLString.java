package parser;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class ParserXMLString {

	private List<Paragraph> paragraphs;

	public ParserXMLString (String xmlString) {
		paragraphs = new ArrayList<Paragraph>();
		init(xmlString);
	}

	private void init(String xmlString) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new InputSource(new StringReader(xmlString)));
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("p");
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				TextNormalizer sanitizedText =
				    new TextNormalizer(nNode.getTextContent());
				paragraphs.add(sanitizedText.getClearedText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the paragraphs
	 */
	public List<Paragraph> getParagraphs() {
		return paragraphs;
	}

	/**
	 * @param paragraphs the paragraphs to set
	 */
	public void setParagraphs(List<Paragraph> paragraphs) {
		this.paragraphs = paragraphs;
	}
}
