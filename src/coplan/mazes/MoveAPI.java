package coplan.mazes;

/**
 * Created by afcoplan on 4/27/17.
 */
public interface MoveAPI {

    boolean isAtStart();
    boolean isAtFinish();

    boolean canMoveLeft();
    boolean canMoveRight();
    boolean canMoveUp();
    boolean canMoveDown();

    boolean moveLeft();
    boolean moveRight();
    boolean moveUp();
    boolean moveDown();
}
