package fieldObjects;

import java.util.ArrayList;
import java.util.Random;

import game.Field;
import game.Game;
import utils.Point;

import static java.lang.Math.abs;

public class MovingApple implements FieldObject {
    private Point location;
    private int lifeTime = 5;

    public MovingApple(int x, int y, int lifeTime) {
        location = new Point(x, y);
        if (lifeTime > 0){
            this.lifeTime = lifeTime;
        }
    }

    public void setLocation(int x, int y) {
        location = new Point(x, y);
    }

    public Point getLocation() {
        return location;
    }

    public int getLifeTime() {
        return lifeTime;
    }

    public int getBonusChance() {
        return lifeTime;
    }

    private void decreaseLifeTime() {
        lifeTime--;
    }

    public void treatCollisionWithSnake(Game game) {
        Field field = game.getField();
        SnakeHead snakeHead = game.findSnakeHead();
        Object tailCell = game.findSnakeTail(snakeHead);
        Point headLocation = snakeHead.getLocation();
        Object apple = field.getField()[headLocation.x][headLocation.y];
        if (tailCell instanceof SnakeHead) {
            SnakeHead snakeTail = (SnakeHead)tailCell;
            snakeTail.setPreviousPart(new SnakePart(snakeTail.getLocation().x,
                    snakeTail.getLocation().y));
            field.getObjects().add(snakeTail.getPreviousPart());
        }
        else {
            SnakePart snakeTail = (SnakePart)tailCell;
            snakeTail.setPreviousPart(new SnakePart(snakeTail.getLocation().x,
                    snakeTail.getLocation().y));
            field.getObjects().add(snakeTail.getPreviousPart());
            snakeTail = (SnakePart)snakeTail;
        }
        field.getObjects().remove(apple);
    }

    public Point chooseNextPoint(Game game) {
        Point snakeHeadPoint = game.findSnakeHead().getLocation();
        Random random = new Random();
        int length = abs(snakeHeadPoint.x - location.x) + abs(snakeHeadPoint.y - location.y);
        Point pointResult = new Point(location);
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                int nextLength = abs(snakeHeadPoint.x - location.x - x) + abs(snakeHeadPoint.y - location.y - y);
                Point nextPoint = new Point(x, y).add(location);
                if ((nextLength > length) && (abs(x) + abs(y) != 2) &&
                        (game.getField().getField()[nextPoint.x][nextPoint.y] instanceof EmptyCell)) {
                    pointResult = nextPoint;
                }
            }
        }
        if (pointResult.equals(location) || length > 5) {
            for (int i = 0; i < 10; i++) {
                int moveX = random.nextInt(3) - 1;
                int moveY = random.nextInt(3) - 1;
                if (abs(moveX) + abs(moveY) == 2) {
                    continue;
                }
                Point nextPoint = new Point(moveX, moveY).add(location);
                if (game.getField().getField()[nextPoint.x][nextPoint.y] instanceof EmptyCell) {
                    pointResult = nextPoint;
                    break;
                }
            }
        }
        return pointResult;
    }

    public void tick(Game game) {
        decreaseLifeTime();
        location = this.chooseNextPoint(game);
        if (lifeTime <= 0){
            Field field = game.getField();
            field.getObjects().remove(this);
        }
    }
}
