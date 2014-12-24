package addressbook;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

abstract class Save extends Thread implements SavingUI {

    static ObjectOutputStream outObject;

    public Save() {
    }

    static {
    }

    /*
     * Writing method. Creates a ObjectOutputStream and writes the current
     * ContactList object's blackBook to file.
     */
    public static boolean save(ContactList list) {
        Thread saving = new Thread();
        boolean saved = false;
        try(OutputStream file = new FileOutputStream("ContactList.ser");
                BufferedOutputStream buffStream = new BufferedOutputStream(file);
                ObjectOutputStream outObject = new ObjectOutputStream(
                        buffStream)) {
            Thread.sleep(300);
            System.out.println(SavingUI.SAVE);
            Thread.sleep(150);
            sort(list.getBlackBook());
            outObject.writeObject(list);
            saved = true;
            Thread.sleep(500);
            if(!saved) {
                throw new IOException(SavingUI.ERR_SAVE);
            }
        } catch(final IOException | InterruptedException ioe) {
            ioe.printStackTrace();
        }
        System.out.println(SavingUI.SAVE_GREEN);
        saving.interrupt();
        return saved;
    }

    /**
     * Displays a checklist of the shutdown process for the Contact List
     * Application. Showing the user that the program has successfully saved,
     * and shutdown.
     *
     * @param processNumber
     *            - The status (int) to be applied for the shutdown sequence.
     */
    private static void shutdownStatus(int processNumber) {
        final StringBuffer sb = new StringBuffer(SavingUI.CHECKLIST);
        switch(processNumber) {
            case 3:
                sb.append(System.lineSeparator() + "\t");
                sb.append(SavingUI.CHECK_1X);
                sb.append(System.lineSeparator() + "\t");
                sb.append(SavingUI.CHECK_2P);
                sb.append(System.lineSeparator() + "\t");
                sb.append(SavingUI.CHECK_3P);
                sb.append(System.lineSeparator());
                break;
            case 2:
                sb.append(System.lineSeparator() + "\t");
                sb.append(SavingUI.CHECK_1X);
                sb.append(System.lineSeparator() + "\t");
                sb.append(SavingUI.CHECK_2X);
                sb.append(System.lineSeparator() + "\t");
                sb.append(SavingUI.CHECK_3P);
                sb.append(System.lineSeparator());
                break;
            case 1:
                sb.append(System.lineSeparator() + "\t");
                sb.append(SavingUI.CHECK_1X);
                sb.append(System.lineSeparator() + "\t");
                sb.append(SavingUI.CHECK_2X);
                sb.append(System.lineSeparator() + "\t");
                sb.append(SavingUI.CHECK_3X);
                sb.append(System.lineSeparator());
                break;
            default:
                sb.append(System.lineSeparator() + "\t");
                sb.append(SavingUI.CHECK_1P);
                sb.append(System.lineSeparator() + "\t");
                sb.append(SavingUI.CHECK_2P);
                sb.append(System.lineSeparator() + "\t");
                sb.append(SavingUI.CHECK_3P);
                sb.append(System.lineSeparator());
                break;
        }
        System.out.println(sb.toString());
    }

    private static boolean isConfirmedShutdown() {
        final Scanner confirm = new Scanner(System.in);
        System.out.println("Are you sure you would like to quit? (Y or N):");
        return confirm.next().equalsIgnoreCase("Y");
    }

    public static boolean closeContactList(ContactList list) {
        Thread closingContactList = new Thread();
        boolean closeApp = false;
        try {
            Save.shutdownStatus(0);
            if(Save.isConfirmedShutdown()) {
                Thread.sleep(300);
                Save.shutdownStatus(3);
                Thread.sleep(500);
                Save.autoSave(list);
                Thread.sleep(500);
                Save.shutdownStatus(2);
                Thread.sleep(500);
                Save.shutdownStatus(1);
                Thread.sleep(500);
                System.out.println("Good-bye.");
                System.exit(0);
                closeApp = true;
            }
        } catch(InterruptedException ie) {
            System.err.println(ie.getMessage());
        }
        closingContactList.interrupt();
        return closeApp;
    }

    public static boolean autoSave(ContactList list) {
        Thread autosaving = new Thread();
        boolean backgroundSave = false;
        try(OutputStream file = new FileOutputStream("ContactList.ser");
                BufferedOutputStream buffStream = new BufferedOutputStream(file);
                ObjectOutputStream outObject = new ObjectOutputStream(
                        buffStream)) {
            Thread.sleep(250);
            System.out.println(SavingUI.AUTOSAVE);
            Thread.sleep(250);
            sort(list.getBlackBook());
            outObject.writeObject(list);
            Thread.sleep(250);
            backgroundSave = true;
            if(!backgroundSave) {
                throw new IOException(SavingUI.ERR_AUTOSAVE);
            }
        } catch(final IOException | InterruptedException ioe) {
            ioe.printStackTrace();
        }
        System.out.println(SavingUI.AUTOSAVE_GREEN);
        autosaving.interrupt();
        return backgroundSave;
    }

    private static void sort(ArrayList<Contact> unsorted) {
        Collections.sort(unsorted);
    }
}
