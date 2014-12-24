package addressbook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * ContactList object contains ArrayList<Contact>, and a String representation
 * of the ContactList's name. (Transient field of String searchField: to be used
 * to search the ContactList)
 *
 * @author Erik
 *
 */
public class ContactList implements ContactListUI, Runnable, Serializable {

    private ArrayList<Contact> blackBook;
    private String listName;

    /**
     * Get Method. Retrieves the String listName of the current ContactList
     * object and returns it to the calling method.
     *
     * @return the listName
     */
    public String getListName() {
        return this.listName;
    }

    /**
     * Sets the value of listName equal to that of the String listName
     *
     * @param listName
     *            the listName to be set for ContactList object.
     */
    public void setListName(String listName) {
        this.listName = listName;
    }

    private transient String searchField;

    public ContactList() {
        this.listName = "";
        this.blackBook = new ArrayList<Contact>();
        this.searchField = "";
    }

    public ArrayList<Contact> getBlackBook() {
        return this.blackBook;
    }

    public void setBlackBook(Object loadList) {
        this.blackBook = (ArrayList<Contact>) loadList;
    }

    /**
     * Get Method. Retrieves the String searchField of the current ContactList
     * object and returns it to the calling method.
     *
     * @return the searchField
     */
    public String getSearchField() {
        return this.searchField;
    }

    /**
     * Sets the value of searchField equal to that of the String search
     *
     * @param search
     *            - the searchField to be set for ContactList object.
     */
    public void setSearchField(String search) {
        this.searchField = search;
    }

    /**
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        displayGreeting();
        while(true) {
            displayMainMenu();
            runMainMenu(getInputInt());
        }
    }

    /**
     *
     */
    private void displayListName() {
        String name = getListName();
        System.out.println(NL + TAB + TAB + TAB + "    " + name
                + "'S CONTACT LIST");
    }

    @Override
    public void searchContacts() {
        int selection = INVALID_SELECTION;
        System.out.println(SEARCH_MENU);
        selection = getInputInt();
        searchContactsBy(selection);
    }

    /**
     * The parameter field determines whether the blackBook is searched by Last
     * Name or Zipcode.
     *
     * @param field
     *            - The selection prompted by the Search Menu.
     */
    private void searchContactsBy(int field) {
        ArrayList<Contact> temp = new ArrayList<Contact>();
        switch(field) {
            case 1:
                temp = searchByLastName();
                displaySearchResults(temp);
                break;
            case 2:
                temp = searchByZipcode();
                displaySearchResults(temp);
                break;
            default:
                break;
        }
    }

    public boolean isLoaded() {
        return this.equals(Load.getLoadableList());
    }

    /**
     * Searches the blackBook field of current ContactList object and compares
     * each Contact's last name in the ArrayList. If matched or contains the
     * last name it is added to a new resultant ArrayList which is returned to
     * the calling method.
     */
    private ArrayList<Contact> searchByLastName() {
        System.out.println("Enter the Last Name to search by: \n");
        String lastToSearch = getInputStr();
        setSearchField(lastToSearch);
        ArrayList<Contact> result = new ArrayList<Contact>();
        for(Contact match : this.blackBook) {
            if(match.getLast().equalsIgnoreCase(lastToSearch)) {
                result.add(match);
            } else if(match.getLast().contains(lastToSearch)) {
                result.add(match);
            }
        }
        return result;
    }

    /**
     * Searches the blackBook field of current ContactList object and compares
     * each Contact's zipcode in the ArrayList. If matched or contains the
     * zipcode it is added to a new resultant ArrayList which is returned to the
     * calling method.
     */
    private ArrayList<Contact> searchByZipcode() {
        System.out.println("Enter the Zipcode to search by: \n");
        String zipcodeToSearch = getInputStr();
        setSearchField(zipcodeToSearch);
        ArrayList<Contact> result = new ArrayList<Contact>();
        for(Contact match : this.blackBook) {
            if(match.getZipcode().equalsIgnoreCase(zipcodeToSearch)) {
                result.add(match);
            } else if(match.getZipcode().contains(zipcodeToSearch)) {
                result.add(match);
            }
        }
        this.sort(result);
        return result;
    }

    /**
     * Prompts the user to shutdown the current Contact List program. Upon the
     * user selecting "Y", the program will autosave the current ContactList
     * Object and exit the program.
     *
     * @see Save#closeContactList(ContactList)
     * @see Save#autoSave(ContactList)
     */
    public void shutdownContactList() {
        Save.closeContactList(this);
    }

