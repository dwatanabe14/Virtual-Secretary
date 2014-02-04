import java.awt.Dimension;
import javax.swing.JFrame;

public class Launcher {

	public static void main(String[] args) { // main method launches the main window
		Interface gui = new Interface("Virtual Secretary");
		Dimension minFrameSize = new Dimension(200, 100);
		gui.setMinimumSize(minFrameSize);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
