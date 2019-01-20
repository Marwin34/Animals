package agh.cs.Animals;

import agh.cs.Animals.MapRepresenation.MapPosition;
import agh.cs.Animals.MapRepresenation.WorldMap;
import agh.cs.Animals.Utitlities.MapVisualizer;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class GameEngine {
    private WorldMap mainMap;

    private final ScheduledExecutorService executorService;

    int passedDays;

    public GameEngine() throws IOException {
        mainMap = new WorldMap(new MapPosition(0, 0), new MapPosition(30, 60),
                new MapPosition(10, 20), new MapPosition(20, 40));

        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(this::update, 0, 1, TimeUnit.SECONDS);

        passedDays = 0;
    }

    public void run() {

    }

    private void update(){
        if(mainMap.getAnimals().size() > 0){
            mainMap.update();
            passedDays++;
            MapVisualizer.drawInTerminal(mainMap);
        }else {
            System.out.print(passedDays);
            System.exit(1);
        }
    }
}
