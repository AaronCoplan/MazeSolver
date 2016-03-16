package coplan.mazes;

public class Solver {

	private Cell[][] cellMaze;
	private Cell[][] solvedMaze;
	
	private int ROWS, COLS;
	
	public Solver(Maze maze){
		this.ROWS = maze.getHeight();
		this.COLS = maze.getWidth();
		this.cellMaze = maze.getCellMaze();
		
		this.solvedMaze = solveMaze();
	}
	
	private Cell[][] solveMaze(){
		//if it is a simple maze
		return simpleSolution();
		//else if it is complex
		//return complexSolution();
	}
	
	private Cell[][] simpleSolution(){
		
		//needs rewritten for cell implementation
		return new SimpleSolution(ROWS, COLS, cellMaze).solve();
		
	}
	
	private Cell[][] complexSolution() {
		
		//needs written with cell implementation in mind
		return new ComplexSolution(ROWS, COLS, cellMaze).solve();
		
	}
	
	public Cell[][] getSolution(){
		return this.solvedMaze;
	}
}
