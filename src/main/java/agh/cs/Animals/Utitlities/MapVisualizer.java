package agh.cs.Animals.Utitlities;

import agh.cs.Animals.MapRepresenation.MapPosition;
import agh.cs.Animals.MapRepresenation.WorldMap;

import java.util.HashMap;

import static org.fusesource.jansi.Ansi.ansi;

public class MapVisualizer {
    public static void drawInTerminal(WorldMap map){
        MapPosition bottomLeft = map.getBottomLeft();
        MapPosition topRight = map.getTopRight();

        HashMap obstacles = map.getObstacles();

        for (int y = topRight.getCordY(); y >= bottomLeft.getCordY(); y--) {
            for (int x = bottomLeft.getCordX(); x <= topRight.getCordX(); x++) {
                MapPosition current = new MapPosition(x, y);
                if (obstacles.containsKey(current)) {
                    System.out.print(ansi().cursor(x + 5,y + 5).a(obstacles.get(current).toString()));
                } else {
                    System.out.print(ansi().cursor(x + 5,y + 5).a(" "));
                }
            }
        }
    }
}
