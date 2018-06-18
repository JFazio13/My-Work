/*
 * James Fazio
 * jfazio2@u.rochester.edu
 * Project 3
 * No collaboration
 */

import java.io.*;
import java.util.*;

public class HuffmanSubmit implements Huffman {
	
	static HashMap<Character, Integer> huffMap = new HashMap(); // Stores character:frequency pairs
	HashMap<Object, String> codeMap = new HashMap();
	HashMap<Object, String> decodeMap = new HashMap();
	
	public class BTNode implements Comparable<BTNode> {
		Object data;
		int freq;
		String code;
		BTNode left;
		BTNode right;
		
		public BTNode(Object obj) {
	    	data = obj;
	    	left = right = null;
	    }
		public BTNode(Object obj, int frequency) {
	    	data = obj;
	    	freq = frequency;
	    	left = right = null;
	    	code = "";
	    }
		public BTNode(BTNode left, BTNode right) { // Used in Huffman tree construction
			this.left = left;
			this.right = right;
			freq = left.freq + right.freq;
		}
		
		public int compareTo(BTNode node) { // Used to determine which nodes are removed from the priority queue in Huffman tree construction
	        if(this.freq < node.freq) {
	        	return -1;
	        } else if(this.freq > node.freq) {
	        	return 1;
	        } else {
	        	return 0;
	        }
	    }
	}
  
	public static File freqGenerate(String inFile, String freqFile) {
		File freq = new File(freqFile);
		try {
			BinaryIn in = new BinaryIn(inFile);
			PrintWriter out = new PrintWriter(freq);
			while(!in.isEmpty()) { // Read input character-by-character
				char c = in.readChar();
				if(!huffMap.containsKey(c)) { // Creates new key in the map if it does not already exist and sets its value to 1
					huffMap.put(c, 1);
				} else { // If the key already exists, its value is increased by 1
					huffMap.replace(c, huffMap.get(c), huffMap.get(c) + 1);
				}
			}
			for(Character key : huffMap.keySet()) { // Iterates the map and prints to output (in binary)
				out.println(String.format("%8s", Integer.toBinaryString(key)).replace(' ', '0') + ":" + huffMap.get(key));
			}
			out.close();
		} catch(FileNotFoundException e) {
			System.out.println("File not found");
		}
		return freq;
	}
	
	public BTNode buildHuffTree() { // Builds initial tree in the encode() method
		PriorityQueue<BTNode> queue = new PriorityQueue();
		for (Character key : huffMap.keySet()) { // Enters all char:freq pairs to priority queue as new nodes
			queue.offer(new BTNode(key, huffMap.get(key)));
		}
		while(queue.size() > 1) { // Until only one node remains, removes lowest two frequency nodes, merges their trees, and enters the result to the p-queue
			BTNode parent = new BTNode(queue.remove(), queue.remove());
			queue.offer(parent);
		}
		BTNode node = queue.remove(); // Last node standing is the completed tree
		return node;
	}
	
	public BTNode buildHuffTree(HashMap<Object, Integer> map) { // Builds tree from frequencies in the decode() method
		PriorityQueue<BTNode> queue = new PriorityQueue();
		for (Object key : map.keySet()) { // Enters all char:freq pairs to priority queue as new nodes
			queue.offer(new BTNode(key, map.get(key)));
		}
		while(queue.size() > 1) { // Until only one node remains, removes lowest two frequency nodes, merges their trees, and enters the result to the p-queue
			BTNode parent = new BTNode(queue.remove(), queue.remove());
			queue.offer(parent);
		}
		BTNode node = queue.remove(); // Last node standing is the completed tree
		return node;
	}
	
	public void codeMap(BTNode node) { // Creates hashmap of character:huffman-code pairs
		if(node == null) { // Recursively call method until the tree has been completely traversed
			return;
		}
		if(node.data != null && !codeMap.containsKey(node.data)) { // Exclude non-character nodes and create new map entry if not present already
			codeMap.put(node.data, node.code);
		}
		codeMap(node.left); // Continue traversing left
		
		if(node.data != null && !codeMap.containsKey(node.data)) { // Exclude non-character nodes and create new map entry if not present already
			codeMap.put(node.data, node.code);
		}
		codeMap(node.right); // Continue traversing right
	}
	
	public void decodeMap(BTNode node) { // Same method as above, except it creates the decodeMap instead of the initial codeMap
		if(node == null) {
			return;
		}
		if(node.data != null && !decodeMap.containsKey(node.data)) {
			decodeMap.put(node.data, node.code);
		}
		decodeMap(node.left);
		
		if(node.data != null && !decodeMap.containsKey(node.data)) {
			decodeMap.put(node.data, node.code);
		}
		decodeMap(node.right);
	}
	
	public void huffCodes(BTNode node, StringBuffer code) { // Traverses the Huffman tree and assigns Huffman codes to each node
		if(node == null) { // Recursively call method until tree is completely traversed
			return;
		}
		node.code = code.toString(); // Traverse tree left
        code.append('0');
        huffCodes(node.left, code);
        code.deleteCharAt(code.length() - 1);
        
        node.code = code.toString(); // Traverse tree right
        code.append('1');
        huffCodes(node.right, code);
        code.deleteCharAt(code.length() - 1);
	}
	
