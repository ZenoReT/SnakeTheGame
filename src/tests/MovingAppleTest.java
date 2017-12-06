package tests;

import fieldObjects.MovingApple;
import fieldObjects.SnakeHead;
import fieldObjects.Wall;
import game.Field;
import game.Game;
import levels.Level;
import levels.Level1;
import levels.TestLevel;
import org.junit.Test;
import utils.Point;

import static org.junit.Assert.*;

public class MovingAppleTest {
    @Test
    public void testRunawayInCenter() throws Exception {
        TestLevel testLevel = new TestLevel();
        testLevel.setField(new Field(5, 5));
        testLevel.getField().getObjects().add(new SnakeHead(0, 0, new Point(0, 0)));
        MovingApple movingApple = new MovingApple(1, 1, 0);
        testLevel.getField().getObjects().add(movingApple);
        testLevel.getField().initilizeField();
        Game testGame = new Game(testLevel);
        Point nextPoint = movingApple.chooseNextPoint(testGame);
        assertEquals(nextPoint, new Point(2, 1));
    }

    
    @Test
    public void testRunawayInCorner() throws Exception {
        TestLevel testLevel = new TestLevel();
        testLevel.setField(new Field(5, 5));
        testLevel.getField().getObjects().add(new SnakeHead(2, 2, new Point(0, 0)));
        testLevel.getField().getObjects().add(new Wall(0, 0));
        testLevel.getField().getObjects().add(new Wall(1, 0));
        testLevel.getField().getObjects().add(new Wall(0, 1));
        MovingApple movingApple = new MovingApple(1, 1, 0);
        testLevel.getField().getObjects().add(movingApple);
        testLevel.getField().initilizeField();
        Game testGame = new Game(testLevel);
        Point nextPoint = movingApple.chooseNextPoint(testGame);
        assertEquals(nextPoint, new Point(1, 2));
    }
}