public class SimulationGameSystem {
    public static void main(String args[]) {
        WorldMap map1 = new WorldMap(new MapPosition(0, 0), new MapPosition(30, 30),
                new MapPosition(10, 10), new MapPosition(20, 20));

        int passedDays = 0;

        System.out.println(map1);

        while(map1.getAnimals().size() > 0)
        {
            map1.update();
            passedDays++;
        }

        System.out.println(map1);
        System.out.println(passedDays);
    }
}
