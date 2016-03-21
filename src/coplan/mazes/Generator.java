package coplan.mazes;
 
import java.util.Collections;
import java.util.Arrays;
 
/*
 * For information on the generation algorithm, see here:
 * https://en.wikipedia.org/wiki/Maze_generation_algorithm#Depth-first_search 
 */

public class Generator {
	
	/*
	 * In the binary maze:
	 * 0 - opening
	 * 1 - wall
	 * 2 - solution path
	 * 3 - start
	 * 4 - end
	 */
	
	//these three variables are used for creation of the generationMaze
	private int x;
	private int y;
	private int[][] generationMaze;
	
	//these variables are used for the transition to a maze of Zeroes and Ones (a "binary" maze)
	private int COLUMNS; //same as width
	private int ROWS; //same as height
	private int[][] binaryMaze;
	private Cell[][] cellMaze;
	private boolean complex;
 
	public Generator(int x, int y, boolean complex) {
		this.x = x;
		this.y = y;
		this.ROWS = (y * 2) + 1;
		this.COLUMNS = (x * 2) + 1;
		generationMaze = new int[this.x][this.y];
		this.complex = complex;
		
		ActionTimer generationTimer = new ActionTimer();
		generationTimer.start();
		generateMaze(0, 0);
		generationTimer.stop();
		System.out.println("Time to generate maze: " + generationTimer.getTimeElapsed());
		
		ActionTimer translation1Timer = new ActionTimer();
		translation1Timer.start();
		this.binaryMaze = translateToBinary();
		translation1Timer.stop();
		System.out.println("Time to translate to binary: " + translation1Timer.getTimeElapsed());
		
		//if the complex variable is true, make it complex
		if(complex == true){
			ActionTimer complexTimer = new ActionTimer();
			complexTimer.start();
		//	makeComplex();
			complexTimer.stop();
			System.out.println("Time to make maze complex: " + complexTimer.getTimeElapsed());
		}
		
		ActionTimer translation2Timer = new ActionTimer();
		translation2Timer.start();
		this.cellMaze = translateToCells();
		translation2Timer.stop();
		System.out.println("Time to translate to cells: " + translation2Timer.getTimeElapsed());
		
		//if the complex variable is true, make it complex
		if(complex == true){
			ActionTimer complexTimer = new ActionTimer();
			complexTimer.start();
			makeCellComplex();
			complexTimer.stop();
			System.out.println("Time to make maze complex: " + complexTimer.getTimeElapsed());
		}
		
		ActionTimer.sumTimes(generationTimer.getTimeElapsed(), translation1Timer.getTimeElapsed(), translation2Timer.getTimeElapsed(), "for generation");
		
		
	}
	
	public Maze getMaze(){
		return new Maze(y, x, cellMaze, complex);
	}
	
	private Cell[][] translateToCells(){
		Cell[][] maze = new Cell[this.y][this.x];
	
		//translate here
		int cellRow = 0;
		int cellCol = 0;
		
		for(int row = 1; row <= ROWS-2; row += 2){
			for(int col = 1; col <= COLUMNS-2; col += 2){
				//check its adjacent borders for openings or walls
				
				int topOpening = binaryMaze[row-1][col];
				int bottomOpening = binaryMaze[row+1][col];
				int leftOpening = binaryMaze[row][col-1];
				int rightOpening = binaryMaze[row][col+1];
				
				maze[cellRow][cellCol] = new Cell(topOpening, rightOpening, bottomOpening, leftOpening);
				
				cellCol++;
			}
			cellCol = 0;
			cellRow++;
		}
		
		maze[0][0].setAsStart();
		maze[this.y-1][this.x-1].setAsEnd();
		
		return maze;
	}
	
