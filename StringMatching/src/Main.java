import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import factory.*;
import parser.*;

public class Main {

    public static void main(String[] args) {
        try {
            Path firstPath = Paths.get("..", "tests", "samples", "slov1a.txt");
            Path secondPath = Paths.get("..", "tests", "samples", "slov1b.txt");
            StringMatching stringMatching =
                new StringMatching(firstPath, secondPath);

            List<AlignmentPair> list = stringMatching.getParagraphPairs();
            for (AlignmentPair pair : list) {
                System.out.println(pair);
            }
            //save(filePathOne,filePathTwo,list);
        } catch (AlignmentParserException|AlignmentFactoryException e) {
            e.printStackTrace();
        }

    }

    public static void save(String filePathOne, String filePathTwo, List<AlignmentPair> list) {

        BufferedWriter br = null;

        try {
            FileWriter fileWriter = new FileWriter("file.txt");
            br = new BufferedWriter(fileWriter);
            br.write(filePathOne+'\n');
            br.write(filePathTwo+'\n');

            for (int i = 0; i < list.size(); i++) {
                br.write(list.get(i) + "\n");
            }
        } catch( IOException e ){
            e.printStackTrace();
        } finally{
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
