package MiniTactics;

class MainHero extends Entity
{
	/** Constructor
	 * 
	 */
	MainHero(int xPos, int yPos)
	{
		setJob(PrimaryJob.WARRIOR);
		setCurrentImage(Hero.MAIN_MALE.getImage());
		setHp(30);
		setAttack(8);
		setDefense(6);
		setMagicAttack(2);
		setMagicResist(6);
		setAgility(3);
		setRowPos(xPos);
		setColPos(yPos);
		setUnitSide(Hero.MAIN_MALE);
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
