package coplan.mazes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Display {

	private JFrame frame;
	private JPanel contentPanel, mazePanel, uiPanel;
	
	private JTextField widthField, heightField;
	private JButton generateMazeButton, showSolutionButton, printButton;
	
	private Maze maze;
	
	private BufferedImage mazeImage, solutionImage;
	
	public Display(){}
	
	public void setUp(){
		this.frame = new JFrame();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		
		createUIPanel();
		
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
	
	private void createUIPanel(){
		this.uiPanel = new JPanel();
		uiPanel.setPreferredSize(new Dimension(150,860));
		uiPanel.setLayout(new BorderLayout());
		
		JPanel topContent = new JPanel();
		topContent.setPreferredSize(new Dimension(150,125));
		topContent.setLayout(new BorderLayout());
		
		final Font labelFont = new Font("Arial", Font.BOLD, 22);
		
		JLabel widthLabel = new JLabel("Width:", JLabel.CENTER);
		widthLabel.setPreferredSize(new Dimension(150, 50));
		widthLabel.setFont(labelFont);
		
		String defaultSize = "25";
		
		this.widthField = new JTextField(defaultSize);
		widthField.setPreferredSize(new Dimension(100, 75));
		widthField.setHorizontalAlignment(JTextField.CENTER);
		widthField.setFont(labelFont);
		
		topContent.add(widthLabel, BorderLayout.NORTH);
		topContent.add(widthField, BorderLayout.CENTER);
		
		JPanel centerContent = new JPanel();
		centerContent.setPreferredSize(new Dimension(150,145));
		centerContent.setLayout(new BorderLayout());
		
		JLabel heightLabel = new JLabel("Height:", JLabel.CENTER);
		heightLabel.setPreferredSize(new Dimension(150, 50));
		heightLabel.setFont(labelFont);
		
		this.heightField = new JTextField(defaultSize);
		heightField.setPreferredSize(new Dimension(100, 75));
		heightField.setHorizontalAlignment(JTextField.CENTER);
		heightField.setFont(labelFont);
		
		JLabel blankLabel1 = new JLabel(" ");
		blankLabel1.setPreferredSize(new Dimension(150,20));
		
		centerContent.add(heightLabel, BorderLayout.NORTH);
		centerContent.add(heightField, BorderLayout.CENTER);
		centerContent.add(blankLabel1, BorderLayout.SOUTH);
		
		JPanel bottomContent = new JPanel();
		bottomContent.setPreferredSize(new Dimension(150,590));
		bottomContent.setLayout(new BorderLayout());
		
		this.printButton = new JButton("Print Maze");
		printButton.setPreferredSize(new Dimension(150, 140));
		
		this.generateMazeButton = new JButton("Generate Maze");
		generateMazeButton.setPreferredSize(new Dimension(150, 225));
		
		this.showSolutionButton = new JButton("Show Solution");
		showSolutionButton.setPreferredSize(new Dimension(150,225));
		
		//add an action listener for the buttons
		ButtonListener listener = new ButtonListener();
		printButton.addActionListener(listener);
		generateMazeButton.addActionListener(listener);
		showSolutionButton.addActionListener(listener);
		
		
		bottomContent.add(printButton, BorderLayout.SOUTH);
		bottomContent.add(generateMazeButton, BorderLayout.NORTH);
		bottomContent.add(showSolutionButton, BorderLayout.CENTER);
		
		uiPanel.add(topContent, BorderLayout.NORTH);
		uiPanel.add(centerContent, BorderLayout.CENTER);
		uiPanel.add(bottomContent, BorderLayout.SOUTH);
	}
	
	public BufferedImage generateMazeImage(Maze maze){
		BufferedImage mazeImage = new BufferedImage(850, 850, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = mazeImage.createGraphics();
		
		g.setColor(null);
		g.fillRect(5, 5, 850, 850);

		int drawSize;
		if(maze.getWidth() >= maze.getHeight()){
			drawSize = 850 / maze.getWidth();
		}else{
			drawSize = 850 / maze.getHeight();
		}

		Cell[][] cellMaze = maze.getCellMaze();

		final int LINE_SIZE = drawSize / 10;

		for(int row = 0; row < maze.getHeight(); row++){
			for(int col = 0; col < maze.getWidth(); col++){

				int x = (col*drawSize);
				int y = (row*drawSize);

				if(cellMaze[row][col].isStart() || cellMaze[row][col].isEnd()){
					g.setColor(Color.PINK);
				}else{
					g.setColor(Color.WHITE);
				}
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

		return mazeImage;
	}
	
	public BufferedImage generateSolutionImage(Maze maze){
		BufferedImage solutionImage = new BufferedImage(850, 850, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = solutionImage.createGraphics();

		int drawSize;
		if(maze.getWidth() >= maze.getHeight()){
			drawSize = 850 / maze.getWidth();
		}else{
			drawSize = 850 / maze.getHeight();
		}

		Cell[][] solutionMaze = maze.getSolution();

		final int LINE_SIZE = drawSize / 10;

		for(int row = 0; row < maze.getHeight(); row++){
			for(int col = 0; col < maze.getWidth(); col++){

				int x = (col*drawSize);
				int y = (row*drawSize);

				if(solutionMaze[row][col].isStart() || solutionMaze[row][col].isEnd()){
					g.setColor(Color.PINK);
				}else if(solutionMaze[row][col].isSolutionCell()){
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
		
		return solutionImage;
	}
	
	private void drawMaze(){
		Graphics g = obtainGraphics();
		g.drawImage(mazeImage, 5, 5, mazeImage.getWidth(), mazeImage.getHeight(), null);
	}
	
	private void drawSolution(){
		Graphics g = obtainGraphics();
		g.drawImage(solutionImage, 5, 5, mazeImage.getWidth(), mazeImage.getHeight(), null);
	}
	
	private Graphics obtainGraphics(){
		Graphics g = null;
		
		while(g == null){
			g = mazePanel.getGraphics();
		}
		
		return g;
	}

	public BufferedImage getMazeImage(){
		return this.mazeImage;
	}
	
	public BufferedImage getSolutionImage(){
		return this.solutionImage;
	}
	
	private class ButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e){
			JButton source = (JButton) e.getSource();
			
			disableComponents();
			
			if(source.equals(generateMazeButton)){
				new Thread(new Runnable(){
					public void run(){
						String width = widthField.getText();
						String height = heightField.getText();

						if(width == null || height == null){
							JOptionPane.showMessageDialog(null, "Error! Either the width or height component is missing!", "ERROR", JOptionPane.ERROR_MESSAGE);
							return;
						}

						int x = Integer.parseInt(width);
						int y = Integer.parseInt(height);
						
						boolean complex;
						int response = JOptionPane.showConfirmDialog(null, "Do you want your maze to be complex?");
						if(response == 0){
							complex = true;
						}else{
							complex = false;
						}

						Maze m = new Generator(x, y, complex).getMaze();
						maze = m;

						mazeImage = generateMazeImage(m);
						drawMaze();
					}}).start();
			}else if(source.equals(showSolutionButton)){
				new Thread(new Runnable(){
					public void run(){
						if(maze != null){
							solutionImage = generateSolutionImage(maze);
							drawSolution();
						}else{
							JOptionPane.showMessageDialog(null, "Error! There is no current maze to show the solution of!", "ERROR", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
				}).start();
			}
			
			reenableComponents();
		}
		
		private void disableComponents(){
			generateMazeButton.setEnabled(false);
			showSolutionButton.setEnabled(false);
			printButton.setEnabled(false);
			widthField.setEditable(false);
			heightField.setEditable(false);
		}
		
		private void reenableComponents(){
			generateMazeButton.setEnabled(true);
			showSolutionButton.setEnabled(true);
			printButton.setEnabled(true);
			widthField.setEditable(true);
			heightField.setEditable(true);
		}
	}
}
