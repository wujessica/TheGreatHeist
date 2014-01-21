package views;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import game.Game;

/**
 * The "TheGreatHeistPanel" class Responsible for the displaying of the various
 * components of the game
 * 
 * @author Jessica Wu and Michael Chen
 * @version 2011-2012
 */

public class TheGreatHeistPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private Game game;
	private int screen;
	private int[] guessPanel;
	private int[][] historyPanel;
	private int[][] cluePanel;
	private boolean gameOver;
	private int nextToFill;

	// Checks if the cursor is over the specified button
	private boolean overArrow;
	private boolean overStartButton;
	private boolean overSubmitButton;

	// Necessary dimensions, sizes, and coordinates for different components
	private final int length = 700;
	private final int width = 1000;
	private final int SQUARE_SIZE = 52;
	private final int NO_OF_ROWS = 10;
	private final int NO_OF_COLUMNS = 4;
	private final int xOfGuessPanel = width / 2;
	private final int yOfGuessPanel = length / 2;
	private final int xOfPeg2 = xOfGuessPanel + SQUARE_SIZE;
	private final int xOfPeg3 = xOfGuessPanel + SQUARE_SIZE * 2;
	private final int xOfPeg4 = xOfGuessPanel + SQUARE_SIZE * 3;
	public final Dimension BOARD_SIZE = new Dimension(NO_OF_COLUMNS
			* SQUARE_SIZE + 1, (NO_OF_ROWS + 1) * SQUARE_SIZE + 1);

	private final int CLUE_SIZE = SQUARE_SIZE / 4;
	private final int xOfClues = SQUARE_SIZE * 5 + CLUE_SIZE;
	private final int yOfClues = SQUARE_SIZE * 2;

	// Specific areas to be kept track of to react to events
	final Rectangle startGame = new Rectangle(width / 3, 3 * length / 4,
			width / 3, 100);
	final Rectangle arrow = new Rectangle(825, 550, 90, 90);
	final Rectangle peg1 = new Rectangle(xOfGuessPanel, yOfGuessPanel,
			SQUARE_SIZE, SQUARE_SIZE);
	final Rectangle peg2 = new Rectangle(xOfPeg2, yOfGuessPanel, SQUARE_SIZE,
			SQUARE_SIZE);
	final Rectangle peg3 = new Rectangle(xOfPeg3, yOfGuessPanel, SQUARE_SIZE,
			SQUARE_SIZE);
	final Rectangle peg4 = new Rectangle(xOfPeg4, yOfGuessPanel, SQUARE_SIZE,
			SQUARE_SIZE);
	final Rectangle submit = new Rectangle(xOfPeg2, yOfGuessPanel + 70, 100,
			100);

	// Constants that represents different symbols
	public static final int EMPTY = -1;
	public static final int SYMBOL1 = 0;
	public static final int SYMBOL2 = 1;
	public static final int SYMBOL3 = 2;
	public static final int SYMBOL4 = 3;
	public static final int SYMBOL5 = 4;

	// Images used that do not change
	private Image startScreen = new ImageIcon("Images/006(edit)(1).png")
			.getImage();
	private Image failScreen = new ImageIcon(
			"Images/Fail Screen Resized With Text.PNG").getImage();
	private Image endScreen = new ImageIcon("Images/End Screen With Text.PNG")
			.getImage();
	private Image instructions = new ImageIcon(
			"Images/Instructions Page(With Text and Arrow).PNG").getImage();
	private Image startButton = new ImageIcon("Images/Start Button 1.PNG")
			.getImage();
	private Image selectedArrow = new ImageIcon("Images/Arrow Selected.PNG")
			.getImage();
	private Image submitButton = new ImageIcon("Images/button_0.PNG")
			.getImage();
	private Image selectedSubmitButton = new ImageIcon("Images/button_1.PNG")
			.getImage();
	private Image guessBackground = new ImageIcon("Images/Guess Panel.PNG")
			.getImage();
	private Image startButton2 = new ImageIcon("Images/Start Button 2.PNG")
			.getImage();
	private Image panel, symbol1, symbol2, symbol3, symbol4, symbol5, stage;

	/**
	 * Constructs the panel and initiates the guess, history, and clue panel
	 * Also adds mouse listeners and sets the dimensions of the game
	 * @param game	the game object
	 */
	public TheGreatHeistPanel(Game game) {

		// Initiates variables and gets the objects required
		this.game = game;
		guessPanel = game.getPlayerGuess().getGuess();
		historyPanel = game.getPreviousAttempts().getGuessHistory();
		cluePanel = game.getPreviousAttempts().getClueHistory();

		Dimension dimension = new Dimension(width, length);
		setPreferredSize(dimension);

		// Adds the necessary listeners
		this.addMouseListener(new MouseHandler());
		this.addMouseMotionListener(new MouseMotionHandler());

	}

	/**
	 * Gives the symbols and background according to the current stage
	 */
	public void getResources() {
		if (screen == 2) {
			// http://www.allfreelogo.com/images/vector-thumb/number-sign-icons-prev1191424989rbDvpc.jpg
			symbol1 = new ImageIcon("Images/1(resized).PNG").getImage();
			symbol2 = new ImageIcon("Images/2(resized).PNG").getImage();
			symbol3 = new ImageIcon("Images/3(resized).PNG").getImage();
			symbol4 = new ImageIcon("Images/4(resized).PNG").getImage();
			stage = new ImageIcon("Images/Stage 1 Background.PNG").getImage();

		} else if (screen == 3) {
			// http://www.lock-smith-tools.co.uk/images/uploads/SOUTHORD_C-500.jpg
			symbol1 = new ImageIcon("Images/Camera.PNG").getImage();
			// http://demos.simplethemes.com/orion/wp-content/uploads/2011/07/1311022937_lock.png
			symbol2 = new ImageIcon("Images/Lock.PNG").getImage();
			// http://images.clipartof.com/small/1059400-Royalty-Free-Vector-Clip-Art-Illustration-Of-A-Security-Guard-By-Doors.jpg
			symbol3 = new ImageIcon("Images/Guard.PNG").getImage();
			// http://showtacle.jp/lasershowguide/laserEffect4.jpg
			symbol4 = new ImageIcon("Images/Laser.PNG").getImage();
			stage = new ImageIcon("Images/Stage 2 Background.PNG").getImage();
		}

		else if (screen == 4) {
			// http://www.freeclipartnow.com/d/28747-1/bomb.jpg
			symbol1 = new ImageIcon("Images/Bomb.PNG").getImage();
			// http://www.artvex.com/content/Clip_Art/Clothing/Gloves/0014646.gif
			symbol2 = new ImageIcon("Images/Gloves.PNG").getImage();
			// http://www.lock-smith-tools.co.uk/images/uploads/SOUTHORD_C-500.jpg
			symbol3 = new ImageIcon("Images/Lockpick.PNG").getImage();
			symbol4 = new ImageIcon("Images/Stethoscope.PNG").getImage();
			stage = new ImageIcon("Images/Stage 3 Background.PNG").getImage();
		}

		else if (screen == 5) {
			// http://cdn.dailyclipart.net/wp-content/uploads/medium/clipart0279.jpg
			symbol1 = new ImageIcon("Images/Money.PNG").getImage();
			// http://images.all-free-download.com/images/graphicmedium/gold_bar_100471.jpg
			symbol2 = new ImageIcon("Images/Gold.PNG").getImage();
			// http://cdn.dailyclipart.net/wp-content/uploads/medium/clipart0277.jpg
			symbol3 = new ImageIcon("Images/Diamond.PNG").getImage();
			// http://en.clipart-fr.com/data/clipart/wedding/wedding_077.jpg
			symbol4 = new ImageIcon("Images/Jewelery.PNG").getImage();
			symbol5 = new ImageIcon("Images/Certificate.PNG").getImage();
			// http://2.bp.blogspot.com/_QfmJ2RGFgcE/TS9VavNIodI/AAAAAAAABCw/9qJSio9Jcqs/s1600
			// /TreasurePile_Shot_HUGEST_Wide.jpg
			stage = new ImageIcon("Images/Stage 4 Background.PNG").getImage();
		}

		else if (screen == 6) {
			symbol1 = new ImageIcon("Images/Clock.PNG").getImage();
			symbol2 = new ImageIcon("Images/Alarm.PNG").getImage();
			// http://www.imageenvision.com/150/28979-cartoon-clip-art-graphic-
			// of-a-tough-brown-american-pitbull-terrier-dog-with-red-eyes-
			// wearing-a-spiked-collar-and-a-broken-chain-by-andy-nortnik.jpg
			symbol3 = new ImageIcon("Images/Dog.PNG").getImage();
			// http:// files.vector-images.com/clipart/policecar5.gif
			symbol4 = new ImageIcon("Images/Cop Car.PNG").getImage();
			// http://cnre.vt.edu/lsg/3104/Main%20Page-revised/Oil%20Exploitation
			// /Main%20Page/Mexico-1/clipart.jpg
			symbol5 = new ImageIcon("Images/Oil Spill.PNG").getImage();
			stage = new ImageIcon("Images/Stage 5 Background.PNG").getImage();
		}

		// Determines how many number of tries are left to be displayed
		if (nextToFill == 0) {
			panel = new ImageIcon("Images/Panel10.PNG").getImage();
		} else if (nextToFill == 1) {
			panel = new ImageIcon("Images/Panel9.PNG").getImage();
		} else if (nextToFill == 2) {
			panel = new ImageIcon("Images/Panel8.PNG").getImage();
		} else if (nextToFill == 3) {
			panel = new ImageIcon("Images/Panel7.PNG").getImage();
		} else if (nextToFill == 4) {
			panel = new ImageIcon("Images/Panel6.PNG").getImage();
		} else if (nextToFill == 5) {
			panel = new ImageIcon("Images/Panel5.PNG").getImage();
		} else if (nextToFill == 6) {
			panel = new ImageIcon("Images/Panel4.PNG").getImage();
		} else if (nextToFill == 7) {
			panel = new ImageIcon("Images/Panel3.PNG").getImage();
		} else if (nextToFill == 8) {
			panel = new ImageIcon("Images/Panel2.PNG").getImage();
		} else if (nextToFill == 9) {
			panel = new ImageIcon("Images/Panel1.PNG").getImage();
		} else if (nextToFill == 10) {
			panel = new ImageIcon ("Images/Panel.PNG").getImage();
		}

	}

	/**
	 * Repaints and updates the panel
	 * 
	 * @param g
	 *            the graphics content
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Checks if the panel must be drawn differently from the main screen
		if (gameOver == true) {
			g.drawImage(failScreen, 0, 0, this);
		} else if (screen == 0) {
			g.drawImage(startScreen, 0, 0, this);
			g.drawImage(startButton, width / 3, 3 * length / 4, this);

			// Displays a different coloured button if the cursor is hovering
			// over the start button
			if (overStartButton == true) {
				g.drawImage(startButton2, width / 3, 3 * length / 4, this);
			} else {
				g.drawImage(startButton, width / 3, 3 * length / 4, this);
			}
		} else if (screen == 1) {
			g.drawImage(instructions, 0, 0, this);
			// Displays a different coloured button if the cursor is over
			// the arrow
			if (overArrow == true) {
				g.drawImage(selectedArrow, 818, 550, this);
			}
		} else if (screen == 7) {
			g.drawImage(endScreen, 0, 0, this);
		}

		// Draws the main screen layout and its specific images
		else if (screen > 1) {

			getResources();

			if (screen == 6) {
				g.drawImage(stage, 70, 0, this);
			} else {
				g.drawImage(stage, 0, 0, this);
			}

			g.drawImage(panel, 0, 0, this);
			g.drawImage(guessBackground, xOfGuessPanel - 24,
					yOfGuessPanel - 20, this);

			// Draws the history panel with the history of clues and guesses
			if (nextToFill <= 10) {
				for (int row = 0; row < nextToFill; row++) {
					for (int column = 0; column < NO_OF_COLUMNS; column++) {
						int xPos = (column + 1) * SQUARE_SIZE;
						int yPos = (row + 2) * SQUARE_SIZE;

						// Draw each piece, depending on the value in board
						if (historyPanel[row][column] == SYMBOL1)
							g.drawImage(symbol1, xPos, yPos, this);
						else if (historyPanel[row][column] == SYMBOL2)
							g.drawImage(symbol2, xPos, yPos, this);
						else if (historyPanel[row][column] == SYMBOL3)
							g.drawImage(symbol3, xPos, yPos, this);
						else if (historyPanel[row][column] == SYMBOL4)
							g.drawImage(symbol4, xPos, yPos, this);
						else if (historyPanel[row][column] == SYMBOL5)
							g.drawImage(symbol5, xPos, yPos, this);
					}
				}
			}

			// Draws the guess panel displaying the current guess and updates
			// as the player alters the guess
			for (int peg = 0; peg < NO_OF_COLUMNS; peg++) {

				// Determines which peg to draw for in the right place
				int xPos = xOfGuessPanel;
				if (peg == 1) {
					xPos = xOfPeg2;
				} else if (peg == 2) {
					xPos = xOfPeg3;
				} else if (peg == 3) {
					xPos = xOfPeg4;
				}

				// Deals with wrap around back to the first peg when it
				// has reached the last peg depending on the stage
				if (guessPanel[peg] == 4 && screen < 5 || guessPanel[peg] == 5
						&& screen >= 5) {
					guessPanel[peg] = 0;
				}

				// Draws the symbol
				if (guessPanel[peg] == SYMBOL1) {
					g.drawImage(symbol1, xPos, yOfGuessPanel, this);
				} else if (guessPanel[peg] == SYMBOL2) {
					g.drawImage(symbol2, xPos, yOfGuessPanel, this);
				} else if (guessPanel[peg] == SYMBOL3) {
					g.drawImage(symbol3, xPos, yOfGuessPanel, this);
				} else if (guessPanel[peg] == SYMBOL4) {
					g.drawImage(symbol4, xPos, yOfGuessPanel, this);
				} else if (guessPanel[peg] == SYMBOL5) {
					g.drawImage(symbol5, xPos, yOfGuessPanel, this);
				}
			}

			// Lights up the submit button if the cursor is over it
			if (overSubmitButton) {
				g.drawImage(selectedSubmitButton, xOfPeg2, yOfGuessPanel + 70,
						this);
			} else {
				g.drawImage(submitButton, xOfPeg2, yOfGuessPanel + 70, this);
			}

			// Draws the previous clues
			for (int drawClue = 1; drawClue <= nextToFill; drawClue++) {
				for (int clue = 0; clue < 4; clue++) {
					int yPos = yOfClues + (drawClue - 1) * 4 * CLUE_SIZE + clue
							* CLUE_SIZE;
					if (cluePanel[drawClue - 1][clue] == 1) {
						g.setColor(Color.GREEN);
						g.fillOval(xOfClues, yPos, CLUE_SIZE, CLUE_SIZE);
					} else if (cluePanel[drawClue - 1][clue] == 2) {
						g.setColor(Color.BLUE);
						g.fillOval(xOfClues, yPos, CLUE_SIZE, CLUE_SIZE);
					} else if (cluePanel[drawClue - 1][clue] == 3) {
						g.setColor(Color.RED);
						g.fillOval(xOfClues, yPos, CLUE_SIZE, CLUE_SIZE);
					}

				}

				// Draws a divider to distinguish groups of clues
				g.setColor(Color.YELLOW);
				g.drawLine(xOfClues, yOfClues + (drawClue - 1) * 4 * CLUE_SIZE
						+ 4 * CLUE_SIZE, xOfClues + CLUE_SIZE, yOfClues
						+ (drawClue - 1) * 4 * CLUE_SIZE + 4 * CLUE_SIZE);

			}
		}
	}

	/**
	 * Responds accordingly when the mouse is pressed
	 */
	private class MouseHandler extends MouseAdapter {
		/**
		 * Responds accordingly to a mousePressed event
		 * 
		 * @param event
		 *            Information about the mouse when its button was pressed
		 */
		public void mousePressed(MouseEvent event) {
			Point pressed = event.getPoint();

			// Changes to the next screen if the corresponding button
			// has been pressed
			if (screen == 0 && startGame.contains(pressed) || screen == 1
					&& arrow.contains(pressed)) {
				screen++;
				repaint();
			}

			// Changes the peg
			getResources();
			if (peg1.contains(pressed)) {
				guessPanel[0]++;
				repaint();
			} else if (peg2.contains(pressed)) {
				guessPanel[1]++;
				repaint();
			} else if (peg3.contains(pressed)) {
				guessPanel[2]++;
				repaint();
			} else if (peg4.contains(pressed)) {
				guessPanel[3]++;
				repaint();
			}

			// Goes to the next level or records the guess
			else if (submit.contains(pressed) && screen > 1 && screen < 7
					&& gameOver == false) {
				System.out.println(guessPanel);
				if (game.checkGuess(guessPanel)) {
					game.getLevel().nextLevel();
					game.resetGuess(guessPanel);
					game.getPreviousAttempts().clearGuessHistory(historyPanel);
					game.getPreviousAttempts().clearClueHistory(cluePanel);
					screen++;
					repaint();
					nextToFill = 0;
				} else {
					for (int col = 0; col < NO_OF_COLUMNS; col++) {
						historyPanel[nextToFill][col] = guessPanel[col];
					}
					// Resets the guess panel to its original state
					// for (int peg = 0; peg < 4;peg++) {
					// guessPanel [peg] = 0;
					// }
					for (int clueNo = 0; clueNo < 4; clueNo++) {
						cluePanel[nextToFill][clueNo] = game.getClue()[clueNo];
						System.out.print(cluePanel[nextToFill][clueNo]);
					}
					System.out.println();
					nextToFill++;
					// Checks if all of the guesses are used up
					if (nextToFill == 10) {
						gameOver = true;
					}
					repaint();
				}
			}
		}
	}

	/**
	 * Monitors the movement of the cursor over the panel
	 */
	private class MouseMotionHandler extends MouseMotionAdapter {
		/**
		 * Responds to mouse movements
		 * 
		 * @param event
		 *            The event created by the mouse movement
		 */
		public void mouseMoved(MouseEvent event) {
			Point pos = event.getPoint();
			// Responds to the mouse hovering over the start button
			if (screen == 0 && pos.x > width / 3 && pos.x < 2 * width / 3
					&& pos.y > 3 * length / 4 && pos.y < 3 * length / 4 + 100) {
				overStartButton = true;
			} else {
				overStartButton = false;
			}

			// Responds to the mouse hovering over the arrow
			if (screen == 1 && pos.x > 825 && pos.x < 825 + 90 && pos.y > 550
					&& pos.y < 550 + 90) {
				overArrow = true;
			} else {
				overArrow = false;
			}

			// Responds to the mouse hovering over the submit button
			if (screen > 1 && pos.x > xOfPeg2 && pos.x < xOfPeg4
					&& pos.y > yOfGuessPanel + 70
					&& pos.y < yOfGuessPanel + 170) {
				overSubmitButton = true;
			} else {
				overSubmitButton = false;
			}
			repaint();
		}
	}

	/**
	 * Starts a new game
	 */
	public void newGame() {
		// Resets all variables
		this.game = new Game();
		screen = 0;
		nextToFill = 0;
		gameOver = false;
		guessPanel = game.getPlayerGuess().getGuess();
		historyPanel = game.getPreviousAttempts().getGuessHistory();
		cluePanel = game.getPreviousAttempts().getClueHistory();
		repaint();

	}
}
