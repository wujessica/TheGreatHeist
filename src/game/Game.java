package game;

import models.*;

/**
 * Bundles all of the different objects that keep track of the various portions
 * of the game together and initiates them
 * 
 * @author Jessica Wu and Michael Chen
 * @version 2011-2012
 */

public class Game {

	// Will keep track of the level, the guess, and the history of guesses and
	// clues
	private Level level;
	private PlayerGuess playerGuess;
	private PreviousAttempts previousAttempts;
	private int[] clue;

	/**
	 * Constructs the game object and initiates essential objects necessary for
	 * game play
	 */
	public Game() {
		// Creates the new objects to keep track of the level, guess, and the
		// history
		level = new Level();
		playerGuess = new PlayerGuess(level);
		previousAttempts = new PreviousAttempts();
		clue = new int[4];
	}

	/**
	 * Gives the current object that is keeping track of the level
	 * 
	 * @return the object responsible for the level
	 */
	public Level getLevel() {
		return level;
	}

	/**
	 * Gives the current object keeping track of the guess
	 * 
	 * @return the object responsible for the guess
	 */
	public PlayerGuess getPlayerGuess() {
		return playerGuess;
	}

	/**
	 * Gives the current object keeping track of the history of guesses and
	 * clues
	 * 
	 * @return the object responsible for the history
	 */
	public PreviousAttempts getPreviousAttempts() {
		return previousAttempts;
	}

	/**
	 * Gives the clues for a guess
	 * 
	 * @return the array representing the clues
	 */
	public int[] getClue() {
		return clue;
	}

	/**
	 * Checks, using the clues, if the guess is correct
	 * 
	 * @param guess
	 *            the guess that the player submitted
	 * 
	 * @return the guess is correct
	 * 
	 * @return the guess is incorrect
	 */
	public boolean checkGuess(int[] guess) {
		clue = generateClues(guess);
		int guessLeft = previousAttempts.getGuessesLeft();
		int match = 0;

		// Goes through the clue and checks if all of them mean correct
		for (int clueNo = 0; clueNo < Clue.NO_OF_CLUES; clueNo++) {
			if (clue[clueNo] == Clue.CORRECT) {
				match++;
			}
		}

		// Checks if all the clues mean correct and if not, records the guess
		// and clues
		if (match == 4) {
			return true;
		} else if (guessLeft > 0) {
			previousAttempts.recordGuess(guess, clue);
			return false;
		}
		return false;
	}

	/**
	 * Creates the clues for the specific guess
	 * 
	 * @param guess
	 *            the guess submitted by the player
	 * 
	 * @return the clues made for the specific guess
	 */
	public int[] generateClues(int[] guess) {
		int[] clueGen = new int[4];
		int[] holdingArray = new int[4];
		int[] levelCode = level.getCode();

		// Sets the holding array values to values that don't come up
		for (int holdingArrayIndex = 0; holdingArrayIndex < 4; holdingArrayIndex++) {
			holdingArray[holdingArrayIndex] = -1;
		}

		// Checks if the guess fully matches the code and sets holding array
		// values if it's correct
		for (int indexOfGuess = 0; indexOfGuess < 4; indexOfGuess++) {
			if (guess[indexOfGuess] == levelCode[indexOfGuess]) {
				holdingArray[indexOfGuess] = guess[indexOfGuess];
				clueGen[indexOfGuess] = Clue.CORRECT;
			}

		}

		// Checks whether or not the current clue is correct
		for (int s = 0; s < 4; s++) {
			if (clueGen[s] == Clue.CORRECT)
				continue;

			// Checks if the number occurs
			boolean numberFound = false;
			for (int a = 0; a < 4; a++) {
				if (guess[s] == holdingArray[a])
					numberFound = true;
			}

			// If the number has been found checks if number of occurrences of
			// that number haven't been reached
			// Then adds it as a wrong position or a wrong number to the cluegen
			// array
			if (numberFound == true) {
				int countLevelCode = 0;
				int countHolding = 0;
				for (int k = 0; k < 4; k++)
					if (levelCode[k] == guess[s])
						countLevelCode++;

				for (int k = 0; k < 4; k++)
					if (holdingArray[k] == guess[s])
						countHolding++;

				if (countHolding < countLevelCode)
					clueGen[s] = Clue.WRONG_POS;
				else
					clueGen[s] = Clue.WRONG;
			}// If the number was not found, then it checks the number again
				// against the level code
			else {
				boolean positionFound = false;
				for (int a = 0; a < 4; a++) {
					if (guess[s] == levelCode[a])
						positionFound = true;
				}
				// If the number is found then it is set as a wrong position
				// Else it sets it as a wrong clue.
				if (positionFound == true) {
					clueGen[s] = Clue.WRONG_POS;
					holdingArray[s] = guess[s];
				} else
					clueGen[s] = Clue.WRONG;
			}
		}

		// Sorts the array in numerical order
		int check = 0;
		for (int clue = 0; clue < 4; clue++) {
			for (int next = clue + 1; next < 4; next++) {
				if (clueGen[clue] > clueGen[next]) {
					check = clueGen[clue];
					clueGen[clue] = clueGen[next];
					clueGen[next] = check;
				}
			}
		}

		// Returns the array of clue results
		return clueGen;

	}

	/**
	 * Resets the guess back to the default
	 * 
	 * @param guess
	 *            the array that keeps track of the guess
	 */
	public void resetGuess(int[] guess) {
		for (int peg = 0; peg < PlayerGuess.NUM_PEGS; peg++) {
			guess[peg] = 0;
		}
	}
}
