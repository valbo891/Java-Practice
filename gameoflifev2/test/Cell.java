package gameoflifev2.test;



class Cell
{
	
	CellStatus status;
	
	Cell(CellStatus status)
	{
		
		if(status.compareTo(CellStatus.OFF) == 0)
		{
			this.status = CellStatus.OFF;
		}
		else if(status.compareTo(CellStatus.ON) == 0)
		{
			this.status = CellStatus.ON;
		}
	}

	CellStatus getStatus()
	{
		return status;
	}

	void setStatus(CellStatus status)
	{
		this.status = status;
	}
	
	
	
	
}