	public void writeCodes(String inputFile, String outputFile) { // Compares inputFile to codeMap and writes encoded binary file to outputFile
		BinaryIn in = new BinaryIn(inputFile);
		BinaryOut out = new BinaryOut(outputFile);
		
		while(!in.isEmpty()) { // Read input by character
			char c = in.readChar();
			for(Object key : codeMap.keySet()) { // Locate each character in the codeMap
				if(key.equals(c)) {
					String code = codeMap.get(key);
					for(int i = 0; i < code.length(); i++) { // For each '1' or '0' in the character's code, write true or false to encoded output
						if (code.substring(i, i+1).equals(("0"))) {
		                    out.write(false);
		                } else if(code.substring(i, i+1).equals(("1"))) {
		                	out.write(true);
		                }
					}
				}
			}
		}
		out.close();
	}
	
	public String readCodes(String inputFile) { // Reads the encoded file and creates a String of 1s and 0s
		BinaryIn in = new BinaryIn(inputFile);
		String str = "";
		while(!in.isEmpty()) {
			boolean read = in.readBoolean();
			if(read) {
				str += 1;
			} else {
				str += 0;
			}
		}
		return str;
	}
	
	public void findCodes(String codes, BTNode root, String outputFile) { // Traverses the String created in readCodes() and writes the code-corresponding characters to the final output file
		BinaryOut out = new BinaryOut(outputFile);
		for (int i = 0; i < codes.length() - 1;) { // Traverse entire string of codes
			BTNode node = root;
			while (node != null && node != null) { // Traverses the reconstructed Huffman tree to assign characters to codes in the code string
				if(i >= codes.length()) { // Prevents an IndexOutOfBoundsException
					break;
				}
				char c = codes.charAt(i);
				i++;
				if (c == '1') { // If the next character of the code string is '1', traverse right
					node = node.right;
					if (node.left == null && node.right == null) { // If you are at a leaf node (character; end of current code), write the character to the output file
						out.write((char) node.data);
						break;
					}
				} else { // If the next character of the code string is '0', traverse left
					node = node.left;
					if (node.left == null && node.right == null) { // If you are at a leaf node (character; end of current code), write the character to the output file
						out.write((char) node.data);
						break;
					}
				}
			}
		}
		out.close();
	}
 
	public void encode(String inputFile, String outputFile, String freqFile) {
		System.out.println("Encoding file: '" + inputFile + "'" + "\n");
		
		System.out.println("Generating frequency file...");
		freqGenerate(inputFile, freqFile); // Generates frequency file
		
		System.out.println("Building Huffman tree...");
		BTNode node = buildHuffTree(); // Build initial Huffman tree
		
		System.out.println("Generating Huffman codes...");
		StringBuffer code = new StringBuffer();
		code.append("");
		huffCodes(node, code); // Assigns Huffman codes to each node
		
		System.out.println("Creating Huffman code map...");
		codeMap(node); // Creates a hashmap of character:Huffman-code pairs
		
		System.out.println("Encoding file...");
		writeCodes(inputFile, outputFile); // Writes codes to encoded output
		
		System.out.println("Encoding complete!" + "\n");
	}
	
   public void decode(String inputFile, String outputFile, String freqFile) {
	   try {
		   Scanner in = new Scanner(new File(freqFile)); // Reads the frequency file to recreate a hashmap of character:freq pairs
		   HashMap<Object, Integer> map = new HashMap();
		   while(in.hasNext()) { // Read frequency file line-by-line
			   String temp = in.nextLine();
			   for(int i = 0; i < temp.length() - 1; i++) { // Traverse each line
				   if(temp.substring(i, i+1).equals(":")) { // Split at ':' and discard colon; first half of string is map key, second half is map value
					   char c = (char) Integer.parseInt((String) temp.substring(0, i), 2); // Convert from binary to character
					   map.put(c, Integer.parseInt(temp.substring(i+1)));
				   }
			   }
		   }
		   in.close();
		   
		   System.out.println("Decoding file: '" + inputFile + "'" + "\n");
		   
		   System.out.println("Rebuilding Huffman tree...");
		   BTNode node = buildHuffTree(map); // Reconstructs Huffman tree from the new map of character:freq pairs
		   
		   System.out.println("Generating Huffman codes...");
		   StringBuffer code = new StringBuffer();
		   code.append("");
		   huffCodes(node, code);
		   
		   System.out.println("Recreating Huffman code map...");
		   decodeMap(node);
		   
		   System.out.println("Reading encoded file... This may take a few minutes.");
		   String codes = readCodes(inputFile);

		   System.out.println("Decoding file...");
		   findCodes(codes, node, outputFile);
		   
		   System.out.println("Decoding complete!");
		   
	   } catch(FileNotFoundException e) {
		   System.out.println("File not found");
	   }
   }

   public static void main(String[] args) {
      HuffmanSubmit huffman = new HuffmanSubmit();
      huffman.encode("ur.jpg", "out.enc", "freq.txt");
      huffman.decode("out.enc", "dec.jpg", "freq.txt");
   }
}
