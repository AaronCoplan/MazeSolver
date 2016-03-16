package coplan.mazes;

public class ComplexSolution {

	private int ROWS, COLS;
	private Cell[][] solutionMaze;
	
	public ComplexSolution(int rows, int cols, Cell[][] cellMaze){
		this.ROWS = rows;
		this.COLS = cols;
		
		//we want to copy it so that we aren't changing the actual maze
		this.solutionMaze = copy(cellMaze);
	}

	public Cell[][] solve(){

		//add implementation here

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
