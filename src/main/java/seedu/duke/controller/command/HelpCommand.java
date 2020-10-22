package seedu.duke.controller.command;

import seedu.duke.common.LogManager;
import seedu.duke.model.DataManager;
import seedu.duke.common.Messages;
import seedu.duke.exception.InvalidHelpCommandException;
import seedu.duke.ui.UserInterface;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HelpCommand extends Command {
    private final UserInterface userInterface = UserInterface.getInstance();
    private static final Logger logger = LogManager.getLogManagerInstance().getLogger();

    public HelpCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(DataManager dataModel) throws InvalidHelpCommandException {
        handleHelp();
    }

    /**
     * <h2>handleHelp()</h2>
     * Prints out all available features users can use.
     * @exception InvalidHelpCommandException to inform the user if their help input is invalid.
     */
    private void handleHelp() throws InvalidHelpCommandException {
        String[] userInputs = userInput.split(" ");
        if (userInputs.length == 1) {
            logger.log(Level.INFO, "printing out all features users can use");
            userInterface.showToUser(Messages.MESSAGE_HELP);
        } else {
            logger.log(Level.WARNING, "invalid help command");
            throw new InvalidHelpCommandException();
        }
    }
}
