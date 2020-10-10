package seedu.duke;

import java.util.ArrayList;

public class ClassManager {
    private ArrayList<Event> classes = new ArrayList<>();

    public ClassManager(ArrayList<Event> inputList) {
        classes = inputList;
    }

    public ArrayList<Event> getClasses() {
        return classes;
    }

    private int getClassListSize() {
        return classes.size();
    }

    public void addClass(String userInput) {
        final String[] classDetails = userInput.trim().split("\\/");

        String classDescription = classDetails[1].substring(2);
        String classStartDate = classDetails[2].substring(2);
        String classEndDate = classDetails[3].substring(2);

        classes.add(new Class(classDescription, classStartDate, classEndDate));

        System.out.println("Got it. I've added this class: ");
        System.out.println(classes.get(getClassListSize() - 1));
        getClassStatement();
    }

    public void deleteClass(String[] userInput) {
        int classIndex = Integer.parseInt(userInput[2]);

        System.out.println("Noted. I've removed this class: ");
        System.out.println(classes.get(classIndex - 1));

        classes.remove(classIndex - 1);
        getClassStatement();
    }

    private void getClassStatement() {
        String classStatement = getClassListSize() == 1 ? " class" : " classes";
        System.out.println("Now you have " + getClassListSize() + classStatement
                + " in the list.");
    }
}
