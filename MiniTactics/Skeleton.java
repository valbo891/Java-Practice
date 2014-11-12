package MiniTactics;

class Skeleton extends Entity
{

	/** Constructor
	 * 
	 */
	Skeleton(int xPos, int yPos)
	{
		setJob(PrimaryJob.SKELETON);
		setCurrentImage(Enemy.SKELETON.getImage());
		setHp(30);
		setAttack(7);
		setDefense(5);
		setMagicAttack(7);
		setMagicResist(3);
		setAgility(3);
		setRowPos(xPos);
		setColPos(yPos);
		setUnitSide(Enemy.SKELETON);
	}
	
	/** Returns amount of damage attack on target would deal
	 * 
	 * @param defender Target of attack
	 * @return amount of damage attack on target deals to target
	 */
	@Override
	int attack(Entity defender)
	{
		int damageDone = getMagicAttack() - (defender.getMagicResist() / 2);
		return damageDone;
		
	}
}
