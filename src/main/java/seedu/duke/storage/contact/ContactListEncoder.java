package seedu.duke.storage.contact;

import seedu.duke.model.contact.Contact;

import java.util.ArrayList;

//@@author AndreWongZH
/**
 * Encodes the String form of contacts in the Arraylist and store them in a text file.
 */
public class ContactListEncoder {
    /**
     * Converts contact type into its data storage representation for all contacts.
     *
     * @param contactList An array list of type contact to be encoded.
     * @return An array list of string of contacts to be written to text file storage.
     */
    public ArrayList<String> encodeContactList(ArrayList<Contact> contactList) {
        ArrayList<String> encodedContacts = new ArrayList<>();

        for (Contact contact: contactList) {
            encodedContacts.add(contact.convertToData());
        }

        return encodedContacts;
    }
}
