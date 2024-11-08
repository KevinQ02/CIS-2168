import java.util.ArrayList;
import java.util.List;

public class IndexNode  {

	// The word for this entry
	String word;
	// The number of occurrences for this word
	int occurences;
	// A list of line numbers for this word.
	List<Integer> list; 
	
	
	
	IndexNode left;
	IndexNode right;
	
	
	// Constructors
	// Constructor should take in a word and a line number
	// it should initialize the list and set occurrences to 1
	
	public IndexNode(String word, int lineNumber){
		this.word = word;
		this.occurences = 1;
		this.list = new ArrayList<>();
		this.list.add(lineNumber);
	}

	public void addOccurence(int lineNumber) {
		this.occurences++;
		if(!this.list.contains(lineNumber)) {
			this.list.add(lineNumber);
		}
	}
	
	
	// Complete This
	// return the word, the number of occurrences, and the lines it appears on.
	// string must be one line
	@Override
	public String toString(){
		return word + " appears " + occurences + " time on lines " + list.toString();
	}


	
}
