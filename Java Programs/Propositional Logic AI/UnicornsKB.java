/*
 * James Fazio
 * jfazio2@u.rochester.edu
 * CSC 242
 * No collaboration
 * Extends file credited to Professor Ferguson
 */

import java.util.*;

public class UnicornsKB extends KB {
	
	public UnicornsKB() {
		super();
		Symbol myth = new Symbol("Mythical");
		Symbol mag = new Symbol("Magical");
		Symbol mort = new Symbol("Mortal");
		Symbol horn = new Symbol("Horned");
		
		addAll(myth);
		addAll(mag);
		addAll(mort);
		addAll(horn);
		
		intern("Mythical");
		intern("Magical");
		intern("Mortal");
		intern("Horned");

		add(new Implication(myth, new Negation(mort)));
		add(new Implication(new Negation(myth), mort));
		add(new Implication(new Disjunction(new Negation(mort), new Negation(myth)), horn));
		add(new Implication(horn, mag));
	}

}
