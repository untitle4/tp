package seedu.duke.controller.command;

import seedu.duke.exception.ExtraParameterException;
import seedu.duke.model.ModelMain;
import seedu.duke.model.quiz.QuizManager;

import static seedu.duke.common.Messages.MESSAGE_MISSING_QUIZ_PARAM;
import static seedu.duke.common.Messages.MESSAGE_QUIZ_NON_NUMBER;

public class QuizCommand extends Command {

    public static final String INPUT_SPACE = " ";
    public static final int MAX_PARAM_SIZE = 2;
    public static final String INPUT_RECORD = "record";

    public QuizCommand(String userInput) {
        super(userInput);
    }


    @Override
    public void execute(ModelMain modelMain) throws ExtraParameterException {
        QuizManager quizManager = (QuizManager) modelMain;
        assert userInput != null;
        String[] separatedInputs = userInput.trim().split(INPUT_SPACE);
        if (separatedInputs.length > MAX_PARAM_SIZE) {
            throw new ExtraParameterException();
        }

        try {
            if (separatedInputs[1].toLowerCase().equals(INPUT_RECORD)) {
                quizManager.recordedQuizzes();
            } else {
                quizManager.checkQuizSizeValidity(separatedInputs);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(MESSAGE_MISSING_QUIZ_PARAM);
        } catch (NumberFormatException e) {
            System.out.println(MESSAGE_QUIZ_NON_NUMBER);
        }
    }
}
