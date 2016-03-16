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
		
		Cell[][] cellMaze = maze.getCellMaze();
		
		final int LINE_SIZE = drawSize / 4;

		System.out.println("Draw Size: " + drawSize);
		
		int numPaints = 0;
		while(numPaints < 5){
			for(int row = 0; row < maze.getHeight(); row++){
				for(int col = 0; col < maze.getWidth(); col++){
					
					int x = (col*drawSize) + 5;
					int y = (row*drawSize) + 5;
					
					g.setColor(Color.WHITE);
					g.fillRect(x, y, drawSize, drawSize);

					if(cellMaze[row][col].isBottomOpen() == false){
						g.setColor(Color.BLACK);
						g.fillRect(x, y+drawSize-LINE_SIZE, drawSize, LINE_SIZE);
					}

					if(cellMaze[row][col].isLeftOpen() == false){
						g.setColor(Color.BLACK);
						g.fillRect(x, y, LINE_SIZE, drawSize);
					}

					if(cellMaze[row][col].isRightOpen() == false){
						g.setColor(Color.BLACK);
						g.fillRect(x+drawSize-LINE_SIZE, y, LINE_SIZE, drawSize);
					}

					if(cellMaze[row][col].isTopOpen() == false){
						g.setColor(Color.BLACK);
						g.fillRect(x, y, drawSize, LINE_SIZE);
					}
					
				}
			}
			
			numPaints++;
			
			try{
				Thread.sleep(10);
			}catch(InterruptedException e){}
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
		
		Cell[][] solutionMaze = maze.getSolution();
		
		final int LINE_SIZE = drawSize / 4;

		System.out.println("Draw Size: " + drawSize);
		
		int numPaints = 0;
		while(numPaints < 5){
			for(int row = 0; row < maze.getHeight(); row++){
				for(int col = 0; col < maze.getWidth(); col++){
					
					int x = (col*drawSize) + 5;
					int y = (row*drawSize) + 5;
					
					if(solutionMaze[row][col].isSolutionCell()){
						g.setColor(Color.GREEN);
					}else{
						g.setColor(Color.WHITE);
					}
					
					g.fillRect(x, y, drawSize, drawSize);

					if(solutionMaze[row][col].isBottomOpen() == false){
						g.setColor(Color.BLACK);
						g.fillRect(x, y+drawSize-LINE_SIZE, drawSize, LINE_SIZE);
					}

					if(solutionMaze[row][col].isLeftOpen() == false){
						g.setColor(Color.BLACK);
						g.fillRect(x, y, LINE_SIZE, drawSize);
					}

					if(solutionMaze[row][col].isRightOpen() == false){
						g.setColor(Color.BLACK);
						g.fillRect(x+drawSize-LINE_SIZE, y, LINE_SIZE, drawSize);
					}

					if(solutionMaze[row][col].isTopOpen() == false){
						g.setColor(Color.BLACK);
						g.fillRect(x, y, drawSize, LINE_SIZE);
					}
					
				}
			}
			
			numPaints++;
			
			try{
				Thread.sleep(10);
			}catch(InterruptedException e){}
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
