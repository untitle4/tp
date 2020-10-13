package seedu.duke;

import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.logging.Level;

import seedu.duke.exception.InvalidClassInputException;
import seedu.duke.exception.TestEmptyStringException;
import seedu.duke.exception.TestParamException;

public class ClassManager {
    private final ArrayList<Event> classes;

    private static String classDescription;
    private static String classStartDate;
    private static String classEndDate;

    public ClassManager(ArrayList<Event> inputList) {
        classes = inputList;
    }

    public ArrayList<Event> getClasses() {
        return classes;
    }

    private int getClassListSize() {
        return classes.size();
    }

    public void addClass(String userInput) throws InvalidClassInputException {

        if ((!userInput.contains("/n")) || (!userInput.contains("/s")) || (!userInput.contains("/e"))) {
            throw new InvalidClassInputException();
        }

        userInput.replaceAll("\\s+","");
        final String[] classDetails = userInput.trim().split("\\/");

        String classDescription = classDetails[1].substring(1).trim();
        String classStartDate = classDetails[2].substring(1).trim();
        String classEndDate = classDetails[3].substring(1).trim();

        if (classDescription.equals("") || classStartDate.equals("") || classEndDate.equals("")) {
            throw new InvalidClassInputException();
        }

        classes.add(new Test(classDescription, classStartDate, classEndDate));

        System.out.println("Got it. I've added this class:");
        System.out.println("  " + classes.get(getClassListSize() - 1));
        getClassStatement();
    }

    public void deleteClass(String[] userInput) {
        try {
            int classIndex = Integer.parseInt(userInput[2]);

            // Just to test if class index is valid - for exception use only
            classes.get(classIndex-1);

            System.out.println("Noted. I've removed this class: ");
            System.out.println(classes.get(classIndex - 1));

            classes.remove(classIndex - 1);
            getClassStatement();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("OOPS! Please indicate which class you'd like to delete");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS! Please indicate a valid class index!");
        } catch (NumberFormatException e) {
            System.out.println("OOPS! Please indicate in NUMERALS, which class you'd like to delete!");
        }
    }

    private void getClassStatement() {
        String classStatement = getClassListSize() == 1 ? " class" : " classes";
        System.out.println("Now you have " + getClassListSize() + classStatement + " in the list.");
    }
}

