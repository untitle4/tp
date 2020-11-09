package seedu.duke.model.quiz;

import java.time.LocalDate;

//@@author untitle4
/**
 * A class for quiz.
 */
public class Quiz {
    private final String question;
    private final String option1;
    private final String option2;
    private final String option3;
    private final String option4;
    private final int answer;
    private final String explanation;
    private LocalDate lastAccessed;

    public Quiz(String question, String option1, String option2, String option3, String option4,
                int answer, String explanation) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
        this.explanation = explanation;
        lastAccessed = LocalDate.now();
    }

    public Quiz(String question, String option1, String option2, String option3, String option4,
                int answer, String explanation, LocalDate lastAccessed) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
        this.explanation = explanation;
        this.lastAccessed = lastAccessed;
    }

    public String getQuestion() {
        return question;
    }

    public int getAnswer() {
        return answer;
    }

    public String getExplanation() {
        return explanation;
    }

    public LocalDate getLastAccessed() {
        return lastAccessed;
    }

    @Override
    public String toString() {
        if (explanation.equals("")) {
            return question + "\n\n"
                    + "(1) " + option1 + "\n"
                    + "(2) " + option2 + "\n"
                    + "(3) " + option3 + "\n"
                    + "(4) " + option4 + "\n\n";
        } else {
            return question + "\n\n"
                    + "(1) " + option1 + "\n"
                    + "(2) " + option2 + "\n"
                    + "(3) " + option3 + "\n"
                    + "(4) " + option4 + "\n\n"
                    + "Explanation: " + explanation + "\n\n";
        }
    }

    //@@author elizabethcwt
    /**
     * <h2>printQuizQuestion() Method</h2>
     * Prints the quiz question for the user to answer.
     *
     * @return String - quiz question in given format.
     */
    public String printQuizQuestion() {
        return question + "\n\n"
                + "(1) " + option1 + "\n"
                + "(2) " + option2 + "\n"
                + "(3) " + option3 + "\n"
                + "(4) " + option4 + "\n\n";
    }

    /**
     * <h2>printPostQuizQuestion() Method</h2>
     * This method first checks if the relevant quiz question has an explanation in the quiz list text file.
     * <br>
     * Explanation absent - Prints:
     * <ul>
     *     <li>Quiz question</li>
     *     <li>Correctness logo of the question (correct or wrong)</li>
     *     <li>Question options</li>
     *     <li>User's answer (correct or wrong)</li>
     *     <li>Correct answer</li>
     * </ul>
     * <br>
     * Explanation present - Prints:
     * <ul>
     *     <li>Quiz question</li>
     *     <li>Correctness logo of the question (correct or wrong)</li>
     *     <li>Question options</li>
     *     <li>User's answer (correct or wrong)</li>
     *     <li>Correct answer</li>
     *     <li>Explanation for the question</li>
     * </ul>
     * @param l - A variable to count the quiz question number the user is at, at that point of time.
     * @param correctnessLogo - To attach the icon representing the correctness of the user's answer for the relevant
     *                        question.
     * @return String - Components as stated above, and in given format.
     */
    public String printPostQuizQuestion(int l, String correctnessLogo) {
        if (explanation.equals("")) {
            return question + correctnessLogo + "\n\n"
                    + "(1) " + option1 + "\n"
                    + "(2) " + option2 + "\n"
                    + "(3) " + option3 + "\n"
                    + "(4) " + option4 + "\n\n"
                    + "Your answer: (" + l + ")\n"
                    + "Correct answer: (" + answer + ")\n\n";
        } else {
            return question + correctnessLogo + "\n\n"
                    + "(1) " + option1 + "\n"
                    + "(2) " + option2 + "\n"
                    + "(3) " + option3 + "\n"
                    + "(4) " + option4 + "\n\n"
                    + "Your answer: (" + l + ")\n"
                    + "Correct answer: (" + answer + ")\n"
                    + "Explanation: " + explanation + "\n";
        }
    }

    //@@author AndreWongZH
    /**
     * Updates the quiz last accessed date to the current date.
     */
    public void updateLastAccessed() {
        lastAccessed = LocalDate.now();
    }

    /**
     * Converts quiz instance into storage readable string form.
     *
     * @return A string representation of the quiz.
     */
    public String convertToData() {
        return question + "|" + option1 + "|" + option2 + "|" + option3 + "|" + option4
                + "|" + answer + "|" + explanation + "|" + lastAccessed;
    }
}
