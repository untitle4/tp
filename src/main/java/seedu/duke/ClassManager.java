package seedu.duke;

import java.io.InvalidClassException;
import java.util.ArrayList;
import seedu.duke.exception.InvalidClassInputException;
import seedu.duke.exception.TestParamException;

public class ClassManager {
    private final ArrayList<Event> classes;

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

        try {
            final String[] classDetails = userInput.trim().split("\\/");

            String classDescription = classDetails[1].substring(2);
            String classStartDate = classDetails[2].substring(2);
            String classEndDate = classDetails[3].substring(2);

            classes.add(new Class(classDescription, classStartDate, classEndDate));

            System.out.println("Got it. I've added this class: ");
            System.out.println(classes.get(getClassListSize() - 1));
            getClassStatement();
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidClassInputException();
        }
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

