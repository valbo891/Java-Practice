package MiniTactics;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

class World
{
	int[][] gameMap = new int[8][8];
	volatile static ArrayList<Entity> entities;
	Entity[][] unitLocations;
	ActiveTurn currentTurn;
	Ai ai;
	
	/** Constructor
	 * 
	 */
	public World()
	{
		ai = new Ai(this);
		
		// Initialize entities
		entities = new ArrayList<Entity>();
		
		// Populate array
		for (int row = 0; row < gameMap.length; row++)
		{
			for (int col = 0; col < gameMap[row].length; col++)
			{
				if (col == 0 || col == gameMap[row].length - 1)
				{
					gameMap[row][col] = 1;

				}
				//System.out.print(gameMap[row][col]);
			}
			//System.out.println();
		}
		
		// Randomly set who will get first turn
//		currentTurn = ActiveTurn.values()[new Random().nextInt(ActiveTurn.values().length)];
		
		currentTurn = ActiveTurn.PLAYER;
		
		// Add entities to list
		entities.add(new Skeleton(0, 0));
		entities.add(new  FemaleSidekick1(7,2 ));
		entities.add(new Orc(3, 4));
		entities.add(new MainHero(7, 3));
		entities.add(new MaleSidekick1(6, 4));
		entities.add(new MaleSidekick2(6, 3));
	}
	
	/** Move or Attack depending on contents of destination
	 * 
	 * @param attackingEntity attacking character
	 * @param row row position of destination square
	 * @param col column position of destination square
	 */
	boolean playTurn(Entity attackingEntity, int row, int col)
	{
		Entity defender = null;
		
		
		for (Entity entity : World.getEntities())
		{
			if (entity.getRowPos() == row
					&& entity.getColPos() == col && !entity.equals(attackingEntity))
			{
				defender = entity;
				//System.out.println(selectedEntity + " attacks " + targetEntity);
				break;
			}
		}
		
		//System.out.println("Target location: " + row + " , " + col);
		//System.out.println("attacker: " + attackingEntity + "\n" + "defender: " + defender);
		
		if(defender != null)
		{
			// attack
			if(CombatSystem.engagingLegalTarget(attackingEntity, defender))
			{
				CombatSystem.battle(attackingEntity, defender);
				// Turn transitions to opponent
				if(currentTurn == ActiveTurn.COMPUTER)
				{
					currentTurn = ActiveTurn.PLAYER;
				}
				else
				{
					currentTurn = ActiveTurn.COMPUTER;
				}
			}
			
			
			
		}
		else
		{
			// move
			if(CombatSystem.getDistance(attackingEntity.getRowPos(), attackingEntity.getColPos(), row, col) <= 2 && destinationTileIsEmpty(row, col))
			{
				System.out.println("move success");
				attackingEntity.setRowPos(row);
				attackingEntity.setColPos(col);
				System.out.println(attackingEntity + " location: " + row + " , " + col);
				
				if(!enemyIsWithinAttackRange(attackingEntity))
				{
					// Turn transitions to opponent
					if(currentTurn == ActiveTurn.COMPUTER)
					{
						currentTurn = ActiveTurn.PLAYER;
					}
					else
					{
						currentTurn = ActiveTurn.COMPUTER;
					}
				}
			}
			else 
			{
				System.out.println("move failure");
				return false;
			}
			
			
		}
		return true;
	
	}
	
	/** Indicates whether or not destination tile is currently occupied
	 * 
	 * @param row number of row to check for occupant in
	 * @param col number of col to check for occupant in
	 * @return
	 */
	static boolean destinationTileIsEmpty(int row, int col)
	{
		for(Entity entity: World.getEntities())
		{
			if(entity.getRowPos() == row && entity.getColPos() == col)
			{
				return false;
			}
		}
		return true;
	}
	
	/** Indicates whether or not an enemy is adjacent to a unit
	 * 
	 * @param attacker the character to check adjacent tiles of for enemies
	 * @return true if an enemy is within attack range, false otherwise
	 */
	static  boolean enemyIsWithinAttackRange(Entity attacker)
	{
		Enum[][] possibleEnemies = {Hero.values(),Enemy.values()};
		Enum[] enemyEnum = null; 
		for(int i = 0;i < possibleEnemies.length;i++)
		{
			for(int j = 0;j < possibleEnemies[i].length;j++)
			{
				if(possibleEnemies[i][j] == attacker.getUnitSide())
				{
					if(i == 0)
					{
						enemyEnum = possibleEnemies[1];
					}
					else if(i == 1)
					{
						enemyEnum = possibleEnemies[0];
					}
				}
			}
		}
		
		for(Entity object: World.getEntities())
		{
			for(Enum enemy: enemyEnum)
			{
				if(enemy == object.getUnitSide())
				{
					if(CombatSystem.getDistance(attacker.getRowPos(), attacker.getColPos(), object.getRowPos(), object.getColPos()) == 1)
					{
						return true;
					}
				}
			}
		}
		return false;
		
	}
	
	/** Retrieve gameMap 
	 * 
	 * @return Two dimensional array of ints representing board
	 */
	int[][] getGameMap()
	{
		return gameMap;
	}
	
	/** Set gameMap 
	 * 
	 * @param gameMap Two dimensional array of ints representing board
	 */
	void setGameMap(int[][] gameMap)
	{
		this.gameMap = gameMap; 
	}
	
	/** Retrieve entities 
	 * 
	 * @return
	 */
	static ArrayList<Entity> getEntities()
	{
		return entities;
	}
	
	/** Set entities
	 * 
	 * @param entities
	 */
	static void setEntities(ArrayList<Entity> entities)
	{
		World.entities = entities;
	}
	
	/** Retrieve currentTurn
	 * 
	 * @return constant indicating whose turn it is currently
	 */
	ActiveTurn getCurrentTurn()
	{
		return currentTurn;
	}

	Ai getAi()
	{
		return ai;
	}

	void setAi(Ai ai)
	{
		this.ai = ai;
	}
	
	
}
