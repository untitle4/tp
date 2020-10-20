package seedu.duke.model.quiz;

import seedu.duke.common.LogManager;
import seedu.duke.model.DataManager;
import seedu.duke.model.quiz.Quiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class QuizManager extends DataManager {
    private final ArrayList<Quiz> quizzes;

    // Add 10 default questions - users can delete if they want
    //TODO

    private static final Logger logger = LogManager.getLoggerInstance().getLogger();

    public QuizManager(ArrayList<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public ArrayList<Quiz> getQuizList() {
        return quizzes;
    }

    public int getQuizListSize() {
        assert quizzes != null;
        return quizzes.size();
    }

    public void takeQuiz(String[] separatedInputs) {
        int noOfQues = Integer.parseInt(separatedInputs[1]);
        //TODO: handle cases: "quiz", "quiz 2"

        // If user wants to try a valid number of questions
        if (noOfQues <= getQuizListSize()) {

            // Create a new list of the question indexes
            List<Integer> quizIndexes = new ArrayList<>();
            for (int i = 0; i < quizzes.size(); i++) {
                quizIndexes.add(i);
            }

            // Shuffle the question indexes
            Collections.shuffle(quizIndexes);

            StringBuilder selectedQuestions = new StringBuilder();

            // Assign random order of indexes, of given no. of questions, to new StringBuilder, selectedQuestions
            for (int j = 0; j < noOfQues; j++) {
                selectedQuestions.append(quizzes.get(j) + "\n");
                System.out.println(selectedQuestions);
            }
        } else if ((noOfQues > getQuizListSize()) && (getQuizListSize() < 10)) {
            // If user wants to try more questions than he/she has in the current quiz (and has less than 10 questions)
            System.out.println("OOPS! You wanted to take a quiz with " + noOfQues + " questions, but your current quiz "
                    + "only has " + getQuizListSize() + " question(s).\nPlease add more questions to your quiz "
                    + "via the 'add quiz' command!\n");
        } else {
            // If user wants to try more questions than he/she has in the current quiz (but has at least 10 questions)
            System.out.println("OOPS! You wanted to quiz a test with " + noOfQues + " questions, but your current quiz "
                    + "only has " + getQuizListSize() + " question(s).\nPlease either add more questions to your quiz "
                    + "via the 'add quiz' command, or take a quiz with a suitable number of questions!\n");
        }
    }

    @Override
    public void delete(String[] userInputs) throws IndexOutOfBoundsException {
        int quizIndex;

        try {
            quizIndex = Integer.parseInt(userInputs[2]);
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! Please indicate in NUMERALS, which cca you'd like to delete!");
            return;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Please indicate which cca you'd like to delete!");
            return;
        }

        if ((quizIndex <= 0) || (quizIndex > getQuizListSize())) {
            throw new IndexOutOfBoundsException();
        }

        System.out.println("Noted. I've removed this quiz: ");
        System.out.println(quizzes.get(quizIndex - 1));

        quizzes.remove(quizIndex - 1);
        getQuizStatement();
    }

    // format: add quiz /q question /o1 option 1 /o2 option 2 /o3 option 3 /o4 option 4 /a answer /exp explanation
    @Override
    public void add(String userInput) {
        if (!userInput.contains(" /q ")) {
            System.out.println("question not found");
        }
        if (!userInput.contains(" /a ")) {
            System.out.println("answer not found");
        }
        if (!userInput.contains(" /o1 ") && !userInput.contains(" /o2 ")
                && !userInput.contains(" /o3 ") && !userInput.contains(" /o4 ")) {
            System.out.println("options not provided");
        }
        String[] separatedInputs = userInput.trim().split("/");

        //todo add exceptions here (index out of bounds)
        String question = separatedInputs[1].substring(1);
        String option1 = separatedInputs[2].substring(2);
        String option2 = separatedInputs[3].substring(2);
        String option3 = separatedInputs[4].substring(2);
        String option4 = separatedInputs[5].substring(2);
        String answer = separatedInputs[6].substring(1);

        quizzes.add(new Quiz(question, option1, option2, option3, option4, answer));
        System.out.println("Quiz question added!");
    }

    public void listQuiz() {
        if (quizzes.size() == 0) {
            System.out.println("Quiz list is empty. Add a question!");
        } else {
            for (int i = 0; i < quizzes.size(); i++) {
                System.out.println("Question " + (i + 1) + ":");
                System.out.println(quizzes.get(i));
            }
        }
    }

    private void getQuizStatement() {
        String quizStatement = getQuizListSize() == 1 ? " question" : " questions";
        System.out.println("Now you have " + getQuizListSize() + quizStatement + " in your quiz.");
    }
}
