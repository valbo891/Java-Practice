package gameoflifev2.test;



import java.awt.Frame;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

class InputHandler
{
	int numberOfRounds = 0;
	int widthOfStructure = 0;
	int heightOfStructure = 0;
	ArrayList<String> gridInputStrings = new ArrayList<String>();
	
	InputHandler()
	{
		
	}
	
	public void start()
	{
		while(true)
		{
			try
			{
				numberOfRounds = 0;
				widthOfStructure = 0;
				heightOfStructure = 0;
				gridInputStrings.clear();
				
	
				// Read input into program
				String unparsedInput = JOptionPane
						.showInputDialog(
								null,
								"Please enter the number of rounds, the width of the cell structure,"
										+ " height of the cell structure, along with the grid contents");
	
				String[] strings = unparsedInput.split(String
						.valueOf((char) 32));
				numberOfRounds = Integer.parseInt(strings[0]);
				widthOfStructure = Integer.parseInt(strings[1]);
				heightOfStructure = Integer.parseInt(strings[2]);
				for (int i = 3; i < strings.length; i++)
				{
					if(strings.length >= 4)
					{
						gridInputStrings.add(strings[i]);
					}
				}
				if (numberOfRounds < 10000 && numberOfRounds > 0
						&& widthOfStructure > 0 && heightOfStructure > 0)
				{
					break;
				}
				
			
				
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				throw new Error("Invalid Input Detected");
				
			}
		}
		String[] list = new String[gridInputStrings.size()];
		for(int i = 0;i < gridInputStrings.size();i++)
		{
			list[i] = gridInputStrings.get(i);
		}
		Game game = new Game(numberOfRounds, widthOfStructure, heightOfStructure,list );
		
		//game.start();
		JFrame frame = new DisplayFrame();
		
		
	}
	
	public static void main(String[] args)
	{
		InputHandler test = new InputHandler();
		test.start();
	}
	
}
