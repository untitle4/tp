package seedu.duke.controller.command;

import seedu.duke.model.Model;

public class AddQuizCommand extends Command {
    public AddQuizCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(Model model) {
        model.getQuizManager().addQuiz(userInput);
    }
}
