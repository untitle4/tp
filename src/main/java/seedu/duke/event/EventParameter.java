package seedu.duke.event;

import seedu.duke.Event;

import java.util.ArrayList;

public class EventParameter {
    private final ArrayList<Event> ccas;
    private final ArrayList<Event> tests;
    private final ArrayList<Event> classes;

    public EventParameter() {
        ccas = new ArrayList<>();
        tests = new ArrayList<>();
        classes = new ArrayList<>();
    }

    public EventParameter(ArrayList<Event> ccas, ArrayList<Event> tests, ArrayList<Event> classes) {
        this.ccas = ccas;
        this.tests = tests;
        this.classes = classes;
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
}
