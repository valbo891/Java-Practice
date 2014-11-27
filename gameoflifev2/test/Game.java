package gameoflifev2.test;

import java.util.ArrayList;



class Game
{
	private int numberOfRounds = 0;
	private int widthOfStructure = 0;
	private int heightOfStructure = 0;
	private static Cell[][][] gameBoards;
	
	Game(int numberOfRounds, int widthOfStructure, int heightOfStructure, String[] gridContents)
	{
		this.numberOfRounds = numberOfRounds;
		this.widthOfStructure = widthOfStructure;
		this.heightOfStructure = heightOfStructure;
		
		
		gameBoards = new Cell[numberOfRounds + 1][widthOfStructure][heightOfStructure];
		
		// initialize first game board
		for(int cellContainerIndex = 0;cellContainerIndex < gameBoards.length;cellContainerIndex++)
		{
			//gameBoards[cellContainerIndex] = new Cell[]
			if(cellContainerIndex == 0)
			{
				for(int cellRowNumber = 0;cellRowNumber < gameBoards[cellContainerIndex].length;cellRowNumber++)
				{
					int contentsIndex = 0;
					//gameBoards[cellContainerIndex][cellRowNumber] = new Cell[gameBoards[cellContainerIndex][cellRowNumber].length];// inserted for testing
					for(int cellIndex = 0;cellIndex < gameBoards[cellContainerIndex][cellRowNumber].length;cellIndex++)
					{
						if(cellIndex < gridContents[cellRowNumber].length() )
						{
							int cellIndexCopy = cellIndex;
							int lengthOfString = gridContents[cellRowNumber].length();
							int i = cellIndexCopy + lengthOfString;
							if(String.valueOf(gridContents[cellRowNumber].charAt(contentsIndex)).equals(String.valueOf('.')) || String.valueOf(gridContents[cellRowNumber].charAt(contentsIndex)).equals( String.valueOf('#')) )
							{	
								//gameBoards[cellContainerIndex][cellRowNumber][cellIndex] = new Cell(gridContents[cellRowNumber].charAt(contentsIndex));
								
								switch(gridContents[cellRowNumber].charAt(contentsIndex))
								{
								case '.': gameBoards[cellContainerIndex][cellRowNumber][cellIndex] = new Cell(CellStatus.OFF);break;
								case '#': gameBoards[cellContainerIndex][cellRowNumber][cellIndex] = new Cell(CellStatus.ON);break;
								}
								
							}
							contentsIndex++;
						}
					}
					
				}
			}
		}
		populateContainers();
		return;
		
		
	}
	
	private void populateContainers()
	{
		if(gameBoards.length > 1)
		{
			for(int cellContainerIndex = 1;cellContainerIndex < gameBoards.length;cellContainerIndex++)
			{
				for(int cellRowNumber = 0;cellRowNumber < gameBoards[cellContainerIndex].length;cellRowNumber++)
				{
					for(int cellIndex = 0;cellIndex < gameBoards[cellContainerIndex][cellRowNumber].length;cellIndex++)
					{
						int previousContainerIndex = cellContainerIndex - 1;
						ArrayList<Position> adjacentCellPositions = getAdjacentCells(cellRowNumber, cellIndex);
						if(gameBoards[previousContainerIndex][cellRowNumber][cellIndex] != null)
						{
							gameBoards[cellContainerIndex][cellRowNumber][cellIndex] = new Cell(findStatus(previousContainerIndex, adjacentCellPositions,gameBoards[previousContainerIndex][cellRowNumber][cellIndex].getStatus() ));
						}
					}

				}

			}
		}
	}
	
	/**
	 * 
	 * @param previousContainerIndex
	 * @param adjacentCellPositions
	 * @param currentStatus previous status of the cell whose adjacent positions are used
	 * @return
	 */
	public CellStatus findStatus(int previousContainerIndex, ArrayList<Position> adjacentCellPositions, CellStatus currentStatus)
	{
		int numberOfAdjacentActiveCells = 0;
		
		for(Position p: adjacentCellPositions)
		{
			if(gameBoards[previousContainerIndex][p.getRowNumber()][p.getColNumber()] != null && gameBoards[previousContainerIndex][p.getRowNumber()][p.getColNumber()].getStatus().equals(CellStatus.ON))
			{
				numberOfAdjacentActiveCells++;
			}
		}
		
		CellStatus finalStatus = currentStatus;
		
		if(currentStatus.equals(CellStatus.OFF) && numberOfAdjacentActiveCells == 3)
		{
			finalStatus = CellStatus.ON;
		}
		else if(currentStatus.equals(CellStatus.ON) && numberOfAdjacentActiveCells < 2)
		{
			finalStatus = CellStatus.OFF;
		}
		else if(currentStatus.equals(CellStatus.ON) && numberOfAdjacentActiveCells > 3)
		{
			finalStatus = CellStatus.OFF;
		}
		return finalStatus;
	}
	
