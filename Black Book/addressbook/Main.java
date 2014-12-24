package addressbook;

/**
 * Contact Book..Main.java.Main
 */
public class Main {

    public static void main(String[] args) {
        ContactList userList = new ContactList();
        userList = Load.loadContactList();
        if(!userList.isLoaded()) {
            userList.displayGreeting();
            userList.displayNamePrompt();
            userList.setListName(userList.getInputStr());
            Save.autoSave(userList);
        } else if(userList.getListName().isEmpty()) {
            userList.displayNamePrompt();
            userList.setListName(userList.getInputStr());
        }
        while(true) {
            userList.run();
        }
    }
}
