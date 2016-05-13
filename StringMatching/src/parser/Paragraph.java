package parser;

import java.util.Random;

public class Paragraph {

    private int paragraphNum;
    private String paragraphContent;

    Paragraph(int paragraphNum, String paragraphContent) {
        setParagraphNum(paragraphNum);
        setParagraphContent(paragraphContent);
    }

    /**
     * If a paragraph ID is not supplied, we generate a random number for it,
     * and return its negative value so as to signal that the ID was not
     * supplied.
     */
    Paragraph(String paragraphContent) {
        Random rand = new Random();
        setParagraphNum(-1 * rand.nextInt(256));
        setParagraphContent(paragraphContent);
    }

    int getParagraphNum() {
        return paragraphNum;
    }

    String getParagraphContent() {
        return paragraphContent;
    }

    void setParagraphNum(int paragraphNum) {
        this.paragraphNum = paragraphNum;
    }

    void setParagraphContent(String paragraphContent) {
        this.paragraphContent = paragraphContent;
    }

    public String toString() {
        return "Paragraph ID: " + getParagraphNum() + "\n" + getParagraphContent();
    }
}
