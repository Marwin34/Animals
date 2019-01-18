import java.util.HashMap;

public class MapVisualizer {
    public static String visualize(WorldMap map) {
        MapPosition bottomLeft = map.getBottomLeft();
        MapPosition topRight = map.getTopRight();
        MapPosition jungleBottomLrft = map.getJungleBottomLeft();
        MapPosition jungleTopRight = map.getJungleTopRight();

        StringBuilder bob = new StringBuilder();

        HashMap obstacles = map.getObstacles();

        for (int y = topRight.getCordY(); y >= bottomLeft.getCordY(); y--) {
            for (int x = bottomLeft.getCordX(); x <= topRight.getCordX(); x++) {
                MapPosition current = new MapPosition(x, y);
                if (obstacles.containsKey(current)) {
                    bob.append(obstacles.get(current));
                } else if (jungleBottomLrft.smallerOrEven(new MapPosition(x, y))
                        && jungleTopRight.largerOrEven(new MapPosition(x, y))) {
                    bob.append("|^");
                } else {
                    bob.append("| ");
                }
            }
            bob.append("|")
                    .append(System.lineSeparator());
        }
        bob.append(System.lineSeparator());

        return bob.toString();
    }
}
