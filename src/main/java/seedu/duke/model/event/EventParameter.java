package seedu.duke.model.event;

import java.util.ArrayList;

public class EventParameter {
    private final ArrayList<Event> ccas;
    private final ArrayList<Event> tests;
    private final ArrayList<Event> classes;
    private final ArrayList<Event> tuitions;

    public EventParameter() {
        ccas = new ArrayList<>();
        tests = new ArrayList<>();
        classes = new ArrayList<>();
        tuitions = new ArrayList<>();
    }

    public EventParameter(ArrayList<Event> ccas, ArrayList<Event> tests,
                          ArrayList<Event> classes, ArrayList<Event> tuitions) {
        this.ccas = ccas;
        this.tests = tests;
        this.classes = classes;
        this.tuitions = tuitions;
    }

    //@@author Aliciaho
    public ArrayList<Event> getEventMasterList() {
        ArrayList<Event> ccas = getCcas();
        ArrayList<Event> tests = getTests();
        ArrayList<Event> classes = getClasses();
        ArrayList<Event> tuitions = getTuitions();

        ArrayList<Event> masterList = new ArrayList<>(ccas);
        masterList.addAll(tests);
        masterList.addAll(classes);
        masterList.addAll(tuitions);

        return masterList;
    }

    public ArrayList<Event> getCcas() {
        return ccas;
    }

    public ArrayList<Event> getTests() {
        return tests;
    }

    public ArrayList<Event> getClasses() {
        return classes;
    }

    public ArrayList<Event> getTuitions() {
        return tuitions;
    }
}
