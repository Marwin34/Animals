package agh.cs.Animals;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;

public class TerminalHandler {
    private final LineReader reader;
    private final String prompt;
    private final Terminal terminal;

    public TerminalHandler() throws IOException {

        terminal = TerminalBuilder
                .builder()
                .build();

        LineReaderBuilder builder = LineReaderBuilder.builder()
                .terminal(terminal)
                .appName("Animals");
        reader = builder.build();

        prompt = "> ";
    }

    public void write(String message) {
        terminal.writer().print(message);
    }

    public String read() {
        return reader.readLine(prompt);
    }

    public String getHistory(){
        return reader.getHistory().toString();
    }
}
