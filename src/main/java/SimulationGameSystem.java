public class SimulationGameSystem {
    public static void main(String args[]) {
        WorldMap map1 = new WorldMap(new MapPosition(0, 0), new MapPosition(30, 30),
                new MapPosition(10, 10), new MapPosition(20, 20));

        System.out.println(map1);

        for (int i = 0; i < 2; i++) {
            map1.update();

            System.out.println(map1);
        }
    }
}
