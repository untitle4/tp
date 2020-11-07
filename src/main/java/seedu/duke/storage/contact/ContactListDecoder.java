package seedu.duke.storage.contact;

import seedu.duke.model.contact.Contact;

import java.util.ArrayList;

//@@author AndreWongZH
/**
 * Decodes the content in the contact text file and add them in an Arraylist as contacts
 * to initialize the contact list.
 */
public class ContactListDecoder {
    public static final int INDEX_SUBJECT = 0;
    public static final int INDEX_NAME = 1;
    public static final int INDEX_PHONE_NUM = 2;
    public static final int INDEX_EMAIL = 3;

    /**
     * Reads and extracts out the information from quiz storage.
     * Checks is any quizzes that have not been attempted since the last 2 days, notify user if so.
     *
     * @param encodedContactList An array list of contacts in string.
     * @return An arraylist of type contact stored in the text file.
     */
    public ArrayList<Contact> decodeContactList(ArrayList<String> encodedContactList) {
        final ArrayList<Contact> decodedContacts = new ArrayList<>();
        for (String encodedContact : encodedContactList) {
            decodedContacts.add(decodeContactFromString(encodedContact));
        }

        return decodedContacts;
    }

    /**
     * Extracts the relevant info from the storage string.
     *
     * @param encodedContact A string of input from storage.
     * @return A contact instance.
     */
    private Contact decodeContactFromString(String encodedContact) {
        final String[] data = encodedContact.trim().split("\\|");

        String subject = data[INDEX_SUBJECT];
        String name = data[INDEX_NAME];
        String phoneNumber = data[INDEX_PHONE_NUM];
        String email = data[INDEX_EMAIL];

        return new Contact(subject, name, phoneNumber, email);
    }
}
