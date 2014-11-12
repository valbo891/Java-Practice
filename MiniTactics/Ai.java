package MiniTactics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

class Ai
{
	private World world;
	private static Map<Entity, Double> enemyMap = new LinkedHashMap<Entity, Double>();

	/**
	 * Constructor
	 * 
	 */
	Ai(World world)
	{
		this.world = world;

	}

	/**
	 * Have ai unit move and act
	 * 
	 * 
	 */
	void respond()
	{
		if (world.currentTurn == ActiveTurn.COMPUTER)
		{
			ArrayList<Entity> allEntitiesCopy = new ArrayList<Entity>(
					World.getEntities());

			outer: for (Entity computerControlledEntity : allEntitiesCopy)
			{
				if (isMemberOfEnemyEnum(computerControlledEntity))
				{
					// update map containing enemy entity, value pairs
					updateAiMap();

					ArrayList<Double> valuesList = new ArrayList<Double>(
							enemyMap.values());
					if (!CombatSystem.fetchAllEnemiesInRange(
							computerControlledEntity, 2).isEmpty()
							&& !valuesList.isEmpty())
					{
						Collections.sort(valuesList);
						for (int i = valuesList.size() - 1; i >= 0; i--)
						{
							Set<Entity> key = new HashSet<Entity>();
							for (Entry<Entity, Double> entityDoubleSet : enemyMap
									.entrySet())
							{
								if (entityDoubleSet.getValue().equals(
										valuesList.get(i)))
								{
									key.add(entityDoubleSet.getKey());
								}

							}
							for (Entity entityKey : key)
							{
								if (CombatSystem.getDistance(
										computerControlledEntity.getRowPos(),
										computerControlledEntity.getColPos(),
										entityKey.getRowPos(),
										entityKey.getColPos()) <= 3)
								{
									if (attemptToMoveAdjacentToTarget(
											computerControlledEntity, entityKey))
									{
										CombatSystem.battle(
												computerControlledEntity,
												entityKey);
										continue outer;
									}

								}
							}

						}
						boolean validLocationFound = false;
						for (int attempts = 0; validLocationFound == false; attempts++)
						{
							int rowLocation = new Random().nextInt(8);
							int colLocation = new Random().nextInt(8);

							if (World.destinationTileIsEmpty(rowLocation,
									colLocation)
									&& CombatSystem.getDistance(
											computerControlledEntity
													.getRowPos(),
											computerControlledEntity
													.getColPos(), rowLocation,
											colLocation) <= 2)
							{
								computerControlledEntity.setRowPos(rowLocation);
								computerControlledEntity.setColPos(colLocation);
								validLocationFound = !validLocationFound;
							}

						}
						if (!CombatSystem.fetchAllEnemiesInRange(
								computerControlledEntity, 0).isEmpty())
						// get // all // enemies // within // 1 // panel
						{
							CombatSystem
									.battle(computerControlledEntity,
											CombatSystem
													.fetchAllEnemiesInRange(
															computerControlledEntity,
															0)
													.get(new Random()
															.nextInt(CombatSystem
																	.fetchAllEnemiesInRange(
																			computerControlledEntity,
																			0)
																	.size())));
							continue outer;
						}
						 
						 
					}
					else
					{
						/*
						 * boolean validLocationFound = false; for (int attempts
						 * = 0; validLocationFound == false; attempts++) { int
						 * rowLocation = new Random() .nextInt(8); int
						 * colLocation = new Random() .nextInt(8);
						 * 
						 * if (World.destinationTileIsEmpty( rowLocation,
						 * colLocation) && CombatSystem .getDistance(
						 * computerControlledEntity.getRowPos(),
						 * computerControlledEntity.getColPos(), rowLocation,
						 * colLocation) <= 2) {
						 * computerControlledEntity.setRowPos(rowLocation);
						 * computerControlledEntity.setColPos(colLocation);
						 * validLocationFound = !validLocationFound; }
						 */
						seekEnemies(computerControlledEntity);

					}
					if (!CombatSystem.fetchAllEnemiesInRange(
							computerControlledEntity, 0).isEmpty()) // get all
						// enemies
						// within 1
						// panel
					{
						CombatSystem
						.battle(computerControlledEntity,
								CombatSystem
								.fetchAllEnemiesInRange(
										computerControlledEntity,
										0)
										.get(new Random()
										.nextInt(CombatSystem
												.fetchAllEnemiesInRange(
														computerControlledEntity,
														0)
														.size())));

					}
				}

			}
		}

		// Turn transitions to opponent
		if (world.currentTurn == ActiveTurn.COMPUTER)
		{
			world.currentTurn = ActiveTurn.PLAYER;
		}
		else
		{
			world.currentTurn = ActiveTurn.COMPUTER;
		}
		return;
		/*
		 * Entity object = World.getEntities().get(2);
		 * 
		 * boolean validLocation = false; for (int i = 0; validLocation ==
		 * false; i++) { int rowLocation = new Random().nextInt(8); int
		 * colLocation = new Random().nextInt(8);
		 * 
		 * if (World.destinationTileIsEmpty(rowLocation, colLocation) &&
		 * CombatSystem.getDistance(object.getRowPos(), object.getColPos(),
		 * rowLocation, colLocation) <= 2) { world.playTurn(object, rowLocation,
		 * colLocation); validLocation = !validLocation; }
		 * 
		 * }
		 * 
		 * ArrayList<Entity> enemies = new ArrayList<Entity>();
		 * if(World.enemyIsWithinAttackRange(object)) {
		 * 
		 * for(Position p: CombatSystem.getSurroundingTiles(object.getRowPos(),
		 * object.getColPos(), 1)) { for(Entity e: World.getEntities()) {
		 * if(p.getRowNumber() == e.getRowPos() && p.getColNumber() ==
		 * e.getColPos() && CombatSystem.engagingLegalTarget(object, e)) {
		 * enemies.add(e); } } }
		 * 
		 * }
		 * 
		 * if(!enemies.isEmpty()) { int enemyIndex = new
		 * Random().nextInt(enemies.size());
		 * 
		 * // attack world.playTurn(object, enemies.get(enemyIndex).getRowPos(),
		 * enemies.get(enemyIndex).getColPos()); }
		 */

		// Map<Position, Integer> map = new HashMap<Position, Integer>();

		// assign all enemies a value

	}

