import parser.*;

public class Main {

	public static void main(String[] args) {
		try {
		    ParserXMLFile parser =
		        new ParserXMLFile("..\\tests\\samples\\slov2a.txt");

		    for (Paragraph p : parser.getParagraphs()) {
			    System.out.println(p);
		    }
		} catch (AlignmentParserException err) {
		    System.err.println(err);
		}
	}
}
