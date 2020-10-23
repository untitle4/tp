package seedu.duke.controller.command;

import seedu.duke.model.ModelMain;
import seedu.duke.model.quiz.QuizManager;

public class QuizCommand extends Command {

    public static final String INPUT_SPACE = " ";

    public QuizCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(ModelMain modelMain) {
        QuizManager quizManager = (QuizManager) modelMain;
        assert userInput != null;
        String[] separatedInputs = userInput.trim().split(INPUT_SPACE);
        quizManager.takeQuiz(separatedInputs);
    }
}