	/*void start()
	{

		Scanner s = new Scanner(System.in);

		// Get user input 
		System.out.println("Please enter the number of rounds, the width of the cell structure,"
				+ " height of the cell structure, along with the grid contents");
		String input = null;

		int numberOfRounds = 0;
		int widthOfStructure = 0;
		int heightOfStructure = 0;
		ArrayList<String> gridInputStrings = new ArrayList<String>();

		// Read input into program
		for(int i = 0;i < 3;i++ )
		{
			switch(i)
			{
			case 0: numberOfRounds = Integer.parseInt(s.next());break;
			case 1: widthOfStructure = Integer.parseInt(s.next());heightOfStructure = Integer.parseInt(s.next());break;
			case 2: 
					while(true)
					{		
						input = s.nextLine();
						
						if( input == null)
						{
							break;
						}
						else if(input.compareTo("") == 0)
						{
						}
						else if(input != null)
						{
							gridInputStrings.add(input);
						}
						
					}
					break;
			default: throw new Error("Unacceptable input detected");
			}
			

		}
		System.out.println(Arrays.toString(gridInputStrings.toArray()));
	}*/
	
/*	void start()
	{
		while (true)
		{

			try
			{
				// Get user input
				int numberOfRounds = 0;
				int widthOfStructure = 0;
				int heightOfStructure = 0;
				ArrayList<String> gridInputStrings = new ArrayList<String>();

				// Read input into program
				String unparsedInput = JOptionPane
						.showInputDialog(
								null,
								"Please enter the number of rounds, the width of the cell structure,"
										+ " height of the cell structure, along with the grid contents");

				String[] strings = unparsedInput.split(String
						.valueOf((char) 32));
				numberOfRounds = Integer.parseInt(strings[0]);
				widthOfStructure = Integer.parseInt(strings[1]);
				heightOfStructure = Integer.parseInt(strings[2]);
				for (int i = 3; i < strings.length; i++)
				{
					if(strings.length >= 4)
					{
						gridInputStrings.add(strings[i]);
					}
				}
				if (numberOfRounds < 10000 && numberOfRounds > 0
						&& widthOfStructure > 0 && heightOfStructure > 0)
				{
					break;
				}
			}
			catch (Exception ex)
			{
				throw new Error("Invalid Input Detected");
			}
		}
		// System.out.println(Arrays.toString(gridInputStrings.toArray()));
	}*/
	
	
	
/*	public static void main(String[] args)
	{
		while(true)
		{
			try
			{
				// Get user input
				int numberOfRounds = 0;
				int widthOfStructure = 0;
				int heightOfStructure = 0;
				ArrayList<String> gridInputStrings = new ArrayList<String>();
	
				// Read input into program
				String unparsedInput = JOptionPane
						.showInputDialog(
								null,
								"Please enter the number of rounds, the width of the cell structure,"
										+ " height of the cell structure, along with the grid contents");
	
				String[] strings = unparsedInput.split(String
						.valueOf((char) 32));
				numberOfRounds = Integer.parseInt(strings[0]);
				widthOfStructure = Integer.parseInt(strings[1]);
				heightOfStructure = Integer.parseInt(strings[2]);
				for (int i = 3; i < strings.length; i++)
				{
					if(strings.length >= 4)
					{
						gridInputStrings.add(strings[i]);
					}
				}
				if (numberOfRounds < 10000 && numberOfRounds > 0
						&& widthOfStructure > 0 && heightOfStructure > 0)
				{
					break;
				}
				String[] list = (String[])gridInputStrings.toArray(new String[gridInputStrings.size()]);
				System.out.println();	
				Game game = new Game(numberOfRounds, widthOfStructure, heightOfStructure,list );
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				throw new Error("Invalid Input Detected");
				
			}
		}
		
		//game.start();
	}*/
	

