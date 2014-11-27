package gameoflifev2.test;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

class GamePanel extends JPanel
{
	private Timer timer;
	int containerIndex = 0;
	
	public GamePanel()
	{
		
		
		// Set background color of panel
		setBackground(new Color(.01f, .99f, .99f));
		
		
		
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				repaint();
				
			}
		});
		
		timer.start();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		
		
		int xDrawLocation = 0;
		int yDrawLocation = 0;
		
		// draw container contents
		for(int boardIndex = containerIndex;boardIndex < containerIndex + 1;boardIndex++)
		{
			for(int rowIndex = 0;rowIndex < Game.getGameBoards()[boardIndex].length;rowIndex++)
			{
				xDrawLocation = 0;
				for(int cellIndex = 0;cellIndex < Game.getGameBoards()[boardIndex][rowIndex].length;cellIndex++)
				{
					
					g.setColor(Color.BLACK);
					g.drawRect(xDrawLocation, yDrawLocation, 32, 32);
					

					if(Game.getGameBoards()[boardIndex][rowIndex][cellIndex] != null && Game.getGameBoards()[boardIndex][rowIndex][cellIndex].getStatus().equals(CellStatus.ON))
					{
						g.setColor(Color.RED);
						g.fillOval(xDrawLocation, yDrawLocation, 32, 32);
					}
					xDrawLocation += 32;
				}
				yDrawLocation += 32;
			}
		}
		containerIndex++;
		if(containerIndex >= Game.getGameBoards().length)
		{
			timer.stop();
		}
	}
}

