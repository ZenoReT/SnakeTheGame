package game;

import fieldObjects.Apple;
import fieldObjects.SnakeHead;
import fieldObjects.SnakePart;
import fieldObjects.Wall;
import utils.Point;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Level{
    public static Field loadFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        List<String> lines = new ArrayList<String>();
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        List<SnakePart> snakeParts = new ArrayList<SnakePart>();
        SnakeHead snakeHead = null;
        Field field = new Field(lines.get(0).length(), lines.size());
        for (int i = 0; i < field.getWidth(); i++) {
            for (int j = 0; j < field.getHeigth(); j++) {
                if (lines.get(j).charAt(i) == '#') {
                    field.getObjects().add(new Wall(i, j));
                } else if (lines.get(j).charAt(i) == 'S') {
                    SnakePart snakePart = new SnakePart(i, j);
                    snakeParts.add(snakePart);
                    field.getObjects().add(snakePart);
                } else if (lines.get(j).charAt(i) == 'H') {
                    snakeHead = new SnakeHead(i, j, new Point(1, 0));
                    field.getObjects().add(snakeHead);
                } else if (lines.get(j).charAt(i) == 'A') {
                    field.getObjects().add(new Apple(i, j));
                }
            }
        }
        snakeHead.setPreviousPart(snakeParts.get(1));
        snakeParts.get(1).setPreviousPart(snakeParts.get(0));
        field.initilizeField();
        return field;
    }

    public static Field initilize(String level) throws IOException {
        return Level.loadFile(String.format("src\\game\\levels\\%s.txt", level));
    };
}
