package seedu.duke.model.quiz;

import java.util.ArrayList;

//@@author elizabethcwt
/**
 * <h2>UserAnswerManager Class</h2>
 * Contains userAnswers, which is an ArrayList of the user's integer answers.
 * <br><br>
 * Also contains correctness, another ArrayList of the boolean values of the user's answers for each question.
 */
public class UserAnswerManager {
    private final ArrayList<Integer> userAnswers;
    private final ArrayList<Boolean> correctness;

    public UserAnswerManager() {
        this.userAnswers = new ArrayList<>();
        this.correctness = new ArrayList<>();
    }

    // Getters
    public ArrayList<Integer> getUserAnswers() {
        return userAnswers;
    }

    public ArrayList<Boolean> getCorrectness() {
        return correctness;
    }
}