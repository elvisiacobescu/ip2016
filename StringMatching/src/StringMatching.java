import java.lang.Double;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import factory.*;
import parser.*;
import textaligner.*;
import textmatchscore.*;

public class StringMatching {

    private Path firstPath;
    private Path secondPath;

    private ParserXMLFile firstFile;
    private ParserXMLFile secondFile;
    private TextNormalizer normalizer;

    private AbstractFactory textAlignerFactory;
    private AbstractFactory alignmentScoreFactory;

    public StringMatching() {
    }

    public StringMatching(Path firstPath, Path secondPath) {
        setFirstPath(firstPath);
        setSecondPath(secondPath);
    }

    public void setFirstPath(Path firstPath) {
        this.firstPath = firstPath;
    }

    public void setSecondPath(Path secondPath) {
        this.secondPath = secondPath;
    }

    private void initParsers() throws AlignmentParserException {
        firstFile = new ParserXMLFile(firstPath);
        secondFile = new ParserXMLFile(secondPath);
        normalizer = new TextNormalizer();
    }

    private void initFactories() throws AlignmentFactoryException {
        textAlignerFactory = FactoryProducer.getFactory("Aligner");
        alignmentScoreFactory = FactoryProducer.getFactory("Score");
    }

    public List<AlignmentPair> getParagraphPairs()
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
                            if (score > 0) {
                                result.add(new AlignmentPair(
                                            firstParagraph,
                                            secondParagraph,
                                            textAligner.getFirstStart(),
                                            textAligner.getSecondStart(),
                                            score));
                            }
                        } catch (AlignmentScoreException err) {
                        }
                    }
                }
            }
        }

        result.sort((a1, a2) -> new Double(a1.getScore()).compareTo(new Double(a2.getScore())));
        removeDuplicates(result);
        return result;
    }

    private void removeDuplicates(List<AlignmentPair> list) {

        for (int i = 0; i < list.size(); ++i) {
            AlignmentPair iPair = list.get(i);

            for (int j = i + 1; j < list.size(); ++j) {
                AlignmentPair jPair = list.get(j);

                if (iPair.getFirstParagraph() == jPair.getFirstParagraph() ||
                        iPair.getSecondParagraph() == jPair.getSecondParagraph()) {
                    list.remove(j);
                    --j;
                }
            }
        }
    }
}
