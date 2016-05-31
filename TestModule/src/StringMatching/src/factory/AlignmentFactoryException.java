package StringMatching.src.factory;

public final class AlignmentFactoryException extends Exception {

    /**
     * Exceptions are serializable, and all serializable classes should define
     * this field, in order for the loader to check that the classes match.
     * <p>
     * For more information, read the last two paragraphs here:
     * https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
     */
    private static final long serialVersionUID = 422L;

    public AlignmentFactoryException(String message) {
        super(message);
    }
}
