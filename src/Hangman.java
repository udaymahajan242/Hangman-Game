import java.io.IOException;

    //NAME OF STUDENT PROGRAMMER => Uday Mahajan

	// PROGRAM NAME => Hangman
	// COURSE => CS 142 (3270)
	// ASSIGNMENT => 05. Hangman Game
	// DUE DAY, DATE & TIME => Tuesday; October 25th, 2016 ; 10:00 AM (PST)

/**
 * A guessing game where you must guess a word. Please fill out your name below:
 * 
 * Author: Uday Mahajan
 * 
 * Please fill out the methods below and remove the "todo" comments. Then you'll
 * be able to play Hangman!
 */
public class Hangman {

	/**
	 * Choose a word randomly among the array of words. You should use the
	 * Math.random method, multiply and cast it appropriately.
	 * 
	 * @param words
	 *            Array of words
	 * @return A randomly selected word
	 */
	public static String chooseWord(String[] words) {
		 
		int randomWord = (int) (Math.random() * words.length);
		return words[randomWord];
	}

	/**
	 * Return true if you can still play the game (there are some letters that
	 * haven't been revealed and you have zero or more mistakes left)
	 * 
	 * @param mistakesLeft
	 *            Mistakes you have left (zero means you're still alive but
	 *            can't make any more mistakes!)
	 * @param revealedLetters
	 *            Boolean array (true for each letter that has been revealed)
	 * @return True if you can still play the game because it hasn't been solved
	 *         and you are still alive. False if you can't play!
	 */
	public static boolean canStillPlay(int mistakesLeft, boolean[] revealedLetters) {
		 
		if (mistakesLeft < 0) {
			return false;

		}
		for (int i = 0; i < revealedLetters.length; i++) {
			if (revealedLetters[i] == false)
				return true;

		}

		return false;
	}

	/**
	 * Create a visual representation of the word revealed so far. Each letter
	 * that has been revealed should be included in the string. Each letter that
	 * has not been revealed should be replaced with a dash (-).
	 * 
	 * @param word
	 *            Secret word
	 * @param revealedLetters
	 *            Boolean array (true for each letter that has been revealed)
	 * @return String representation of partially revealed word. Use
	 *         concatenation to build it!
	 */
	public static String wordSoFar(char[] word, boolean[] revealedLetters) {
		 
		 
	    String s = "";
	    
		for (int i = 0; i < word.length; i++) {
		    
			
			if (revealedLetters  [i] == true ) {
			 s= s + word [i];
		
			} 
			
			else {
			s= s + "-" ;
			
			}
		}
		
		return s;
	}

	/**
	 * Make a guess in the Hangman game. Modify revealedLetters according to the
	 * guess. Each letter of the word that matches the letter guessed should be
	 * revealed (set to true). Don't set any letters to false!
	 * 
	 * @param guess
	 *            Letter guessed
	 * @param word
	 *            Secret word
	 * @param revealedLetters
	 *            Boolean array (true for each letter that has been revealed)
	 * @return true if guess was correct (revealed at least one letter), false
	 *         otherwise
	 */
	public static boolean makeGuess(char guess, char[] word, boolean[] revealedLetters) {
		 
		
		
	boolean revealed = false;
		
		for (int i = 0; i < word.length; i++) {		
			
		if 	(guess == word[i] && revealedLetters[i] == false ){
			revealedLetters[i] = true;
			revealed = true;
			}
		
							
		}
		return revealed == true;
				
	 
	}
	

	/**
	 * Return whether or not the letter was already guessed.
	 * 
	 * @param letter
	 *            Letter in the range a through z.
	 * @param guessedLetters
	 *            Boolean array of 26 elements. The first position in the array
	 *            represents the first letter of the alphabet (a), the second
	 *            position b, and so forth. Each position is true if its
	 *            corresponding letter was already guessed and false if it
	 *            wasn't.
	 * @return True if the letter was guessed according to guessedLetters.
	 */
	private static boolean letterWasGuessed(char letter, boolean[] guessedLetters) {
		 
		
		
	
		return guessedLetters[letter - 'a'] ;
	}

	/**
	 * Main method to implement the game of Hangman using the methods above.
	 * 
	 * @param args
	 * @throws IOException
	 */
	
	public static void main(String[] args) throws IOException {
		String[] possibleWords = { "acorn", "baker", "camel", "denim", "ember", "fauna", "grape", "heist", "igloo",
				"jumpy", "koala", "llama", "mocha", "never", "otter", "pride", "quite", "rocky", "salad", "truck",
				"under", "viola", "wrist", "xenon", "yacht", "zebra" };
		String chosenWord = chooseWord(possibleWords);
		int mistakesLeft = 7;
		boolean[] revealedLetters = new boolean[chosenWord.length()];
		boolean[] guessedLetters = new boolean[26];
		while (canStillPlay(mistakesLeft, revealedLetters)) {
			char guess;
			System.out.println("Word so far: " + wordSoFar(chosenWord.toCharArray(), revealedLetters));
			System.out.println("You may make " + mistakesLeft + " more mistake" + (mistakesLeft == 1 ? "" : "s") + ".");
			while (true) {
				System.out.print("Make a guess: ");
				guess = (char) System.in.read();
				// Read extra characters
				while (guess != '\n' && System.in.read() != '\n')
					;
				if (guess < 'a' || guess > 'z') {
					System.out.println("Guess must be between 'a' and 'z' (lower case). Try again!");
				} else if (letterWasGuessed(guess, guessedLetters)) {
					System.out.println("You already guessed that letter!");
				} else {
					break;
				}
			}
			guessedLetters[guess - 'a'] = true;
			if (makeGuess(guess, chosenWord.toCharArray(), revealedLetters)) {
				System.out.println("Correct guess!");
			} else {
				System.out.println("Incorrect guess.");
				mistakesLeft--;
			}
		}
		if (mistakesLeft < 0) {
			// We made a mistake when we weren't allowed to, so we lost.
			System.out.println("You lost. The word was: " + chosenWord);
		} else {
			System.out.println("You won!");
		}
	}
}
