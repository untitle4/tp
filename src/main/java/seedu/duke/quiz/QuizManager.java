package seedu.duke.quiz;

import seedu.duke.storage.QuizStorageManager;

public class QuizManager {
    // format: add quiz /q question /o1 option1 /o2 option2 /o3 option3 /o4 options4 /a answer
    public void addQuiz(String userInput) {
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
        String[] separatedInputs = userInput.split(" ");

        String question = separatedInputs[3];
        String option1 = separatedInputs[5];
        String option2 = separatedInputs[7];
        String option3 = separatedInputs[9];
        String option4 = separatedInputs[11];
        String answer = separatedInputs[13];

        Quiz quiz = new Quiz(question, option1, option2, option3, option4, answer);
        new QuizStorageManager().saveQuizData(quiz);
    }
}
