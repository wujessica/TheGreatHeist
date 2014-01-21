package models;

/**
 * Keeps track of the code that needs to be guessed
 * 
 * @author Jessica Wu and Michael Chen
 * @version 2011-2012
 */
public class Code {

	private int[] code;

	/**
	 * Initiates the code
	 */
	public Code() {
		code = new int[PlayerGuess.NUM_PEGS];
	}

	/**
	 * Gets the level's code
	 * 
	 * @return the code
	 */
	public int[] getCode() {
		return code;
	}

	/**
	 * Creates a random code depending on the level
	 * 
	 * @param level
	 *            the level to generate the code for
	 */
	public void generateRandomCode(int level) {
		if (level < 4) {
			for (int peg = 0; peg < PlayerGuess.NUM_PEGS; peg++) {
				code[peg] = Peg
						.generateRandomPeg(Level.STARTING_NUM_OF_CHOICES);
			}
		} else {
			for (int peg = 0; peg < PlayerGuess.NUM_PEGS; peg++) {
				code[peg] = Peg.generateRandomPeg(Level.MORE_DIFFICULT);
			}
		}
	}

}
