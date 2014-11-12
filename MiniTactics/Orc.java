package MiniTactics;

class Orc extends Entity
{

	/** Constructor
	 * 
	 */
	Orc(int xPos, int yPos)
	{
		setJob(PrimaryJob.ORC);
		setCurrentImage(Enemy.ORC.getImage());
		setHp(30);
		setAttack(7);
		setDefense(8);
		setMagicAttack(2);
		setMagicResist(3);
		setAgility(5);
		setRowPos(xPos);
		setColPos(yPos);
		setUnitSide(Enemy.ORC);
	}
	
	/** Returns amount of damage attack on target would deal
	 * 
	 * @param defender Target of attack
	 * @return amount of damage attack on target deals to target
	 */
	@Override
	int attack(Entity defender)
	{
		int damageDone = getAttack() - (defender.getDefense() / 2);
		return damageDone;
		
	}

}