	/**
	 * Moves towards enemies
	 * 
	 * @param seeker
	 *            character that is looking for enemies to fight
	 */
	/*
	 * private void seekEnemies(Entity seeker) { Entity highestValueEnemy =
	 * null; ArrayList<Double> values = new ArrayList(enemyMap.values()); if
	 * (!values.isEmpty()) { Collections.sort(values); for (Entry entry :
	 * enemyMap.entrySet()) { if
	 * (entry.getValue().equals(values.get(values.size() - 1))) {
	 * highestValueEnemy = (Entity) entry.getKey(); //break; } if
	 * (highestValueEnemy != null) { int rowDifference =
	 * Math.abs(seeker.getRowPos() - highestValueEnemy.getRowPos()); int
	 * colDifference = Math.abs(seeker.getColPos() -
	 * highestValueEnemy.getColPos());
	 * 
	 * int rowsToMove = 0; int colsToMove = 0;
	 * 
	 * if (rowDifference == 0) {
	 * 
	 * } else if (rowDifference == 1) { rowsToMove = 1; } else if (rowDifference
	 * > 1) { rowsToMove = 2; }
	 * 
	 * if (colDifference == 0) {
	 * 
	 * } else if (colDifference == 1) { colsToMove = 1; } else if (colDifference
	 * > 1) { colsToMove = 2; }
	 * 
	 * if (rowDifference != 0) { if (seeker.getRowPos() -
	 * highestValueEnemy.getRowPos() > 0) { rowsToMove *= -1; } else if
	 * (seeker.getRowPos() - highestValueEnemy.getRowPos() < 0) { rowsToMove *=
	 * 1; } } if (colDifference != 0) { if (seeker.getColPos() -
	 * highestValueEnemy.getColPos() > 0) { colsToMove *= -1; } else if
	 * (seeker.getColPos() - highestValueEnemy.getColPos() < 0) { colsToMove *=
	 * 1; } } if(World.destinationTileIsEmpty(seeker.getRowPos() + rowsToMove,
	 * seeker.getColPos() + colsToMove)) { seeker.setRowPos(seeker.getRowPos() +
	 * rowsToMove); seeker.setColPos(seeker.getColPos() + colsToMove); return; }
	 * } }
	 * 
	 * // account for target space being occupied(even by ally)
	 * 
	 * } }
	 */

