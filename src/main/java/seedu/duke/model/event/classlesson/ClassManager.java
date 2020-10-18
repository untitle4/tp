package seedu.duke.model.event.classlesson;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.duke.Event;
import seedu.duke.common.LogManager;
import seedu.duke.common.Messages;
import seedu.duke.exception.InvalidClassInputException;
import seedu.duke.controller.parser.DateTimeParser;
import seedu.duke.model.event.EventDataManager;
import seedu.duke.ui.UserInterface;

/**
 * <h2>ClassManager class</h2>
 * Stores user's classes in an ArrayList of Event class, named classes.
 * <br><br>
 * Contains methods which allow:
 * <ul>
 *     <li>Attaining class list size</li>
 *     <li>Adding new classes into ArrayList</li>
 *     <li>Deleting classes from ArrayList</li>
 *     <li>Setting class as {@code DONE}</li>
 * </ul>
 *
 * @see ClassManager#getClassListSize()
 * @see ClassManager#add(String)
 * @see ClassManager#delete(String[])
 * @see ClassManager#getClassStatement()
 * @see ClassManager#setDone(String[])
 */
public class ClassManager extends EventDataManager {
    // Initialising ArrayList to store classes
    private final ArrayList<Event> classes;
    private UserInterface userInterface;

    // Initialising Logger with name "Class"
    private static final Logger logger = LogManager.getLoggerInstance().getLogger();

    public ClassManager(ArrayList<Event> classes) {
        this.classes = classes;
        userInterface = UserInterface.getInstance();
    }

    public ArrayList<Event> getClasses() {
        return classes;
    }

    /**
     * <h2>getClassListSize()</h2>
     * Attains the size of the user's classes ArrayList.
     * @return int - Classes ArrayList size
     */
    public int getClassListSize() {
        // Assertion to test assumption that ArrayList is not null
        assert classes != null : "classes ArrayList should not be null";
        return classes.size();
    }

    /**
     * <h2>addClass()</h2>
     * Adds new class to classes ArrayList.
     * @param userInput To take in the String consisting of the class name, start date-time and end date-time.
     * @exception InvalidClassInputException if user input does not meet the requirements.
     * @see ClassManager#getClassStatement()
     */
    @Override
    public void add(String userInput) throws InvalidClassInputException {
        logger.log(Level.INFO, "initialising adding of a class");

        // Checks if user input contains the 3 required parameters (/n, /s and /e)
        if ((!userInput.contains("/n")) || (!userInput.contains("/s")) || (!userInput.contains("/e"))) {
            logger.log(Level.WARNING, "either class description, start date-time or end date-time parameter is"
                    + " missing");
            throw new InvalidClassInputException();
        }

        // Splitting /n, /s and /e info. via a String array called classDetails
        final String[] classDetails = userInput.trim().split("\\/");

        logger.log(Level.INFO, "splitting the user input into class description, start date-time and end "
                + "date-time");
        String classDescription = classDetails[1].substring(1).trim().replaceAll("\\s+"," ");
        String classStartDate = classDetails[2].substring(1).trim();
        String classEndDate = classDetails[3].substring(1).trim();

        // Checking if any of the 3 required parameters are empty
        if (classDescription.equals("") || classStartDate.equals("") || classEndDate.equals("")) {
            logger.log(Level.WARNING, "either class description, start date-time or end date-time is"
                    + " missing");
            throw new InvalidClassInputException();
        }

        // Checking and converting user's date-time input into format of expected output
        try {
            LocalDateTime.parse(classStartDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            LocalDateTime.parse(classEndDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            classes.add(new Class(classDescription, classStartDate, classEndDate));
            logger.log(Level.INFO, "adding the new class to the ArrayList");
        } catch (DateTimeParseException e) {
            userInterface.showToUser(Messages.MESSAGE_INVALID_DATE);
            return;
        }

        userInterface.showToUser(Messages.MESSAGE_CLASS_ADD_SUCCESS,
                classes.get(getClassListSize() - 1).toString());
        getClassStatement();
    }

    /**
     * <h2>deleteClass()</h2>
     * Deletes a class from the classes ArrayList.
     * @param userInputs To take in the class index of the class to be deleted.
     */
    @Override
    public void delete(String[] userInputs) {
        try {
            // Tries to convert classIndex user input into an integer
            int classIndex = Integer.parseInt(userInputs[2]);

            // Assertion to test assumption that classIndex should be a positive integer
            assert classIndex > 0 : "classIndex should be a positive integer";

            // Just to test if class index is valid - for exception use only
            classes.get(classIndex - 1);

            userInterface.showToUser(Messages.MESSAGE_CLASS_DELETE_SUCCESS,
                    classes.get(classIndex - 1).toString());

            // Deletes class from classes ArrayList
            classes.remove(classIndex - 1);
            logger.log(Level.INFO, "deletion of class from ArrayList");
            getClassStatement();
        } catch (ArrayIndexOutOfBoundsException e) {
            userInterface.showToUser(Messages.MESSAGE_CLASS_DELETE_ERROR_NO_NUMBER_GIVEN);
            logger.log(Level.WARNING, "absence of class index for deletion");
        } catch (IndexOutOfBoundsException e) {
            userInterface.showToUser(Messages.MESSAGE_INVALID_CLASS_INDEX);
            logger.log(Level.WARNING, "invalid class index entered for deletion");
        } catch (NumberFormatException e) {
            userInterface.showToUser(Messages.MESSAGE_CLASS_DELETE_ERROR_NON_NUMBER);
            logger.log(Level.WARNING, "non-integer class index entered for deletion");
        }
    }

    /**
     * <h2>getClassStatement()</h2>
     * Prints statement to update the user once class has been added or deleted.
     */
    private void getClassStatement() {
        String classStatement = getClassListSize() == 1 ? " class" : " classes";
        userInterface.showToUser("Now you have " + getClassListSize() + classStatement + " in the list.");
    }

    /**
     * <h2>setClassDone()</h2>
     * Sets class as done.
     * @param userInputs To take in the class index of the class to be set as done.
     * @exception IndexOutOfBoundsException when user input is an invalid class index integer.
     * @see ClassManager#getClassStatement()
     */
    @Override
    public void setDone(String[] userInputs) throws IndexOutOfBoundsException {
        int classNumber;
        logger.log(Level.INFO, "initialising setting class as done");

        try {
            // Trying to convert user's input into an integer
            classNumber = Integer.parseInt(userInputs[2]);
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "wrong number format entered");
            userInterface.showToUser(Messages.MESSAGE_CLASS_DONE_ERROR_NON_NUMBER);
            return;
        } catch (ArrayIndexOutOfBoundsException e) {
            userInterface.showToUser(Messages.MESSAGE_CLASS_DONE_ERROR_NO_NUMBER_GIVEN);
            return;
        }

        // Checking if user's input is a valid class index integer
        if ((classNumber <= 0) || (classNumber > getClassListSize())) {
            logger.log(Level.WARNING, "index entered is out of bounds");
            throw new IndexOutOfBoundsException();
        }

        // Sets class as done
        classes.get(classNumber - 1).setDone();
        logger.log(Level.INFO, "set class as done from ArrayList");

        userInterface.showToUser(Messages.MESSAGE_CLASS_DONE_SUCCESS,
                "  " + classes.get(classNumber - 1));

        getClassStatement();
    }
}

