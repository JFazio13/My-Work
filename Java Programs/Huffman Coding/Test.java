import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Test {
	
	public static void test(String inFile, String outFile) {
		HashMap<String, Integer> map = new HashMap();
		try {
			BinaryIn in = new BinaryIn(inFile);
			PrintWriter out = new PrintWriter(new File(outFile));
			
			while(!in.isEmpty()) {
				char c = in.readChar();
				String bit = String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0');
				if(!map.containsKey(bit)) { // Creates new key in the map if it does not already exist and sets its value to 1
					map.put(bit, 1);
				} else { // If the key already exists, its value is increased by 1
					map.replace(bit, map.get(bit), map.get(bit) + 1);
				}
			}
			for (String key : map.keySet()) { // Iterates the map and prints to output
				out.println(key + ":" + map.get(key));
			}
			out.close();
		} catch(FileNotFoundException e) {
			System.out.println("File not found");
		}
	}
	
	public static void main(String[] args) throws IOException {
		test("ur.jpg", "out.txt");
	}

}
