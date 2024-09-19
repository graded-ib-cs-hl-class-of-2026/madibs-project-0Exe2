import java.util.Scanner;
import java.util.HashMap; // import the HashMap class
// from https://www.w3schools.com/java/java_hashmap.asp

public class App {

    /**
     * This method is the primary driver method for your program.
     * It should primarily call other methods to do its work.
     * Do NOT edit this method until after you have fully satisfied the reqs for a 5
     */

    // class-wide scanner object (so don't need to create a new one in every function)
    Scanner scan = new Scanner(System.in);

    // ANSI reset color code
    // ansi code from from https://www.geeksforgeeks.org/how-to-print-colored-text-in-java-console/
    public static final String ANSI_RESET = "\u001B[0m";

    /*
        This is a function to start the game and run the game loop
        and call all other functions

        Parameters:
            - none

        Returns:
            - none
    */
    public void start() {
        boolean userWantsToStop = false;

        while (userWantsToStop == false) {
            printInstructions();

            printGreeting();

            doMadlib();

            // game loop logic below

            System.out.println(returnColoredText("green", "│") + " Do you want to play again? 'Yes' or 'No'");

            System.out.print(returnColoredText("green", "│ ↳") + " ");

            String userResponse = scan.nextLine();

            if (userResponse.toLowerCase().equals("no")) {
                userWantsToStop = true;

                System.out.println(returnColoredText("green", "│") + " Thanks for playing!");
            }
        }

        scan.close();
    }

    /* Here you should create all missing methods used by the start() method
     * I have created the signature for ONE of the methods below.
     * You can create any other methods you need as well!
    */

    /*
        This is a helper function that will print a prompt to the user
        and return their input

        Parameters:
            - scan (type Scanner): the class-wide object for
            getting user input

            - prompt (type String): the message to display to the user

        Returns:
            - (type String): the user's input
    */
    public String getUserInput() {

        System.out.print(returnColoredText("green", "│ ↳") + " ");

        String input = scan.nextLine();

        return input;
    }

    /*
        This is a helper function to return the ANSI color code of a specific color

        Parameters:
            - colorName (type String): the color name for the function to return
              the color code for

        Returns:
            - (type String): the ANSI color code
    */
    public String getColorCodes(String colorName) {

        // https://stackoverflow.com/questions/8261075/adding-multiple-entries-to-a-hashmap-at-once-in-one-statement
        // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
        HashMap<String, String> colors = new HashMap<String, String>()
        {{
            //put("red", "\u001B[31m");
            //put("yellow", "\u001B[33m");
            //put("blue", "\u001B[34m");
            //put("purple", "\u001B[35m");
            //put("cyan", "\u001B[36m");
            //put("white", "\u001B[37m");
            put("green", "\u001B[32m");
        }};

        return colors.get(colorName);
    }

    /*
        This is a helper function to print line to console in a specific color

        Parameters:
            - colorName (type String): the color name for the function to print
              in
            - message (type String): the message to display to the user

        Returns:
            - void
    */
    public void printColoredLn(String colorName, String message) {
        System.out.println(getColorCodes(colorName) + message + ANSI_RESET);
    }

    /*
        This is a helper function to return a string in a specific color

        Parameters:
            - colorName (type String): the color name for the function to return
              the color text for
            - message (type String): the message to embed in formatted color codes

        Returns:
            - (type String): the colored text
    */
    public String returnColoredText(String colorName, String message) {
        return getColorCodes(colorName) + message + ANSI_RESET;
    }

