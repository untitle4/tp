package seedu.duke.controller.command;

import seedu.duke.exception.IncompleteFindCommandException;
import seedu.duke.exception.MissingParameterException;
import seedu.duke.model.ModelMain;
import seedu.duke.model.contact.ContactManager;
import seedu.duke.model.event.EventManager;
import seedu.duke.model.quiz.QuizManager;

//@@author AndreWongZH
/**
 * Represents the command for searching via event description.
 */
public class FindCommand extends Command {
    public FindCommand(String userInput) {
        super(userInput);
    }

    /**
     * Runs find command on corresponding model type.
     *
     * @param modelMain A model type to be modified or read.
     * @throws MissingParameterException If command is lacking keywords.
     * @throws IncompleteFindCommandException If model is missing.
     */
    @Override
    public void execute(ModelMain modelMain) throws MissingParameterException, IncompleteFindCommandException {
        if (modelMain == null) {
            throw new IncompleteFindCommandException();
        }

        if (modelMain instanceof EventManager) {
            EventManager eventManager = (EventManager) modelMain;
            eventManager.find(userInput);
        } else if (modelMain instanceof QuizManager) {
            QuizManager quizManager = (QuizManager) modelMain;
            quizManager.find(userInput);
        } else if (modelMain instanceof ContactManager) {
            ContactManager contactManager = (ContactManager) modelMain;
            contactManager.find(userInput);
        }
    }
}
