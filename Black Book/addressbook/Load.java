package addressbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

abstract class Load extends Thread implements LoadingUI {

    static ObjectInputStream loadStream;
    static ContactList loadableList;
    static {
        Load.loadStream = null;
    }

    /**
     * Get Method. Retrieves the ContactList loadableList of the current Load
     * object and returns it to the calling method.
     *
     * @return the loadableList
     */
    public static ContactList getLoadableList() {
        return Load.loadableList;
    }

    /**
     * Sets the value of loadableList equal to that of the ContactList
     * loadableList
     *
     * @param loadableList
     *            the loadableList to be set for Load object.
     */
    public static void setLoadableList(ContactList loadableList) {
        Load.loadableList = loadableList;
    }

    /*
     * Reading method. Creates an ObjectInputStream and reads a previously
     * created file for a ContactList object. Sets the blackBook of current
     * ContactList object equal to that object read.
     */
    public static ContactList loadContactList() {
        Thread loadingContactList = new Thread();
        loadableList = new ContactList();
        boolean loaded = false;
        System.out.println(LOCATING_FILE);
        try(InputStream inputStream = new FileInputStream("ContactList.ser");
                ObjectInputStream loadStream = new ObjectInputStream(
                        inputStream)) {
            Thread.sleep(350);
            System.out.println(FILE_FOUND);
            Thread.sleep(200);
            Load.setLoadableList((ContactList) loadStream.readObject());
            loaded = true;
            Thread.sleep(150);
        } catch(IOException | ClassNotFoundException | InterruptedException ioe) {
            System.out.println(NO_FILE);
        }
        if(Boolean.valueOf(loaded).equals(Boolean.TRUE)) {
            System.out.println(LOAD_GREEN);
        } else {
            System.err.println(ERR_LOAD);
        }
        loadingContactList.interrupt();
        return getLoadableList();
    }
}
