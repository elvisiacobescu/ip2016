package parser;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ParserXMLFile parser = new ParserXMLFile("demo.txt");
		for (String str : parser.getParagraphs() ){
			System.out.println(str);
		}
		
	}

}
