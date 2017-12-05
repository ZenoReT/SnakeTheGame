package tests;
import static org.junit.Assert.*;

import org.junit.Test;

import fieldObjects.AcceleratorBonus;
import fieldObjects.Apple;
import fieldObjects.FieldObject;
import fieldObjects.ResetAcceleratorBonus;
import fieldObjects.Shell;
import fieldObjects.SnakeHead;
import fieldObjects.SnakePart;
import fieldObjects.Wall;
import game.Field;
import game.Game;
import levels.Level;
import levels.TestLevel;
import utils.Point;

public class Tests {

	@Test
	public void testCorrectSnakeHeadMove() {
		Field field = new Field(3, 3);
		field.getObjects().add(new SnakeHead(2, 2, new Point(0, -1)));
		field.initilizeField();
		Level level = new TestLevel();
		level.setField(field);
		Game game = new Game(level);
		
		game.tick();
		
		assertEquals(field.getField()[2][1].getClass(), SnakeHead.class);
	}
	
	@Test
	public void testCorrectEatsApple() {
		Field field = new Field(3, 3);
		field.getObjects().add(new SnakeHead(2, 2, new Point(0, -1)));
		field.getObjects().add(new Apple(2, 1));
		field.initilizeField();
		Level level = new TestLevel();
		level.setField(field);
		Game game = new Game(level);
		
		game.tick();
		
		assertTrue(!field.getObjects().contains(new Apple(2, 1)));
	}
	
	@Test
	public void testIncreaseAfterEatsApple() {
		Field field = new Field(3, 3);
		field.getObjects().add(new SnakeHead(2, 2, new Point(0, -1)));
		field.getObjects().add(new Apple(2, 1));
		field.getObjects().add(new Apple(2, 0));
		field.initilizeField();
		Level level = new TestLevel();
		level.setField(field);
		Game game = new Game(level);
		
		game.tick();
		game.tick();
		int actualLength = 2;
		int expected = 3;
		SnakeHead snakeHead = game.findSnakeHead();
		SnakePart currentPart = snakeHead.getPreviousPart();
		while(currentPart.getPreviousPart() != null) {
			currentPart = currentPart.getPreviousPart();
			actualLength++;
		}
		
		assertEquals(expected, actualLength);
	}
	
	@Test
	public void testCorrectMoveSnakeWithBody() {
		Field field = new Field(3, 3);
		SnakeHead snakeHead = new SnakeHead(2, 1, new Point(0, -1));
		SnakePart snakePart = new SnakePart(2, 2);
		snakeHead.setPreviousPart(snakePart);
		field.getObjects().add(snakeHead);
		field.getObjects().add(snakePart);
		field.initilizeField();
		Level level = new TestLevel();
		level.setField(field);
		Game game = new Game(level);
		game.tick();
		
		assertTrue(field.getField()[2][0].getClass() == SnakeHead.class);
		assertTrue(field.getField()[2][1].getClass() == SnakePart.class);
	}

	@Test
	public void testCorrectDeadInWall() {
		Field field = new Field(3, 3);
		field.getObjects().add(new SnakeHead(2, 2, new Point(0, -1)));
		field.getObjects().add(new Wall(2, 1));
		field.initilizeField();
		Level level = new TestLevel();
		level.setField(field);
		Game game = new Game(level);
		
		game.tick();
		
		assertTrue(game.gameOver);
	}
	
	@Test
	public void testCorrectDeadInSnakePart() {
		Field field = new Field(3, 3);
		field.getObjects().add(new SnakeHead(2, 2, new Point(0, -1)));
		SnakePart firstPart = new SnakePart(2, 1);
		SnakePart secondPart = new SnakePart(2, 0);
		firstPart.setPreviousPart(secondPart);
		field.getObjects().add(firstPart);
		field.getObjects().add(secondPart);
		field.initilizeField();
		Level level = new TestLevel();
		level.setField(field);
		Game game = new Game(level);
		
		game.tick();
		
		assertTrue(game.gameOver);
	}
	
	@Test
	public void testCorrectDeadInSnakeHead() {
		Field field = new Field(3, 3);
		field.getObjects().add(new SnakeHead(2, 2, new Point(0, -1)));
		field.getObjects().add(new SnakeHead(2, 1, new Point(0, 0)));
		field.initilizeField();
		Level level = new TestLevel();
		level.setField(field);
		Game game = new Game(level);
		
		game.tick();
		
		assertTrue(game.gameOver);
	}
	
