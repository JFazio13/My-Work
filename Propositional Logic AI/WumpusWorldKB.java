/*
 * File credit to Professor Ferguson
 * Edited as necessary
 */

import java.util.*;

public class WumpusWorldKB extends KB {
	
	public WumpusWorldKB() {
		super();
		Symbol p11 = new Symbol("P1,1");
		Symbol p12 = new Symbol("P1,2");
		Symbol p21 = new Symbol("P2,1");
		Symbol p22 = new Symbol("P2,2");
		Symbol p31 = new Symbol("P3,1");
		Symbol b11 = new Symbol("B1,1");
		Symbol b21 = new Symbol("B2,1");
		
		addAll(p11); // Add all possible symbols to ALL list in KB
		addAll(p12);
		addAll(p21);
		addAll(p22);
		addAll(p31);
		addAll(b11);
		addAll(b21);
		
		intern("P1,1"); // Intern symbol names to map
		intern("P1,2");
		intern("P2,1");
		intern("P2,2");
		intern("P3,1");
		intern("B1,1");
		intern("B2,1");

		add(new Negation(p11)); // Add background knowledge to KB
		add(new Biconditional(b11, new Disjunction(p12, p21)));
		add(new Biconditional(b21, new Disjunction(p12, new Disjunction(p22, p31))));
		add(new Negation(b11));
		add(b21);
	}

}
