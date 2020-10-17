package seedu.duke.controller;

import seedu.duke.model.Model;
import seedu.duke.controller.command.Command;
import seedu.duke.controller.command.CommandFactory;
import seedu.duke.controller.parser.CommandParser;
import seedu.duke.controller.parser.CommandType;
import seedu.duke.exception.CcaEmptyStringException;
import seedu.duke.exception.CcaParamException;
import seedu.duke.exception.ContactEmptyStringException;
import seedu.duke.exception.ContactParamException;
import seedu.duke.exception.EmptyTuitionInputException;
import seedu.duke.exception.InvalidClassInputException;
import seedu.duke.exception.InvalidCommandException;
import seedu.duke.exception.InvalidHelpCommandException;
import seedu.duke.exception.InvalidTuitionInputException;
import seedu.duke.exception.TestEmptyStringException;
import seedu.duke.exception.TestParamException;

public class ControlManager {
    private final String userInput;
    private final Model model;

    public ControlManager(String userInput, Model model) {
        this.userInput = userInput;
        this.model = model;
    }

    public CommandType runLogic() {
        CommandType commandType = null;
        try {
            commandType = new CommandParser(userInput).extractCommand();
            Command actionableCommand = new CommandFactory(commandType, userInput).generateActionableCommand();
            if (commandType == CommandType.BYE) {
                return commandType;
            }
            actionableCommand.execute(model);
        } catch (InvalidClassInputException e) {
            e.printStackTrace();
        } catch (TestParamException e) {
            e.printStackTrace();
        } catch (TestEmptyStringException e) {
            e.printStackTrace();
        } catch (CcaParamException e) {
            e.printStackTrace();
        } catch (CcaEmptyStringException e) {
            e.printStackTrace();
        } catch (EmptyTuitionInputException e) {
            e.printStackTrace();
        } catch (InvalidTuitionInputException e) {
            e.printStackTrace();
        } catch (ContactEmptyStringException e) {
            e.printStackTrace();
        } catch (InvalidHelpCommandException e) {
            e.printStackTrace();
        } catch (ContactParamException e) {
            e.printStackTrace();
        } catch (InvalidCommandException e) {
            System.out.println("☹ Oops! I did not recognize that command! Enter 'help' if needed!");
        }

        return commandType;
    }
}
