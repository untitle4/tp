package seedu.duke.model.event;

import seedu.duke.exception.SwappedParameterException;
import seedu.duke.model.ModelManager;
import seedu.duke.model.event.tuition.EventTuitionManager;

//@@author AndreWongZH
/**
 * Represents the model class which is inherited by Class, Test, CCA, Tuition Manager.
 */
public abstract class EventDataManager extends ModelManager implements EventInteractable {
    public static final int BEGIN_INDEX = 0;
    public static final int END_INDEX = 1;
    public static final String N_PREFIX = "n";
    public static final String S_PREFIX = "s";
    public static final String E_PREFIX = "e";
    public static final String L_PREFIX = "l";
    public static final int N_INDEX = 1;
    public static final int S_INDEX = 2;
    public static final int E_INDEX = 3;
    public static final int L_INDEX = 4;

    /**
     * Validates if the parameters are swapped.
     *
     * @param userInputs An arraylist of type string of the user input.
     * @throws SwappedParameterException If letter does not match up with the required prefix.
     */
    protected void validateSwappedParameters(String[] userInputs) throws SwappedParameterException {
        boolean hasN = userInputs[N_INDEX].substring(BEGIN_INDEX, END_INDEX).contentEquals(N_PREFIX);
        boolean hasS = userInputs[S_INDEX].substring(BEGIN_INDEX, END_INDEX).contentEquals(S_PREFIX);
        boolean hasE = userInputs[E_INDEX].substring(BEGIN_INDEX, END_INDEX).contentEquals(E_PREFIX);
        boolean hasL = true;

        if (this instanceof EventTuitionManager) {
            hasL = userInputs[L_INDEX].substring(BEGIN_INDEX, END_INDEX).contentEquals(L_PREFIX);
        }

        if (!hasN || !hasS || !hasE || !hasL) {
            throw new SwappedParameterException();
        }
    }
}
