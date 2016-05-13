package parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.NamedNodeMap;

public class ParserXMLFile {

	private List<Paragraph> paragraphs;

	public ParserXMLFile (String fileName){
		paragraphs = new ArrayList<Paragraph>();
		this.init(fileName);
	}

	private void init(String fileName) {
		try {
			File inputFile = new File(fileName);

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("p");
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				NamedNodeMap nmap = nNode.getAttributes();
				int pid = Integer.parseInt(nmap.getNamedItem("id").getNodeValue());
				paragraphs.add(new Paragraph(pid, nNode.getTextContent()));
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
