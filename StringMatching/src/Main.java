import parser.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ParserXMLFile parser = new ParserXMLFile("..\\tests\\samples\\slov1a.txt");
		for (Paragraph p : parser.getParagraphs() ){
			System.out.println(p);
		}

	}

}
