package MiniTactics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

enum Enemy
{
	ORC("orc"), SKELETON("skeleton");

	private BufferedImage image;

	/**Constructor
	 * 
	 * 
	 */
	Enemy(String classification)
	{
		try
		{
			// load images
			switch (classification)
			{
			case "orc":
				image = ImageIO.read(new File(
						"C:/Users/Anthony/Desktop/New Folder/workspace/Projects/src/MiniTactics/orc.png"));
				break;
			case "skeleton":
				image = ImageIO
				.read(new File(
						"C:/Users/Anthony/Desktop/New Folder/workspace/Projects/src/MiniTactics/skeleton.png"));
				break;
			}
		}
		catch (IOException ex)
		{
			throw new Error("Unable to load enemy image");
		}
	}
	
	BufferedImage getImage()
	{
		return image;
	}
}
