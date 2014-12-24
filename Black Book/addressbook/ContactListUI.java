package addressbook;

import java.util.ArrayList;

/**
 * Random Because Eclipse decided to erase.
 */
interface ContactListUI {

    static final String NL = System.lineSeparator();
    static final String TAB = "\t";
    static final String GREETING = ContactListUI.TAB + ContactListUI.TAB
            + "  Welcome to the Contact List Application" + ContactListUI.NL;
    static final String NAME_LIST = "Enter a [NAME] for this Contact List:"
            + ContactListUI.NL;
    static final String MAIN_MENU = ContactListUI.NL + ContactListUI.TAB
            + "-------------------Contact List Application------------------"
            + ContactListUI.NL + ContactListUI.TAB
            + "|                                                           |"
            + ContactListUI.NL + ContactListUI.TAB
            + "|                 [1] Create New Contact                    |"
            + ContactListUI.NL + ContactListUI.TAB
            + "|                                                           |"
            + ContactListUI.NL + ContactListUI.TAB
            + "|                 [2] Show Contact List                     |"
            + ContactListUI.NL + ContactListUI.TAB
            + "|                                                           |"
            + ContactListUI.NL + ContactListUI.TAB
            + "|                 [3] Search Contact List                   |"
            + ContactListUI.NL + ContactListUI.TAB
            + "|                                                           |"
            + ContactListUI.NL + ContactListUI.TAB
            + "|                 [4] Save Contact List                     |"
            + ContactListUI.NL + ContactListUI.TAB
            + "|                                                           |"
            + ContactListUI.NL + ContactListUI.TAB
            + "|                 [0] Exit Contact List Program             |"
            + ContactListUI.NL + ContactListUI.TAB
            + "|                                                           |"
            + ContactListUI.NL + ContactListUI.TAB
            + "-------------------------------------------------------------"
            + ContactListUI.NL;
    static final String SEARCH_MENU = ContactListUI.NL + ContactListUI.TAB
            + ContactListUI.TAB + "----------Search Menu----------"
            + ContactListUI.NL + ContactListUI.TAB + ContactListUI.TAB
            + "|                             |" + ContactListUI.NL
            + ContactListUI.TAB + ContactListUI.TAB
            + "|       [1] Search By         |" + ContactListUI.NL
            + ContactListUI.TAB + ContactListUI.TAB
            + "|           Last Name         |" + ContactListUI.NL
            + ContactListUI.TAB + ContactListUI.TAB
            + "|                             |" + ContactListUI.NL
            + ContactListUI.TAB + ContactListUI.TAB
            + "|       [2] Search By         |" + ContactListUI.NL
            + ContactListUI.TAB + ContactListUI.TAB
            + "|           Zipcode           |" + ContactListUI.NL
            + ContactListUI.TAB + ContactListUI.TAB
            + "|                             |" + ContactListUI.NL
            + ContactListUI.TAB + ContactListUI.TAB
            + "-------------------------------" + ContactListUI.NL;
    static final String INVALID_LAST = "[INVALID_LAST]";
    static final String INVALID_CHOICE = ContactListUI.NL
            + "Invalid selection " + NL + "Please select a valid option:";
    static final String REDIRECTED = "You are being redirected to the [MAIN "
            + "MENU]. . .";
    static final int INVALID_SELECTION = -1;

    void displayGreeting();

    void displayNamePrompt();

    void displayMainMenu();

    void displaySearchMenu();

    void displayBlackBook();

    void displaySearchResults(ArrayList<Contact> results);

    void searchContacts();

    String getInputStr();

    int getInputInt();

    void sort(ArrayList<Contact> unsorted);

    void runMainMenu(int choice);

    boolean addContact();
}