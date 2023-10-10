import java.math.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Robot robot = new Robot(0,0, Direction.DOWN);
        moveRobot(robot, -10, 20);
    }

    public static void moveRobot(Robot robot, int toX, int toY) {

        System.out.println("Robot look: " + robot.getDirection());
        System.out.printf("Postistion: (%d , %d)\n",robot.getX(), robot.getY());
        System.out.println(toX);

        //x
        if(robot.getX() > toX){
            while (robot.getDirection() != Direction.LEFT){
                robot.turnLeft();
            }
            while (robot.getX() != toX){
                robot.stepForward();
            }
        }
        else {
            while (robot.getDirection() != Direction.LEFT){
                robot.turnRight();
            }
            while (robot.getX() != toX){
                robot.stepForward();
            }
        }

        //y
        if(robot.getY() > toY){
            while (robot.getDirection() != Direction.DOWN){
                robot.turnRight();
            }
            while (robot.getY() != toY){
                robot.stepForward();
            }
        }
        else {
            while (robot.getDirection() != Direction.UP){
                robot.turnLeft();
            }
            while (robot.getY() != toY){
                robot.stepForward();
            }
        }
        System.out.printf("Postistion: (%d , %d)\n",robot.getX(), robot.getY());

    }
}