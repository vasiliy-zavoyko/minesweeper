package ru.zavoyko.sweeper;

import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import ru.zavoyko.sweeper.sweeper.JavaSweeper;

public class App {

    public static void main(String[] args) {
        int cols = 9;
        int rows = 9;
        int bombs = 3;
        try {
            final var parser = new DefaultParser();
            final var options = new Options();
            options.addOption(new Option("b", "bomb", false, "Number of bombs."));
            options.addOption(new Option("c", "cols", false, "Number of columns."));
            options.addOption(new Option("r", "rows", false, "Number of rows."));
            final var commandLine = parser.parse(options, args);
            if (commandLine.hasOption("b")) {
                bombs = Integer.parseInt(commandLine.getOptionValue("b"));
            }
            if (commandLine.hasOption("c")) {
                cols = Integer.parseInt(commandLine.getOptionValue("c"));
            }
            if (commandLine.hasOption("r")) {
                rows = Integer.parseInt(commandLine.getOptionValue("r"));
            }
        } catch (ParseException | NumberFormatException exception) {
            System.out.print("Start error: ");
            System.out.println(exception.getMessage());
            System.exit(1);
        }
        new JavaSweeper(cols, rows, bombs);
    }

}
