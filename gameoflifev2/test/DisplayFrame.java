package gameoflifev2.test;



import javax.swing.JFrame;
import javax.swing.JPanel;

class DisplayFrame extends JFrame
{
	JPanel panel;
	
	DisplayFrame()
	{
		// Initialize panel
		panel = new GamePanel();
		
		// Add panel to frame
		add(panel);
		
		// Set frame properties
		setTitle("Conway's Game Of Life");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(true);
		
	}
}

