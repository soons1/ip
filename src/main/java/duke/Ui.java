package duke;

public class Ui{

    private static final String LINEBREAK = "_______________________________________________________________________________";

    public void greet() {
        String snowBoyAscii =
                "      *      \n" +
                        "     ***     \n" +
                        "   *******   \n" +
                        "  *  o o  *  \n" +
                        " *    >    * \n" +
                        " *  \\___/  * \n" +
                        "  *       *  \n" +
                        "   *******   \n" +
                        "     ***     \n" +
                        "      *      ";
        String toPrint = snowBoyAscii + "\n";
        toPrint += " Hello! I'm SnowBoy\n" + " What can I do for you?";
        Ui.beautify(toPrint);
    }

    public static void beautify(String toPrint) {
        System.out.println(LINEBREAK);
        System.out.println(toPrint);
        System.out.println(LINEBREAK);
    }

    public void showLoadingError() {
        String toPrint = " No existing list detected. Creating new list...";
        Ui.beautify(toPrint);
    }

    public void exit() {
        String toPrint = " Bye. Hope to see you again soon!";
        Ui.beautify(toPrint);
    }
}