package factory;

public class FactoryProducer {

	public static AbstractFactory getFactory(String choice){

		if(choice.equalsIgnoreCase("ALIGNER")){
			return new TextAlignerFactory();

		}else if(choice.equalsIgnoreCase("SCORE")){
			return new TextMatchScoreFactory();
		}

		return null;
	}
}