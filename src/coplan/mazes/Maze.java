package coplan.mazes;

public class Maze {

	private int ROWS, COLS;
	private Cell[][] cellMaze;
	private Cell[][] solution;
	
	public Maze(int rows, int cols, Cell[][] cellMaze){
		this.ROWS = rows;
		this.COLS = cols;
		this.cellMaze = cellMaze;
		
		this.solveMaze();
		//calculate difficulty -> this.difficulty = calculateDifficulty(rows, cols, binaryMaze);
	}
	
	private void solveMaze(){
		ActionTimer timer = new ActionTimer();
		timer.start();
		Solver solver = new Solver(this);
		timer.stop();
		System.out.println("Time to solve maze: " + timer.getTimeElapsed());
		
		this.solution = solver.getSolution();
	}
	
	public Cell[][] getCellMaze(){
		return cellMaze;
	}
	
	public Cell[][] getSolution(){
		return solution;
	}
	
	public int getWidth(){
		return COLS;
	}
	
	public int getHeight(){
		return ROWS;
	}
	
	
	
	/*
	private int calculateDifficulty(int rows, int cols, int[][] binaryMaze){
		PERHAPS USE HOW COMPLEX IT IS TO COMPUTE THE SOLUTION TO DETERMINE THE DIFFICULTY
		ALSO TAKE THE SIZE (W,H) INTO ACCOUNT
	}

	public int getDifficulty(){
		return difficulty;
	}
	*/
}
