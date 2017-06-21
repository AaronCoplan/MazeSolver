/*
MIT License

Copyright (c) 2017 Aaron Coplan

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

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
