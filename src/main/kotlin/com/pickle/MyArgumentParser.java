package com.pickle;

import org.apache.commons.cli.*;

public class MyArgumentParser {
    private final Options options;

    /**
     * The constructor for MyArgumentParser
     * this.options is an Options object to store all the arguments
     * this.addAllArguments() is a method to add all the arguments
     */
    public MyArgumentParser() {
        this.options = new Options();
        this.addAllArguments();
    }

    /**
     * The Build method for MyArgumentParser
     *
     * @param args
     * @return CommandLine object
     */
    public static CommandLine build(String... args) {
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(new MyArgumentParser().options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("Pickle", new MyArgumentParser().options);
            System.exit(1);
        }

        return cmd;
    }

    /**
     * The addAllArguments method for MyArgumentParser
     */

    public void addAllArguments() {
        addBasicArguments();
        addHeaderArguments();
        addRequestArguments();
        addExpectedRequestArguments();
        addSoapRequestArguments();
    }

    /**
     * The addBasicArguments method for MyArgumentParser
     */
    public void addBasicArguments() {
        addArgument("i", "input", "Show input");
        addArgument("o", "output", "Show output");
    }

    /**
     * The addHeaderArguments method for MyArgumentParser
     */
    public void addHeaderArguments() {
        addArgument("h", "header", "Show header");
        addArgument("a", "accept", "Show accept header");
        addArgument("c", "content-type", "Show content-type header");
        addArgument("au", "authorization", "Show Authorization header");
        addArgument("u", "user-agent", "Show user-agent header");
    }

    /**
     * The addRequestArguments method for MyArgumentParser
     */
    public void addRequestArguments() {
        addArgument("r", "request", "Show request");
        addArgument("m", "method", "Show http method");
        addArgument("u", "url", "Show url");
        addArgument("d", "data", "Show request data");
    }

    /**
     * The addExpectedRequestArguments method for MyArgumentParser
     */
    public void addExpectedRequestArguments() {
        addArgument("er", "expected-request", "Show expected request");
        addArgument("em", "expected-method", "Show expected http method");
        addArgument("eu", "expected-url", "Show expected url");
        addArgument("ed", "expected-data", "Show expected request data");
        addArgument("s", "status", "Show status");
    }

    /**
     * The addSoapRequestArguments method for MyArgumentParser
     */
    private void addSoapRequestArguments() {
        addArgument("ac", "action", "Show SOAP action");
    }

    /**
     * The addArgument method for MyArgumentParser
     *
     * @param shortOpt
     * @param longOpt
     * @param description
     */
    public void addArgument(String shortOpt, String longOpt, String description) {
        Option option = new Option(shortOpt, longOpt, true, description);
        option.setRequired(false);
        options.addOption(option);
    }
}
