import java.lang.Double;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import factory.*;
import parser.*;
import textaligner.*;
import textmatchscore.*;
import tagterm.*;

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
		try {
			this.firstPath = validateFile(firstPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setSecondPath(Path secondPath) {
		try {
			this.secondPath = validateFile(secondPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	public Path validateFile(Path path) throws Exception{

		// Create a new `tagterm` object using a path to the Python executable.
		Tagterm tagterm = new Tagterm("../../Tagterminator/bin/tagterm.exe");

		// Validate the content of an HTML file.
		boolean validate_resp = tagterm.validate(path.toString());
		System.out.println("validate: " + validate_resp);

		// Convert a HTML file to XHTML.
		String converted_file = tagterm.convert(path.toString());
		System.out.println("convert: " + converted_file);

		// Remove tags within XHTML and output XML.
		String removed_file = tagterm.remove(converted_file);
		System.out.println("remove: " + removed_file);

		return Paths.get(removed_file);
	}
}
