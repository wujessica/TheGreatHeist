package models;

/**
 * Keeps track of the pegs
 * 
 * @author Jessica Wu and Michael Chen
 * @version 2011-2012
 */
public class Peg {
	public static final int PEG_1 = 1;
	public static final int PEG_2 = 2;
	public static final int PEG_3 = 3;
	public static final int PEG_4 = 4;
	public static final int PEG_5 = 5;

	/**
	 * Randomly generates a peg
	 * 
	 * @param numOfChoices
	 *            the number of choices depending on the level
	 * @return the randomly generated peg
	 */
	public static int generateRandomPeg(int numOfChoices) {
		int randomPeg = (int) (Math.random() * (numOfChoices));
		return randomPeg;
	}

}
