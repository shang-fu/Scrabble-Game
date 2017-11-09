// Name: Shang-Fu Hsieh
// USC NetID: 5379158999
// CS 455 PA4
// Spring 2017

/**
 * Score object is consisted of word and its score
 * Score object is comparable with provided standard
 * 
 * @author Shang-Fu Hsieh
 *
 */
public class Score implements Comparable<Score> {
	
	private String word; // string to storing word
	private int score; // storing score for the string
	
	/**
	 * construct Score object with input string and score
	 * 
	 * @param word input word
	 * @param score input score
	 */
	public Score (String word, int score) { // construct Score object
		this.word = word;
		this.score = score;
	}
	
	/**
	 * implementing compareTo method with standard provided below
	 */
	public int compareTo(Score other) { // implementing compareTo method
		
		if (this.score > other.score) { // if the score is higher, put it into higher rank in the list
			return -1;
		}
		else if (this.score < other.score) { // if the score is lower, put it into lower rank in the list
			return 1;
		}
		else { // if the scores are equal, then sort with their alphabetical order
			return this.word.compareTo(other.word);
		}
	}
	
	/**
	 * make the Score object is printable
	 * 
	 * @return return string of Score object
	 */
	public String toSting() {
		return this.score + ": " + this.word;
	}

}
