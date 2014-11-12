package MiniTactics;

import java.util.ArrayList;
import java.util.Random;

class CombatSystem
{
	CombatSystem()
	{
		
	}
	
	/** Indicates whether attacker and defender are on opposing sides
	 * 
	 * @param attacker the attacking character
	 * @param defender the defending character
	 * @return true if opponent is an enemy, false otherwise
	 */
	static boolean engagingLegalTarget(Entity attacker, Entity defender)
	{
		Enum[] attackerEnum = null;
		
		Enum[][] characterEnums = {Hero.values(),Enemy.values()};
		
		for(int i = 0;i < characterEnums.length;i++)
		{
			for(int j = 0;j < characterEnums[i].length;j++)
			{
				if(characterEnums[i][j] == attacker.getUnitSide())
				{
					attackerEnum = characterEnums[i];
				}
			}
		}
		
		for(Enum enumMember: attackerEnum)
		{
			if(enumMember == defender.getUnitSide())
			{
				System.out.println("Same side");
				return false;
			}
		}
		System.out.println("opposing side");
		return true;
	}
	
	/**	Returns the distance between 2 locations
	 * 
	 * @param x			First things row location
	 * @param y			First things column location
	 * @param x1		Second things column location
	 * @param y1		Second things column location
	 * @return			Distance between locations
	 */
	static int getDistance(int x, int y, int x1, int y1)
	{
		int firstLocationIndicesConvertedToInt = Integer.valueOf(String.valueOf(x) + String.valueOf(y));
		int secondLocationIndicesConvertedToInt = Integer.valueOf(String.valueOf(x1) + String.valueOf(y1));
		int greaterIndicesX = 0;
		int greaterIndicesY = 0;
		int lesserIndicesX = 0;
		int lesserIndicesY = 0;
		int range = 0;

		if(x != x1 ) // In different rows regardless of column
		{
			if(x > x1) 
			{
				greaterIndicesX = x;
				greaterIndicesY = y;
				lesserIndicesX = x1;
				lesserIndicesY = y1;
			}
			else 
			{
				greaterIndicesX = x1;
				greaterIndicesY = y1;
				lesserIndicesX = x;
				lesserIndicesY = y;
			}
		}
		else if(firstLocationIndicesConvertedToInt == secondLocationIndicesConvertedToInt) // In same row and column
		{
			greaterIndicesX = x;
			greaterIndicesY = y;
			lesserIndicesX = x1;
			lesserIndicesY = y1;
			

		}
		else // In same row and different column
		{
			if(firstLocationIndicesConvertedToInt > secondLocationIndicesConvertedToInt)
			{
				greaterIndicesX = x;
				greaterIndicesY = y;
				lesserIndicesX = x1;
				lesserIndicesY = y1;
			}
			else if(secondLocationIndicesConvertedToInt > firstLocationIndicesConvertedToInt)
			{
				greaterIndicesX = x1;
				greaterIndicesY = y1;
				lesserIndicesX = x;
				lesserIndicesY = y;
			}
		
		}
		range += greaterIndicesX - lesserIndicesX;
		//greaterIndicesX = greaterIndicesX - range;
		int yDifference = greaterIndicesY - lesserIndicesY;
		if(yDifference < 0)		// If difference in y values is negative get absolute value
		{
			yDifference = yDifference * -1; 
		}
		range += yDifference;


		return range;
	}
	
	/** Returns a list containing all enemies that are in attack range
	 * 
	 * @param seeker the unit that is seeking enemies
	 * @param move the movement range of the seeker
	 * @return list of enemies
	 */
	static ArrayList<Entity> fetchAllEnemiesInRange(Entity seeker, int move)
	{
		ArrayList<Entity> enemyList = new ArrayList<Entity>();
		
		for(Entity character: World.getEntities())
		{
			if(engagingLegalTarget(seeker, character) && getDistance(seeker.getRowPos(), seeker.getColPos(), character.getRowPos(), character.getColPos()) <= move + 1)
			{
				enemyList.add(character);
			}
		}
		return enemyList;
	}
	
	/** Get all the tile locations surrounding a certain tile
	 * 
	 * @param row row location to find surrounding tiles for
	 * @param col col location to find surrounding tiles for
	 * @param range radius to search within(usually identical to move value)
	 * @return list of surrounding tile positions
	 */
	static ArrayList<Position> getSurroundingTiles(int row, int col, int range)
	{
		ArrayList<Position> positions = new ArrayList<Position>();
		
		for(int i = 0;i < 8;i++)
		{
			for(int j = 0;j < 8;j++)
			{
				if(CombatSystem.getDistance(row, col, i, j) <= range)
				{
					assert (range >= 0) : "range value passed to getSorroundingTiles method is illegal";
					positions.add(new Position(i, j));
				}
			}
		}
		
		return positions;
	}
	
	/** Perform battle related operations
	 * 
	 * @param attacker character that is attacking
	 * @param defender character that is defending
	 */
	static void battle(Entity attacker, Entity defender)
	{
		double critChance = attacker.getAgility() / 100.0;
		
		int damage = attacker.attack(defender);
		
		if(new Random().nextDouble() < critChance)
		{
			damage *= 2;
		}
		
		defender.setHp(defender.getHp() - damage);
		if(defender.getHp() <= 0)
		{
			World.getEntities().remove(defender);
		}
		
	}
}
