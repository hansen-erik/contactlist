package addressbook;

/**
 * Contact Book..ContactUI.java.ContactUI
 */
/**
 * @author Erik Hansen
 */
interface ContactUI {

    static final int MAX_ATTEMPTS = 3;
    static final String INVALID_NAME = "";
    static final String CREATE_HEADER = ContactUI.NL + ContactUI.TB
            + "New Contact Creation:" + ContactUI.NL;
    /*
     * These private strings are the prompts the user will see while they create
     * a new contact.
     */
    static final String NL = System.lineSeparator();
    static final String REQ_LAST = ContactUI.NL
            + "**The field \"Last Name\" is REQUIRED**";
    static final String PROMPT_ADDRESS = ContactUI.NL
            + "Enter Street Address (ex. 12345 El Monte Rd):" + ContactUI.NL;
    static final String PROMPT_CITY = ContactUI.NL + "Enter City Name:"
            + ContactUI.NL;
    static final String PROMPT_EMAIL = ContactUI.NL + "Enter Email Address:"
            + ContactUI.NL;
    static final String PROMPT_FIRST = ContactUI.NL + "Enter First Name:"
            + ContactUI.NL;
    static final String PROMPT_LAST = ContactUI.NL + "Enter Last Name:"
            + ContactUI.NL;
    static final String PROMPT_NOTES = ContactUI.NL + "Enter Notes:"
            + ContactUI.NL;
    static final String PROMPT_PHONE = ContactUI.NL + "Enter Phone Number:"
            + ContactUI.NL;
    static final String PROMPT_STATE = ContactUI.NL + "Enter State Name:"
            + ContactUI.NL;
    static final String PROMPT_ZIPCODE = ContactUI.NL + "Enter Local Zipcode:"
            + ContactUI.NL;
    static final String TB = "\t";
    static final String BLANK = "[BLANK]";

    void promptAddress();

    void promptCity();

    void promptEmail();

    void promptFirst();

    void promptLast();

    void promptNotes();

    void promptPhone();

    void promptState();

    void promptZipcode();

    String readInput();

    /**
     * Gathers the field information for a Contact and then creates a new
     * Contact object with those parameters.
     *
     * @return Contact
     */
    Contact create();

    /**
     * @return
     */
    String requireLastName();
}