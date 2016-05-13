package parser;

public class AlignmentParserException extends Exception {

    /**
     * Exceptions are serializable, and all serializable classes should define
     * this field, in order for the loader to check that the classes match.
     *
     * For more information, read the last two paragraphs here:
     * https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
     *
     */
    private static final long serialVersionUID = 420L;

    public AlignmentParserException(String message) {
        super(message);
    }
}
