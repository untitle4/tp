package seedu.duke.storage.contact;

import seedu.duke.model.contact.Contact;

import java.util.ArrayList;

//@@author AndreWongZH
/**
 * To encode the String form of quizzes in the Arraylist and store them in a text file.
 */
public class ContactListEncoder {

    public ArrayList<String> encodeContactList(ArrayList<Contact> contactList) {
        ArrayList<String> encodedContacts = new ArrayList<>();

        for (Contact contact: contactList) {
            encodedContacts.add(contact.convertToData());
        }

        return encodedContacts;
    }
}
