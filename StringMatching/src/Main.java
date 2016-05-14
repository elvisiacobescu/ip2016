import factory.*;
import parser.*;
import textaligner.*;
import textmatchscore.*;

public class Main {

    public static void main(String[] args) {
        try {
            ParserXMLFile firstFile =
                new ParserXMLFile("..\\tests\\samples\\slov1a.txt");

            ParserXMLFile secondFile =
                new ParserXMLFile("..\\tests\\samples\\slov1b.txt");

            TextNormalizer normalizer = new TextNormalizer();

            AbstractFactory textAlignerFactory =
                FactoryProducer.getFactory("Aligner");

            AbstractFactory alignmentScoreFactory =
                FactoryProducer.getFactory("Score");

            for (Paragraph firstParagraph : firstFile.getParagraphs()) {

                normalizer.setInitialText(firstParagraph.getParagraphContent());
                normalizer.normalize();
                firstParagraph.setParagraphContent(normalizer.getFinalText());

                for (Paragraph secondParagraph : secondFile.getParagraphs()) {

                    normalizer.setInitialText(secondParagraph.getParagraphContent());
                    normalizer.normalize();
                    secondParagraph.setParagraphContent(normalizer.getFinalText());

                    for (String algorithm : textAlignerFactory.getAlgorithms()) {

                        TextAligner textAligner =
                            textAlignerFactory.getTextAligner(
                                    algorithm,
                                    firstParagraph.getParagraphContent(),
                                    secondParagraph.getParagraphContent());

                        textAligner.align();
                    }
                }
            }
        } catch (AlignmentParserException | AlignmentFactoryException err) {
            System.err.println(err);
        }
    }
}
