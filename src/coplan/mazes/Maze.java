package coplan.mazes;

public class Maze {

	private int ROWS, COLS;
	private int[][] binaryMaze;
	private int[][] solution;
	
	public Maze(int rows, int cols, int[][] binaryMaze){
		this.ROWS = rows;
		this.COLS = cols;
		this.binaryMaze = binaryMaze;
		
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
	
	/*
	private int calculateDifficulty(int rows, int cols, int[][] binaryMaze){
		PERHAPS USE HOW COMPLEX IT IS TO COMPUTE THE SOLUTION TO DETERMINE THE DIFFICULTY
		ALSO TAKE THE SIZE (W,H) INTO ACCOUNT
	}
	*/
	
	public void printBinary(){
		for(int row = 0; row < ROWS; row++){
			for(int col = 0; col < COLS; col++){
				System.out.print(binaryMaze[row][col] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public int[][] getBinary(){
		return this.binaryMaze;
	}
	
	public int[][] getSolution(){
		return this.solution;
	}
	
	public int getWidth(){
		return COLS;
	}
	
	public int getHeight(){
		return ROWS;
	}
	
	/*
	public int getDifficulty(){
		return difficulty;
	}
	*/
}
