package agh.cs.Animals;

import agh.cs.Animals.MapRepresenation.MapPosition;
import agh.cs.Animals.MapRepresenation.WorldMap;

import java.io.IOException;

public class GameEngine {
    private TerminalHandler mainTerminal;
    private WorldMap mainMap;

    public GameEngine() throws IOException {
         mainTerminal = new TerminalHandler();

         mainMap =  new WorldMap(new MapPosition(0, 0), new MapPosition(30, 30),
                 new MapPosition(10, 10), new MapPosition(20, 20));


    }

    public void run(){
        int passedDays = 0;

        System.out.println(mainMap);

        while(mainMap.getAnimals().size() > 0)
        {
            mainMap.update();
            passedDays++;
        }

        System.out.println(mainMap);
        System.out.println(passedDays);
    }
}
