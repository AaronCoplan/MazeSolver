package coplan.mazes;

public class Solver {

	private Maze maze;
	private Cell[][] solution;
	
	public Solver(Maze maze){
		this.maze = maze;
		this.solution = this.solveMaze(maze.isComplex());
	}
	
	private Cell[][] solveMaze(boolean complex){
		if(complex == true){
			return simpleSolution();
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
		return this.solution;
	}
}
