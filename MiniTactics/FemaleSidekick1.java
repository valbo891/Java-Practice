package MiniTactics;

class FemaleSidekick1 extends Entity
{
	/** Constructor
	 * 
	 */
	FemaleSidekick1(int xPos, int yPos)
	{
		setJob(PrimaryJob.THIEF);
		setCurrentImage(Hero.FEMALE_SIDEKICK_1.getImage());
		setHp(30);
		setAttack(6);
		setDefense(5);
		setMagicAttack(3);
		setMagicResist(6);
		setAgility(8);
		setRowPos(xPos);
		setColPos(yPos);
		setUnitSide(Hero.FEMALE_SIDEKICK_1);
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
