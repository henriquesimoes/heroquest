package br.unicamp.ic.mc322.heroquest;

import br.unicamp.ic.mc322.heroquest.engine.GameEngine;
import br.unicamp.ic.mc322.heroquest.engine.gui.GraphicEngine;
import br.unicamp.ic.mc322.heroquest.engine.terminal.TerminalEngine;
import org.apache.commons.cli.*;

public class Main {
    public static void main(String[] args) {
        Options options = loadOptions();
        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args);
            GameEngine engine;

            if (cmd.hasOption("--cli"))
                engine = new TerminalEngine();
            else
                engine = new GraphicEngine();

            engine.run();
        } catch (ParseException ex) {
            System.err.println(ex.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("heroquest", options);
        }
    }

    private static Options loadOptions() {
        Options options = new Options();

        Option option = new Option("c", "cli", false, "Use CLI game version");
        options.addOption(option);

        return options;
    }
}