	@Test
	public void testCorrectMoveToTail() {
		Field field = new Field(3, 3);
		SnakeHead snakeHead = new SnakeHead(2, 2, new Point(0, -1));
		SnakePart firstPart = new SnakePart(1, 2);
		SnakePart secondPart = new SnakePart(1, 1);
		SnakePart thirdPart = new SnakePart(2, 1);
		snakeHead.setPreviousPart(firstPart);
		firstPart.setPreviousPart(secondPart);
		secondPart.setPreviousPart(thirdPart);
		field.getObjects().add(snakeHead);
		field.getObjects().add(firstPart);
		field.getObjects().add(secondPart);
		field.getObjects().add(thirdPart);
		field.initilizeField();
		Level level = new TestLevel();
		level.setField(field);
		Game game = new Game(level);
		
		game.tick();
		
		assertTrue(!game.gameOver);
	}
	
	@Test
	public void testCorrectIncreaseSpeedAfterBonus() {
		Field field = new Field(3, 3);
		field.getObjects().add(new SnakeHead(2, 2, new Point(0, -1)));
		FieldObject acceleratorBonus = (FieldObject)new AcceleratorBonus(2, 1, 5, 50);
		field.getObjects().add(acceleratorBonus);
		field.initilizeField();
		Level level = new TestLevel();
		level.setField(field);
		Game game = new Game(level);
		int basicSpeed = 500;
		game.tick();
		
		assertEquals(basicSpeed - 50, game.getSpeed());
		assertTrue(!field.getObjects().contains(acceleratorBonus));
	}
	
	@Test
	public void testCorrectSetDefaultSpeedAfterBonus() {
		Field field = new Field(3, 3);
		field.getObjects().add(new SnakeHead(2, 2, new Point(0, -1)));
		FieldObject resetBonus = (FieldObject) new ResetAcceleratorBonus(2, 1, 5, 500);
		field.getObjects().add(resetBonus);
		Level level = new TestLevel();
		level.setField(field);
		field.initilizeField();
		Game game = new Game(level);
		game.setSpeed(450);
		int basicSpeed = 500;
		game.tick();
		
		assertEquals(basicSpeed, game.getSpeed());
		assertTrue(!field.getObjects().contains(resetBonus));
	}
	
	@Test
	public void testBonusesWillClearAfterLifeTime() {
		Field field = new Field(15, 15);
		field.getObjects().add(new SnakeHead(1, 14, new Point(0, -1)));
		FieldObject resetBonus = (FieldObject)new ResetAcceleratorBonus(2, 1, 10, 0);
		FieldObject acceleratorBonus = (FieldObject)new AcceleratorBonus(2, 2, 10, 0);
		field.getObjects().add(resetBonus);
		field.getObjects().add(acceleratorBonus);
		field.initilizeField();
		Level level = new TestLevel();
		level.setField(field);
		Game game = new Game(level);
		for (int i = 0; i < 11; i++) {
			game.tick();
		}
		
		assertTrue(!field.getObjects().contains(resetBonus));
		assertTrue(!field.getObjects().contains(acceleratorBonus));
	}
	
	@Test
	public void testShellKillSnakeWhenHitTheHead() {
		Field field = new Field(15, 15);
		field.getObjects().add(new SnakeHead(1, 14, new Point(0, -1)));
		field.getObjects().add(new Shell(1, 13, new Point(0, 1)));
		field.initilizeField();
		Level level = new TestLevel();
		level.setField(field);
		Game game = new Game(level);
		game.tick();
		
		assertTrue(game.gameOver);
	}

	@Test
	public void testShellNotKillSnakeWhenSheTurn() {
		Field field = new Field(15, 15);
		field.getObjects().add(new SnakeHead(1, 14, new Point(1, 0)));
		field.getObjects().add(new Shell(1, 13, new Point(0, 1)));
		field.initilizeField();
		Level level = new TestLevel();
		level.setField(field);
		Game game = new Game(level);
		game.tick();
		
		assertTrue(!game.gameOver);
	}
}
