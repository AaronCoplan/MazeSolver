package coplan.mazes;

public class SimpleSolution {

	private int ROWS, COLS;
	private Cell[][] solutionMaze;
	
	public SimpleSolution(int rows, int cols, Cell[][] cellMaze){
		this.ROWS = rows;
		this.COLS = cols;
		
		//we want to copy it so that we aren't changing the actual maze
		this.solutionMaze = copy(cellMaze);
	}

	public Cell[][] solve(){

		//add implementation here
		
		int numChanges = 0;
		
		do{
			for(int row = 0; row < ROWS; ){
				
			}
		}while(numChanges != 0);

		return solutionMaze;
	}

	private Cell[][] copy(Cell[][] array){

		Cell[][] copy = new Cell[array.length][array[0].length];

		for(int row = 0; row < array.length; row++){
			for(int col = 0; col < array[0].length; col++){
				copy[row][col] = array[row][col];
			}
		}

		return copy;
	}
}
