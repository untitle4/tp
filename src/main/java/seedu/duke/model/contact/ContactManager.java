package seedu.duke.model.contact;

import seedu.duke.common.LogManager;
import seedu.duke.common.Messages;
import seedu.duke.ui.UserInterface;
import seedu.duke.exception.ContactEmptyStringException;
import seedu.duke.exception.ContactParamException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContactManager {
    private ArrayList<Contact> contacts = new ArrayList<>();
    private static final Logger logger = LogManager.getLogger();
    private UserInterface userInterface;

    public ContactManager() {
        userInterface = UserInterface.getInstance();
    }

    public ArrayList<Contact> getContactList() {
        return contacts;
    }

    public int getContactListSize() {
        assert contacts != null;
        return contacts.size();
    }

    public void addContact(String userInput) throws ContactEmptyStringException, ContactParamException {
        if (!userInput.contains("/s")) {
            userInterface.showToUser(Messages.MESSAGE_SUBJECT_NOT_FOUND);
            throw new ContactParamException();
        } else if (!userInput.contains("/n")) {
            userInterface.showToUser(Messages.MESSAGE_NAME_NOT_FOUND);
            throw new ContactParamException();
        } else if (!userInput.contains("/p")) {
            userInterface.showToUser(Messages.MESSAGE_PHONE_NUMBER_NOT_FOUND);
            throw new ContactParamException();
        } else if (!userInput.contains("/e")) {
            userInterface.showToUser(Messages.MESSAGE_EMAIL_ADDRESS_NOT_FOUND);
            throw new ContactParamException();
        }

        String[] seperatedInputs = userInput.trim().split("\\/");

        logger.log(Level.INFO, "splitting user input into subject, name, phone number"
                + "and email address.");
        String subject = seperatedInputs[1].substring(1).trim();
        String name = seperatedInputs[2].substring(1).trim();
        String phoneNumber = seperatedInputs[3].substring(1).trim();
        String emailAddress = seperatedInputs[4].substring(1).trim();

        if (subject.equals("") || name.equals("")
                || phoneNumber.equals("") || emailAddress.equals("")) {
            logger.log(Level.WARNING, "subject/name/phone number/email address is empty");
            throw new ContactEmptyStringException();
        }

        contacts.add(new Contact(subject, name, phoneNumber, emailAddress));

        userInterface.showToUser(Messages.MESSAGE_CONTACT_ADD_SUCCESS,
                contacts.get(getContactListSize() - 1).toString());
        getContactStatement();
    }

    public void deleteContact(String[] userInput) throws IndexOutOfBoundsException {
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

    public void listContacts() {
        if (contacts.size() == 0) {
            userInterface.showToUser(Messages.MESSAGE_EMPTY_CONTACT_LIST);
        } else {
            for (int i = 0; i < getContactListSize(); i++) {
                userInterface.showToUser("Contact " + (i + 1) + ":",
                        contacts.get(i).toString());
            }
        }
    }

    private void getContactStatement() {
        String contactStatement = getContactListSize() <= 1 ? " contact" : " contacts";
        userInterface.showToUser("Now you have " + getContactListSize() + contactStatement + " in your list.");
    }
}
