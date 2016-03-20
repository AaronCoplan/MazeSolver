package coplan.mazes;

public class Solver {

	private Cell[][] cellMaze;
	private Cell[][] solvedMaze;
	
	private int ROWS, COLS;
	
	public Solver(Maze maze){
		this.ROWS = maze.getHeight();
		this.COLS = maze.getWidth();
		this.cellMaze = maze.getCellMaze();
		
		this.solvedMaze = solveMaze(maze.isComplex());
	}
	
	private Cell[][] solveMaze(boolean complex){
		if(complex == true){
			return complexSolution();
		}else{
			return simpleSolution();
		}
	}
	
	private Cell[][] simpleSolution(){
		return new SimpleSolution(ROWS, COLS, cellMaze).solve();
	}
	
	private Cell[][] complexSolution() {
		return new ComplexSolution(cellMaze).solve();
	}
	
	public Cell[][] getSolution(){
		return this.solvedMaze;
	}
}
