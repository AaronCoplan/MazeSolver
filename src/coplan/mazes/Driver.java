package coplan.mazes;

public class Driver {

	public static void main(String[] args){
		int x = 20, y = 20;
		
		Maze maze = new Generator(x, y).getMaze();
		
		
		Display d = new Display();
		
		//d.drawMaze(maze);
		d.drawSolution(maze);		
		
		//TODO: make mazes into complex mazes by opening up more holes in walls, which may generate loops
		//TODO: solve mazes, both simple and complex
		//TODO: Making this change to demonstrate a commit.
	}
}
