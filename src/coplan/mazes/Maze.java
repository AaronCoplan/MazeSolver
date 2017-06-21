/*
MIT License

Copyright (c) 2017 Aaron Coplan

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

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
		
		int binaryRow = 1;
		int binaryCol = 1;
		
		for(int cellRow = 0; cellRow < cellMaze.length; cellRow++){
			for(int cellCol = 0; cellCol < cellMaze[0].length; cellCol++){
				Cell c = cellMaze[cellRow][cellCol];
				
				binaryMaze[binaryRow][binaryCol] = true;
				
				if(c.isRightOpen()){
					binaryMaze[binaryRow][binaryCol + 1] = true;
				}
				
				if(c.isBottomOpen()){
					binaryMaze[binaryRow + 1][binaryCol] = true;
				}
				
				binaryCol += 2;
			}
			binaryCol = 1;
			binaryRow += 2;
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
