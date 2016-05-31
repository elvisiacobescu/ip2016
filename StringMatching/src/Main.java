import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import factory.*;
import parser.*;

public class Main {

    public static void main(String[] args) {
        try {
        	String filePathOne = "../tests/samples/slov1a.txt";
        	String filePathTwo = "../tests/samples/slov1b.txt";
			List<AlignmentPair> list = StringMatching.getParagraphPairs(filePathOne,filePathTwo);
			for( AlignmentPair pair : list){
				System.out.println(pair);
			}
			save(filePathOne,filePathTwo,list);
		} catch (AlignmentParserException|AlignmentFactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    
    public static void save(String filePathOne, String filePathTwo, List<AlignmentPair> list){
    	
		BufferedWriter br = null;
		try{
			
			FileWriter fileWriter = new FileWriter("file.txt");
			br = new BufferedWriter(fileWriter);
			br.write(filePathOne+'\n');
			br.write(filePathTwo+'\n');
			
			for(int i = 0; i < list.size(); i++){
				
				br.write(list.get(i) + "\n");
			}
		}
		catch( IOException e ){
			e.printStackTrace();
		}
		finally{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
