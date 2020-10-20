package seedu.duke.controller;

import seedu.duke.common.Messages;
import seedu.duke.controller.command.ListCommand;
import seedu.duke.controller.parser.ModelParser;
import seedu.duke.exception.ContactParamException;
import seedu.duke.exception.EmptyParameterException;
import seedu.duke.exception.InvalidCommandException;
import seedu.duke.exception.InvalidHelpCommandException;
import seedu.duke.exception.InvalidModelException;
import seedu.duke.exception.MissingParameterException;
import seedu.duke.exception.QuizParamException;
import seedu.duke.model.DataManager;
import seedu.duke.model.Model;
import seedu.duke.controller.command.Command;
import seedu.duke.controller.command.CommandFactory;
import seedu.duke.controller.parser.CommandParser;
import seedu.duke.controller.parser.CommandType;
import seedu.duke.model.ModelType;
import seedu.duke.ui.UserInterface;

public class ControlManager {
    private final String userInput;
    private final Model model;
    private final UserInterface userInterface;

    public ControlManager(String userInput, Model model) {
        this.userInput = userInput;
        this.model = model;
        userInterface = UserInterface.getInstance();
    }

    public CommandType runLogic() {
        CommandType commandType = null;
        ModelType modelType;
        try {
            commandType = new CommandParser(userInput).extractCommand();
            Command actionableCommand = new CommandFactory(commandType, userInput).generateActionableCommand();
            if (commandType == CommandType.BYE) {
                return commandType;
            }
            modelType = new ModelParser(userInput).extractModel();
            DataManager dataModel = new ModelExtractor(model, modelType).retrieveModel();

            if (modelType == ModelType.EVENT) {
                new ListCommand(userInput).execute(model.getEventManager());
            } else {
                actionableCommand.execute(dataModel);
            }
        } catch (InvalidHelpCommandException e) {
            e.printStackTrace();
        } catch (ContactParamException e) {
            e.printStackTrace();
        } catch (QuizParamException e) {
            e.printStackTrace();
        } catch (InvalidCommandException e) {
            System.out.println("☹ Oops! I did not recognize that command! Enter 'help' if needed!");
        } catch (InvalidModelException e) {
            System.out.println("No such model");
        } catch (MissingParameterException e) {
            userInterface.showToUser(Messages.MESSAGE_MISSING_PARAMETERS);
        } catch (EmptyParameterException e) {
            userInterface.showToUser(Messages.MESSAGE_EMPTY_PARAMETERS);
        }

        return commandType;
    }
}
