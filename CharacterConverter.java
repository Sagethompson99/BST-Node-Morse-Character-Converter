import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import bridges.connect.Bridges;
import bridges.validation.RateLimitException;

public class CharacterConverter {
	
	//regular tree with MorseCharacters as keys
	private BSTNode<MorseCharacter, String> root;
	//tree with letters as keys (for easy conversion)
	private BSTNode<String, MorseCharacter> root2;
	
	public CharacterConverter(File aConversionFile)  {
		try {
		Scanner file = new Scanner(aConversionFile);
		   
		   //goes through file, creates MorseCharacter objects, adds them to the binary tree
		   while(file.hasNextLine()) {
			  String line = file.nextLine();
			  String[] split = line.split(" ");
			  String character = split[0];
			  MorseCharacter mc = new MorseCharacter(split[1]);
			  
			  //creates new root if root is null
			  if(root == null && root2 == null) {
				  root = new BSTNode<MorseCharacter, String>(mc, character);
				  root2 = new BSTNode<String, MorseCharacter>(character, mc);
			  }
			  //if root is not null, add new node
			  else {
			      root.add(mc, character);
			      root2.add(character, mc);
			  }
		   }
		   file.close();
		}
		
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		  
	}
	
	//converts English characters to morse code
	public String convertFromEnglish(String anInput) {
		String translation = "";
		String[] letter = new String[anInput.length()];
		
		   //searches root2 using each character from anInput and returns associated value
		   // **searches root2 because root2 uses English characters as keys**
		   for(int i = 0; i < anInput.length(); i++) {
			  letter[i] = "" + anInput.charAt(i);
			     
			     if(anInput.length() == 1 || i == anInput.length()-1) {
                    translation += root2.search(letter[i]);
			     }
			     else {
				    translation += root2.search(letter[i]) + " ";
			     }
		   }
		   
		return translation;
	}
	
	//convert morse code to English
	public String convertToEnglish(String anInput) {
		String translation = "";
		String[] letters = anInput.split(" ");
		
		   //searches root for each character in anInput and returns associated value
		   // **searches root because root uses morse character as keys**
		   for(int i = 0; i < letters.length; i++) {
              MorseCharacter mc = new MorseCharacter(letters[i]);
              translation += root.search(mc);
		   }
		   
		return translation;
	}
	
	//returns a list of morse characters sorted in their natural order
	public ArrayList<MorseCharacter> getSymbolAlphabet() {
		ArrayList<MorseCharacter> list = new ArrayList<MorseCharacter>();
		
		//create a BinaryNode with root values for use of an iterator
		BinaryNode<MorseCharacter, String> bn = new BinaryNode<MorseCharacter, String>(root.key, root.value);
		//creates an iterator as defined in BinaryNode class
		BinaryNode<MorseCharacter, String>.inOrderIterator iterator = bn.new inOrderIterator(root);
		
		//loops through iterator and adds keys to the list
		while(iterator.hasNext()) {
			BinaryNode<MorseCharacter, String> node = iterator.next();
			list.add(node.key);
		}
		
		return list;
	}
	
	//visualizes the tree on Bridges
	public void visualize() {
		//my bridges info
		 Bridges bridges = new Bridges(0, "sagethompson99", "230287246357");
		 bridges.setTitle("Binary Search Tree Example, Sage Thompson");
		 
		 //visualize the root
		 bridges.setDataStructure(root);
		
		 try {
	        bridges.visualize();
		 }
		 catch(IOException e) {
		    System.out.println("Something went wrong!");
		 }
		 catch(RateLimitException e) {
			System.out.println("Something went wrong!");
		 }
		 
	}
    
	//main method; created for testing purposes
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("CODEFILE.DAT");
		CharacterConverter cc = new CharacterConverter(file);
		cc.visualize();
		String morseCode = "... .- --. .";
		String letters = "ABC";
		System.out.println(morseCode + " translates to: " + cc.convertToEnglish(morseCode));
		System.out.println(letters + " translates to: " + cc.convertFromEnglish(letters.toUpperCase()));
		System.out.println("\n" + cc.getSymbolAlphabet());
	}
}

    