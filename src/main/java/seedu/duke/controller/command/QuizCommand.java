package seedu.duke.controller.command;

import seedu.duke.model.ModelMain;
import seedu.duke.model.quiz.QuizManager;

import static seedu.duke.common.Messages.MESSAGE_MISSING_QUIZ_PARAM;
import static seedu.duke.common.Messages.MESSAGE_QUIZ_NON_NUMBER;

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
        try {
            if (separatedInputs[1].equals("record")) {
                quizManager.recordedQuizzes();
            } else {
                quizManager.takeQuiz(separatedInputs);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(MESSAGE_MISSING_QUIZ_PARAM);
        } catch (NumberFormatException e) {
            System.out.println(MESSAGE_QUIZ_NON_NUMBER);
        }
    }
}
