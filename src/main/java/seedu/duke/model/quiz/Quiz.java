package seedu.duke.model.quiz;

public class Quiz {
    private final String question;
    private final String option1;
    private final String option2;
    private final String option3;
    private final String option4;
    private final String answer;
    private String explanation = "";

    public Quiz(String question, String option1, String option2, String option3, String option4, String answer) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
    }

    public Quiz(String question, String option1, String option2, String option3, String option4,
                String answer, String explanation) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
        this.explanation = explanation;
    }

    public String getQuestion() {
        return question;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public String getAnswer() {
        return answer;
    }

    public String getExplanation() {
        return explanation;
    }

    public String convertToData() {
        if (explanation.equals("")) {
            return question + "|" + option1 + "|" + option2 + "|" + option3 + "|"
                    + option4 + "|" + answer;
        } else {
            return question + "|" + option1 + "|" + option2 + "|" + option3 + "|" + option4
                    + "|" + answer + "|" + explanation;
        }
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
}
