package models;

import views.TheGreatHeistPanel;

/**
 * Keeps track of the history of guesses and clues
 * 
 * @author Jessica Wu and Michael Chen
 * @version 2011-2012
 */

public class PreviousAttempts {

	public static final int MAX_GUESSES = 10;

	private int[][] previousGuesses;
	private int[][] previousClues;
	private int guessesLeft;
	private int nextToFill; // Keeps track of the next row to fill in the history

	/**
	 * Initiates a new clue history and guess history
	 */
	public PreviousAttempts() {
		previousGuesses = new int[MAX_GUESSES][PlayerGuess.NUM_PEGS];
		previousClues = new int[MAX_GUESSES][PlayerGuess.NUM_PEGS];
		clearGuessHistory(previousGuesses);
		clearClueHistory(previousClues);
	}

	/**
	 * Gets the guess history
	 * 
	 * @return the history of guesses
	 */
	public int[][] getGuessHistory() {
		return previousGuesses;
	}

	/**
	 * Gets the clue history
	 * 
	 * @return the history of clues
	 */
	public int[][] getClueHistory() {
		return previousClues;
	}

	/**
	 * Gets the number of guesses left
	 * 
	 * @return the number of guesses leftover
	 */
	public int getGuessesLeft() {
		return guessesLeft;
	}

	/**
	 * Clears the guess history
	 * 
	 * @param previousGuesses
	 *            the guess history
	 * @return the reseted guess history
	 */
	public int[][] clearGuessHistory(int[][] previousGuesses) {
		for (int row = 0; row < MAX_GUESSES; row++) {
			for (int col = 0; col < PlayerGuess.NUM_PEGS; col++) {
				previousGuesses[row][col] = -1;
			}
		}
		nextToFill = 0;
		guessesLeft = 10;
		return previousGuesses;
	}

	/**
	 * Clears the clue history
	 * 
	 * @param previousClues
	 *            the clue history
	 * @return the reseted clue history
	 */
	public int[][] clearClueHistory(int[][] previousClues) {
		for (int row = 0; row < MAX_GUESSES; row++) {
			for (int col = 0; col < PlayerGuess.NUM_PEGS; col++) {
				previousClues[row][col] = TheGreatHeistPanel.EMPTY;
			}
		}
		return previousClues;
	}

	/**
	 * Records the guess and corresponding clues to the guess and clue history
	 * 
	 * @param guess
	 *            the guess to be recorded
	 * @param clues
	 *            the clues to be recorded
	 */
	public void recordGuess(int[] guess, int[] clues) {
		// The number of guesses goes down since needing to record the guess
		// means it was wrong and the player has used up a guess
		guessesLeft--;

		if (guessesLeft != 0) {
			for (int peg = 0; peg < PlayerGuess.NUM_PEGS; peg++) {
				previousGuesses[nextToFill][peg] = guess[peg];
				previousClues[nextToFill][peg] = clues[peg];
			}
			nextToFill++;
		}
	}
}
