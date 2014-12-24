/**
 *
 */
package addressbook;

/**
 * @author Erik
 *
 */
public interface SavingUI {

    static final String NL = System.lineSeparator();
    /**
     * Save: Inform user that the program is attempting to write to disk.
     */
    static final String SAVE = "[SAVE]: [WRITE_FILE]\nAttempting to save Contact List. . .";
    /**
     * Save Green: Inform user that Contact List save was successful.
     */
    static final String SAVE_GREEN = "[SAVE]: [SUCCESS]\n";
    /**
     * Error Saving: There was an I/O error while writing to disk.
     */
    static final String ERR_SAVE = "[SAVE_FAIL]: [RED]\nError Writing to Disk\n";
    /**
     * Exit Prompt: Ask user for Y or N answer.
     */
    static final String EXIT_PROMPT = "[SHTDWN_CONFIRM]: Do you want to Exit the Contact List Program? (Y or N)";
    /**
     * Checklist: Shows the header for Shutdown Checklist.
     */
    static final String CHECKLIST = "[SHTDWN_CHKLIST] Shutdown Checklist:";
    /**
     * Check 1 Empty: Shows empty box for Confirmed Shutdown Program.
     */
    static final String CHECK_1P = "[ ] Confirmed Shutdown Program";
    /**
     * Check 1 Filled: Shows 'x' in Confirmed Shutdown Program box.
     */
    static final String CHECK_1X = "[x] Confirmed Shutdown Program";
    /**
     * Check 2 Empty: Shows empty box for Autosave Complete.
     */
    static final String CHECK_2P = "[ ] Autosave Complete";
    /**
     * Check 2 Filled: Shows 'x' in Autosave Complete box.
     */
    static final String CHECK_2X = "[x] Autosave Complete";
    /**
     * Check 3 Empty: Shows empty box for Shutdown Process Complete.
     */
    static final String CHECK_3P = "[ ] Shutdown Process Complete";
    /**
     * Check 3 Filled: Shows 'x' in Shutdown Process Complete box.
     */
    static final String CHECK_3X = "[x] Shutdown Process Complete";
    /**
     * Shutdown: Inform user that Contact List shutdown is initiated.
     */
    static final String SHUTDOWN = "[SHUTDOWN]: Contact List is shutting down . . .\n\n"
            + SavingUI.CHECKLIST;
    /**
     * Shutdown Green: Show user shutdown process boxes are complete with 'x'.
     */
    static final String SHUTDOWN_GREEN = SavingUI.CHECKLIST + SavingUI.NL
            + SavingUI.NL + SavingUI.CHECK_1X + SavingUI.NL + SavingUI.CHECK_2X
            + SavingUI.NL + SavingUI.CHECK_3X + SavingUI.NL;
    /**
     * Autosave: Show user that Autosave process is "in progress".
     */
    static final String AUTOSAVE = "[AUTOSAVE]: [TO_DISK] Autosaving Contact List . . .";
    /**
     * Autosave Green: Inform user that Autosave is complete with success.
     */
    static final String AUTOSAVE_GREEN = "[AUTOSAVE]: [SUCCESS] Autosave Complete\n\n";
    /**
     * Autosave Error: Inform user there is a error.
     */
    static final String ERR_AUTOSAVE = "[AUTOSAVE_ERR]: [FAIL] Error Writing to Disk";
}