	/**
	 * Moves towards enemies
	 * 
	 * @param seeker
	 *            character that is looking for enemies to fight
	 */
	private void seekEnemies(Entity seeker)
	{
		// Entity highestValueEnemy = null;
		ArrayList<Double> values = new ArrayList(enemyMap.values());
		if (!values.isEmpty())
		{
			Collections.sort(values);
			for (int i = values.size() - 1; i > 0; i--)
			{
				ArrayList<Entity> enemiesWithValue = new ArrayList<Entity>(
						enemiesWithMatchingValue(values.get(i)));
				for (Entity enemy : enemiesWithValue)
				{
					int rowDifference = Math.abs(seeker.getRowPos()
							- enemy.getRowPos());
					int colDifference = Math.abs(seeker.getColPos()
							- enemy.getColPos());

					int rowsToMove = 0;
					int colsToMove = 0;

					if (rowDifference == 0)
					{

					}
					else if (rowDifference == 1)
					{
						rowsToMove = 1;
					}
					else if (rowDifference > 1)
					{
						rowsToMove = 2;
					}

					if (colDifference == 0)
					{

					}
					else if (colDifference == 1)
					{
						colsToMove = 1;
					}
					else if (colDifference > 1)
					{
						colsToMove = 2;
					}

					if (rowDifference != 0)
					{
						if (seeker.getRowPos() - enemy.getRowPos() > 0)
						{
							rowsToMove *= -1;
						}
						else if (seeker.getRowPos() - enemy.getRowPos() < 0)
						{
							rowsToMove *= 1;
						}
					}
					if (colDifference != 0)
					{
						if (seeker.getColPos() - enemy.getColPos() > 0)
						{
							colsToMove *= -1;
						}
						else if (seeker.getColPos() - enemy.getColPos() < 0)
						{
							colsToMove *= 1;
						}
					}
					if (World.destinationTileIsEmpty(seeker.getRowPos()
							+ rowsToMove, seeker.getColPos() + colsToMove))
					{
						seeker.setRowPos(seeker.getRowPos() + rowsToMove);
						seeker.setColPos(seeker.getColPos() + colsToMove);
						return;
					}
				}
			}
		}
	}

	private ArrayList<Entity> enemiesWithMatchingValue(Double value)
	{
		ArrayList<Entity> enemies = new ArrayList<Entity>();
		for (Entry<Entity, Double> entry : enemyMap.entrySet())
		{
			if (entry.getValue().equals(value))
			{
				enemies.add(entry.getKey());
			}
		}
		return enemies;
	}

	/**
	 * Attempt to move to tile that is adjacent to target
	 * 
	 * @param seeker
	 *            unit that is attempting to move to a tile adjacent to target
	 * @param target
	 *            unit to attempt to move adjacent to
	 * @return true if moving adjacent to target is possible, false otherwise
	 */
	private boolean attemptToMoveAdjacentToTarget(Entity seeker, Entity target)
	{
		for (Position p : CombatSystem.getSurroundingTiles(target.getRowPos(),
				target.getColPos(), 1))
		{
			if (World.destinationTileIsEmpty(p.rowNumber, p.colNumber)
					&& CombatSystem.getDistance(seeker.getRowPos(),
							seeker.getColPos(), p.rowNumber, p.colNumber) <= 2)
			{
				seeker.setRowPos(p.rowNumber);
				seeker.setColPos(p.colNumber);
				return true;
			}
		}
		return false;
	}

	private void updateAiMap()
	{
		enemyMap.clear();
		for (Entity entity : World.getEntities())
		{
			if (!isMemberOfEnemyEnum(entity))
			{
				double value = 100 * (1.0 - (entity.getHp() / 30.0));
				enemyMap.put(entity, value);
			}

		}

	}

	/**
	 * Indicates whether an entity is an enemy unit
	 * 
	 * @param entity
	 *            character to determine side of
	 * @return true if entity is a unit controlled by computer, false otherwise
	 */
	private boolean isMemberOfEnemyEnum(Entity entity)
	{
		for (Enum enumMember : Enemy.values())
		{
			if (enumMember.equals(entity.getUnitSide()))
			{
				return true;
			}
		}
		return false;
	}

}

class Position
{
	int rowNumber = 0;
	int colNumber = 0;

	Position(int rowNumber, int colNumber)
	{
		this.rowNumber = rowNumber;
		this.colNumber = colNumber;
	}

	int getRowNumber()
	{
		return rowNumber;
	}

	int getColNumber()
	{
		return colNumber;
	}
}
