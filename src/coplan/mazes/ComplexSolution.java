package coplan.mazes;

public class ComplexSolution {

	private int rows, cols;
	private Cell[][] solutionMaze;
	
	public ComplexSolution(int rows, int cols, Cell[][] cellMaze){
		this.rows = rows;
		this.cols = cols;
		
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
				copy[row][col] = array[row][col];
			}
		}

		return copy;
	}
}
