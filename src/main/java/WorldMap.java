import java.util.*;
import java.util.stream.Collectors;

public class WorldMap {
    private final MapPosition bottomLeft;
    private final MapPosition topRight;

    private final MapPosition jungleBottomLeft;
    private final MapPosition jungleTopRight;

    private HashMap<MapPosition, IMapElement> obstacles;
    private List<Animal> animals;
    private final List<Integer> chances;

    public WorldMap(MapPosition bottomLeft, MapPosition topRight, MapPosition jungleBottomLeft, MapPosition jungleTopRight) {
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;
        this.jungleBottomLeft = jungleBottomLeft;
        this.jungleTopRight = jungleTopRight;

        obstacles = new HashMap<>();
        animals = new ArrayList<>();
        chances = new ArrayList<>();

        for (int x = bottomLeft.getCordX(); x <= topRight.getCordX(); ++x) {
            if (x < jungleBottomLeft.getCordX() || x > jungleTopRight.getCordX()) {
                chances.add(1);
            } else {
                chances.add(7);
            }
        }

        for (int i = 0; i < 10; i++)
            spawnGrass();
        spawnAdams();
    }

    private void spawnGrass() {
        int newGrassX = Losulosu.getRandom(chances);
        int newGrassY = Losulosu.getRandom(chances);

        obstacles.put(new MapPosition(newGrassX, newGrassY), new Grass(new MapPosition(newGrassX, newGrassY)));
    }

    private void spawnAdams() {
        animals.add(new Animal(new MapPosition(5, 5), Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1)));
        animals.add(new Animal(new MapPosition(7, 5), Arrays.asList(10, 1, 1, 1, 1, 1, 1, 1)));
        animals.add(new Animal(new MapPosition(5, 7), Arrays.asList(1, 10, 1, 1, 1, 1, 1, 1)));
        animals.add(new Animal(new MapPosition(5, 9), Arrays.asList(1, 1, 1, 10, 1, 1, 1, 1)));
        animals.add(new Animal(new MapPosition(9, 5), Arrays.asList(1, 1, 1, 1, 1, 10, 1, 1)));
        animals.add(new Animal(new MapPosition(3, 3), Arrays.asList(1, 1, 5, 1, 1, 7, 1, 1)));

        obstacles.putAll(animals.stream().collect(Collectors.toMap(Animal::getPosition, animal -> animal)));
    }

    public void update() {
        cleanDeadAnimals();

        spawnGrass();
        for (Animal animal : animals) {
            MapPosition targetPosition = newAnimalPosition(animal.getPosition(), animal.getGenes());
            interaction(animal, targetPosition);
            updateAnimalPosition(animal, targetPosition);
        }
    }

    private void interaction(Animal animal, MapPosition targetPosition){
        if(isOccupied(targetPosition)){
            IMapElement targetElement = obstacles.get(targetPosition);
            if(targetElement.getClass().equals(Grass.class)){
                animal.eat();
            }/*else if(targetElement.getClass().equals(Animal.class)){
                animal.;
            }*/
        }
    }

    private void updateAnimalPosition(Animal animal, MapPosition targetPosition){
        obstacles.remove(animal.getPosition());
        animal.moveTo(targetPosition);
        obstacles.put(animal.getPosition(), animal);
    }

    private void cleanDeadAnimals(){
        Iterator<Animal> iterator = animals.iterator();
        while (iterator.hasNext()){
            Animal temporary = iterator.next();
            if (!temporary.isAlive()){
                obstacles.remove(temporary.getPosition());
                iterator.remove();
            }
        }
    }

    private MapPosition newAnimalPosition(MapPosition currentPosition, List<Integer> directionChances) {
        int[] diffX = {-1, 0, 1, 1, 1, 0, -1, -1};
        int[] diffY = {1, 1, 1, 0, -1, -1, -1, 0};
        MapPosition newPosition;
        int direction;
        do {
            direction = Losulosu.getRandom(directionChances);
            newPosition = currentPosition.addVector(diffX[direction], diffY[direction]);
        } while (!inMap(newPosition));

        return newPosition;
    }

    private boolean inMap(MapPosition position) {
        return (position.largerOrEven(bottomLeft) && position.smallerOrEven(topRight));
    }

    private boolean isOccupied(MapPosition target) {
        return obstacles.containsKey(target);
    }

    @Override
    public String toString() {
        return MapVisualizer.visualize(this);
    }

    public MapPosition getBottomLeft() {
        return bottomLeft;
    }

    public MapPosition getTopRight() {
        return topRight;
    }

    public MapPosition getJungleBottomLeft() {
        return jungleBottomLeft;
    }

    public MapPosition getJungleTopRight() {
        return jungleTopRight;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public HashMap<MapPosition, IMapElement> getObstacles() {
        return obstacles;
    }
}
