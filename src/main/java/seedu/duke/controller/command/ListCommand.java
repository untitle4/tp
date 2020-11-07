package seedu.duke.controller.command;

import seedu.duke.exception.ExtraParameterException;
import seedu.duke.exception.IncompleteListCommandException;
import seedu.duke.model.ModelMain;
import seedu.duke.model.contact.ContactManager;
import seedu.duke.model.event.EventManager;
import seedu.duke.model.quiz.QuizManager;

//@@author AndreWongZH
/**
 * Represents the command for listing events, quizzes and contacts.
 */
public class ListCommand extends Command {
    public static final String INPUT_SPACE = " ";
    public static final int INPUT_LENGTH_TWO = 2;

    public ListCommand(String userInput) {
        super(userInput);
    }

    /**
     * Runs list command on corresponding model type.
     *
     * @param modelMain A model type to be modified or read.
     * @throws IncompleteListCommandException If model is missing.
     * @throws ExtraParameterException If user adds extra parameters to list contacts or quiz.
     */
    @Override
    public void execute(ModelMain modelMain) throws IncompleteListCommandException, ExtraParameterException {
        if (modelMain == null) {
            throw new IncompleteListCommandException();
        }

        String[] separatedInputs = userInput.split(INPUT_SPACE);

        if (modelMain instanceof EventManager) {
            EventManager eventManager = (EventManager) modelMain;
            eventManager.list(userInput);
        } else if (modelMain instanceof QuizManager && separatedInputs.length == INPUT_LENGTH_TWO) {
            QuizManager quizManager = (QuizManager) modelMain;
            quizManager.list();
        } else if (modelMain instanceof ContactManager && separatedInputs.length == INPUT_LENGTH_TWO) {
            ContactManager contactManager = (ContactManager) modelMain;
            contactManager.list();
        } else {
            throw new ExtraParameterException();
        }
    }
}
