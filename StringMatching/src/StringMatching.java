import java.lang.Double;
import java.util.ArrayList;
import java.util.List;

import factory.*;
import parser.*;
import textaligner.*;
import textmatchscore.*;

public class StringMatching {

    private static ParserXMLFile firstFile;
    private static ParserXMLFile secondFile;
    private static TextNormalizer normalizer;

    private static AbstractFactory textAlignerFactory;
    private static AbstractFactory alignmentScoreFactory;

    private static void initParsers() throws AlignmentParserException {
        firstFile = new ParserXMLFile("../tests/samples/slov1a.txt");
        secondFile = new ParserXMLFile("../tests/samples/slov1b.txt");
        normalizer = new TextNormalizer();
    }

    private static void initFactories() throws AlignmentFactoryException {
        textAlignerFactory = FactoryProducer.getFactory("Aligner");
        alignmentScoreFactory = FactoryProducer.getFactory("Score");
    }

    public static List<AlignmentPair> getParagraphPairs(String[] args)
            throws AlignmentParserException, AlignmentFactoryException {

        initParsers();
        initFactories();

        ArrayList<AlignmentPair> result = new ArrayList<AlignmentPair>();

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
                            continue;
                        }

                        try {
                            double score = scoreCalculator.getScore();
                            result.add(new AlignmentPair(
                                        firstParagraph,
                                        secondParagraph,
                                        textAligner.getFirstStart(),
                                        textAligner.getSecondStart(),
                                        score));
                        } catch (AlignmentScoreException err) {
                        }
                    }
                }
            }
        }

        result.sort((a1, a2) -> new Double(a1.getScore()).compareTo(new Double(a2.getScore())));
        return result;
    }
}