	private ArrayList<Position> getAdjacentCells(int row, int col)
	{
		// accept row, col locations as parameters, return 8 positions surrounding
		// the cell whose adjacent cells you're searching for(if they're outside grid bounds
		// then readjust the position coordinates so it will wrap. 
		
		ArrayList<Position> positions = new ArrayList<Position>();
		
		for(int direction = 0;direction < 8;direction++)
		{
			int amountToAddToRow = 0;
			int amountToAddToCol = 0;
			
			switch(direction)
			{
				case 0: amountToAddToRow = -1;amountToAddToCol = -1;break;
				case 1: amountToAddToRow = -1;amountToAddToCol = 0;break;
				case 2: amountToAddToRow = -1;amountToAddToCol = 1;break;
				case 3: amountToAddToRow = 0;amountToAddToCol = -1;break;
				case 4: amountToAddToRow = 0;amountToAddToCol = 1;break;
				case 5: amountToAddToRow = 1;amountToAddToCol = -1;break;
				case 6: amountToAddToRow = 1;amountToAddToCol = 0;break;
				case 7: amountToAddToRow = 1;amountToAddToCol = 1;break;
			}
			positions.add(new Position(row + amountToAddToRow,col + amountToAddToCol));
		}
		wrapInvalidPositions(positions, row, col);
		return positions;
		
	
	}
	
	/** Readjusts position coordinates if it corresponds to a location 
	 *  that is not on the grid
	 * @param positions List of locations on the grid that surround a cell
	 * @row row index of original cell
	 * @col column index of original cell
	 */
	private void wrapInvalidPositions(ArrayList<Position> positions, int row, int col)
	{
		for(Position p : positions)
		{
			int rowManipulation = 0;
			int colManipulation = 0;
			
			int positionRowIndex = p.getRowNumber();
			int positionColIndex = p.getColNumber();
			
		/*	if((p.getRowNumber() == -1 || p.getRowNumber() == gameBoards[0].length)&&(p.getColNumber() == -1 || p.getColNumber() == gameBoards[0][0].length))
			{
				if(rowManipulation > 0)
				{
					rowManipulation = 1;
				}
				else if(rowManipulation < 0)
				{
					rowManipulation = -1;
				}
				if(colManipulation > 0)
				{
					colManipulation = 1;
				}
				else if(colManipulation < 0)
				{
					colManipulation = -1;
				}
				alterPositionCoordinates(p, rowManipulation, colManipulation,positionRowIndex, positionColIndex);
			}*/
			if(p.getRowNumber() == -1 || p.getRowNumber() == gameBoards[0].length)
			{
				if(p.getRowNumber() > 0)
				{
					rowManipulation = 1;
					positionRowIndex = -1;
				}
				else if(p.getRowNumber() < 0)
				{
					rowManipulation = -1;
					positionRowIndex = gameBoards[0].length;
				}
				//alterPositionCoordinates(p, rowManipulation, colManipulation,positionRowIndex, positionColIndex);
			}
			if(p.getColNumber() == -1 || p.getColNumber() == gameBoards[0][0].length)
			{
				if(p.getColNumber() > 0)
				{
					colManipulation = 1;
					positionColIndex = -1;
				}
				else if(p.getColNumber() < 0)
				{
					colManipulation = -1;
					positionColIndex = gameBoards[0][0].length;
				}
				
			}
			alterPositionCoordinates(p, rowManipulation, colManipulation,positionRowIndex, positionColIndex);
		}
	}
	
	
	private void alterPositionCoordinates(Position position,int rowManipulation, int colManipulation, int rowNumber, int colNumber)
	{
		
		
		while(true)
		{
			rowNumber += rowManipulation;
			colNumber += colManipulation;
			
			if(gameBoards[0][rowNumber][colNumber] != null)
			{
				position.setRowNumber(rowNumber);
				position.setColNumber(colNumber);
				break;
			}
		}
	}

	static Cell[][][] getGameBoards()
	{
		return Game.gameBoards;
	}

	static void setGameBoards(Cell[][][] gameBoards)
	{
		Game.gameBoards = gameBoards;
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

	void setRowNumber(int rowNumber)
	{
		this.rowNumber = rowNumber;
	}

	void setColNumber(int colNumber)
	{
		this.colNumber = colNumber;
	}
}
