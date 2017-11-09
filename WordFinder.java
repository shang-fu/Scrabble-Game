// Name: Shang-Fu Hsieh
// USC NetID: 5379158999
// CS 455 PA4
// Spring 2017

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * This Class test Scrabble, find out all anagram of specific input string including its subsets and their score
 * @author Shang-Fu Hsieh
 *
 */
public class WordFinder {

	public static void main(String[] args) {
		String fileName; // file name
		boolean done = false; // if not done, keep while loop
		if (args.length == 0) // if user does not input file name, used default sowpods.txt
			fileName = "sowpods.txt";
		else
			fileName = args[0];
		try {
			AnagramDictionary anadic = new AnagramDictionary(fileName); // create AnagramDictionary object
			System.out.println("Type . to quit.");
			System.out.print("Rack? ");
			Scanner in = new Scanner(System.in); // create Scanner object to scan file data
			while (!done && in.hasNextLine()) {	
				String input = in.next();
				if (input.charAt(0) != '.') { // if user input ".", the terminate
					go(input, anadic);
					System.out.print("Rack? ");
				}
				else
					done = true;
			}
		}
		catch (FileNotFoundException ex) {
			System.out.println("File not found: " + fileName);
		}
	}
	
	/**
	 * create Rack object, find out all subset of input string and their score
	 * 
	 * @param input input string
	 * @param anadic anagram ditionary
	 */
	public static void go(String input, AnagramDictionary anadic) {
		Rack rack = new Rack(input); // create Rack object
		ScoreTable table = new ScoreTable(); // create ScoreTable object
		for (String str : rack.getSubset()) { // for each subset of string
			ArrayList<String> anagrams = anadic.getAnagramsOf(str); // find their anagrams
			if (anagrams != null) // if anagrams found, put into table
				table.put(anagrams);
		}
		ArrayList<Score> sortList = table.getList(); // get the final table
		Collections.sort(sortList); // sort all of found words
		System.out.println("We can make " + sortList.size() + " words from " + '"' + rack.getOrigWord() + '"' );
		if (sortList.size() > 0) { // if words found, print them out
			System.out.println("All of the words with their scores (sorted by score):");
			for (Score sc : sortList)
				System.out.println(sc.toSting());
		}
	}
}
