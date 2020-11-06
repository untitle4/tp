package seedu.duke.storage.contact;

import seedu.duke.model.contact.Contact;

import java.util.ArrayList;

//@@author AndreWongZH
/**
 * To decode the content in the quizzes text file and add them in an Arraylist as quizzes
 * to initialize the quiz list.
 */
public class ContactListDecoder {

    //@@author AndreWongZH
    public ArrayList<Contact> decodeContactList(ArrayList<String> encodedContactList) {
        final ArrayList<Contact> decodedContacts = new ArrayList<>();
        for (String encodedContact : encodedContactList) {
            decodedContacts.add(decodeContactFromString(encodedContact));
        }

        return decodedContacts;
    }

    private Contact decodeContactFromString(String encodedContact) {
        final String[] data = encodedContact.trim().split("\\|");

        String subject = data[0];
        String name = data[1];
        String phoneNumber = data[2];
        String email = data[3];

        return new Contact(subject, name, phoneNumber, email);
    }
}