    /*
        This is a function to print the instructions for the game

        Parameters:
            - none

        Returns:
            - none
    */
    public void printInstructions() {
        // ASCII art from https://patorjk.com/software/taag/#p=display&f=RubiFont&t=Madlibs
        printColoredLn("green", """
╭──────────────────────────────────────╮
│                                      │
│ ▗▖  ▗▖ ▗▄▖ ▗▄▄▄ ▗▖   ▗▄▄▄▖▗▄▄▖  ▗▄▄▖ │
│ ▐▛▚▞▜▌▐▌ ▐▌▐▌  █▐▌     █  ▐▌ ▐▌▐▌    │
│ ▐▌  ▐▌▐▛▀▜▌▐▌  █▐▌     █  ▐▛▀▚▖ ▝▀▚▖ │
│ ▐▌  ▐▌▐▌ ▐▌▐▙▄▄▀▐▙▄▄▖▗▄█▄▖▐▙▄▞▘▗▄▄▞▘ │
│                                      │
╰──────────────────────────────────────╯""");

        // Unicode symbols for formatting looks like https://www.w3.org/TR/xml-entity-names/025.html
        printColoredLn("green", "╭────────────────╮");
        printColoredLn("green", "│  Instructions  │");
        printColoredLn("green", "├────────────────╯"); //  ╰

        // bullet point icon from  https://www.merriam-webster.com/grammar/how-to-use-bullet-points
        System.out.println(returnColoredText("green", "│") + " • Enter your name");
        System.out.println(returnColoredText("green", "│") + " • Enter the prompted words to create a funny story!");
        System.out.println(returnColoredText("green", "│") + " • Repeat until you're tired of laughing");
    }

    /*
        This is a function to print the greeting for the game

        Parameters:
            - none

        Returns:
            - none
    */
    public void printGreeting() {
        printColoredLn("green","├──────────────────────────╮"); // ╭
        printColoredLn("green","│    Welcome to Madlibs    │");
        printColoredLn("green","├──────────────────────────╯"); //  ╰
        System.out.println(returnColoredText("green", "│") + " What is your name?");

        String userName = getUserInput();

        printColoredLn("green","├────────────────────");
        System.out.println(returnColoredText("green", "│") + " Hello, " + returnColoredText("green", userName) + "!");
        System.out.println(returnColoredText("green", "│") + " Let's play a game!");
    }

    /*
        This is a function to run and play the madlib game

        Parameters:
            - none

        Returns:
            - none
    */
    public void doMadlib() {
        // I'll create an array of the terms I need, and loop
        // through it to create a map of terms for the user's input

        // https://www.w3schools.com/java/java_arrays.asp
        String[] terms = {
            "country",
            "noun",
            "adjective",
            "profession",
            "name",
            "tool",
            "family member",
            "animal",
            "color",
            "plural noun",
            "location"
        };

        HashMap<String, String> userInputs = new HashMap<String, String>();

        printColoredLn("green","├────────────────────");
        System.out.println(returnColoredText("green", "│") + " Please enter the following:");

        // https://www.w3schools.com/java/java_foreach_loop.asp
        for (String term : terms) {
            System.out.println(returnColoredText("green", "│") + " Enter a " + returnColoredText("green", term));

            String userInput = getUserInput();

            // this adds the user's input to the hashmap
            userInputs.put(term, userInput);
        }

        String story = "There was once an epic [profession] from [country] who moved\n" +
            returnColoredText("green", "│") + " " + "to a [location]. They were known for their [adjective] [noun]\n" +
            returnColoredText("green", "│") + " " + "and their trusty [tool]. They lived with [family member]\n" +
            returnColoredText("green", "│") + " " + "and their pet [animal]. They loved the color [color] and\n" +
            returnColoredText("green", "│") + " " + "were known by the pseudonym [name]. They were always\n" +
            returnColoredText("green", "│") + " " + "seen wearing [color] [plural noun].";

        // https://www.w3schools.com/java/java_foreach_loop.asp (same as above)
        // looping through the array of terms, creating a variable of type String
        // and saving it to a variable named "term"
        for (String term : terms) {
            story = story.replace("[" + term + "]", returnColoredText("green", userInputs.get(term)));
        }

        printColoredLn("green", "├────────────────────");
        System.out.println(returnColoredText("green", "│") + " Here is your story:");
        printColoredLn("green","├────────────────────");
        System.out.println(returnColoredText("green", "│") + " " + story);
        printColoredLn("green", "╰────────────────────");
    }

    /* DO NOT TOUCH OR EDIT THE METHOD BELOW! ADD NO CODE BELOW THIS LINE */
    public static void main(String[] args) throws Exception {
        new App().start();
    }
}
