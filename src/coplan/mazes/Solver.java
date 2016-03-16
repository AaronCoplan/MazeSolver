package coplan.mazes;

import java.util.Arrays;

public class Solver {

	private int[][] binaryMaze;
	private int[][] solvedMaze;
	
	private int ROWS, COLS;
	
	public Solver(Maze maze){
		this.ROWS = maze.getHeight();
		this.COLS = maze.getWidth();
		this.binaryMaze = maze.getBinary();
		
		this.solvedMaze = solveMaze();
	}
	
	private int[][] solveMaze(){
		//if it is a simple maze
		return simpleSolution();
		//else if it is complex
		//return complexSolution();
	}
	
	private int[][] simpleSolution(){
		
		solvedMaze = copy(binaryMaze);
		
		int numChanged;
		
		do{
			numChanged = 0;
			for(int row = 1; row < ROWS - 1; row++){
				for(int col = 1; col < COLS - 1; col++){
					int sum = 0;

					if(solvedMaze[row-1][col] == 1){sum++;}
					if(solvedMaze[row+1][col] == 1){sum++;}
					if(solvedMaze[row][col-1] == 1){sum++;}
					if(solvedMaze[row][col+1] == 1){sum++;}

					if(sum >= 3){
						if(solvedMaze[row][col] != 1){
							solvedMaze[row][col] = 1;
							numChanged++;
						}
					}
				}
			}
		}while(numChanged != 0);
		
		for(int row = 1; row < ROWS - 1; row++){
			for(int col = 1; col < COLS - 1; col++){
				if(solvedMaze[row][col] == 0){
					solvedMaze[row][col] = 2;
				}
			}
		}
		
		for(int row = 1; row < ROWS - 1; row++){
			for(int col = 1; col < COLS - 1; col++){
				if(solvedMaze[row][col] != 2){
					solvedMaze[row][col] = binaryMaze[row][col];
				}
			}
		}

		return solvedMaze;
	}
	
	private int[][] complexSolution() {
		
		solvedMaze = copy(binaryMaze);

		return solvedMaze;
	}
	
	private int[][] copy(int[][] array){
		int[][] copy = new int[array.length][array[0].length];
		
		for(int row = 0; row < array.length; row++){
			for(int col = 0; col < array[0].length; col++){
				copy[row][col] = array[row][col];
			}
		}
		
		return copy;
	}
	
	public int[][] getSolution(){
		return this.solvedMaze;
	}
}
