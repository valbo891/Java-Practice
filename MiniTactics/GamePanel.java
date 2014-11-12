package MiniTactics;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.*;

//import java.awt.event;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

class GamePanel extends JPanel
{

	private World world;
	private Timer timer;
	private Entity selectedEntity = null;
	

	// private Entity targetEntity = null;
	// String s;
	/**
	 * Constructor
	 * 
	 */
	GamePanel()
	{
		// Initialize world
		world = new World();

		// Initialize timer
		timer = new Timer(30, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				repaint();
			}
		});
		timer.start();

		addMouseListener(new MouseAdapter()
		{
			// Figure out press event issues
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (world.getCurrentTurn() == ActiveTurn.PLAYER
						&& selectedEntity == null)
				{
					int colClicked = e.getX() / 32; // inverted to coordinate with mouse axis
					int rowClicked = e.getY() / 32;

					for (Entity entity : World.getEntities())
					{
						if (entity.getRowPos() == rowClicked
								&& entity.getColPos() == colClicked)
						{
							selectedEntity = entity;
							System.out.println(selectedEntity + " selected");
							break;
						}
					}

				}
				else if (world.getCurrentTurn() == ActiveTurn.PLAYER
						&& selectedEntity != null)
				{
					int colClicked = e.getX() / 32; // inverted to coordinate
					// with mouse axis
					int rowClicked = e.getY() / 32;
					
					//commenceTurn(rowClicked, colClicked);
					if(world.playTurn(selectedEntity, rowClicked, colClicked))
					{
						
						world.getAi().respond();
						
					}

					// targetEntity = null;
					selectedEntity = null;
					
					
					

				}
				else if (world.getCurrentTurn() == ActiveTurn.COMPUTER)
				{
					// targetEntity = null;
					selectedEntity = null;
				}

			}
		});

	}

//	/**
//	 * Take action
//	 * 
//	 * @param row
//	 *            the row number
//	 * @param col
//	 *            the column number
//	 */
//	void commenceTurn(int row, int col)
//	{
//		// System.out.println(row + "," + col);
//		world.playTurn(selectedEntity, row, col);
//	}

	@Override
	public void paintComponent(Graphics g)
	{

		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g; 
		
		if (world != null)
		{
			int xDrawLocation = 0;
			int yDrawLocation = 0;

			// draw map
			for (int row = 0; row < world.getGameMap().length; row++)
			{
				xDrawLocation = 0;
				for (int col = 0; col < world.getGameMap()[row].length; col++)
				{
					BufferedImage image = null;
					switch (world.getGameMap()[row][col])
					{
					case 0:
						image = Tile.PLAIN.getImage();
						break;
					case 1:
						image = Tile.SYMBOL.getImage();
						break;
					}
					// draw tile
					g2.drawImage(image, xDrawLocation, yDrawLocation, null);

					Entity characterAtThisLocation = null;
					for (Entity entity : World.getEntities())
					{
						if (entity.getRowPos() == row
								&& entity.getColPos() == col)
						{
							characterAtThisLocation = entity;

						}
					}
					
					g2.setColor(Color.BLACK);
					// draw tile outline(for user reference)
					g2.drawRect(xDrawLocation, yDrawLocation, 32, 32);

					// draw character at this location
					if (characterAtThisLocation != null)
					{
						g2.drawImage(characterAtThisLocation.getCurrentImage(),
								xDrawLocation, yDrawLocation, null);
						g2.setColor(Color.WHITE);
						g2.fillRect(xDrawLocation, yDrawLocation, 10, 10);
						g2.setColor(Color.BLACK);
						g2.drawString(String.valueOf(characterAtThisLocation.getHp()),xDrawLocation + 3, yDrawLocation + 2);
					}
					
					
					
					xDrawLocation += image.getWidth();
				}
				yDrawLocation += Tile.PLAIN.getImage().getHeight();
			}

			// draw characters
			// for(Entity entity: World.getEntities())
			// {
			// g2.drawImage(entity.getCurrentImage(),entity.getxPos(),entity.getyPos(),null);
			// }
			g.drawString("current turn: " + world.getCurrentTurn(), getWidth() / 3, getHeight() / 12);
		}

	}
}
