package agh.cs.Animals.MapRepresenation;

public class MapPosition {
    private final int cordX, cordY;

    public MapPosition(int cordX, int cordY) {
        this.cordX = cordX;
        this.cordY = cordY;
    }

    public boolean largerOrEven(MapPosition that){
        return cordX >= that.getCordX() && cordY >= that.getCordY();
    }

    public boolean smallerOrEven(MapPosition that){
        return cordX <= that.getCordX() && cordY <= that.getCordY();
    }

    public MapPosition addVector(int x, int y){
        return new MapPosition(this.cordX + x, this.cordY + y);
    }

    @Override
    public String toString(){
        return "| |";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MapPosition that = (MapPosition) o;

        if (cordX != that.cordX) return false;
        return cordY == that.cordY;
    }

    @Override
    public int hashCode() {
        int result = cordX;
        result = 31 * result + cordY;
        return result;
    }

    public int getCordX() {
        return cordX;
    }

    public int getCordY() {
        return cordY;
    }
}
