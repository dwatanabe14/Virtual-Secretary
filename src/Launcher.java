import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * A basic homework planner that uses Swing for the GUI
 * @author David Watanabe
 *
 */

public class Launcher {
	
	/**
	 * main method runs the program
	 * @param args
	 */
	public static void main(String[] args) {
		Interface gui = new Interface("Virtual Secretary");
		TextScanner scanner = new TextScanner();
		scanner.newFile();
		Dimension minFrameSize = new Dimension(200, 100);
		gui.setMinimumSize(minFrameSize);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
