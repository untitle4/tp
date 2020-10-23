package seedu.duke.model.quiz;

import java.util.ArrayList;

public class UserAnswerManager {
    private final ArrayList<Integer> userAnswers;
    private final ArrayList<Boolean> correctness;

    public UserAnswerManager() {
        this.userAnswers = new ArrayList<Integer>();
        this.correctness = new ArrayList<Boolean>();
    }

    // Getters
    public ArrayList<Integer> getUserAnswers() {
        return userAnswers;
    }

    public ArrayList<Boolean> getCorrectness() {
        return correctness;
    }
}