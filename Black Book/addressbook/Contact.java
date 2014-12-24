package addressbook;
import java.io.Serializable;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Contact implements ContactUI, Comparable<Contact>, Serializable {

    private static final long serialVersionUID = 3827003769662523826L;
    static transient String VALID_EMAIL = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
    static transient String VALID_PHONE = "^[+]?[01]?[- .]?(\\([2-9]\\d{2}\\)|[2-9]\\d{2})[- .]?\\d{3}[- .]?\\d{4}$";
    private String address;
    private String city;
    private String email;
    private String first;
    private String last;
    private String notes;
    private String phone;
    private String state;
    private String zipcode;

    public Contact() {

        this(
                ContactUI.BLANK, ContactUI.BLANK, ContactUI.BLANK,
                ContactUI.BLANK, ContactUI.BLANK, ContactUI.BLANK,
                ContactUI.BLANK, ContactUI.BLANK);
    }

    /**
     * @param f
     * @param l
     * @param e
     * @param a
     * @param c
     * @param s
     * @param z
     * @param n
     */
    Contact(String f, String p, String e, String a, String c, String s,
            String z, String n) {

        this.first = f;
        this.phone = p;
        this.email = e;
        this.address = a;
        this.city = c;
        this.state = s;
        this.zipcode = z;
        this.notes = n;
    }

    /**
     * Compares current Contact object to parameter Contact object
     * alphabetically by Contact's last name, then comparing the first name,
     * adjust the order.
     *
     * @param o
     *            - the Contact to compare.
     * @return compResults - positive, negative,or equals (setting the natural
     *         sorting order for the Contact to Contact comparison (i.e.Last
     *         Name, then First Name alphabetical order)).
     */
    @Override
    public int compareTo(final Contact o) { /* Added by EH */

        int compResults = this.last.compareToIgnoreCase(o.getLast());
        if (compResults == 0) {
            compResults = this.first.compareToIgnoreCase(o.getFirst());
        }
        return compResults;
    }

    public String contactHeader() {

        String contactHeader = "";
        final int length = this.findContactHeaderLength();
        for (int i = 0; i < length; i++) {
            contactHeader += "=";
        }
        return contactHeader;
    }

    @Override
    public Contact create() {

        final Contact addContact = new Contact();
        this.promptFirst();
        addContact.first = this.readInput();
        addContact.last = this.requireLastName();
        if (addContact.last.equalsIgnoreCase(ContactUI.INVALID_NAME)) {
            return addContact;
        }
        this.promptPhone();
        final String tele = this.readInput();
        if (this.isValidPhone(tele)) {
            addContact.phone = this.formatPhoneNumber(tele);
        }
        this.promptEmail();
        final String handle = this.readInput();
        if (this.isValidEmail(handle)) {
            addContact.email = handle;
        }
        this.promptAddress();
        addContact.address = this.readInput();
        this.promptCity();
        addContact.city = this.readInput();
        this.promptState();
        addContact.state = this.readInput();
        this.promptZipcode();
        addContact.zipcode = this.readInput();
        this.promptNotes();
        addContact.notes = this.readInput();
        return addContact;
    }

    public int findContactHeaderLength() {

        final int contactHeaderLength = 2 + this.last.length()
                + this.first.length();
        return contactHeaderLength;
    }

    /**
     * Formats the Contact's Phone Number as entered and returns the String to
     * include: parentheses [()], hyphen [-], and the US Country code [+1].
     *
     * @param number
     *            - the Contact's phone number to be formatted.
     * @return number - a String representation of the Contact's Phone Number
     *         formatted to allow users to see what is normally seen as the
     *         standard US phone number.
     */
    public String formatPhoneNumber(String number) { /* Added by EH */

        final StringBuffer buff = new StringBuffer(number);
        String formattedNumber = "";
        if (number.length() == 11) {
            buff.insert(0, "+");
            buff.insert(2, " (");
            buff.insert(7, ") ");
            buff.insert(12, "-");
            return formattedNumber = buff.toString();
        }
        if (number.length() == 10) {
            buff.insert(0, "+1 (");
            buff.insert(7, ") ");
            buff.insert(12, "-");
            return formattedNumber = buff.toString();
        }
        if (number.length() == 7) {
            buff.insert(4, "-");
            return formattedNumber = buff.toString();
        }
        return formattedNumber;
    }

    /**
     * Get Method. Retrieves the String address of the current Contact object
     * and returns it to the calling method.
     *
     * @return the address
     */
    public String getAddress() {

        return this.address;
    }

    /**
     * Get Method. Retrieves the String city of the current Contact object and
     * returns it to the calling method.
     *
     * @return the city
     */
    public String getCity() {

        return this.city;
    }

    /**
     * Get Method. Retrieves the String email of the current Contact object and
     * returns it to the calling method.
     *
     * @return the email
     */
    public String getEmail() {

        return this.email;
    }

    /**
     * Get Method. Retrieves the String first of the current Contact object and
     * returns it to the calling method.
     *
     * @return the first
     */
    public String getFirst() {

        return this.first;
    }

    /**
     * Get Method. Retrieves the String last of the current Contact object and
     * returns it to the calling method.
     *
     * @return the last
     */
    public String getLast() {

        return this.last;
    }

    /**
     * Get Method. Retrieves the String notes of the current Contact object and
     * returns it to the calling method.
     *
     * @return the notes
     */
    public String getNotes() {

        return this.notes;
    }

    /**
     * Get Method. Retrieves the String phone of the current Contact object and
     * returns it to the calling method.
     *
     * @return the phone
     */
    public String getPhone() {

        return this.phone;
    }

    /**
     * Get Method. Retrieves the String state of the current Contact object and
     * returns it to the calling method.
     *
     * @return the state
     */
    public String getState() {

        return this.state;
    }

    /**
     * Get Method. Retrieves the String zipcode of the current Contact object
     * and returns it to the calling method.
     *
     * @return the zipcode
     */
    public String getZipcode() {

        return this.zipcode;
    }

    public boolean isValidEmail(String handle) {

        return Pattern.matches(Contact.VALID_EMAIL, handle);
    }

    public boolean isValidPhone(String phone) {

        return Pattern.matches(Contact.VALID_PHONE, phone);
    }

    /**
     * @see ContactUI#promptAddress()
     */
    @Override
    public void promptAddress() {

        System.out.println(ContactUI.PROMPT_ADDRESS);
    }

    /**
     * @see ContactUI#promptCity()
     */
    @Override
    public void promptCity() {

        System.out.println(ContactUI.PROMPT_CITY);
    }

    /**
     * @see ContactUI#promptEmail()
     */
    @Override
    public void promptEmail() {

        System.out.println(ContactUI.PROMPT_EMAIL);
    }

    /**
     * @see ContactUI#promptFirst()
     */
    @Override
    public void promptFirst() {

        System.out.println(ContactUI.PROMPT_FIRST);
    }

    /**
     * @see ContactUI#promptLast()
     */
    @Override
    public void promptLast() {

        System.out.println(ContactUI.PROMPT_LAST);
    }

    /**
     * @see ContactUI#promptNotes()
     */
    @Override
    public void promptNotes() {

        System.out.println(ContactUI.PROMPT_NOTES);
    }

    /**
     * @see ContactUI#promptPhone()
     */
    @Override
    public void promptPhone() {

        System.out.println(ContactUI.PROMPT_PHONE);
    }

    /**
     * @see ContactUI#promptState()
     */
    @Override
    public void promptState() {

        System.out.println(ContactUI.PROMPT_STATE);
    }

    /**
     * @see ContactUI#promptZipcode()
     */
    @Override
    public void promptZipcode() {

        System.out.println(ContactUI.PROMPT_ZIPCODE);
    }

    /**
     * Returns the input gathered from the user via standard Java Scanner after
     * prompted for last name.
     *
     * @return familyName - the Contact's last Name
     */
    @Override
    public String requireLastName() {

        String familyName = null;
        boolean noLastName = true;
        int attempt = -1;
        do {
            this.promptLast();
            familyName = this.readInput();
            if (!familyName.isEmpty()) {
                this.last = familyName;
                noLastName = false;
            }
            if (familyName.isEmpty()) {
                attempt++;
                System.err.println("Field \"Last Name\" is REQUIRED:");
                System.err
                .println((ContactUI.MAX_ATTEMPTS - attempt)
                        + " attempt(s) remaining until you will be redirected to the [MAIN MENU]");
                if (familyName.isEmpty()
                        && ((ContactUI.MAX_ATTEMPTS - attempt) == 0)) {
                    System.out
                    .println("Redirecting you to the [MAIN MENU]. . .");
                    familyName = ContactUI.INVALID_NAME;
                    noLastName = false;
                }
            }
        } while (noLastName);
        return familyName;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        final StringBuilder builder = new StringBuilder();
        builder.append(ContactUI.TB);
        builder.append(this.last);
        builder.append(", ");
        builder.append(this.first);
        builder.append(ContactUI.NL + ContactUI.TB);
        builder.append(this.contactHeader());
        builder.append(ContactUI.NL + ContactUI.NL + ContactUI.TB
                + ContactUI.TB);
        builder.append("Phone Number:");
        builder.append(ContactUI.NL + ContactUI.NL + ContactUI.TB
                + ContactUI.TB);
        builder.append(this.phone);
        builder.append(ContactUI.NL + ContactUI.NL + ContactUI.TB
                + ContactUI.TB);
        builder.append("Email Address:");
        builder.append(ContactUI.NL + ContactUI.NL + ContactUI.TB
                + ContactUI.TB);
        builder.append(this.email);
        builder.append(ContactUI.NL + ContactUI.NL + ContactUI.TB
                + ContactUI.TB);
        builder.append("Address:");
        builder.append(ContactUI.NL + ContactUI.NL + ContactUI.TB
                + ContactUI.TB);
        builder.append(this.address);
        builder.append(ContactUI.NL + ContactUI.TB + ContactUI.TB);
        builder.append(this.city);
        builder.append(", ");
        builder.append(this.state);
        builder.append(" ");
        builder.append(this.zipcode);
        builder.append(ContactUI.NL + ContactUI.NL + ContactUI.TB
                + ContactUI.TB);
        builder.append("Contact Notes:");
        builder.append(ContactUI.NL + ContactUI.NL + ContactUI.TB
                + ContactUI.TB);
        builder.append(this.notes);
        builder.append(ContactUI.NL + ContactUI.NL);
        return builder.toString();
    }

    /**
     * @see ContactUI#readInput()
     */
    @Override
    public String readInput() {

        final Scanner read = new Scanner(System.in);
        return read.nextLine().toUpperCase();
    }
}