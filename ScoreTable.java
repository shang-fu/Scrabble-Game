// Name: Shang-Fu Hsieh
// USC NetID: 5379158999
// CS 455 PA4
// Spring 2017

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class give each word with their score, and sort by their score. If score are same, then sort by their alphabetical order.
 * 
 * @author Shang-Fu Hsieh
 *
 */
public class ScoreTable {
	
	private ArrayList<Score> sortList; // sorted list
	private int[] scoreStandard; // score for each character
	public static final int SCORE_ONE = 1;
	public static final int SCORE_TWO = 2;
	public static final int SCORE_THREE = 3;
	public static final int SCORE_FOUR = 4;
	public static final int SCORE_FIVE = 5;
	public static final int SCORE_EIGHT = 8;
	public static final int SCORE_TEN = 10;
	
	public ScoreTable() {
		sortList = new ArrayList<Score>(); // create an ArrayList object
		scoreStandard = new int[26]; // create array with size 26 for each letter
		
		// hard coded score
		for (int i = 0 ; i < 26 ; i++) {
			if (i == ('a' - 'a') || i == ('e' - 'a') || i == ('i' - 'a') || i == ('o' - 'a') || i == ('u' - 'a'))
				scoreStandard[i] = SCORE_ONE;
			if (i == ('l' - 'a') || i == ('n' - 'a') || i == ('s' - 'a') || i == ('t' - 'a') || i == ('r' - 'a'))
				scoreStandard[i] = SCORE_ONE;
			if (i == ('d' - 'a') || i == ('g' - 'a'))
				scoreStandard[i] = SCORE_TWO;
			if (i == ('b' - 'a') || i == ('c' - 'a') || i == ('m' - 'a') || i == ('p' - 'a'))
				scoreStandard[i] = SCORE_THREE;
			if (i == ('f' - 'a') || i == ('h' - 'a') || i == ('v' - 'a') || i == ('w' - 'a') || i == ('y' - 'a'))
				scoreStandard[i] = SCORE_FOUR;
			if (i == ('k' - 'a'))
				scoreStandard[i] = SCORE_FIVE;
			if (i == ('j' - 'a') || i == ('x' - 'a'))
				scoreStandard[i] = SCORE_EIGHT;
			if (i == ('q' - 'a') || i == ('z' - 'a'))
				scoreStandard[i] = SCORE_TEN;
		}
	}
	
	public ArrayList<Score> getList() {
		if (sortList != null)
			return new ArrayList<Score>(sortList);
		else
			return null;
	}
	
	/**
	 * This method find out score for all of anagrams and put them into an ArrayList
	 * 
	 * @param anagram list of all anagrams
	 */
	public void put(ArrayList<String> anagram) {
		for (String str : anagram) // for each anagram
			sortList.add(new Score(str, getScore(str))); // find out their score and create Score object then add to ArrayList
	}
	
	/**
	 * This method find out score for the input string
	 * 
	 * @param str input string
	 * @return return its score
	 */
	private int getScore(String str) {
		int sum = 0; // initialize sum = 0
		for (Character ch : str.toCharArray()) // for each character in the string
			sum = sum + scoreStandard[ch - 'a']; // add its score into sum
		return sum;
	}

	/**
	 * Test ScoreTable class
	 * 1. create AnagramDictrionay object with "tinyDictionary.txt"
	 * 2. create ScoreTable object
	 * 3. find out anagram of "gdo"
	 * 4. find out anagram of "rilfe"
	 * 5. find out score for all of anagrams
	 * 6. print out sorted score
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		   try {
			   AnagramDictionary anadic = new AnagramDictionary("tinyDictionary.txt");
			   ScoreTable table = new ScoreTable();

			   // test to find anagram of "gdo"
			   System.out.println("finding out anagram of gdo...");
			   for (String word : anadic.getAnagramsOf("gdo")) {
				   System.out.print(word + ", ");
			   }
			   // test to find anagram of "rilfe"
			   System.out.println("\nfinding out anagram of rilfe...");
			   for (String word : anadic.getAnagramsOf("rilfe")) {
				   System.out.print(word + ", ");
			   }
			   System.out.println("\nOriginal order is list: ");
			   
			   table.put(anadic.getAnagramsOf("gdo")); // put all anagram to table
			   table.put(anadic.getAnagramsOf("rilfe")); // put all anagram to table
			   
			   for (Score sc : table.sortList) // print out score before sorting
				   System.out.println(sc.toSting());
			   
			   Collections.sort(table.sortList); // sort score
			   System.out.println("After sorting: ");
			   for (Score sc : table.sortList) // print out score after sorting
				   System.out.println(sc.toSting());
		   }
		   catch (FileNotFoundException ex) {
			   System.out.println("File not found.");
		   }

	}

}
