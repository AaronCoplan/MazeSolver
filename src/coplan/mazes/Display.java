package coplan.mazes;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class Display {

	private JFrame frame;
	private JPanel mazePanel;
	
	public Display(){
		setUp();
	}
	
	private void setUp(){
		this.frame = new JFrame();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.mazePanel = new JPanel();
		
		//width and height of 860 leaves padding of 5 on all sides of the maze
		//true display of maze will be 850x850
		mazePanel.setPreferredSize(new Dimension(860, 860));
		
		frame.getContentPane().add(mazePanel);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public void drawMaze(Maze maze){
		Graphics g = obtainGraphics();
		
		int drawSize;
		if(maze.getWidth() >= maze.getHeight()){
			drawSize = 850 / maze.getWidth();
		}else{
			drawSize = 850 / maze.getHeight();
		}
		
		int[][] mazeBinary = maze.getBinary();

		while(true){
			for(int row = 0; row < maze.getHeight(); row++){
				for(int col = 0; col < maze.getWidth(); col++){
					if(mazeBinary[row][col] == 1){
						g.setColor(Color.BLACK); //wall
					}else if(mazeBinary[row][col] == 0){
						g.setColor(Color.WHITE); //opening
					}else if(mazeBinary[row][col] == 3){
						g.setColor(Color.CYAN); //start
					}else if(mazeBinary[row][col] == 4){
						g.setColor(Color.MAGENTA); //finish
					}else if(mazeBinary[row][col] == 2){
						g.setColor(Color.GREEN); //solution
					}

					g.fillRect((col*drawSize) + 5, (row*drawSize) + 5, drawSize, drawSize);
				}
			}
		}
	}
	
	public void drawSolution(Maze maze){
		Graphics g = obtainGraphics();
		
		int drawSize;
		if(maze.getWidth() >= maze.getHeight()){
			drawSize = 850 / maze.getWidth();
		}else{
			drawSize = 850 / maze.getHeight();
		}
		
		int[][] solutionBinary = maze.getSolution();

		while(true){
			for(int row = 0; row < maze.getHeight(); row++){
				for(int col = 0; col < maze.getWidth(); col++){
					if(solutionBinary[row][col] == 1){
						g.setColor(Color.BLACK); //wall
					}else if(solutionBinary[row][col] == 0){
						g.setColor(Color.WHITE); //opening
					}else if(solutionBinary[row][col] == 3){
						g.setColor(Color.CYAN); //start
					}else if(solutionBinary[row][col] == 4){
						g.setColor(Color.MAGENTA); //finish
					}else if(solutionBinary[row][col] == 2){
						g.setColor(Color.GREEN); //solution
					}

					g.fillRect((col*drawSize) + 5, (row*drawSize) + 5, drawSize, drawSize);
				}
			}
		}
	}

	private Graphics obtainGraphics(){
		Graphics g = null;
		while((g = mazePanel.getGraphics()) == null){
			try{
				Thread.sleep(5);
			}catch(InterruptedException e){}
		}
		
		return g;
	}

	public BufferedImage getImage(){
		BufferedImage bi = new BufferedImage(this.mazePanel.getWidth(), this.mazePanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
		this.mazePanel.paint(bi.getGraphics());
		
		return bi;
	}
}
