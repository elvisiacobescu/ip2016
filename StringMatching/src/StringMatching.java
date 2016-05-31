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
	
	private static void initParsers(String filePathOne, String filePathTwo) throws AlignmentParserException {
		firstFile = new ParserXMLFile(filePathOne);
		secondFile = new ParserXMLFile(filePathTwo);
		normalizer = new TextNormalizer();
	}

	private static void initFactories() throws AlignmentFactoryException {
		textAlignerFactory = FactoryProducer.getFactory("Aligner");
		alignmentScoreFactory = FactoryProducer.getFactory("Score");
	}

	public static List<AlignmentPair> getParagraphPairs(String filePathOne, String filePathTwo)
			throws AlignmentParserException, AlignmentFactoryException {

		initParsers(filePathOne, filePathTwo);
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
							if( score > 0)
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
		removeDuplicates(result);
		return result;
	}

	private static void removeDuplicates(List<AlignmentPair> list) {

		for( int i = 0; i < list.size(); ++i){

			AlignmentPair iPair = list.get(i);
			for( int j = i+1; j < list.size(); ++j){

				AlignmentPair jPair = list.get(j);
				if( iPair.getFirstParagraph() == jPair.getFirstParagraph() ||
						iPair.getSecondParagraph() == jPair.getSecondParagraph() ||
						( iPair.getFirstParagraph() == jPair.getSecondParagraph() &&
							iPair.getSecondParagraph() == jPair.getFirstParagraph())
					){
					list.remove( j );
					--j;
				}
			}
		}
	}


}
