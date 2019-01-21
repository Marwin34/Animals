package agh.cs.Animals;

import agh.cs.Animals.MapRepresenation.MapPosition;
import agh.cs.Animals.MapRepresenation.WorldMap;
import agh.cs.Animals.Utitlities.MapVisualizer;

import static org.fusesource.jansi.Ansi.ansi;


public class GameEngine{
    private WorldMap mainMap;

    private int passedDays;

    public GameEngine() {
        mainMap = new WorldMap(new MapPosition(0, 0), new MapPosition(60, 30),
                new MapPosition(20, 10), new MapPosition(40, 20));

        passedDays = 0;
        MapVisualizer.hideCursor();
    }

    public void run() throws InterruptedException {
        while(true){
            long deltaTime = System.currentTimeMillis();
            if(mainMap.getAnimals().size() > 0){
                mainMap.update(passedDays);
                passedDays++;
                MapVisualizer.drawInTerminal(mainMap);
                displayInformation();
            }else {
                System.out.println();
                MapVisualizer.showCursor();
                System.exit(1);
            }
            deltaTime = System.currentTimeMillis() - deltaTime;

            if(deltaTime < 200){
                Thread.sleep(200 - deltaTime);
            }
        }
    }

    private void displayInformation(){
        System.out.print(ansi().cursor(mainMap.getTopRight().getCordY() + 10, 0).
                a(String.format("Dzień %s, populacja %s.", passedDays, mainMap.getAnimals().size())));
    }
}
