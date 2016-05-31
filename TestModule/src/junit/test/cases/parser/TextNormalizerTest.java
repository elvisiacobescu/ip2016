package junit.test.cases.parser;


import StringMatching.src.parser.TextNormalizer;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Mihai on 09.05.2016.
 */
public class TextNormalizerTest {

    private TextNormalizer textNormalizer;

    @Test
    public void sanitizeTest() throws Exception {
        textNormalizer = new TextNormalizer("<p> " +
                "                                                       Alt text " +
                "                                                       <h1>   " +
                "                                                          <p>" +
                "                                                             Text    " +
                "                                                          </p>   " +
                "                                                       </h1> akfasfvasufasufv  " +
                "                                                   </p>");

        textNormalizer.clearText();
        textNormalizer.removeExtraSpaces();
        textNormalizer.correctStyle();
        textNormalizer.setFinalText(textNormalizer.getStyleCorrectedText());
        System.out.println(textNormalizer.getFinalText());

    }

    @Test
    public void sanitizeTextWithoutTags() {

        textNormalizer = new TextNormalizer("Lorem,ipsum!!(sin)dolor  ?!!?sic amet. ");
        textNormalizer.normalize();
        textNormalizer.setFinalText(textNormalizer.getStyleCorrectedText());
        System.out.println(textNormalizer.getFinalText());

    }


    @Test
    public void sanitizeFromReadFile() throws IOException {


        String contents = new String(Files.readAllBytes(Paths.get("C:\\Users\\Minion\\IdeaProjects\\Automated Test Cases IP\\src\\fisiere_test\\011-MOJDAR-81388-86085_gh.html")));
        textNormalizer = new TextNormalizer(contents);
        textNormalizer.normalize();
        textNormalizer.setFinalText(textNormalizer.getStyleCorrectedText());
        PrintWriter fileOutput = new PrintWriter("C:\\Users\\Minion\\Desktop\\rezultate_teste\\rezultate_nomalizare.html");
        fileOutput.write(textNormalizer.getFinalText());

    }


    @Test
    public void sanitizeFromReadFileHTML() throws IOException {

        String contents = new String(Files.readAllBytes(Paths.get("C:\\Users\\Minion\\IdeaProjects\\Automated Test Cases IP\\src\\fisiere_test\\videos.xml")));
        textNormalizer = new TextNormalizer(contents);
        textNormalizer.normalize();
        textNormalizer.setFinalText(textNormalizer.getStyleCorrectedText());
        PrintWriter fileOutput = new PrintWriter("C:\\Users\\Minion\\Desktop\\rezultate_teste\\rezultate_normalizare2.html");
        fileOutput.write(textNormalizer.getFinalText());
        System.out.println("Fisierul trebuie sa fie respins, vedem asta prin outputul vid");

    }

}