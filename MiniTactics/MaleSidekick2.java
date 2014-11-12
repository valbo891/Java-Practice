package MiniTactics;

class MaleSidekick2 extends Entity
{
	/** Constructor
	 * 
	 */
	MaleSidekick2(int xPos, int yPos)
	{
		setJob(PrimaryJob.BATTLEMAGE);
		setCurrentImage(Hero.MALE_SIDEKICK_2.getImage());
		setHp(30);
		setAttack(2);
		setDefense(4);
		setMagicAttack(9);
		setMagicResist(7);
		setAgility(5);
		setRowPos(xPos);
		setColPos(yPos);
		setUnitSide(Hero.MALE_SIDEKICK_2);
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
