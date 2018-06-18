/*
 * James Fazio
 * jfazio2@u.rochester.edu
 * CSC 242
 * No collaboration
 */


import java.util.*;

public class ProblemTestMain {

	public static void main(String[] args) {
		ModusPonensKB mpkb = new ModusPonensKB();
		WumpusWorldKB wwkb = new WumpusWorldKB();
		UnicornsKB ukb = new UnicornsKB();
		
		TT mpT = new TT();
		System.out.println("Modus Ponens TTE test:" + "\n");
		System.out.println("Modus Ponens: " + mpT.ttEntails(mpkb, mpkb.symtab.symbols.get("Q")) + "\n");
		
		TT wwT = new TT();
		System.out.println("Wumpus World TTE test:" + "\n");
		System.out.println("Pit at P1,2: " + wwT.ttEntails(wwkb, wwkb.symtab.symbols.get("P1,2")) + "\n");
		
		TT uT = new TT();
		System.out.println("Unicorns TTE test:" + "\n");
		
		System.out.println("Prove unicorn is mythical?: " + uT.ttEntails(ukb, ukb.symtab.symbols.get("Mythical")) + "\n");
		System.out.println("Prove unicorn is magical?: " + uT.ttEntails(ukb, ukb.symtab.symbols.get("Magical")) + "\n");
		System.out.println("Prove unicorn is horned?: " + uT.ttEntails(ukb, ukb.symtab.symbols.get("Horned")) + "\n");
	}

}
