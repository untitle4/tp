package seedu.duke.contact;

import seedu.duke.exception.ContactEmptyStringException;
import seedu.duke.exception.ContactParamException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContactManager {
    private ArrayList<Contact> contacts = new ArrayList<>();
    private static final Logger logger = Logger.getLogger("CONTACT");

    public ContactManager() {

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
            System.out.println("Subject not found");
            throw new ContactParamException();
        } else if (!userInput.contains("/n")) {
            System.out.println("Name not found");
            throw new ContactParamException();
        } else if (!userInput.contains("/p")) {
            System.out.println("Phone number not found");
            throw new ContactParamException();
        } else if (!userInput.contains("/e")) {
            System.out.println("Email address not found");
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

        System.out.println("Got it. I've added this contact: ");
        System.out.println(contacts.get(getContactListSize() - 1));
        getContactStatement();
    }

    public void deleteContact(String[] userInput) throws IndexOutOfBoundsException {
        int contactIndex = 0;

        try {
            contactIndex = Integer.parseInt(userInput[2]);
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! Please indicate in NUMERALS, which cca you'd like to delete!");
            return;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Please indicate which contact you'd like to delete!");
            return;
        }

        if ((contactIndex <= 0) || (contactIndex > getContactListSize())) {
            throw new IndexOutOfBoundsException();
        }

        System.out.println("Noted. I've removed this contact: ");
        System.out.println(contacts.get(contactIndex - 1));

        contacts.remove(contactIndex - 1);
        getContactStatement();
    }

    public void listContacts() {
        if (contacts.size() == 0) {
            System.out.println("Contact list is empty. Add some!!");
        } else {
            for (int i = 0; i < getContactListSize(); i++) {
                System.out.println("Contact " + (i + 1) + ":");
                System.out.println(contacts.get(i));
            }
        }
    }

    private void getContactStatement() {
        String contactStatement = getContactListSize() <= 1 ? " contact" : " contacts";
        System.out.println("Now you have " + getContactListSize() + contactStatement + " in your list.");
    }
}
