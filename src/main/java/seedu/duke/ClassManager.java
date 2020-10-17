package seedu.duke;

import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.duke.exception.InvalidClassInputException;
import seedu.duke.parser.DateTimeParser;

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
 * @see ClassManager#addClass(String)
 * @see ClassManager#deleteClass(String[])
 * @see ClassManager#getClassStatement()
 * @see ClassManager#setClassDone(String[])
 */
public class ClassManager {
    // Initialising ArrayList to store classes
    private final ArrayList<Event> classes;

    // Initialising Logger with name "Class"
    private static final Logger logger = LogManager.getLogger();

    public ClassManager(ArrayList<Event> classes) {
        this.classes = classes;
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
    public void addClass(String userInput) throws InvalidClassInputException {
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
        String classDescription = classDetails[1].substring(2);
        String classStartDate = classDetails[2].substring(2);
        String classEndDate = classDetails[3].substring(2);

        // Checking if any of the 3 required parameters are empty
        if (classDescription.equals("") || classStartDate.equals("") || classEndDate.equals("")) {
            logger.log(Level.WARNING, "either class description, start date-time or end date-time is"
                    + " missing");
            throw new InvalidClassInputException();
        }

        // Checking and converting user's date-time input into format of expected output
        try {
            String changedClassStartDate = new DateTimeParser(classStartDate).changeDateTime();
            String changedClassEndDate = new DateTimeParser(classEndDate).changeDateTime();

            classes.add(new Class(classDescription, changedClassStartDate, changedClassEndDate));
            logger.log(Level.INFO, "adding the new class to the ArrayList");
        } catch (DateTimeParseException | StringIndexOutOfBoundsException
                | ArrayIndexOutOfBoundsException | ParseException e) {
            System.out.println("☹ OOPS!!! Please enter valid date and time in format yyyy-mm-dd HHMM!");
            return;
        }

        System.out.println("Got it. I've added this class: ");
        System.out.println(classes.get(getClassListSize() - 1));
        getClassStatement();
    }

    /**
     * <h2>deleteClass()</h2>
     * Deletes a class from the classes ArrayList.
     * @param userInput To take in the class index of the class to be deleted.
     */
    public void deleteClass(String[] userInput) {
        try {
            // Tries to convert classIndex user input into an integer
            int classIndex = Integer.parseInt(userInput[2]);

            // Assertion to test assumption that classIndex should be a positive integer
            assert classIndex > 0 : "classIndex should be a positive integer";

            // Just to test if class index is valid - for exception use only
            classes.get(classIndex - 1);

            System.out.println("Noted. I've removed this class: ");
            System.out.println(classes.get(classIndex - 1));

            // Deletes class from classes ArrayList
            classes.remove(classIndex - 1);
            logger.log(Level.INFO, "deletion of class from ArrayList");
            getClassStatement();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS! Please indicate which class you'd like to delete");
            logger.log(Level.WARNING, "absence of class index for deletion");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS! Please indicate a valid class index!");
            logger.log(Level.WARNING, "invalid class index entered for deletion");
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS! Please indicate in NUMERALS, which class you'd like to delete!");
            logger.log(Level.WARNING, "non-integer class index entered for deletion");
        }
    }

    /**
     * <h2>getClassStatement()</h2>
     * Prints statement to update the user once class has been added or deleted.
     */
    private void getClassStatement() {
        String classStatement = getClassListSize() == 1 ? " class" : " classes";
        System.out.println("Now you have " + getClassListSize() + classStatement + " in the list.");
    }

    /**
     * <h2>setClassDone()</h2>
     * Sets class as done.
     * @param userInput To take in the class index of the class to be set as done.
     * @exception IndexOutOfBoundsException when user input is an invalid class index integer.
     * @see ClassManager#getClassStatement()
     */
    public void setClassDone(String[] userInput) throws IndexOutOfBoundsException {
        int classNumber;
        logger.log(Level.INFO, "initialising setting class as done");

        try {
            // Trying to convert user's input into an integer
            classNumber = Integer.parseInt(userInput[2]);
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "wrong number format entered");
            System.out.println("☹ OOPS!!! Please indicate in NUMERALS, which class you'd like to set as Done!");
            return;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Please indicate which class you'd like to set as Done!");
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

        System.out.println("Nice! I've marked this class as done:");
        System.out.println("  " + classes.get(classNumber - 1));

        getClassStatement();
    }
}

