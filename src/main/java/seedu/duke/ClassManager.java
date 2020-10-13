package seedu.duke;

import seedu.duke.exception.InvalidClassInputException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Level;
import java.util.logging.Logger;
import seedu.duke.exception.InvalidClassInputException;

public class ClassManager {
    private final ArrayList<Event> classes;
    private static final Logger logger = Logger.getLogger("Class");

    public ClassManager(ArrayList<Event> inputList) {
        classes = inputList;
    }

    public ArrayList<Event> getClasses() {
        return classes;
    }

    public int getClassListSize() {
        assert classes != null : "classes ArrayList should not be null";
        return classes.size();
    }

    public void addClass(String userInput) throws InvalidClassInputException {
        logger.log(Level.INFO, "initialising adding of a class");
        if ((!userInput.contains("/n")) || (!userInput.contains("/s")) || (!userInput.contains("/e"))) {
            logger.log(Level.WARNING, "either class description, start date-time or end date-time parameter is"
                    + " missing");
            throw new InvalidClassInputException();
        }

        userInput.replaceAll("\\s+", "");
        final String[] classDetails = userInput.trim().split("\\/");

        logger.log(Level.INFO, "splitting the user input into class description, start date-time and end "
                + "date-time");
        String classDescription = classDetails[1].substring(2);
        String classStartDate = classDetails[2].substring(2);
        String classEndDate = classDetails[3].substring(2);

        if (classDescription.equals("") || classStartDate.equals("") || classEndDate.equals("")) {
            logger.log(Level.WARNING, "either class description, start date-time or end date-time is"
                    + " missing");
            throw new InvalidClassInputException();
        }

        classes.add(new Class(classDescription, classStartDate, classEndDate));
        logger.log(Level.INFO, "adding the new class to the ArrayList");

        System.out.println("Got it. I've added this class: ");
        System.out.println(classes.get(getClassListSize() - 1));
        getClassStatement();
    }

    private static int classIndex;
    public void deleteClass(String[] userInput) {
        try {
            classIndex = Integer.parseInt(userInput[2]);
            assert classIndex > 0 : "classIndex should be a positive integer";

        // Just to test if class index is valid - for exception use only
        classes.get(classIndex - 1);

        System.out.println("Noted. I've removed this class: ");
        System.out.println(classes.get(classIndex - 1));

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

    private void getClassStatement() {
        String classStatement = getClassListSize() == 1 ? " class" : " classes";
        System.out.println("Now you have " + getClassListSize() + classStatement + " in the list.");
    }
}

