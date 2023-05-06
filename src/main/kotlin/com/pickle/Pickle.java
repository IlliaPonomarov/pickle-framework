package com.pickle;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

public class Pickle {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("No arguments provided");
            System.exit(1);
        }

        CommandLine myArgumentParser = MyArgumentParser.build(args);

        if ((myArgumentParser.hasOption("i") || myArgumentParser.hasOption("input")) && (myArgumentParser.hasOption("o") || myArgumentParser.hasOption("output"))) {
            System.out.println("Input");
        }
    }
}
