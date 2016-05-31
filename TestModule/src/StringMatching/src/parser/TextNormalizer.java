package StringMatching.src.parser;

public final class TextNormalizer {

    private String initialText;
    private String clearedText;
    private String spacesRemovedText;
    private String styleCorrectedText;
    private String finalText;

    public TextNormalizer(String text) {
        initialText = text;
    }

    public TextNormalizer() {
    }

    public void setInitialText(String text) {
        initialText = text;
    }

    public String getInitialText() {
        return initialText;
    }

    public void setClearedText(String text) {
        clearedText = text;
    }

    public String getClearedText() {
        return clearedText;
    }

    public void setSpacesRemovedText(String text) {
        spacesRemovedText = text;
    }

    public String getSpacesRemovedText() {
        return spacesRemovedText;
    }

    public void setStyleCorrectedText(String text) {
        styleCorrectedText = text;
    }

    public String getStyleCorrectedText() {
        return styleCorrectedText;
    }

    public void setFinalText(String text) {
        finalText = text;
    }

    public String getFinalText() {
        return finalText;
    }

    public void clearText() {
        setClearedText(getInitialText().trim());
        setClearedText(getClearedText().replace("\r", ""));
        setClearedText(getClearedText().replace('\t', ' '));
    }

    public void removeExtraSpaces() {
        String paragraphPattern = "([.!?])\\n+";
        String middlePattern = "([^.!?])\\n+";

        setSpacesRemovedText(
                getClearedText().replaceAll(paragraphPattern, "$1\n"));

        setSpacesRemovedText(
                getSpacesRemovedText().replaceAll(middlePattern, "$1 "));

        setSpacesRemovedText(
                getSpacesRemovedText().replaceAll(" +", " "));
    }

    public void correctStyle() {
        setStyleCorrectedText(
                getSpacesRemovedText().replaceAll(" ([.,!?)-])", "$1"));

        setStyleCorrectedText(
                getStyleCorrectedText().replaceAll("([(-]) ", "$1"));

        setStyleCorrectedText(getStyleCorrectedText().replace("--", " -- "));

        setStyleCorrectedText(
                getStyleCorrectedText().replaceAll("([a-zA-Z])\\(", "$1 ("));

        setStyleCorrectedText(
                getStyleCorrectedText().replaceAll("([.,!?)])([a-zA-Z])",
                        "$1 $2"));
    }

    public void normalize() {
        clearText();
        removeExtraSpaces();
        correctStyle();
        setFinalText(getStyleCorrectedText());
    }
}
