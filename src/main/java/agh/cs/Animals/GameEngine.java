package agh.cs.Animals;

import agh.cs.Animals.MapRepresenation.MapPosition;
import agh.cs.Animals.MapRepresenation.WorldMap;
import org.jline.reader.EndOfFileException;
import org.jline.reader.UserInterruptException;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class GameEngine {
    private TerminalHandler mainTerminal;
    private WorldMap mainMap;

    private final ScheduledExecutorService executorService;

    private boolean toDraw;

    public GameEngine() throws IOException {
        mainTerminal = new TerminalHandler();

        mainMap = new WorldMap(new MapPosition(0, 0), new MapPosition(30, 30),
                new MapPosition(10, 10), new MapPosition(20, 20));

        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(this::update, 0, 1, TimeUnit.SECONDS);

        toDraw = true;
    }

    public void run() {
        int passedDays = 0;
        mainTerminal.write("Hello2");
        while (true) {
            try {
                passedDays++;
                if(toDraw){
                    mainTerminal.write(mainMap.toString());
                    toDraw = false;
                }

            } catch (UserInterruptException e) {
                // Ignore
            } catch (EndOfFileException e) {
                return;
            }
        }
    }

    private void update(){
        mainMap.update();
        toDraw = true;
    }
}
