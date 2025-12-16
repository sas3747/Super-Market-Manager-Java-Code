package edu.cw1.supermarket;

import javax.swing.*;
import java.awt.*;

/**
 * This is one our additional Features which shows popup after every task is done to confirm that the task was completed.
 */
public class Dialogs 
{
 public static void success(Component parent, String title, String message)
 {
        showAutoDismiss(parent, title, message, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void info(Component parent, String title, String message) 
    {
        showAutoDismiss(parent, title, message, JOptionPane.INFORMATION_MESSAGE);
    }

    private static void showAutoDismiss(Component parent, String title, String message, int type) 
    {
        JOptionPane pane = new JOptionPane(message, type);
        JDialog dialog = pane.createDialog(parent, title);
        dialog.setModal(false);                      
        dialog.setAlwaysOnTop(true);                 // float above
        Styles.applyDialogDecor(dialog);             
        dialog.setVisible(true);
        
        new javax.swing.Timer(2000, e -> dialog.dispose()).start(); // Auto-close after 2 seconds
    }


    public static void warn(Component parent, String title, String message)    //Manual-close helpers (warn/error)
    {
        showManual(parent, title, message, JOptionPane.WARNING_MESSAGE);
    }

    public static void error(Component parent, String title, String message) 
    {
        showManual(parent, title, message, JOptionPane.ERROR_MESSAGE);
    }

    private static void showManual(Component parent, String title, String message, int type) 
    {
        JOptionPane pane = new JOptionPane(message, type, JOptionPane.DEFAULT_OPTION);
        JDialog dialog = pane.createDialog(parent, title);
        dialog.setModal(true);                       // Compulsary for user to acknowledge
        Styles.applyDialogDecor(dialog);
        dialog.setVisible(true);
    }
}
