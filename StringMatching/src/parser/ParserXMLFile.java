package parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class ParserXMLFile {

	private List<String> paragraphs;

	public ParserXMLFile (String fileName){
		paragraphs = new ArrayList<String>();
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
				paragraphs.add(nNode.getTextContent());
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