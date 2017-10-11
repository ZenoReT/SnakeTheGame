import static org.junit.Assert.*;

import org.junit.Test;

public class Tests {

	@Test
	public void testCorrectSnakeHeadMove() {
		Field field = new Field(3, 3);
		field.objects.add(new SnakeHead(2, 2));
		field.initilizeField();
		Game game = new Game();
		game.tick(field);
		assertTrue(field.field[2][1].getClass() == SnakeHead.class);
	}
	
	@Test
	public void testCorrectEatApple() {
		Field field = new Field(3, 3);
		field.objects.add(new SnakeHead(2, 2));
		field.objects.add(new Apple(2, 1));
		field.initilizeField();
		Game game = new Game();
		game.tick(field);
		assertTrue(!field.objects.contains(new Apple(2, 1)));
	}
	
	@Test
	public void testCorrectMoveSnakeWithBody() {
		Field field = new Field(3, 3);
		SnakeHead snakeHead = new SnakeHead(2, 1);
		SnakePart snakePart = new SnakePart(2, 2);
		snakeHead.previousPart = snakePart;
		field.objects.add(snakeHead);
		field.objects.add(snakePart);
		field.initilizeField();
		Game game = new Game();
		game.tick(field);
		assertTrue(field.field[2][0].getClass() == SnakeHead.class &&
				   field.field[2][1].getClass() == SnakePart.class);
	}

	@Test
	public void testCorrectDeadInWall() {
		Field field = new Field(3, 3);
		field.objects.add(new SnakeHead(2, 2));
		field.objects.add(new Wall(2, 1));
		field.initilizeField();
		Game game = new Game();
		game.tick(field);
		assertTrue(game.gameOver);
	}
	
	@Test
	public void testCorrectDeadInSnakePart( ) {
		Field field = new Field(3, 3);
		field.objects.add(new SnakeHead(2, 2));
		field.objects.add(new SnakePart(2, 1));
		field.initilizeField();
		Game game = new Game();
		game.tick(field);
		assertTrue(game.gameOver);
	}
	
	@Test
	public void testCorrectDeadInSnakeHead( ) {
		Field field = new Field(3, 3);
		field.objects.add(new SnakeHead(2, 2));
		field.objects.add(new SnakeHead(2, 1));
		field.initilizeField();
		Game game = new Game();
		game.tick(field);
		assertTrue(game.gameOver);
	}

}
