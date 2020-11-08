package seedu.duke.controller;

import org.junit.jupiter.api.Test;
import seedu.duke.controller.parser.CommandType;
import seedu.duke.model.ConfigParameter;
import seedu.duke.model.Model;
import seedu.duke.model.contact.ContactManager;
import seedu.duke.model.event.EventManager;
import seedu.duke.model.event.EventParameter;
import seedu.duke.model.quiz.QuizManager;
import seedu.duke.storage.contact.ContactStorageManager;
import seedu.duke.storage.event.EventStorageManager;
import seedu.duke.storage.quiz.QuizStorageManager;
import seedu.duke.model.config.ConfigManager;

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
                new ContactManager(new ArrayList<>()), new QuizManager(new ArrayList<>()), ConfigManager.getInstance());
        EventStorageManager eventStorageManager = new EventStorageManager("events.txt");
        QuizStorageManager quizStorageManager =  new QuizStorageManager("quiz.txt");
        ContactStorageManager contactStorageManager =  new ContactStorageManager("contact.txt");
        return new ControlManager(userInput, model, eventStorageManager, quizStorageManager, contactStorageManager);
    }
}