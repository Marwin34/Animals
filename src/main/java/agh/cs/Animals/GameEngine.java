package agh.cs.Animals;

import agh.cs.Animals.MapRepresenation.MapPosition;
import agh.cs.Animals.MapRepresenation.WorldMap;
import agh.cs.Animals.Utitlities.MapVisualizer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.fusesource.jansi.Ansi.ansi;


public class GameEngine{
    private WorldMap mainMap;

    private int passedDays;
    private int timeSpeed;


    public GameEngine() {
        mainMap = new WorldMap(new MapPosition(0, 0), new MapPosition(60, 30),
                new MapPosition(20, 10), new MapPosition(40, 20));

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(this::update, 0, 1, TimeUnit.SECONDS);

        passedDays = 0;
        MapVisualizer.hideCursor();
        timeSpeed = 1;
    }

    public void run() {

    }

    private void update(){
        if(mainMap.getAnimals().size() > 0){
            mainMap.update();
            passedDays++;
            MapVisualizer.drawInTerminal(mainMap);
            displayInformation();
        }else {
            System.out.println();
            MapVisualizer.showCursor();
            System.exit(1);
        }
    }

    private void displayInformation(){
        System.out.print(ansi().cursor(mainMap.getTopRight().getCordY() + 10, 0).
                a(String.format("%s %s | %s %s", "Dzień: ", passedDays, "Prędkość upływu czasu: x", timeSpeed)));
    }
}
