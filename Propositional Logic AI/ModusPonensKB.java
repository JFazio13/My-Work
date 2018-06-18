/*
 * File credit to Professor Ferguson
 * Edited as necessary
 */

public class ModusPonensKB extends KB {
	
	public ModusPonensKB() {
		super();
		Symbol p = new Symbol("P");
		Symbol q = new Symbol("Q");
		
		addAll(p); // Add all possible symbols to ALL list in KB
		addAll(q);
		
		intern("P"); // Intern symbol names to map
		intern("Q");
		
		add(p); // Add background knowledge to KB
		add(new Implication(p, q));
	}

}