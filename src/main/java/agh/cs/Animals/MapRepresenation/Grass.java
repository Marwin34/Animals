package agh.cs.Animals.MapRepresenation;

public class Grass implements IMapElement{
    private final MapPosition position;

    public Grass(MapPosition position) {
        this.position = position;
    }

    @Override
    public MapPosition getPosition() {
        return position;
    }

    @Override
    public String toString(){
        return "*";
    }
}
