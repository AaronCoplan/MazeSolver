package coplan.mazes;

public class Maze {

	private int ROWS, COLS;
	private Cell[][] cellMaze;
	private Cell[][] cellSolution;
	private boolean[][] binaryMaze;
	private boolean[][] binarySolution;
	private boolean complex;
	
	public Maze(int rows, int cols, Cell[][] cellMaze, boolean complex){
		this.ROWS = rows;
		this.COLS = cols;
		this.cellMaze = cellMaze;
		this.complex = complex;
		
		translateToBinary();
		
		this.solveMaze();
		//calculate difficulty -> this.difficulty = calculateDifficulty(rows, cols, binaryMaze);
	}
	
	private void solveMaze(){
		ActionTimer timer = new ActionTimer();
		timer.start();
		Solver solver = new Solver(this);
		
		timer.stop();
		System.out.println("Time to solve maze: " + timer.getTimeElapsed());
		
		this.cellSolution = solver.getSolution();
	}
	
	private void translateToBinary(){
		//translate the Cell[][] to Boolean[][] for solving purposes
		//this will improve the ability to solve complex mazes
		
		//go cell by cell, only looking at the right and bottom borders of each cell
		
		binaryMaze = new boolean[ROWS*2+1][COLS*2+1];
		
		for(int row = 0; row < ROWS*2+1; row++)
		{
			for(int col = 0; col < ROWS*2+1; col++)
			{
				
			}
		}
		
		printBinaryMaze();
	}
	
	private void printBinaryMaze(){
		for(int row = 0; row < binaryMaze.length; row++){
			for(int col = 0; col < binaryMaze[0].length; col++){
				String str = binaryMaze[row][col] + "";
				System.out.print(str.substring(0,1) + " ");
			}
			System.out.println();
		}
	}
	
	public boolean[][] getBinaryMaze(){
		return binaryMaze;
	}
	
	public Cell[][] getCellMaze(){
		return cellMaze;
	}
	
	public Cell[][] getSolution(){
		return cellSolution;
	}
	
	public int getWidth(){
		return COLS;
	}
	
	public int getHeight(){
		return ROWS;
	}
	
	public boolean isComplex(){
		return complex;
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
