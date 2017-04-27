package coplan.mazes;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by afcoplan on 4/27/17.
 */
public class MoveAPIImpl implements MoveAPI {

    private Maze maze;
    private int currentRow;
    private int currentCol;
    private Graphics g;
    private long waitTime;

    public MoveAPIImpl(Maze maze, int startRow, int startCol, Graphics g, long waitTime){
        this.g = g;
        this.maze = maze;
        this.currentCol = startCol;
        this.currentRow = startRow;
        this.waitTime = waitTime;
    }

    @Override
    public boolean isAtStart() {
        return maze.getCellMaze()[currentRow][currentCol].isStart();
    }

    @Override
    public boolean isAtFinish() {
        return maze.getCellMaze()[currentRow][currentCol].isEnd();
    }

    @Override
    public boolean canMoveLeft() {
        return maze.getCellMaze()[currentRow][currentCol].isLeftOpen();
    }

    @Override
    public boolean canMoveRight() {
        return maze.getCellMaze()[currentRow][currentCol].isRightOpen();
    }

    @Override
    public boolean canMoveUp() {
        return maze.getCellMaze()[currentRow][currentCol].isTopOpen();
    }

    @Override
    public boolean canMoveDown() {
        return maze.getCellMaze()[currentRow][currentCol].isBottomOpen();
    }

    @Override
    public boolean moveLeft() {
        System.out.print("Attempting to move left: ");
        if(canMoveLeft()){
            maze.getCellMaze()[currentRow][currentCol].setAsUserBlockCell(false);
            maze.getCellMaze()[currentRow][currentCol-1].setAsUserBlockCell(true);
            --currentCol;
            drawMaze(Display.generateMazeImage(maze));
            try{
                Thread.sleep(waitTime);
            }catch (InterruptedException e){}
            System.out.println("success");
            return true;
        }else{
            System.out.println("failure");
            return false;
        }
    }

    @Override
    public boolean moveRight() {
        System.out.print("Attempting to move right: ");
        if(canMoveRight()){
            maze.getCellMaze()[currentRow][currentCol].setAsUserBlockCell(false);
            maze.getCellMaze()[currentRow][currentCol+1].setAsUserBlockCell(true);
            ++currentCol;
            drawMaze(Display.generateMazeImage(maze));
            try{
                Thread.sleep(waitTime);
            }catch (InterruptedException e){}
            System.out.println("success");
            return true;
        }else{
            System.out.println("failure");
            return false;
        }
    }

    @Override
    public boolean moveUp() {
        System.out.print("Attempting to move up: ");
        if(canMoveUp()){
            maze.getCellMaze()[currentRow][currentCol].setAsUserBlockCell(false);
            maze.getCellMaze()[currentRow-1][currentCol].setAsUserBlockCell(true);
            --currentRow;
            drawMaze(Display.generateMazeImage(maze));
            try{
                Thread.sleep(waitTime);
            }catch (InterruptedException e){}
            System.out.println("success");
            return true;
        }else{
            System.out.println("failure");
            return false;
        }
    }

    @Override
    public boolean moveDown() {
        System.out.print("Attempting to move down: ");
        if(canMoveDown()){
            maze.getCellMaze()[currentRow][currentCol].setAsUserBlockCell(false);
            maze.getCellMaze()[currentRow+1][currentCol].setAsUserBlockCell(true);
            ++currentRow;
            drawMaze(Display.generateMazeImage(maze));
            try{
                Thread.sleep(waitTime);
            }catch (InterruptedException e){}
            System.out.println("success");
            return true;
        }else{
            System.out.println("failure");
            return false;
        }
    }

    private void drawMaze(BufferedImage mazeImage){
        g.drawImage(mazeImage, 5, 5, mazeImage.getWidth(), mazeImage.getHeight(), null);
    }
}
