package agh.cs.Animals;

public class SimulationGameSystem {
    public static void main(String[] args) {
        GameEngine main = new GameEngine();
        try {
            main.run();
        } catch (InterruptedException ex) {
            System.out.print(ex.getMessage());
        }

    }
}
