package seedu.duke.model.contact;

//@@author untitle4
/**
 * <h2> Class class </h2>
 * Contains details of contact.
 */
public class Contact {
    private final String subject;
    private final String name;
    private final String phoneNumber;
    private final String email;

    public Contact(String subject, String name, String phoneNumber, String email) {
        this.subject = subject;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return subject + " teacher: " + name
                + "\nPhone number: " + phoneNumber
                + "\nemail address: " + email + "\n";
    }

    public String convertToData() {
        return subject + "|" + name + "|" + phoneNumber + "|" + email;
    }
}
