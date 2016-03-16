package coplan.mazes;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class Display {

	private JFrame frame;
	private JPanel contentPanel, mazePanel, uiPanel;
	
	public Display(){
		setUp();
	}
	
	private void setUp(){
		this.frame = new JFrame();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		
		this.uiPanel = new JPanel();
		uiPanel.setPreferredSize(new Dimension(150,860));
		uiPanel.setLayout(new BorderLayout());
		
		JPanel topContent = new JPanel();
		topContent.setPreferredSize(new Dimension(150,250));
		topContent.setLayout(new BorderLayout());
		
		JLabel widthLabel = new JLabel("Width:", JLabel.CENTER);
		widthLabel.setPreferredSize(new Dimension(150, 150));
		JTextField widthValue = new JTextField();
		
		topContent.add(widthLabel, BorderLayout.NORTH);
		topContent.add(widthValue, BorderLayout.CENTER);
		
		JPanel centerContent = new JPanel();
		centerContent.setPreferredSize(new Dimension(150,250));
		centerContent.setLayout(new BorderLayout());
		
		JLabel heightLabel = new JLabel("Height:", JLabel.CENTER);
		heightLabel.setPreferredSize(new Dimension(150, 150));
		JTextField heightValue = new JTextField();
		
		centerContent.add(heightLabel, BorderLayout.NORTH);
		centerContent.add(heightValue, BorderLayout.CENTER);
		
		JPanel bottomContent = new JPanel();
		bottomContent.setPreferredSize(new Dimension(150,360));
		bottomContent.setLayout(new BorderLayout());
		
		JLabel blankLabel = new JLabel(" ");
		JButton generateButton = new JButton("Generate Maze");
		JButton showSolutionButton = new JButton("Show Solution");
		showSolutionButton.setPreferredSize(new Dimension(150,100));
		
		bottomContent.add(blankLabel, BorderLayout.NORTH);
		bottomContent.add(generateButton, BorderLayout.CENTER);
		bottomContent.add(showSolutionButton, BorderLayout.SOUTH);
		
		uiPanel.add(topContent, BorderLayout.NORTH);
		uiPanel.add(centerContent, BorderLayout.CENTER);
		uiPanel.add(bottomContent, BorderLayout.SOUTH);
		
		//delete this later
		uiPanel.setOpaque(true);
		uiPanel.setBackground(Color.CYAN);
		
		contentPanel.add(uiPanel, BorderLayout.EAST);
		
		
		this.mazePanel = new JPanel();
		//width and height of 860 leaves padding of 5 on all sides of the maze
		//true display of maze will be 850x850
		mazePanel.setPreferredSize(new Dimension(860, 860));
		contentPanel.add(mazePanel, BorderLayout.WEST);
		
		frame.getContentPane().add(contentPanel);
		
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
