/**
 *
 */
package addressbook;

/**
 * @author Erik
 *
 */
public interface LoadingUI {

    static final String NL = System.lineSeparator();
    /**
     * Locating File: Inform user that the program is attempting to find the
     * file specified.
     */
    static final String LOCATING_FILE = "[LOAD]: [LOC_FILE] Locating Contact List File. . .";
    /**
     * File Found: Inform user program is attempting to load the old file.
     */
    static final String FILE_FOUND = "[LOAD]: [FILE_FOUND] Loading Contact List. . .";
    /**
     * Error Loading: There was an I/O Exception. Class was not found.
     */
    static final String ERR_LOAD = "[LOAD_FAIL]: [RED]\nError Reading From Disk";
    /**
     * No File: The File was not found. Inform user a new contact list will be
     * created.
     */
    static final String NO_FILE = "[LOAD_NOFILE]: [YELLOW]\nCreating New Contact List. . .";
    /**
     * Load Green: Inform user the load was a success and program is finished
     * loading.
     */
    static final String LOAD_GREEN = "[LOAD]: [SUCCESS]";
}
