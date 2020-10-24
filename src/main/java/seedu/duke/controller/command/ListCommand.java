package seedu.duke.controller.command;

import seedu.duke.exception.IncompleteListCommandException;
import seedu.duke.exception.MissingParameterException;
import seedu.duke.model.ModelMain;
import seedu.duke.model.contact.ContactManager;
import seedu.duke.model.event.EventManager;
import seedu.duke.model.quiz.QuizManager;

//@@author AndreWongZH
/**
 * Represents the command for listing events, quizzes and contacts.
 */
public class ListCommand extends Command {
    public ListCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(ModelMain modelMain) throws IncompleteListCommandException, MissingParameterException {
        if (modelMain == null) {
            throw new IncompleteListCommandException();
        }

        if (modelMain instanceof EventManager) {
            EventManager eventManager = (EventManager) modelMain;
            eventManager.list(userInput);
        } else if (modelMain instanceof QuizManager) {
            QuizManager quizManager = (QuizManager) modelMain;
            quizManager.list();
        } else if (modelMain instanceof ContactManager) {
            ContactManager contactManager = (ContactManager) modelMain;
            contactManager.list();
        }
    }
}
