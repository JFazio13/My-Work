/*
 * File credit to Professor Ferguson
 * No further edits
 */



public class Negation extends UnaryCompoundSentence {
	
	protected Sentence argument;
	
	public Negation(Sentence argument) {
		super(UnaryConnective.NOT, argument);
		this.argument = argument;
	}

	/**
	 * Return true if this Negation is satisfied by the given Model.
	 * That is, if its argument is not satisfied by the Model.
	 */
	public boolean isSatisfiedBy(Model model) {
		return !argument.isSatisfiedBy(model);
	}

}
