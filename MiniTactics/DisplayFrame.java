package MiniTactics;

import javax.swing.JFrame;
import javax.swing.JPanel;

class DisplayFrame extends JFrame
{
	JPanel panel;

	public DisplayFrame()
	{
		// Initialize panel
		panel = new GamePanel();

		// Add panel to frame
		add(panel);

		// Set frame properties
		setTitle("MiniTactics");
		setSize(262, 284);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		
		

	}
}
