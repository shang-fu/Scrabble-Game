// Name: Shang-Fu Hsieh
// USC NetID: 5379158999
// CS 455 PA4
// Spring 2017

import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;


/**
 * A dictionary of all anagram sets. 
 * Note: the processing is case-sensitive; so if the dictionary has all lower
 * case words, you will likely want any string you test to have all lower case
 * letters too, and likewise if the dictionary words are all upper case.
 */

public class AnagramDictionary {
	
	private File inFile; // private File object
	private Scanner in; // private Scanner object
	private Map<String,ArrayList<String>> dictionary; // private Map object


   /**
    * Create an anagram dictionary from the list of words given in the file
    * indicated by fileName.  
    * PRE: The strings in the file are unique.
    * @param fileName  the name of the file to read from
    * @throws FileNotFoundException  if the file is not found
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException {
	   
	   inFile = new File(fileName); // create File
	   in = new Scanner(inFile); // create Scanner
	   dictionary = new HashMap<String,ArrayList<String>>(); // create HashMap
	   
	   while (in.hasNext()) { // if file still has data
		   String word = in.next();
		   String sortWord = sortString(word);
		   
		   if (dictionary.containsKey(sortWord)) { // if key exists
			   dictionary.get(sortWord).add(word); // add new word into ArrayList
		   }
		   else {
			   ArrayList<String> strList = new ArrayList<String>(); // create ArrayList object into List reference
			   strList.add(word); // add word to ArrayList
			   dictionary.put(sortWord, strList); // add into HashMap
		   }
		   
	   }

   }
   

   /**
    * Get all anagrams of the given string. This method is case-sensitive.
    * E.g. "CARE" and "race" would not be recognized as anagrams.
    * @param s string to process
    * @return a list of the anagrams of s
    * 
    */
   public ArrayList<String> getAnagramsOf(String s) {
	   if (dictionary.containsKey(sortString(s))) // if dictionary contains this string
		   return new ArrayList<String>(dictionary.get(sortString(s)));
	   else
		   return null;
       
   }
   
   /**
    * Sort a string with character in alphabetical order
    * @param sin Input string
    * @return a string with sorted character in alphabetical order
    */
   private String sortString(String sin) {
	   char[] chars = sin.toCharArray(); // create char array for this string
	   Arrays.sort(chars); // sort character
	   return new String(chars); // return sorted string
   }
   
   /**
    * test AnagramDictionary class
    * 	1. create AnagramDictionary object
    * 	2. check each key and its anagram
    * 	3. try method getAnagramOf
    */
   public static void main(String[] args) {
	   
	   try {
		   AnagramDictionary anadic = new AnagramDictionary("tinyDictionary.txt"); // create AnagramDictionary with tinyDictionary.txt
		   Set<Map.Entry<String,ArrayList<String>>> entrySet = anadic.dictionary.entrySet();
		   for (Map.Entry<String,ArrayList<String>> entry : entrySet) { // check each key on dictionary and its anagram
			   System.out.print("key: " + entry.getKey() + ", element: ");
			   for (String word : entry.getValue())
				   System.out.print(word + ", ");
			   System.out.print("\n");
		   }
		   // test to find anagram of "gdo"
		   System.out.println("\nfinding out anagram of gdo...");
		   for (String word : anadic.getAnagramsOf("gdo")) {
			   System.out.print(word + ", ");
		   }
	   }
	   catch (FileNotFoundException ex) {
		   System.out.println("File not found.");
	   }
   }
}
