package coplan.mazes;

import java.util.Scanner;

public class Driver {

	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter x, the width of the maze:");
		String xString = sc.nextLine();
		System.out.println("Enter y, the height of the maze:");
		String yString = sc.nextLine();
		
		sc.close();
		
		int x = Integer.parseInt(xString);
		int y = Integer.parseInt(yString);

		
		Maze maze = new Generator(x, y).getMaze();
		
		
		Display d = new Display();
		
		d.drawMaze(maze);
		//d.drawSolution(maze);		

	}
}
