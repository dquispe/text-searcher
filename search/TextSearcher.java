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
	* Tokenizer to break down text into valid words
	*/
	private TextTokenizer lexer;

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
		this.lexer = new TextTokenizer(fileContents, "[a-zA-Z0-9']+");
		while(this.lexer.hasNext()){
			this.tokens.add(this.lexer.next());
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
		queryWord = queryWord.toLowerCase();

		for(int i = 0; i < this.tokens.size(); i++){
			String currWord = this.tokens.get(i);

			if(queryWord.equals(currWord.toLowerCase())){
				int leftContext = 0;
				int rightContext = 0;

				for(int leftIndex = 1; leftContext < contextWords; leftIndex++){
					if(i - leftIndex < 0) break;

					String possibleLeftWord = this.tokens.get(i - leftIndex);

					if(this.lexer.isWord(possibleLeftWord)){
						leftContext++;
					}
					currWord = possibleLeftWord + currWord;
				}

				for(int rightIndex = 1; rightContext < contextWords; rightIndex++){
					if(i + rightIndex >= this.tokens.size()) break;

					String possibleRightWord = this.tokens.get(i + rightIndex);

					if(this.lexer.isWord(possibleRightWord)){
						rightContext++;
					}
					currWord = currWord + possibleRightWord;
				}
				wordsMatched.add(currWord);
			}
		}

		return wordsMatched.toArray(new String[wordsMatched.size()]);
	}
}

// Any needed utility classes can just go in this file

