package seedu.duke.controller.command;

import seedu.duke.model.Model;

public class DeleteQuizCommand extends Command {
    public DeleteQuizCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(Model model) {
        model.getQuizManager().deleteQuiz(userInput.split(" "));
    }
}
