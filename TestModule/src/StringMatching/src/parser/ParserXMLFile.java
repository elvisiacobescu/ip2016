package StringMatching.src.parser;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class ParserXMLFile {

    private List<Paragraph> paragraphs;

    public ParserXMLFile(String fileName) throws AlignmentParserException {
        paragraphs = new ArrayList<Paragraph>();
        findParagraphs(fileName);
    }

    /**
     * This is the main method which does the splitting of paragraphs. After
     * instantiating a new XML document, it searches for all <p> tags, iterates
     * through them, and creates a list of paragraph objects, containing the
     * paragraph ID and contents.
     */
    private void findParagraphs(String fileName) throws AlignmentParserException {
        try {
            File inputFile = new File(fileName);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList allTags = doc.getElementsByTagName("p");

            for (int tag = 0; tag < allTags.getLength(); tag++) {
                Node oneTag = allTags.item(tag);
                NamedNodeMap attributes = oneTag.getAttributes();

                /**
                 * If the current paragraph does not have an "id" attribute,
                 * the Paragraph class will generate a random, negative one
                 * by default.
                 */
                if (attributes.getNamedItem("id") == null) {
                    paragraphs.add(new Paragraph(oneTag.getTextContent()));
                } else {
                    int pid = Integer.parseInt(attributes.getNamedItem("id").getNodeValue());
                    paragraphs.add(new Paragraph(pid, oneTag.getTextContent()));
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException err) {
            throw new AlignmentParserException(err.getMessage());
        }
    }

    public List<Paragraph> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(List<Paragraph> paragraphs) {
        this.paragraphs = paragraphs;
    }
}