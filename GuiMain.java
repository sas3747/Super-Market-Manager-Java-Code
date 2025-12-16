package edu.cw1.supermarket;

import javax.swing.SwingUtilities;

/**
 * entry point for our GUI 
 */
public class GuiMain {
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(() -> 
        {
            Styles.applyLightUI();

            ProductManager manager = new ProductManager();
            SimpleSupermarketGUI gui = new SimpleSupermarketGUI(manager); // Launches the GUI
            gui.setVisible(true);
        });
    }
}
