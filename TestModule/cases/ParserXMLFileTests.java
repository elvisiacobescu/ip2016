package junit.test.cases;

import StringMatching.src.parser.AlignmentParserException;
import StringMatching.src.parser.Paragraph;
import StringMatching.src.parser.ParserXMLFile;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Mihai on 16.05.2016.
 *
 */
public class ParserXMLFileTests {

    ParserXMLFile parserXMLFile;

    @Test
    public void parserTest() throws AlignmentParserException, IOException {
        String out = "";
        parserXMLFile = new ParserXMLFile("D:\\GitHub\\ip2016\\StringMatching\\tests\\samples\\slov1a.txt");
        for(Paragraph p : parserXMLFile.getParagraphs())
            out += p.getParagraphContent();
        String content;
        content = new String(Files.readAllBytes(Paths.get("C:\\Users\\Minion\\IdeaProjects\\Automated Test Cases IP\\src\\output.txt")));
        assert (out.equals(content.trim())):"Method fails due to inexact parsing, or due to lack of responsibility from this module in parsing the given files.";
    }

}