import java.util.ArrayList;
import java.util.List;

public class Animal implements IMapElement {
    private final List<Integer> genes;

    private MapPosition position;

    private int energy;

    private boolean isAlive;

    public Animal(MapPosition bornPosition, List<Integer> inheritedGenes){
        position = bornPosition;

        genes = new ArrayList<>();
        genes.addAll(inheritedGenes);

        energy = 100;

        isAlive = true;
    }

    public void moveTo(MapPosition newPosition){
        energy -= 5;

        if(energy < 0)
            isAlive = false;

        this.position = newPosition;
    }

    public void eat(){
        energy += 50;
        if(energy > 100)
            energy = 100;
    }

    public Animal reproduce(Animal partner){
        return null;
    }

    public MapPosition getPosition() {
        return position;
    }

    public List<Integer> getGenes() {
        return genes;
    }

    public boolean isAlive(){
        return isAlive;
    }

    @Override
    public String toString(){
        return "M";
    }
}