	private int[][] translateToBinary(){
		
		StringBuffer mazeVisual = new StringBuffer();
		
		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				mazeVisual.append((generationMaze[j][i] & 1) == 0 ? "+-" : "+ ");
			}
			mazeVisual.append("+\n");
			for (int j = 0; j < x; j++) {
				mazeVisual.append((generationMaze[j][i] & 8) == 0 ? "| " : "  ");
			}
			mazeVisual.append("|\n");
		}
		for (int j = 0; j < x; j++) {
			mazeVisual.append("+-");
		}
		mazeVisual.append("+\n");
		
		StringBuffer binaryMazeString = new StringBuffer();
		String[] lines = mazeVisual.toString().split("\n");
		
		for(int x = 0; x < lines.length; x++){
			for(int i = 0; i < lines[x].length(); i++){
				char c = lines[x].charAt(i);
				switch(c)
				{
				case '+': binaryMazeString.append("1"); break;
				case '-': binaryMazeString.append("1"); break;
				case '|': binaryMazeString.append("1"); break;
				case ' ': binaryMazeString.append("0"); break;
				}
			}
			binaryMazeString.append("\n");
		}
		
		int[][] binaryMaze = new int[ROWS][COLUMNS]; 
				
		String[] binaryLines = binaryMazeString.toString().split("\n");
		for(int row = 0; row < ROWS; row++){
			for(int col = 0; col < COLUMNS; col++){
				switch(binaryLines[row].charAt(col))
				{
				case '1': binaryMaze[row][col] = 1; break;
				case '0': binaryMaze[row][col] = 0; break;
				}
			}
		}
		
		binaryMaze[1][0] = 3;
		binaryMaze[ROWS-2][COLUMNS-1] = 4;
		
		return binaryMaze;
	}
 
	private void makeComplex()
	{
		int area = ROWS*COLUMNS;
		int numRemovals = area/20;
		
		while(numRemovals > 0)
		{			
			int randRow = 1 + (int)(Math.random()*(ROWS-2));
			int randCol = 1 + (int)(Math.random()*(COLUMNS-2));
			
			if(binaryMaze[randRow][randCol] == 1)
			{
				int numAdjWalls = 0;
				if(binaryMaze[randRow][randCol + 1] == 1) { numAdjWalls++; }
				if(binaryMaze[randRow][randCol - 1] == 1) { numAdjWalls++; }
				if(binaryMaze[randRow + 1][randCol] == 1) { numAdjWalls++; }
				if(binaryMaze[randRow - 1][randCol] == 1) { numAdjWalls++; }

				if(numAdjWalls > 1)
				{
					binaryMaze[randRow][randCol] = 0;
					numRemovals--;
				}
			}
		}
	}
	
	private void makeCellComplex()
	{
		int numRows = cellMaze[0].length;
		int numCols = cellMaze.length;
		
		int area = numRows*numCols;
		int numRemovals = area/20;
		
		while(numRemovals > 0)
		{			
			int randRow = (int)(Math.random()*(numRows));
			int randCol = (int)(Math.random()*(numCols));	
			
			if(cellMaze[randRow][randCol].getNumOpening() > 2)
			{
				boolean wallWasRemoved = false;
				do{
					int randInt = (int)(Math.random()*4);
					if(randInt == 0)
					{
						if(!cellMaze[randRow][randCol].isLeftOpen())
						{
							cellMaze[randRow][randCol].setLeftOpening(true);
							cellMaze[randRow-1][randCol].setRightOpening(true);
							wallWasRemoved = true;
						}
					}
					else if(randInt == 1)
					{
						{
							cellMaze[randRow][randCol].setRightOpening(true);
							cellMaze[randRow+1][randCol].setLeftOpening(true);
							wallWasRemoved = true;
						}
					}
					else if(randInt == 2)
					{
						{
							cellMaze[randRow][randCol].setTopOpening(true);
							cellMaze[randRow][randCol+1].setBottomOpening(true);
							wallWasRemoved = true;
						}
					}
					else if (randInt == 3)
					{
						{
							cellMaze[randRow][randCol].setBottomOpening(true);
							cellMaze[randRow][randCol-1].setTopOpening(true);
							wallWasRemoved = true;
						}
					}
				}while(!wallWasRemoved);
			}
			numRemovals--;
		}
	}
	
	private void generateMaze(int cx, int cy){
		DIR[] dirs = DIR.values();
		Collections.shuffle(Arrays.asList(dirs));
		for (DIR dir : dirs) {
			int nx = cx + dir.dx;
			int ny = cy + dir.dy;
			if (between(nx, x) && between(ny, y)
					&& (generationMaze[nx][ny] == 0)) {
				generationMaze[cx][cy] |= dir.bit;
				generationMaze[nx][ny] |= dir.opposite.bit;
				generateMaze(nx, ny);
			}
		}
	}
 
	private static boolean between(int v, int upper){
		return (v >= 0) && (v < upper);
	}
 
	private enum DIR {
		N(1, 0, -1), S(2, 0, 1), E(4, 1, 0), W(8, -1, 0);
		private final int bit;
		private final int dx;
		private final int dy;
		private DIR opposite;
 
		// use the static initializer to resolve forward references
		static {
			N.opposite = S;
			S.opposite = N;
			E.opposite = W;
			W.opposite = E;
		}
 
		private DIR(int bit, int dx, int dy){
			this.bit = bit;
			this.dx = dx;
			this.dy = dy;
		}
	};
}