    /**
     * Prints to console. Informs user that an invalid menu selection has been
     * chosen.
     *
     * @return
     */
    public void errorSelection() {
        System.err.println(INVALID_CHOICE);
    }

    /**
     * Writing method. Saves the current ContactList object to disk.
     *
     * @see Save#save(ContactList)
     */
    public void saveContactList() {
        Save.save(this);
    }

    /**
     * Printing method. Prints the current blackBook field to console.
     *
     * @see addressbook.ContactListUI#displayBlackBook()
     */
    @Override
    public void displayBlackBook() {
        int contacts = 0;
        this.sort(blackBook);
        for(Contact person : this.blackBook) {
            ++contacts;
            System.out.println(person);
        }
        System.out.println(TAB + contacts
                + " Contact(s) stored in your Contact" + " List" + NL + TAB
                + "[SCROLL_UP] to see your Contacts");
    }

    /**
     * Boolean method, attempts to create a new Contact and add it to the
     * blackBook of the current ContactList object.
     *
     * @return true - if the Contact is successfully created and added to the
     *         blackBook field.
     *
     * @see addressbook.ContactListUI#addContact()
     */
    @Override
    public boolean addContact() {
        Contact add = new Contact();
        add = add.create();
        if(!add.getLast().isEmpty()) {
            this.blackBook.add(add);
            this.sort(blackBook);
            Save.autoSave(this);
            return true;
        }
        System.err.println("Contact addition was unsuccessful");
        return false;
    }

    /**
     * Printing method. Prints the Main Menu to the console.
     *
     * @see addressbook.ContactListUI#displayMainMenu()
     */
    @Override
    public void displayMainMenu() {
        System.out.println(NL);
        this.displayListName();
        System.out.println(MAIN_MENU);
    }

    /**
     *
     * @see addressbook.ContactListUI#displaySearchResults(java.util.ArrayList)
     */
    @Override
    public void displaySearchResults(ArrayList<Contact> result) {
        int searchcount = 0;
        this.sort(result);
        for(Contact results : result) {
            ++searchcount;
            System.out.println(results);
        }
        System.out.println(NL + TAB + searchcount + " Contact(s) matched "
                + searchField + "." + NL + TAB + "[SCROLL_UP] to see your"
                + " Contacts");
    }

    /**
     * @see addressbook.ContactListUI#displayGreeting()
     */
    @Override
    public void displayGreeting() {
        System.out.println(NL + GREETING);
    }

    /**
     * @see addressbook.ContactListUI#displaySearchMenu()
     */
    @Override
    public void displaySearchMenu() {
        System.out.println(NL + SEARCH_MENU);
    }

    /**
     * @see addressbook.ContactListUI#getInputStr()
     */
    @Override
    public String getInputStr() {
        Scanner input = new Scanner(System.in);
        return input.nextLine().toUpperCase();
    }

    /**
     * @see addressbook.ContactListUI#getInputInt()
     */
    @Override
    public int getInputInt() {
        Scanner input = new Scanner(System.in);
        if(input.hasNextInt()) {
            return input.nextInt();
        }
        input.nextLine();
        return INVALID_SELECTION;
    }

    /**
     * @see addressbook.ContactListUI#sort(java.util.ArrayList)
     */
    @Override
    public void sort(ArrayList<Contact> unsorted) {
        Collections.sort(unsorted);
        System.out.println("Sort Complete [x]" + NL);
    }

    /**
     * @see addressbook.ContactListUI#displayNamePrompt()
     */
    @Override
    public void displayNamePrompt() {
        System.out.println(TAB + NAME_LIST);
    }

    /**
     * @see addressbook.ContactListUI#runMainMenu(int)
     */
    @Override
    public void runMainMenu(int choice) {
        if(choice >= 0 && choice <= 4) {
            switch(choice) {
                case 1:
                    this.addContact();
                    break;
                case 2:
                    this.displayBlackBook();
                    break;
                case 3:
                    this.searchContacts();
                    break;
                case 4:
                    this.saveContactList();
                    break;
                case 0:
                    this.shutdownContactList();
                    break;
                default:
                    errorSelection();
                    break;
            }
        } else {
            errorSelection();
        }
    }
}
