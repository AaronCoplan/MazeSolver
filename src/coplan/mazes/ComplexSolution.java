package coplan.mazes;

public class ComplexSolution {

	private int ROWS, COLS;
	private Cell[][] solutionMaze;
	private Cell[][] cellMaze;
	
	int beginX = 0;
	int beginY = 0;
	int endX = ROWS-1;
	int endY = COLS-1;
	
	boolean[][] wasHere = new boolean[ROWS][COLS];
	boolean[][] solutionCell = new boolean[ROWS][COLS];
	
	public ComplexSolution(Maze maze){
		ROWS = maze.getWidth();
		COLS = maze.getHeight();
		
		cellMaze = maze.getCellMaze();
		
		//we want to copy it so that we aren't changing the actual maze
		this.solutionMaze = copy(cellMaze);
	}

	/*
	public Cell[][] solve()
	{		
		for(int row = 0; row < ROWS; row++)
		{
			for(int col = 0; col < COLS; col++)
			{
				wasHere[row][col] = false;
				solutionCell = false;
			}
		}
		
		boolean b = recursiveSolve(beginX, beginY)
	}
	
	public boolean recursiveSolve(int x, int y)
	{
		if(x == endX && y == endY)
		{
			return true;
		}
		if(Cell[x][y])
	}
	
	
	
	
	
/*	public Cell[][] solve(){

		boolean changeIsMade;
		
		do{
			changeIsMade = false;
			
			for(int row = 0; row < ROWS; row++){
				for(int col = 0; col < COLS; col++){
					Cell c = solutionMaze[row][col];
					
					//have to make sure it only has one opening and is not the start or end of the maze
					if(c.getNumOpening() == 1 && !c.isStart() && !c.isEnd()){
						/*
						 * having only 1 opening indicates it is a dead end
						 * 1 opening implies it has 3 walls
						 * thus, find and close off the fourth wall
						 * do the corresponding action for its neighbor
						
						
						if(c.isBottomOpen()){
							c.setBottomOpening(false);
							solutionMaze[row+1][col].setTopOpening(false);
						}
						
						if(c.isTopOpen()){
							c.setTopOpening(false);
							solutionMaze[row-1][col].setBottomOpening(false);
						}
						
						if(c.isLeftOpen()){
							c.setLeftOpening(false);
							solutionMaze[row][col-1].setRightOpening(false);
						}
						
						if(c.isRightOpen()){
							c.setRightOpening(false);
							solutionMaze[row][col+1].setLeftOpening(false);
						}
						
						changeIsMade = true;
					}
				}
			}
		}while(changeIsMade);
			
		int[][] numbers = new int[ROWS][COLS];
		for(int x = 0; x < ROWS; x++)
		{
			for(int y = 0; y < COLS; y++)
			{
				if(solutionMaze[x][y].isSolutionCell())
					numbers[x][y] = -1;
				else
					numbers[x][y] = -2;
			}
		}
		
		boolean changeIsMade2 = false;
		numbers[0][0] = 1;
		do
		{
			for(int x = 0; x < ROWS; x++)
			{
				for(int y = 0; y < COLS; y++)
				{
					if(numbers[x][y] == -1)
					{
						//check left
						if(solutionMaze[x][y].isLeftOpen() && numbers[x-1][y] > 0)
							numbers[x][y] = 1 + numbers[x-1][y];
						//check up
						if(solutionMaze[x][y].isTopOpen() && numbers[x][y+1] > 0)
							numbers[x][y] = 1 + numbers[x][y+1];

						//check right
						if(solutionMaze[x][y].isRightOpen() && numbers[x+1][y] > 0)
							numbers[x][y] = 1 + numbers[x+1][y];

						//check down
						if(solutionMaze[x][y].isBottomOpen() && numbers[x][y-1] > 0)
							numbers[x][y] = 1 + numbers[x][y-1];
						
					}
				}
			}
		} while(changeIsMade2);
		
		
		Cell[][] newSolutionMaze = copy(cellMaze);
		
		for(int row = 0; row < ROWS; row++){
			for(int col = 0; col < COLS; col++){
				if(!solutionMaze[row][col].isBox()){
					newSolutionMaze[row][col].setAsSolutionCell();
				}
			}
		}

		return newSolutionMaze;
	}
*/	

	//you never use this...is this method needed?
	//if not, delete it
	
	private Cell[][] copy(Cell[][] array){

		Cell[][] copy = new Cell[array.length][array[0].length];

		for(int row = 0; row < array.length; row++){
			for(int col = 0; col < array[0].length; col++){
				Cell c = array[row][col];
				Cell newCell = new Cell(c.isTopOpen(), c.isRightOpen(), c.isBottomOpen(), c.isLeftOpen());
				
				if(c.isStart()){
					newCell.setAsStart();
				}else if(c.isEnd()){
					newCell.setAsEnd();
				}
				
				copy[row][col] = newCell;
			}
		}

		return copy;
	}
}
