package coplan.mazes;

public class Solver {

	private Maze maze;
	
	public Solver(Maze maze){
		this.maze = maze;
	}
	
	private Cell[][] solveMaze(boolean complex){
		if(complex == true){
			return complexSolution();
		}else{
			return simpleSolution();
		}
	}
	
	private Cell[][] simpleSolution(){
		return new SimpleSolution(maze).solve();
	}
	
	private Cell[][] complexSolution() {
		return new ComplexSolution(maze).solve();
	}
	
	public Cell[][] getSolution(){
		return this.maze.getSolution();
	}
}
