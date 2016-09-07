package search;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class TextSearcher {

	/**
	* List of words in text document
	*/
	private List<String> tokens = new ArrayList<String>();

	/**
	 * Initializes the text searcher with the contents of a text file.
	 * The current implementation just reads the contents into a string
	 * and passes them to #init().  You may modify this implementation if you need to.
	 *
	 * @param f Input file.
	 * @throws IOException
	 */
	public TextSearcher(File f) throws IOException {
		FileReader r = new FileReader(f);
		StringWriter w = new StringWriter();
		char[] buf = new char[4096];
		int readCount;

		while ((readCount = r.read(buf)) > 0) {
			w.write(buf,0,readCount);
		}

		init(w.toString());
	}

	/**
	 *  Initializes any internal data structures that are needed for
	 *  this class to implement search efficiently.
	 */
	protected void init(String fileContents) {
		TextTokenizer lexer = new TextTokenizer(fileContents, "[a-zA-Z0-9']+");
		while(lexer.hasNext()){
			tokens.add(lexer.next());
		}
	}

	/**
	 *
	 * @param queryWord The word to search for in the file contents.
	 * @param contextWords The number of words of context to provide on
	 *                     each side of the query word.
	 * @return One context string for each time the query word appears in the file.
	 */
	public String[] search(String queryWord,int contextWords) {
		List<String> wordsMatched = new ArrayList<String>();

		for(int i = 0; i < tokens.size(); i++){
			String currWord = tokens.get(i);
			if(queryWord.equals(currWord)){
				wordsMatched.add(currWord);
			}
		}

		return wordsMatched.toArray(new String[wordsMatched.size()]);
	}
}

// Any needed utility classes can just go in this file

