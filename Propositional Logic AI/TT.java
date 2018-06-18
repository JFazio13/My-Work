/*
 * James Fazio
 * jfazio2@u.rochester.edu
 * CSC 242
 * No collaboration
 */


import java.util.*;

public class TT {
	
	public TT() { // New TT takes no parameters
		
	}
	
	public static <E> E first(List<E> list) { // Get first element of list
		return list.get(0);
	}
	
	public static <E> List<E> rest(List<E> list) { // Get list after first element
		return list.subList(1, list.size());
	}
	
	public boolean ttEntails(KB kb, Sentence alpha) { // Takes in a KB and a sentence and uses model checking to determine entailment
		System.out.println("Checking applicable models...");
		List<Sentence> symbols = new ArrayList(kb.sentencesAll); // List of all sentences in Knowledge base and alpha
		return checkModels(kb, alpha, symbols, new LogicModel());
	}
	
	private boolean checkModels(KB kb, Sentence alpha, List<Sentence> symbols, LogicModel model) { // Performs model checking to determine entailment
		if (symbols.isEmpty()) { // If there are no sentences left, check KB and alpha
			if (model.satisfies(kb)) { // If KB is satisfied by model, return model.satisfies(alpha)
				System.out.println("KB: " + model.satisfies(kb));
				System.out.println("Alpha: " + model.satisfies(alpha));
				return model.satisfies(alpha);
			} else {
				// Else, return true;
				return true;
			}
		} // If symbols is not empty...
		Sentence p = first(symbols);
		List<Sentence> rest = rest(symbols);
		if(p instanceof CompoundSentence && !(p instanceof Negation)) { // Do not attempt to create new model using CompoundSentence (unless Negation)
			p = first(rest);
			rest = rest(symbols);
		}
		return checkModels(kb, alpha, rest, model.newModel(p, true)) && checkModels(kb, alpha, rest, model.newModel(p, false));
	}
	
}
