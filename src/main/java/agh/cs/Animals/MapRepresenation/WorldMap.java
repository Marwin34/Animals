package agh.cs.Animals.MapRepresenation;

import agh.cs.Animals.Utitlities.Losulosu;

import java.util.*;
import java.util.stream.Collectors;

public class WorldMap {
    private final MapPosition bottomLeft;
    private final MapPosition topRight;

    private final MapPosition jungleBottomLeft;
    private final MapPosition jungleTopRight;

    private HashMap<MapPosition, IMapElement> obstacles;
    private List<Animal> animals;
    private List<Animal> childrens;
    private final List<Integer> chancesX;
    private final List<Integer> chancesY;
    
    public WorldMap(MapPosition bottomLeft, MapPosition topRight, MapPosition jungleBottomLeft, MapPosition jungleTopRight) {
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;
        this.jungleBottomLeft = jungleBottomLeft;
        this.jungleTopRight = jungleTopRight;

        obstacles = new HashMap<>();
        animals = new ArrayList<>();
        chancesX = new ArrayList<>();
        chancesY = new ArrayList<>();
        childrens = new ArrayList<>();

        for (int x = bottomLeft.getCordX(); x <= topRight.getCordX(); ++x) {
            if (x < jungleBottomLeft.getCordX() || x > jungleTopRight.getCordX()) {
                chancesX.add(1);
            } else {
                chancesX.add(8);
            }
        }

        for (int y = bottomLeft.getCordY(); y <= topRight.getCordY(); ++y) {
            if (y < jungleBottomLeft.getCordY() || y > jungleTopRight.getCordY()) {
                chancesY.add(1);
            } else {
                chancesY.add(8);
            }
        }

        for (int i = 0; i < 10; i++)
            spawnGrass();
        spawnAdams();
    }

    private void spawnGrass() {
        for (int i = 0; i < 3; i++) {
            int newGrassX = Losulosu.getRandom(chancesX);
            int newGrassY = Losulosu.getRandom(chancesY);

            obstacles.putIfAbsent(new MapPosition(newGrassX, newGrassY), new Grass(new MapPosition(newGrassX, newGrassY)));
        }
    }

    private void spawnAdams() {
        animals.add(new Animal(new MapPosition(55, 20), Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1)));
        animals.add(new Animal(new MapPosition(7, 5), Arrays.asList(11, 1, 1, 1, 1, 1, 1, 1)));
        animals.add(new Animal(new MapPosition(35, 15), Arrays.asList(1, 5, 1, 6, 1, 6, 1, 1)));
        animals.add(new Animal(new MapPosition(25, 14), Arrays.asList(1, 3, 1, 7, 1, 1, 8, 1)));
        animals.add(new Animal(new MapPosition(9, 13), Arrays.asList(1, 1, 4, 1, 1, 11, 1, 1)));
        animals.add(new Animal(new MapPosition(3, 12), Arrays.asList(1, 1, 7, 1, 1, 11, 1, 1)));
        animals.add(new Animal(new MapPosition(51, 20), Arrays.asList(1, 1, 1, 2, 1, 4, 1, 1)));
        animals.add(new Animal(new MapPosition(16, 5), Arrays.asList(11, 3, 1, 6, 1, 6, 1, 1)));
        animals.add(new Animal(new MapPosition(24, 18), Arrays.asList(1, 11, 1, 1, 1, 6, 1, 1)));
        animals.add(new Animal(new MapPosition(27, 14), Arrays.asList(1, 6, 1, 11, 1, 1, 7, 1)));
        animals.add(new Animal(new MapPosition(30, 21), Arrays.asList(1, 1, 1, 9, 1, 11, 1, 1)));
        animals.add(new Animal(new MapPosition(4, 12), Arrays.asList(1, 1, 7, 1, 1, 11, 1, 1)));

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
        spotChildren();
    }

    private void interaction(Animal animal, MapPosition targetPosition) {
        if (isOccupied(targetPosition)) {
            IMapElement targetElement = obstacles.get(targetPosition);
            if (targetElement instanceof Grass) {
                animal.eat();
                obstacles.remove(targetPosition);
            } else if (targetElement instanceof Animal) {
                if (animal.isAbleToReproduce()) {
                    Animal children = animal.reproduce((Animal) targetElement); // WE CAN DO THAT BECAUSE WE CHECK CLASS EARLIER
                    childrens.add(children);
                }
            }
        } else {
            if (animal.canReproduceWithoutPartner()) {
                Animal children = animal.reproduceWithoutPartner();
                childrens.add(children);
            }
        }
    }

    private void spotChildren() {
        for (Animal child : childrens) {
            obstacles.put(child.getPosition(), child);
            animals.add(child);
        }
        childrens.clear();
    }

    private void updateAnimalPosition(Animal animal, MapPosition targetPosition) {
        obstacles.remove(animal.getPosition());
        animal.moveTo(targetPosition);
        obstacles.put(animal.getPosition(), animal);
    }

    private void cleanDeadAnimals() {
        Iterator<Animal> iterator = animals.iterator();
        while (iterator.hasNext()) {
            Animal temporary = iterator.next();
            if (!temporary.isAlive()) {
                obstacles.remove(temporary.getPosition());
                iterator.remove();
            }
        }
    }

    private MapPosition newAnimalPosition(MapPosition currentPosition, List<Integer> directionChances) {
        int[] diffX = {-1, 0, 1, 1, 1, 0, -1, -1};
        int[] diffY = {1, 1, 1, 0, -1, -1, -1, 0};

        int direction = Losulosu.getRandom(directionChances);
        MapPosition newPosition = currentPosition.addVector(diffX[direction], diffY[direction]);

        if (!inMap(newPosition)) {
            direction = (direction + 4) % 8;
            newPosition = currentPosition.addVector(diffX[direction], diffY[direction]);
        }

        return newPosition;
    }

    private boolean inMap(MapPosition position) {
        return (position.largerOrEven(bottomLeft) && position.smallerOrEven(topRight));
    }

    private boolean isOccupied(MapPosition target) {
        return obstacles.containsKey(target);
    }

    public MapPosition getBottomLeft() {
        return bottomLeft;
    }

    public MapPosition getTopRight() {
        return topRight;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public HashMap<MapPosition, IMapElement> getObstacles() {
        return obstacles;
    }
}
