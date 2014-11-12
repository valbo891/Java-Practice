package MiniTactics;

class MaleSidekick1 extends Entity
{
	/** Constructor
	 * 
	 */
	MaleSidekick1(int xPos, int yPos)
	{
		setJob(PrimaryJob.KNIGHT);
		setCurrentImage(Hero.MALE_SIDEKICK_1.getImage());
		setHp(30);
		setAttack(8);
		setDefense(6);
		setMagicAttack(4);
		setMagicResist(6);
		setAgility(3);
		setRowPos(xPos);
		setColPos(yPos);
		setUnitSide(Hero.MALE_SIDEKICK_1);
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
