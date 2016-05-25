package junit.test.cases;


import StringMatching.src.parser.TextNormalizer;
import org.junit.Test;

/**
 * Created by Mihai on 09.05.2016.
 *
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
        assert (textNormalizer.getStyleCorrectedText().equals("<p> Alt text <h1> <p> Text </p> </h1> akfasfvasufasufv </p>"));
    }

    @Test
    public void sanitizeTextWithoutTags(){
        textNormalizer = new TextNormalizer("Lorem,ipsum!!(sin)dolor  ?!!?sic amet. ");
        textNormalizer.normalize();
        textNormalizer.setFinalText(textNormalizer.getStyleCorrectedText());
        assert (textNormalizer.getStyleCorrectedText().equals("Lorem, ipsum!! (sin) dolor?!!? sic amet.")): "Incorrect punctuation or spaces found in output string!";
    }
}