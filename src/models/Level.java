package models;

//import javax.swing.JOptionPane;

/**
 * Keeps track of the characteristics of the levels
 * 
 * @author Jessica Wu and Michael Chen
 * @version 2011-2012
 */

public class Level {

	public static final int STARTING_LEVEL = 1;
	public static final int MAX_LEVEL = 5;
	public static final int STARTING_NUM_OF_CHOICES = 4;
	public static final int MORE_DIFFICULT = 5;

	private int level;
	private int numOfChoices;
	private int[] code;

	/**
	 * Sets up the level object and starting variables
	 */
	public Level() {
		// Sets everything to the beginning characteristics
		level = STARTING_LEVEL;
		numOfChoices = STARTING_NUM_OF_CHOICES;
		generateRandomCode(level);
		// Displays the code for testing purposes
		for (int i = 0; i < 4; i++) {
			System.out.print(code[i]);
		}
		System.out.println();
	}

	/**
	 * Gives the current level
	 * 
	 * @return the current level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Gives the current number of choices
	 * 
	 * @return the number of choices
	 */
	public int getNoOfChoices() {
		return numOfChoices;
	}

	/**
	 * Gives the current code
	 * 
	 * @return the current code
	 */
	public int[] getCode() {
		return code;
	}

	/**
	 * Resets the level and its contents back to the starting point
	 * 
	 */
	public void resetLevel() {
		level = STARTING_LEVEL;
		numOfChoices = STARTING_NUM_OF_CHOICES;

	}

	/**
	 * Goes to the next level
	 * 
	 */
	public void nextLevel() {
		if (level < MAX_LEVEL) {
			level++;
			generateRandomCode(level);
			for (int i = 0; i < 4; i++) {
				System.out.print(code[i]);
			}
			System.out.println();
		} else {
			resetLevel();
			//JOptionPane.showMessageDialog(null,
				//	"You've successfully broken into the bank!",
				//	"Congratulations!", JOptionPane.OK_OPTION);
		}

		// Checks if the difficulty will increase depending on the level
		if (level == 4 || level == 5) {
			numOfChoices = MORE_DIFFICULT;
		}

	}

	/**
	 * Randomly generates a code depending on the difficulty level
	 * 
	 * @param level
	 *            the level to generate a code for
	 */
	public void generateRandomCode(int level) {
		code = new int[PlayerGuess.NUM_PEGS];
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
