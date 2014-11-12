package MiniTactics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

enum Hero
{
	MAIN_MALE("main"), MALE_SIDEKICK_1("sidekick_1"), MALE_SIDEKICK_2("sidekick_2"), FEMALE_SIDEKICK_1("sidekick_3");
	
	private BufferedImage image;
	
	/** Constructor 
	 * 
	 */
	Hero(String classification)
	{
		try
		{
			switch(classification)
			{
			case "main": image = ImageIO.read(new File("C:/Users/Anthony/Desktop/New Folder/workspace/Projects/src/MiniTactics/hero_male.png"));break;
			case "sidekick_1": image = ImageIO.read(new File("C:/Users/Anthony/Desktop/New Folder/workspace/Projects/src/MiniTactics/sidekick_male1.png"));break;
			case "sidekick_2": image = ImageIO.read(new File("C:/Users/Anthony/Desktop/New Folder/workspace/Projects/src/MiniTactics/sidekick_male2.png"));break;
			case "sidekick_3": image = ImageIO.read(new File("C:/Users/Anthony/Desktop/New Folder/workspace/Projects/src/MiniTactics/sidekick_female1.png"));break;
			
			
			}
		}
		catch (IOException e)
		{
			throw new Error("Unable to load enemy image");
		}
	}
	
	BufferedImage getImage()
	{
		return image;
	}
}
