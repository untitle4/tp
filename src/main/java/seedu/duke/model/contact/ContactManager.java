package seedu.duke.model.contact;

import seedu.duke.common.LogManager;
import seedu.duke.common.Messages;
import seedu.duke.exception.EmptyParameterException;
import seedu.duke.exception.MissingParameterException;
import seedu.duke.model.ModelManager;
import seedu.duke.ui.UserInterface;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContactManager extends ModelManager implements ContactInteractable {
    private static final int USER_INPUT_OFFSET = 12;
    private static final int EMPTY_SIZE = 0;
    private static final String INPUT_SPACE = " ";
    private final ArrayList<Contact> contacts;
    private static final Logger logger = LogManager.getLogManagerInstance().getLogger();
    private final UserInterface userInterface;

    public ContactManager(ArrayList<Contact> contacts) {
        userInterface = UserInterface.getInstance();
        this.contacts = contacts;
    }

    public int getContactListSize() {
        return contacts.size();
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    //@@author untitle4
    /**
     * Adds a contact with provided details.
     * @param userInput The input entered by the user.
     * @throws EmptyParameterException if no parameter are provided for each section.
     * @throws MissingParameterException if sections symbols are missing.
     */
    @Override
    public void add(String userInput) throws EmptyParameterException, MissingParameterException {
        if (!userInput.contains("/s") || !userInput.contains("/n")
                || !userInput.contains("/p") || !userInput.contains("/e")) {
            throw new MissingParameterException("'/s', '/n', '/p' and '/e'");
        }

        String[] seperatedInputs = userInput.trim().split("/");

        logger.log(Level.INFO, "splitting user input into subject, name, phone number"
                + "and email address.");
        String subject = seperatedInputs[1].substring(1).trim();
        String name = seperatedInputs[2].substring(1).trim();
        String phoneNumber = seperatedInputs[3].substring(1).trim();
        String emailAddress = seperatedInputs[4].substring(1).trim();

        if (subject.equals("") || name.equals("")
                || phoneNumber.equals("") || emailAddress.equals("")) {
            logger.log(Level.WARNING, "subject/name/phone number/email address is empty");
            throw new EmptyParameterException();
        }

        contacts.add(new Contact(subject, name, phoneNumber, emailAddress));

        userInterface.showToUser(Messages.MESSAGE_CONTACT_ADD_SUCCESS,
                contacts.get(getContactListSize() - 1).toString());
        getContactStatement();
    }

    //@@author untitle4
    /**
     * Delete a contact indicated by the user input.
     * @param userInput the input provided by the user.
     * @throws IndexOutOfBoundsException if there is not such a contact in the list.
     */
    @Override
    public void delete(String[] userInput) throws IndexOutOfBoundsException {
        int contactIndex = 0;

        try {
            contactIndex = Integer.parseInt(userInput[2]);
        } catch (NumberFormatException e) {
            userInterface.showToUser(Messages.MESSAGE_CONTACT_DELETE_ERROR_NON_NUMBER);
            return;
        } catch (IndexOutOfBoundsException e) {
            userInterface.showToUser(Messages.MESSAGE_CONTACT_DELETE_ERROR_NO_NUMBER_GIVEN);
            return;
        }

        if ((contactIndex <= 0) || (contactIndex > getContactListSize())) {
            throw new IndexOutOfBoundsException();
        }

        userInterface.showToUser(Messages.MESSAGE_CONTACT_DELETE_SUCCESS,
                contacts.get(contactIndex - 1).toString());

        contacts.remove(contactIndex - 1);
        getContactStatement();
    }

    //@@author untitle4
    /**
     * Provide a list of contacts for the user.
     */
    @Override
    public void list() {
        if (contacts.size() == 0) {
            userInterface.showToUser(Messages.MESSAGE_EMPTY_CONTACT_LIST);
        } else {
            for (int i = 0; i < getContactListSize(); i++) {
                userInterface.showToUser("Contact " + (i + 1) + ":",
                        contacts.get(i).toString());
            }
        }
    }

    //@@author AndreWongZH
    /**
     * Prints to user all the found events that matches with keyword provided.
     *
     * @param userInput The input entered by the user.
     * @throws MissingParameterException If input supplied does not contain any keywords.
     */
    @Override
    public void find(String userInput) throws MissingParameterException {
        String param = userInput.substring(USER_INPUT_OFFSET).trim();

        if (param.length() == EMPTY_SIZE) {
            throw new MissingParameterException("keywords as");
        }

        ArrayList<String> filteredContacts = filterContacts(userInput);

        if (filteredContacts.size() == EMPTY_SIZE) {
            userInterface.showToUser(Messages.MESSAGE_NO_EVENTS_FOUND);
            return;
        }

        userInterface.printArray(filteredContacts);
    }

    //@@author AndreWongZH
    /**
     * Searches for a match in the contacts list against the keyword.
     *
     * @param userInput The input entered by the user.
     * @return An ArrayList of contacts after filtering.
     */
    private ArrayList<String> filterContacts(String userInput) {
        ArrayList<String> filteredContacts = new ArrayList<>();
        String[] separatedInputs = userInput.split(INPUT_SPACE);

        for (Contact contact : contacts) {
            for (String keyword: separatedInputs) {
                keyword = keyword.toLowerCase();
                boolean matchName = contact.getName().toLowerCase().contains(keyword);
                boolean matchEmail = contact.getEmail().toLowerCase().contains(keyword);
                boolean matchSubject = contact.getSubject().toLowerCase().contains(keyword);
                boolean matchPhoneNumber = contact.getPhoneNumber().toLowerCase().contains(keyword);
                if (matchName || matchEmail || matchSubject || matchPhoneNumber) {
                    filteredContacts.add(contact.toString());
                    break;
                }
            }
        }

        return filteredContacts;
    }

    //@@author untitle4
    /**
     * A simple method to show contact(s) in the text box.
     */
    private void getContactStatement() {
        String contactStatement = getContactListSize() <= 1 ? " contact" : " contacts";
        userInterface.showToUser("Now you have " + getContactListSize() + contactStatement + " in your list.");
    }
}
