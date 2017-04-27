package coplan.mazes;

/**
 * Created by afcoplan on 4/27/17.
 */
public class UserSolution {

    public static void run(MoveAPI moveAPI){

        /*
         * User: write solution code here
         */

        while(true){
            if(moveAPI.canMoveRight()){
                moveAPI.moveRight();
            }else if(moveAPI.canMoveDown()){
                moveAPI.moveDown();
            }
        }
        
    }
}
