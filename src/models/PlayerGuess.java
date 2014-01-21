package models;

/**
 * Keeps track of the guess made by the player
 * 
 * @author Jessica Wu and Michael Chen
 * @version 2011-2012
 */
public class PlayerGuess {

	public static final int NUM_PEGS = 4;

	private int[] guess;
	private Level level;

	/**
	 * Creates the array to keep track of the guess and gets the current level
	 * 
	 * @param level
	 *            the current level object
	 */
	public PlayerGuess(Level level) {
		guess = new int[NUM_PEGS];
		this.level = level;
	}

	/**
	 * Gets the current guess
	 * 
	 * @return the current guess
	 */
	public int[] getGuess() {
		return guess;
	}

	/**
	 * Gets the peg at the given position
	 * 
	 * @param position
	 *            the position of the peg
	 * @return the peg at the position
	 */
	public int getPeg(int position) {
		return guess[position];
	}

	/**
	 * Changes the current peg to the next one
	 * 
	 * @param pos
	 *            the peg to be changed
	 */
	public void nextPeg(int pos) {
		int check = level.getNoOfChoices();
		guess[pos]++;

		// Resets the peg back to the first peg after the last peg
		if (guess[pos] == check) {
			guess[pos] = 0;
		}
	}

}
