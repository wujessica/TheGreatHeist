package game;
import javax.swing.JFrame;
import views.TheGreatHeistFrame;

/** The "TheGreatHeistMain" class
 * Starts up the frame, and subsequently, the whole game
 * @author Jessica Wu and Michael Chen
 * @version 2011-2012
 */

public class TheGreatHeistMain {

	public static void main (String[] args) {
		// Starts up the frame
		TheGreatHeistFrame frame = new TheGreatHeistFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}

}
