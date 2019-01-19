package agh.cs.Animals;

import java.io.IOException;

public class SimulationGameSystem {
    public static void main(String[] args) {
        try {
            System.out.println("Hello world!");
            GameEngine main = new GameEngine();
            main.run();
        }catch (IOException ex){
            System.out.println("ERROR!!! " + ex.getMessage());
        }
    }
}
