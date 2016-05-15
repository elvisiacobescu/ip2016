import factory.*;
import parser.*;
import textaligner.*;
import textmatchscore.*;

public class Main {

    private static ParserXMLFile firstFile;
    private static ParserXMLFile secondFile;
    private static TextNormalizer normalizer;

    private static AbstractFactory textAlignerFactory;
    private static AbstractFactory alignmentScoreFactory;

    private static void initParsers() throws AlignmentParserException {
        firstFile = new ParserXMLFile("..\\tests\\samples\\slov1a.txt");
        secondFile = new ParserXMLFile("..\\tests\\samples\\slov1b.txt");
        normalizer = new TextNormalizer();
    }

    private static void initFactories() throws AlignmentFactoryException {
        textAlignerFactory = FactoryProducer.getFactory("Aligner");
        alignmentScoreFactory = FactoryProducer.getFactory("Score");
    }

    public static void main(String[] args) {
        try {
            initParsers();
            initFactories();
        } catch (AlignmentParserException | AlignmentFactoryException err) {
            System.err.println(err);
            System.exit(1);
        }

        for (Paragraph firstParagraph : firstFile.getParagraphs()) {

            normalizer.setInitialText(firstParagraph.getParagraphContent());
            normalizer.normalize();
            firstParagraph.setParagraphContent(normalizer.getFinalText());

            for (Paragraph secondParagraph : secondFile.getParagraphs()) {

                normalizer.setInitialText(secondParagraph.getParagraphContent());
                normalizer.normalize();
                secondParagraph.setParagraphContent(normalizer.getFinalText());

                for (String algorithm : textAlignerFactory.getAlgorithms()) {

                    TextAligner textAligner;
                    try {
                        textAligner =
                            textAlignerFactory.getTextAligner(
                                    algorithm,
                                    firstParagraph.getParagraphContent(),
                                    secondParagraph.getParagraphContent());

                        textAligner.align();
                    } catch (AlignmentFactoryException err) {
                        System.err.println(err);
                        continue;
                    }

                    String firstAlignment =
                        firstParagraph.getParagraphContent().substring(
                                textAligner.getFirstStart());

                    String secondAlignment =
                        secondParagraph.getParagraphContent().substring(
                                textAligner.getSecondStart());

                    for (String calculator : alignmentScoreFactory.getAlgorithms()) {

                        TextMatchScore scoreCalculator;
                        try {
                            scoreCalculator =
                                alignmentScoreFactory.getTextMatchScore(
                                        calculator,
                                        firstAlignment,
                                        secondAlignment);
                        } catch (AlignmentFactoryException err) {
                            System.err.println(err);
                            continue;
                        }

                        try {
                            double score = scoreCalculator.getScore();

                            System.out.print("First Paragraph ID: ");
                            System.out.println(firstParagraph.getParagraphNum());

                            System.out.print("Second Paragraph ID: ");
                            System.out.println(secondParagraph.getParagraphNum());

                            System.out.print("Aligner: ");
                            System.out.println(algorithm);

                            System.out.print("Calculator: ");
                            System.out.println(calculator);

                            System.out.print("Score: ");
                            System.out.println(score);

                            System.out.println("");
                        } catch (AlignmentScoreException err) {
                            System.err.println(err);
                        }
                    }
                }
            }
        }
    }
}
