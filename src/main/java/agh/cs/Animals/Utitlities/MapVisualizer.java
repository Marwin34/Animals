package agh.cs.Animals.Utitlities;

import agh.cs.Animals.MapRepresenation.MapPosition;
import agh.cs.Animals.MapRepresenation.WorldMap;

import java.util.HashMap;

public class MapVisualizer {
    public static String visualize(WorldMap map) {
        MapPosition bottomLeft = map.getBottomLeft();
        MapPosition topRight = map.getTopRight();

        StringBuilder bob = new StringBuilder();

        HashMap obstacles = map.getObstacles();

        for (int y = topRight.getCordY(); y >= bottomLeft.getCordY(); y--) {
            for (int x = bottomLeft.getCordX(); x <= topRight.getCordX(); x++) {
                MapPosition current = new MapPosition(x, y);
                if (obstacles.containsKey(current)) {
                    bob.append(obstacles.get(current));
                } else {
                    bob.append(" ");
                }
            }
            bob.append(System.lineSeparator());
        }
        bob.append(System.lineSeparator());

        return bob.toString();
    }
}
