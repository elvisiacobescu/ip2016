package StringMatching.src.factory;

public final class FactoryProducer {

    public static AbstractFactory getFactory(String choice)
            throws AlignmentFactoryException {

        if (choice.equalsIgnoreCase("ALIGNER")) {
            return new TextAlignerFactory();
        } else if (choice.equalsIgnoreCase("SCORE")) {
            return new TextMatchScoreFactory();
        }

        String error = "Invalid factory. Valid choices are Aligner and Score.";
        throw new AlignmentFactoryException(error);
    }
}
