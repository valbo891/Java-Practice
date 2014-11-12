package MiniTactics;

import java.awt.JobAttributes;
import java.awt.image.BufferedImage;

abstract class Entity
{
	// Add move stat and instead of xPos and yPos use row and column location
	// that cooresponds with 2d array
	private BufferedImage currentImage;
	private int hp = 0;
	private int attack = 0;
	private int defense = 0;
	private int magicAttack = 0;
	private int magicResist = 0;
	private int agility = 0;
	private PrimaryJob job =  null;
	private int move = 2;
	private int rowPos = 0;
	private int colPos = 0;
	private Enum unitSide;
	private int ap = 2;
	
	/** Returns amount of damage attack on target would deal
	 * 
	 * @param defender Target of attack
	 * @return amount of damage attack on target deals to target
	 */
	abstract int attack(Entity defender);

	BufferedImage getCurrentImage()
	{
		return currentImage;
	}

	void setCurrentImage(BufferedImage currentImage)
	{
		this.currentImage = currentImage;
	}

	int getAttack()
	{
		return attack;
	}

	void setAttack(int attack)
	{
		this.attack = attack;
	}

	int getDefense()
	{
		return defense;
	}

	void setDefense(int defense)
	{
		this.defense = defense;
	}

	int getMagicAttack()
	{
		return magicAttack;
	}

	void setMagicAttack(int magicAttack)
	{
		this.magicAttack = magicAttack;
	}

	int getMagicResist()
	{
		return magicResist;
	}

	void setMagicResist(int magicResist)
	{
		this.magicResist = magicResist;
	}

	int getAgility()
	{
		return agility;
	}

	void setAgility(int agility)
	{
		this.agility = agility;
	}

	int getHp()
	{
		return hp;
	}

	void setHp(int hp)
	{
		this.hp = hp;
	}

	PrimaryJob getJob()
	{
		return job;
	}

	void setJob(PrimaryJob job)
	{
		this.job = job;
	}



	int getMove()
	{
		return move;
	}

	void setMove(int move)
	{
		this.move = move;
	}

	int getRowPos()
	{
		return rowPos;
	}

	void setRowPos(int rowPos)
	{
		this.rowPos = rowPos;
	}

	int getColPos()
	{
		return colPos;
	}

	void setColPos(int colPos)
	{
		this.colPos = colPos;
	}

	Enum getUnitSide()
	{
		return unitSide;
	}

	void setUnitSide(Enum unitSide)
	{
		this.unitSide = unitSide;
	}
	
	/*@Override
	int compareTo(Entity entity)
	{
		
	}*/
}
