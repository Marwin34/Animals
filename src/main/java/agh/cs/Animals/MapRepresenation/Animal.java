package agh.cs.Animals.MapRepresenation;

import agh.cs.Animals.Utitlities.Losulosu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Animal implements IMapElement {
    private final List<Integer> genes;

    private MapPosition position;

    private int energy;

    public Animal(MapPosition bornPosition, List<Integer> inheritedGenes) {
        position = bornPosition;

        genes = new ArrayList<>();
        genes.addAll(inheritedGenes);

        energy = 100;
    }

    public void moveTo(MapPosition newPosition) {
        energy -= 5;

        this.position = newPosition;
    }

    public void eat() {
        energy += 50;
        if (energy > 100)
            energy = 100;
    }

    public Animal reproduce(Animal partner) {
        energy -= 55;

        return new Animal(position, combineGenes(partner.genes));
    }

    private List<Integer> combineGenes(List<Integer> inputGenes) {
        Integer[] array = {1, 1, 1, 1, 1, 1, 1, 1};
        int startIndex = Losulosu.getRandom(Arrays.asList(array));
        int endIndex = 0;
        if (startIndex == genes.size() - 1) {
            startIndex = endIndex;
        } else {
            for (int i = 0; i < startIndex; i++) {
                array[i] = 0;
            }
            endIndex = Losulosu.getRandom(Arrays.asList(array));
        }

        List<Integer> newGenes;
        newGenes = new ArrayList<>(genes);

        for (int i = startIndex; i <= endIndex; i++) {
            newGenes.set(i, inputGenes.get(i));
        }

        return newGenes;
    }

    public MapPosition getPosition() {
        return position;
    }

    public List<Integer> getGenes() {
        return genes;
    }

    public boolean isAlive() {
        return energy > 0;
    }

    public boolean isAbleToReproduce() {
        return energy >= 55;
    }

    @Override
    public String toString() {
        return "M";
    }
}
