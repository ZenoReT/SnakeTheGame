package game;

import fieldObjects.Apple;
import fieldObjects.FieldObject;
import game.Game;
import levels.Level;
import utils.Point;

import java.util.ArrayList;
import java.util.Random;

public class AppleGenerator {
    public static void addApple(Level level) {
        ArrayList<FieldObject> emptyCells = level.getEmptyCells();
        Random rnd = new Random();
        int id = rnd.nextInt(emptyCells.size());
        Point cellLocation = emptyCells.get(id).getLocation();
        level.getField().getObjects().add(new Apple(cellLocation.x, cellLocation.y));
    }
}
