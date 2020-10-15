package seedu.duke.quiz;

public class Quiz {
    public static final String QUIZ_ICON = "[QUIZ]";
    protected String description;
    protected String option1;
    protected String option2;
    protected String option3;
    protected String option4;
    protected String answer;

    public Quiz(String description, String option1, String option2,
                 String option3, String option4, String answer) {
        this.description = description;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
    }

    public String getDescription() {
        return description;
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

    public String getQuizIcon() {
        return QUIZ_ICON;
    }

    @Override
    public String toString() {
        return description + "\n"
                + "(1) " + option1 + "\n"
                + "(2) " + option2 + "\n"
                + "(3) " + option3 + "\n"
                + "(4) " + option4 + "\n";
    }
}
