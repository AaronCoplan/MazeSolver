package coplan.mazes;

import java.util.Arrays;

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
		
		solvedMaze = copy(cellMaze);

		return solvedMaze;
	}
	
	private Cell[][] complexSolution() {
		
		//needs written with cell implementation in mind
		
		solvedMaze = copy(cellMaze);

		return solvedMaze;
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
	
	public Cell[][] getSolution(){
		return this.solvedMaze;
	}
}
