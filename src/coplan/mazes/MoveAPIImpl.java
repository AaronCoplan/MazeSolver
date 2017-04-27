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

    public MoveAPIImpl(Maze maze, int startRow, int startCol, Graphics g){
        this.g = g;
        this.maze = maze;
        this.currentCol = startCol;
        this.currentRow = startRow;
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
        if(canMoveLeft()){
            maze.getCellMaze()[currentRow][currentCol].setAsUserBlockCell(false);
            maze.getCellMaze()[currentRow][currentCol-1].setAsUserBlockCell(true);
            drawMaze(Display.generateMazeImage(maze));
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean moveRight() {
        if(canMoveRight()){
            maze.getCellMaze()[currentRow][currentCol].setAsUserBlockCell(false);
            maze.getCellMaze()[currentRow][currentCol+1].setAsUserBlockCell(true);
            drawMaze(Display.generateMazeImage(maze));
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean moveUp() {
        if(canMoveUp()){
            maze.getCellMaze()[currentRow][currentCol].setAsUserBlockCell(false);
            maze.getCellMaze()[currentRow-1][currentCol].setAsUserBlockCell(true);
            drawMaze(Display.generateMazeImage(maze));
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean moveDown() {
        if(canMoveDown()){
            maze.getCellMaze()[currentRow][currentCol].setAsUserBlockCell(false);
            maze.getCellMaze()[currentRow+1][currentCol].setAsUserBlockCell(true);
            drawMaze(Display.generateMazeImage(maze));
            return true;
        }else{
            return false;
        }
    }

    private void drawMaze(BufferedImage mazeImage){
        g.drawImage(mazeImage, 5, 5, mazeImage.getWidth(), mazeImage.getHeight(), null);
    }
}
