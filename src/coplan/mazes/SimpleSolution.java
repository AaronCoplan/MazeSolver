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

public class SimpleSolution {

	private int ROWS, COLS;
	private Cell[][] solutionMaze;
	private Cell[][] cellMaze;
	
	public SimpleSolution(Maze maze){
		
		this.ROWS = maze.getHeight();
		this.COLS = maze.getWidth();
		
		this.cellMaze = maze.getCellMaze();
		
		//we want to copy it so that we aren't changing the actual maze
		this.solutionMaze = copy(cellMaze);
	}

	public Cell[][] solve(){

		boolean changeIsMade;
		
		do{
			changeIsMade = false;
			
			for(int row = 0; row < ROWS; row++){
				for(int col = 0; col < COLS; col++){
					Cell c = solutionMaze[row][col];
					
					//have to make sure it only has one opening and is not the start or end of the maze
					if(c.getNumOpening() == 1 && !c.isStart() && !c.isEnd()){
						/*
						 * having only 1 opening indicates it is a dead end
						 * 1 opening implies it has 3 walls
						 * thus, find and close off the fourth wall
						 * do the corresponding action for its neighbor
						*/
						
						if(c.isBottomOpen()){
							c.setBottomOpening(false);
							solutionMaze[row+1][col].setTopOpening(false);
						}
						
						if(c.isTopOpen()){
							c.setTopOpening(false);
							solutionMaze[row-1][col].setBottomOpening(false);
						}
						
						if(c.isLeftOpen()){
							c.setLeftOpening(false);
							solutionMaze[row][col-1].setRightOpening(false);
						}
						
						if(c.isRightOpen()){
							c.setRightOpening(false);
							solutionMaze[row][col+1].setLeftOpening(false);
						}
						
						changeIsMade = true;
					}
				}
			}
		}while(changeIsMade);
		
		Cell[][] newSolutionMaze = copy(cellMaze);
		
		for(int row = 0; row < ROWS; row++){
			for(int col = 0; col < COLS; col++){
				if(!solutionMaze[row][col].isBox()){
					newSolutionMaze[row][col].setAsSolutionCell();
				}
			}
		}
		
		

		return newSolutionMaze;
	}

	private Cell[][] copy(Cell[][] array){

		Cell[][] copy = new Cell[array.length][array[0].length];

		for(int row = 0; row < array.length; row++){
			for(int col = 0; col < array[0].length; col++){
				Cell c = array[row][col];
				Cell newCell = new Cell(c.isTopOpen(), c.isRightOpen(), c.isBottomOpen(), c.isLeftOpen());
				
				if(c.isStart()){
					newCell.setAsStart();
				}else if(c.isEnd()){
					newCell.setAsEnd();
				}
				
				copy[row][col] = newCell;
			}
		}

		return copy;
	}
}
