/*
 * James Fazio
 * jfazio2@u.rochester.edu
 * CSC 242
 * No collaboration
 * Implements interface credited to Professor Ferguson
 */


import java.util.*;

public class LogicModel implements Model {
	
	protected List<Symbol> symbolList = new ArrayList(); // List of Symbols
	protected List<Boolean> assignmentList = new ArrayList(); // List of assignments for Symbols
	protected HashMap<Sentence, Boolean> assignments = new HashMap(); // Hash map of Sentence:Boolean pairs
	
	public LogicModel() { // Empty model
		
	}
	
	public LogicModel(Map<Symbol, Boolean> values) { // Create new model from existing model/values
		assignments.putAll(values);
	}
	
	public void set(Symbol sym, boolean value) { // Set the value of an individual symbol
		sym.value = value;
		symbolList.add(sym);
		assignmentList.add(value);
	}
	
	public boolean get(Symbol sym) { // Get value of an individual symbol
		return sym.value;
	}
	
	public boolean satisfiesClause(Clause c) { // Determines if a clause is satisfied by a model
		return c.isSatisfiedBy(this);
	}
	
	public boolean satisfiesClauseSet(Set<Clause> clauses) { // Determines if a set of clauses is determined by a model
		for(Clause c : clauses) { // If all clauses are true, set is true
			boolean value = c.isSatisfiedBy(this);
			if(value == false) {
				return false;
			}
		}
		return true;
	}
	
	public boolean satisfies(KB kb) { // Determines if a KB is satisfied by a model
		boolean evaluate = satisfies(kb, 0);
		return evaluate;
	}
	
	private boolean satisfies(KB kb, int index) { // If all sentences in a KB are true; KB is satisfied
		if(index == kb.sentences.size()) {
			return true;
		}
		boolean evaluate = satisfies(kb.sentences.get(index));
		if(evaluate == false) {
			return false;
		} else {
			return satisfies(kb, index + 1);
		}
	}
	
	public boolean satisfies(Sentence sentence) { // Determines if a sentence is satisfied by a model
		if(sentence instanceof CompoundSentence) {
			if(sentence instanceof Negation) {
				return !satisfies(((Negation) sentence).argument);
			}
			return sentence.isSatisfiedBy(this);
		}
		if(assignments.get(sentence) == true) {
			return true;
		} else {
			return false;
		}
	}
	
	public LogicModel newModel(Sentence symbol, boolean b) { // Creates and returns a new model from an existing model and a new sentence
		LogicModel model = new LogicModel();
		model.assignments.putAll(this.assignments);
		if(symbol instanceof Negation) {
			model.assignments.put(((Negation) symbol).argument, b);
		} else {
			model.assignments.put(symbol, b);
		}
		return model;
	}
	
	public LogicModel flip(Symbol s) { // Create new model by fliping all values in current model
		if (satisfies(s)) {
			return newModel(s, false);
		}
		if (!satisfies(s)) {
			return newModel(s, true);
		}
		return this;
	}
	
	public void dump() {
		for(int i = 0; i < symbolList.size(); i++) {
			System.out.println(symbolList.get(i) + ":" + assignmentList.get(i));
		}
	}

}
