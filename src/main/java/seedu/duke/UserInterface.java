package seedu.duke;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private static UserInterface userInterface = null;
    private final Scanner in;
    private final PrintStream out;

    private UserInterface() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    public static UserInterface getInstance() {
        if (userInterface == null) {
           userInterface = new UserInterface();
        }

        return userInterface;
    }

    public void showWelcomeMessage() {
        showToUser(
                Messages.MESSAGE_HELLO_FROM_DUKE,
                Messages.MESSAGE_PROMPT_NAME);

        String userName = getUserCommand();

        showToUser(Messages.MESSAGE_HELLO + userName,
                Messages.MESSAGE_PROMPT_COMMAND);
    }

    public String getUserCommand() {
        return in.nextLine();
    }

    public void showToUser(String... message) {
        for (String m : message) {
            out.println(m);
        }
    }

    public void printArray(ArrayList<String> stringArrayList) {
        assert stringArrayList != null;
        for (String line : stringArrayList) {
            System.out.println(line);
        }
    }
}
