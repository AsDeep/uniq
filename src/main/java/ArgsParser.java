import java.util.ArrayList;
import java.util.List;

import org.kohsuke.args4j.*;

public class ArgsParser {

    public String input = "";

    @Option(name = "-i", usage = "Ignore register")
    public boolean ignoreReg = false;

    @Option(name = "-s", usage = "Ignore First N symbols")
    public int ignoreCount = 0;

    @Option(name = "-u", usage = "Only unique strings are out")
    public boolean onlyUnique = false;

    @Option(name = "-o", usage = "Output to this file")
    public String out = "";

    @Option(name = "-c", usage = "Print count of replaced strings at first")
    public Boolean count = false;

    @Argument
    public List<String> arguments = new ArrayList<>();

    public static void main(String[] args) {
        new ArgsParser().parseArgs(args);

    }

    public void parseArgs(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
            if (arguments.size() != 0 && arguments.size() > 1) {
                System.err.println("Wrong arguments. Usage: uniq [-i] [-u] [-c] [-s num] [-o ofile] [file]");
                System.exit(-1);
            }
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("Usage: uniq [-i] [-u] [-c] [-s num] [-o ofile] [file]");
            System.exit(-1);
        }
        if (arguments.size() == 1) {
            input = arguments.get(0);
        }
    }
}