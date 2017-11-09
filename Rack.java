// Name: Shang-Fu Hsieh
// USC NetID: 5379158999
// CS 455 PA4
// Spring 2017

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A Rack of Scrabble tiles
 */

public class Rack {
	
	private String original; // string with original form
	private String unique; // string with unique characters
	private int[] multi; // integer array for multiplicity
	private ArrayList<String> subset; // all of possible subset for input string 
	
	/**
	 * 	1. for input string, convert to all upper case
	 * 	2. find out string with unique letter and multiplicity
	 * 	3. find out all subset of input string
	 * 
	 * @param s input string to construct Rack object
	 */
	public Rack(String s) {
		original = sortString(s.toLowerCase()); // input string stored to original
		Map<Character,Integer> map = new HashMap<>(); // create a HashMap
		for (char ch : original.toCharArray()) {
			if (map.containsKey(ch)) { // if this character has already exist in the HashMap
				map.put(ch, map.get(ch) + 1); // add one more to its multiplicity
			}
			else {
				map.put(ch, 1); // multiplicity is 1
			}
		}
		Set<Map.Entry<Character,Integer>> entrySet = map.entrySet();
		StringBuffer strbuf = new StringBuffer(); // create StringBuffer object to append character
		multi = new int[entrySet.size()]; // multi array size is equal to size of set
		int i = 0;
		for (Map.Entry<Character,Integer> entry : entrySet) {
			strbuf.append(entry.getKey()); // for each unique character, append to StringBuffer
			multi[i] = entry.getValue(); // the array value is equal to character multiplicity
			i++;
		}
		unique = strbuf.toString();
		subset = allSubsets(unique,multi,0);
		
	}
   

	/**
	 * Finds all subsets of the multiset starting at position k in unique and mult.
     * unique and mult describe a multiset such that mult[i] is the multiplicity of the char
     *      unique.charAt(i).
     * PRE: mult.length must be at least as big as unique.length()
     * @param unique a string of unique letters
     * @param mult the multiplicity of each letter from unique.  
     * @param k the smallest index of unique and mult to consider.
     * @return all subsets of the indicated multiset
     * @author Claire Bono
     */
	private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
		ArrayList<String> allCombos = new ArrayList<String>();
		
		if (k == unique.length()) {  // multiset is empty
			allCombos.add("");
			return allCombos;
		}
		// get all subsets of the multiset without the first unique char
		ArrayList<String> restCombos = allSubsets(unique, mult, k+1);
		
		// prepend all possible numbers of the first char (i.e., the one at position k) 
		// to the front of each string in restCombos.  Suppose that char is 'a'...
		
		String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
		for (int n = 0; n <= mult[k]; n++) {   
			for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets 
                                                        // we found in the recursive call
				// create and add a new string with n 'a's in front of that subset
				allCombos.add(firstPart + restCombos.get(i));  
			}
			firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
		}
		return allCombos;
	}
	
	/**
	 * 
	 * @return create new ArrayList, and return all subset
	 */
	public ArrayList<String> getSubset() {
		if (subset != null)
			return new ArrayList<String>(subset);
		else
			return null;
	}
	
	/**
	 * 
	 * @return return original user input string with sorted order
	 */
	public String getOrigWord() {
		return original;
	}
   
    /**
     * Sort a string with character in alphabetical order
     * @param sin Input string
     * @return a string with sorted character in alphabetical order
     */
	private String sortString(String sin) {
		char[] chars = sin.toCharArray();
	    Arrays.sort(chars);
	    return new String(chars);
    }
   
	/**
	 * Test Rack class
	 * 1. create a Rack object with input string "bbacccdddd"
	 * 2. print out unique word and multiplicity
	 * 3. print out all of subset
	 */
	public static void main(String[] args) {
    	Rack rack = new Rack("bbacccdddd");
	    System.out.println("original word is: " + rack.original);
	    System.out.println("unique word is: " + rack.unique);
	    System.out.print("multiplicity for each character is: ");
	    for (Integer i : rack.multi) {
	    	System.out.print(i + ", ");
	    }
	    System.out.print("\n");
	   
	    for (String str : rack.subset) 
	    	System.out.println(str);
	   
	    return;
	}
}
