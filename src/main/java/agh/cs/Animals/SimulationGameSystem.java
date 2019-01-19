package agh.cs.Animals;

import java.io.IOException;

public class SimulationGameSystem {
    public static void main(String args[]) {
        try {
            GameEngine main = new GameEngine();
        }catch (IOException ex){
            System.out.println("ERROR!!! " + ex.getMessage());
        }

    }
}
