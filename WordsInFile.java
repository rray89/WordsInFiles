
import java.util.*;
import java.io.*;

public class WordsInFile {

	private HashMap<String, ArrayList<String>> wordsinfile;
	
	//constructor
	public WordsInFile () 
	{
		wordsinfile = new HashMap<String, ArrayList<String>>();
	}
	
	//method addWordsFromFile: read file word by word and add it the HashMap 
	private void addWordsFromFile (File file) throws IOException
	{
		try (BufferedReader bf = new BufferedReader(new FileReader(file.getPath()))) {
			
			String line;
			//read file line by line in a while loop
			while ((line = bf.readLine()) != null) {
				
				//create a scanner to read the line word by word in a while loop
				Scanner lineScanner = new Scanner(line);
				while (lineScanner.hasNext()) {
					String word = lineScanner.next();
					
					//when the word is not contained in the HashMap wordsinfile as a key 
					//i.e. There is no value mapped to it
					if(!wordsinfile.containsKey(word)) {
						
						//create a new ArrayList and add file path to it 
						ArrayList<String> filepaths = new ArrayList<String>();
						filepaths.add(file.getPath());
						
						//put the word (key) and ArrayList filepaths (value) into HashMap
						wordsinfile.put(word, filepaths);
						
					} 
					//when the word is contained in the HashMap wordsinfile as a key
					//but the file is not contained in the ArrayList mapped to it 
					else if (wordsinfile.containsKey(word) && !wordsinfile.get(word).contains(file.getPath())){
						
						//create a deep copy of the ArrayList mapped to the word and add file path to it 
						ArrayList<String> filepaths = wordsinfile.get(word);
						filepaths.add(file.getPath());
						
						//replace the old ArrayList with the new one
						wordsinfile.replace(word, filepaths);
					}
				}
				lineScanner.close();
			}
		}
	}
	
	//method buildWordFileMap: takes an ArrayList<String> filepaths as a parameter
	//add all the words contained in the files to HashMap by calling addWordsFromFile method
	public void buildWordFileMap (ArrayList<String> filepaths) throws IOException
	{
		if (filepaths.isEmpty() || filepaths.size() == 0)
			return;
		
		wordsinfile.clear();
		
		for (String filepath: filepaths) {
			File file = new File(filepath);
			this.addWordsFromFile(file);
		}	
	}
	
	//method maxNumber: find the word that appears in the most file. return the max number of appearances
	public int maxNumber () {
		
		int max = 0;
		
		for(String word: wordsinfile.keySet()) {
			if(wordsinfile.get(word).size() > max) {
				max = wordsinfile.get(word).size();
			}
		}
		
		return max;
	}
	
	//method wordsInNumFiles: takes an integer Num and return an ArrayList of words that appeared Num times 
	public ArrayList<String> wordsInNumFiles (int num) 
	{	
		ArrayList<String> result = new ArrayList<String>();
		
		for (String word: wordsinfile.keySet()) {
			if(wordsinfile.get(word).size() == num) {
				result.add(word);
			}
		}	
		return result;
	}
	
	//method printInFiles: 
	public void printInFiles (String word) 
	{
		if (word.length() == 0 || word == null || !wordsinfile.containsKey(word))
			return;
		
		//create a deep copy of the ArrayList of files that the word appeared in
		ArrayList<String> filepaths = wordsinfile.get(word);
		
		for (int i = 0; i < filepaths.size(); i++) 
			System.out.println(filepaths.get(i));
	}
	
	//main method
	public static void main(String[] args) throws IOException
	{		
		ArrayList<String> filepaths = new ArrayList<String>();
		filepaths.add("src/brief1.txt");
		filepaths.add("src/brief2.txt");
		filepaths.add("src/brief3.txt");
		filepaths.add("src/brief4.txt");
		
		WordsInFile wf = new WordsInFile();
		
		wf.buildWordFileMap(filepaths);
		System.out.println("Max word is " + wf.maxNumber());
		
		System.out.println("\nWords that appeared once: ");
		System.out.println(wf.wordsInNumFiles(1).toString());
		
		System.out.println("\nWords that appeared twice: ");
		System.out.println(wf.wordsInNumFiles(2).toString());
		
		System.out.println("\nWords that appeared three times: ");
		System.out.println(wf.wordsInNumFiles(3).toString());
		
		System.out.println("\nWord \"cats\" is contained in the following files: ");
		wf.printInFiles("cats");
	}

}
