package seedu.duke.controller.command;

import seedu.duke.model.DataManager;
import seedu.duke.model.quiz.QuizManager;

public class QuizCommand extends Command {
    public QuizCommand(String userInput) {
        super(userInput);
    }

    @Override
    //TODO edit this
    public void execute(DataManager dataModel) {
    }

    public void execute(QuizManager quizManager) {
        String[] separatedInputs = userInput.trim().split(" ");
        quizManager.takeQuiz(separatedInputs);
    }
}
