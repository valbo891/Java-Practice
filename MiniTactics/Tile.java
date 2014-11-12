package MiniTactics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

enum Tile
{
	PLAIN(0), SYMBOL(1);

	private BufferedImage image;
	
	/** Contructor 
	 * 
	 * @param number Indicates tile that should be loaded
	 */
	private Tile(int number)
	{
		try
		{
			// Load image
			switch (number)
			{
			case 0:
				image = ImageIO
				.read(new File(
						"C:/Users/Anthony/Desktop/New Folder/workspace/Projects/src/MiniTactics/desert_floor_plain.png"));
				break;
			case 1:
				image = ImageIO
				.read(new File(
						"C:/Users/Anthony/Desktop/New Folder/workspace/Projects/src/MiniTactics/desert_floor.png"));
				break;

			}
		}
		catch (IOException ex)
		{
			throw new Error("Unable to load image");
		}
	}
	
	/** Retrieve image
	 * 
	 * @return Image associated with this tile
	 */
	BufferedImage getImage()
	{
		return image;
	}
	
	
}
