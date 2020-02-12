
public class MorseCharacter implements Comparable<MorseCharacter>{

	private String character;
	
	//default constructor
	public MorseCharacter(){
		character = "";
	}
	
	public MorseCharacter(String character) {
		this.character = character;
	}
	
	//returns morse character
	public String getCode() {
		return character;
	}

	//sets morse character
	public void setCode(String character) {
		this.character = character;
	}
	
	@Override
	//returns morse character
	public String toString() {
		return this.getCode();
	}

	@Override
	//creates hash code
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((character == null) ? 0 : character.hashCode()) - 31;
		return result;
	}
	
	@Override
	//method for comparing two morseCharacters
	public int compareTo(MorseCharacter o) {
		String obj1 = this.getCode();
		String obj2 = ((MorseCharacter) o).getCode();
		int result = 0;
	    int i = 0;
	    int shortest = 0;
	    boolean matching = true;
	    
	    //finds shorter morseCharacter
	    if(obj1.length() <= obj2.length()) {
	    	shortest = obj1.length();
	    }
	    else {
	    	shortest = obj2.length();
	    }
	    
	    //loops through each character of both morseCharacters while they are equal
        while(obj1.charAt(i) == obj2.charAt(i) && i < shortest - 1) {
        	i++;
        }
        //if not equal, whichever character has the "-" is greater
        if(obj1.charAt(i) == '.' && obj2.charAt(i) == '-') {
        	matching = false;
        	result = -1;
        }
        //if not equal, whichever character has the "-" is greater
        if(obj2.charAt(i) == '.' && obj1.charAt(i) == '-') {
        	matching = false;
        	result = 1;
        }
        
        //if compared characters match
        if(matching) {
        	//whichever morseCharacter is longer is greater
        	if(obj1.length() > shortest) {
        		result = 1;
        	}
        	if(obj2.length() > shortest) {
        		result = -1;
        	}
        }
	    
		   return result;
	}
	
	@Override
	//returns boolean for whether two morseCharacters are equal or not
	public boolean equals(Object obj) {
		//compared to self
		if (this == obj)
			return true;
		
		//compared to null object
		if (obj == null)
			return false;
		
		//compared to object of another class
		if (getClass() != obj.getClass())
			return false;
		MorseCharacter other = (MorseCharacter) obj;
		
		//if both not null
		if (character == null) {
			if (other.character != null)
				return false;
		} 
		//if characters are not equal
		else if (!character.equals(other.character))
			return false;
		
		//if all cases return true, then return true
		return true;
	}
	
}