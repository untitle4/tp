package seedu.duke.controller;

import org.junit.jupiter.api.Test;
import seedu.duke.controller.parser.CommandType;
import seedu.duke.model.ConfigParameter;
import seedu.duke.model.Model;
import seedu.duke.model.contact.ContactManager;
import seedu.duke.model.event.EventManager;
import seedu.duke.model.event.EventParameter;
import seedu.duke.model.quiz.QuizManager;
import seedu.duke.storage.EventStorageManager;
import seedu.duke.storage.QuizStorageManager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

//@@author AndreWongZH
class ControlManagerTest {
    @Test
    void runLogic_listEventInput_expectListCommand() {
        ControlManager controlManager = initializeControlManager("list event");
        CommandType actualCommandType = controlManager.runLogic();
        assertEquals(CommandType.LIST, actualCommandType);
    }

    @Test
    void runLogic_byeInput_expectByeCommand() {
        ControlManager controlManager = initializeControlManager("bye");
        CommandType actualCommandType = controlManager.runLogic();
        assertEquals(CommandType.BYE, actualCommandType);
    }

    @Test
    void runLogic_invalidInput_expectNull() {
        ControlManager controlManager = initializeControlManager("invalid command");
        CommandType actualCommandType = controlManager.runLogic();
        assertNull(actualCommandType);
    }

    private ControlManager initializeControlManager(String userInput) {
        Model model = new Model(new EventManager(new EventParameter(), new ConfigParameter()),
                new ContactManager(), new QuizManager(new ArrayList<>()));
        EventStorageManager eventStorageManager = new EventStorageManager("events.txt");
        QuizStorageManager quizStorageManager =  new QuizStorageManager("quiz.txt");
        return new ControlManager(userInput, model,
                eventStorageManager, quizStorageManager);
    }
}