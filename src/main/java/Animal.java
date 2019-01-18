import java.util.ArrayList;
import java.util.List;

public class Animal implements IMapElement {
    private final List<Integer> genes;

    private MapPosition position;

    private int energy;

    public Animal(MapPosition bornPosition, List<Integer> inheritedGenes){
        position = bornPosition;

        genes = new ArrayList<>();
        genes.addAll(inheritedGenes);

        energy = 100;
    }

    public MapPosition getPosition() {
        return position;
    }

    public List<Integer> getGenes() {
        return genes;
    }

    public void moveTo(MapPosition newPosition){
        this.position = newPosition;
    }

    @Override
    public String toString(){
        return "|A";
    }
}
