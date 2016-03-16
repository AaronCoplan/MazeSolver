package coplan.mazes;

public class SimpleSolution {

	private int ROWS, COLS;
	private Cell[][] solutionMaze;
	private Cell[][] cellMaze;
	
	public SimpleSolution(int rows, int cols, Cell[][] cellMaze){
		this.ROWS = rows;
		this.COLS = cols;
		
		this.cellMaze = cellMaze;
		
		//we want to copy it so that we aren't changing the actual maze
		this.solutionMaze = copy(cellMaze);
	}

	public Cell[][] solve(){

		//add implementation here
		
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
						*/
						
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
		
		for(int row = 0; row < ROWS; row++){
			for(int col = 0; col < COLS; col++){
				
				if(!solutionMaze[row][col].isBox()){
					solutionMaze[row][col].setAsSolutionCell();
				}else{
					solutionMaze[row][col] = cellMaze[row][col];
				}
			}
		}
		
		

		return solutionMaze;
	}

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
