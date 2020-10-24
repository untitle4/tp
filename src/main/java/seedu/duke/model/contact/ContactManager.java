package seedu.duke.model.contact;

import seedu.duke.common.LogManager;
import seedu.duke.common.Messages;
import seedu.duke.exception.EmptyParameterException;
import seedu.duke.exception.MissingParameterException;
import seedu.duke.model.ModelManager;
import seedu.duke.ui.UserInterface;
import seedu.duke.exception.ContactParamException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContactManager extends ModelManager implements ContactInteractable {
    private final ArrayList<Contact> contacts = new ArrayList<>();
    private static final Logger logger = LogManager.getLogManagerInstance().getLogger();
    private final UserInterface userInterface;

    public ContactManager() {
        userInterface = UserInterface.getInstance();
    }

    public int getContactListSize() {
        return contacts.size();
    }

    @Override
    public void add(String userInput) throws ContactParamException, EmptyParameterException {
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

    @Override
    public void find(String userInput) throws MissingParameterException {

    }

    private void getContactStatement() {
        String contactStatement = getContactListSize() <= 1 ? " contact" : " contacts";
        userInterface.showToUser("Now you have " + getContactListSize() + contactStatement + " in your list.");
    }
}
