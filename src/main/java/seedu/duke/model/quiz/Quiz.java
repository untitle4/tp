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

    public String convertToData() {
        return question + "|" + option1 + "|" + option2 + "|" + option3 + "|" + option4
                + "|" + answer + "|" + explanation + "|" + lastAccessed;
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
    public String printQuizQuestion() {
        return question + "\n\n"
                + "(1) " + option1 + "\n"
                + "(2) " + option2 + "\n"
                + "(3) " + option3 + "\n"
                + "(4) " + option4 + "\n\n";
    }

    //@@author elizabethcwt
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
     * Update quiz last accessed date to the current date.
     */
    public void updateLastAccessed() {
        lastAccessed = LocalDate.now();
    }
}
