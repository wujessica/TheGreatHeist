package views;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

import game.Game;

/**
 * Sets up the frame of The Great Heist game
 * 
 * @author Jessica Wu and Michael Chen
 * @version 2011-2012
 */
public class TheGreatHeistFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Game game;
	private TheGreatHeistPanel gameBoard;
	private JMenuItem newOption, exitOption, instructionsOption, aboutOption;

	/**
	 * Constructs the frame and initiates key variables 
	 */
	public TheGreatHeistFrame() {

		// Sets up the frame for the game
		super("The Great Heist");

		// Creates a new game object
		game = new Game();

		setResizable(false);
		setLocation(0, 0);
		setIconImage(Toolkit.getDefaultToolkit().getImage("Images/Icon.png"));
		gameBoard = new TheGreatHeistPanel(game);
		getContentPane().add(gameBoard, BorderLayout.CENTER);

		addMenus();

	}

	/**
	 * Creates the menu and adds it to the frame
	 */
	private void addMenus() {

		// Sets up the Game menu options and the key stroke shortcuts for each
		// option
		newOption = new JMenuItem("New");
		newOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				InputEvent.CTRL_MASK));
		newOption.addActionListener(this);
		exitOption = new JMenuItem("Exit");
		exitOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				InputEvent.CTRL_MASK));
		exitOption.addActionListener(this);

		// Sets up the Help menu options and the key stroke shortcuts for each
		// option
		instructionsOption = new JMenuItem("Instructions");
		instructionsOption.setMnemonic('I');
		instructionsOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
				InputEvent.CTRL_MASK));
		instructionsOption.addActionListener(this);
		aboutOption = new JMenuItem("About");
		aboutOption.addActionListener(this);

		// Sets up the Game and Help Menus
		JMenu gameMenu = new JMenu("Game");
		gameMenu.setMnemonic('G');
		JMenu helpMenu = new JMenu("Help");

		// Add each MenuItem to the Game Menu
		gameMenu.add(newOption);
		// gameMenu.addSeparator ();
		gameMenu.add(exitOption);

		// Add each MenuItem to the Help Menu
		helpMenu.add(instructionsOption);
		// helpMenu.addSeparator ();
		helpMenu.add(aboutOption);

		// Add the GameMenu and HelpMenu to the top menu bar
		JMenuBar mainMenu = new JMenuBar();
		mainMenu.add(gameMenu);
		mainMenu.add(helpMenu);

		// Displays the menus
		setJMenuBar(mainMenu);
	}

	/**
	 * Responds to events on the menu
	 * 
	 * @param event
	 *            the event performed
	 */
	public void actionPerformed(ActionEvent event) {

		// Responds if New is selected
		if (event.getSource() == newOption) {
			gameBoard.newGame();
		}

		// Responds if Exit is selected
		else if (event.getSource() == exitOption) {
			System.exit(0);
		}

		// Responds if Instructions is selected
		else if (event.getSource() == instructionsOption) {
			JOptionPane
					.showMessageDialog(
							this,
							"In order to pull this off, you’ll need to know the rules."
									+ "\nUse the mouse to click on the symbols to change it."
									+ "\nUsing these symbols you must figure out the right code."
									+ "\nHit the submit button when you're ready."
									+ "\nIf you have the correct symbol in the correct place,"
									+ "\nthe sidebar will display a green circle "
									+ "\nIf you have the right symbol , but not in the right"
									+ "\nposition it will display a blue circle. Finally "
									+ "\nif a symbol you entered in is not in the code at "
									+ "\nall then you will see a red circle. Using these "
									+ "\nclues you can narrow down the possibilities, until "
									+ "\nyou too can pull off a Grrrrreat Heist!",
							"Instructions", JOptionPane.INFORMATION_MESSAGE);
		}

		// Responds if About is selected
		// Includes credits for images
		else if (event.getSource() == aboutOption) {
			JOptionPane.showMessageDialog(this,
					"The Great Heist was brought to you by:"
							+ "\n Michael Chen and Jessica Wu"
							+ "\n\u00a9 2011-2012"
							+ "\n"
							+ "\n Thanks to:"
							+ "\n allfreelogo, locksmithtools, simplethemes, clipartof,"
							+ "\n showtacle, freeclipartnow, artvex, dailyclipart,"
							+ "\n allfreedownload, clipart-fr, imageenvision,"
							+ "\n vector-images, Paramount Pictures, and cnre,"
							+ "\n for the various images we used.", "About",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
