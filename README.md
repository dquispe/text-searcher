# Text Searcher

TextSearcher implements a search interface to an underlying text file, consisting of the single method search(String,int). This method takes in a single query word, and returns all substrings found in the file consisting of the query word and a few words of context, given by contextWords, from either side of the query word.

## Performance Notes
- It is safe to assume that the contents of the underlying file can be held in memory, i.e. it is not necessary to handle multi gigabyte files.
- It is safe to assume that many searches will be done over the same document. A single instance of the TextSearcher class can be created once, and then the search method can be called many times.

## Additional Requirements
- A “word” consists of any sequence of the characters a-z, A-Z, 0-9, and apostrophe.  Therefore, “animal’s” is a single word, as are “1844” and “xxxxx10x”. Any non-word character is regarded as punctuation and will be ignored by the search (but included in the results).
- Search is case-insensitive. The query words “species”, “SPECIES”, and “SpEcIeS” should all return the same results.
- If the query word occurs too close to the beginning or end of the file to permit the requested number of context words, start the result string at the beginning or end it at the end of the file as appropriate.
- Strings are returned in document order.
- Overlapping hits require no special treatment; each is returned as a separate hit.
- The search method never returns null. If no hits are found it returns an empty array.