package edu.cw1.supermarket;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

/**
 * we had to create a new subclass named styles cuz if we tried to run the GUI without the subclass the GUI textfields wasnt working cuz of the same text and GUI background colour
 */
public class Styles
{

    private static final Color BG_LIGHT       = new Color(245, 245, 247);   // window background
    private static final Color CARD_BG_LIGHT  = new Color(255, 255, 255);   // card panels
    private static final Color FIELD_BG_LIGHT = new Color(250, 250, 250);   // inputs
    private static final Color FG_LIGHT       = new Color(28, 28, 30);      // text
    private static final Color ACCENT_BLUE    = new Color(0, 122, 255);     
    private static final Color DANGER_RED     = new Color(200, 70, 70);     // delete
    private static final Color BTN_BG_LIGHT   = new Color(238, 238, 240);   // neutral button
    private static final Color BORDER_LIGHT   = new Color(220, 220, 223);   // light border
    private static final Color ERROR_RED      = new Color(220, 53, 69);     // invalid field

  
    private static final Color BG_DARK        = new Color(28, 28, 30); // DarkTheme for just in case
    private static final Color CARD_BG_DARK   = new Color(44, 44, 46);
    private static final Color FIELD_BG_DARK  = new Color(54, 54, 56);
    private static final Color FG_DARK        = new Color(235, 235, 245);
    private static final Color BTN_BG_DARK    = new Color(60, 60, 62);
    private static final Color BORDER_DARK    = new Color(70, 70, 72);

    
    /** Apply the LIGHT theme GUI . */
    public static void applyLightUI() {
        UIManager.put("control", new ColorUIResource(BG_LIGHT));
        UIManager.put("Panel.background", new ColorUIResource(BG_LIGHT));
        UIManager.put("OptionPane.background", new ColorUIResource(CARD_BG_LIGHT));
        UIManager.put("OptionPane.messageForeground", new ColorUIResource(FG_LIGHT));
        UIManager.put("TextField.background", new ColorUIResource(FIELD_BG_LIGHT));
        UIManager.put("TextField.foreground", new ColorUIResource(FG_LIGHT));
        UIManager.put("TextArea.background", new ColorUIResource(FIELD_BG_LIGHT));
        UIManager.put("TextArea.foreground", new ColorUIResource(FG_LIGHT));
        UIManager.put("Label.foreground", new ColorUIResource(FG_LIGHT));
        UIManager.put("Button.background", new ColorUIResource(BTN_BG_LIGHT));
        UIManager.put("Button.foreground", new ColorUIResource(FG_LIGHT));
        UIManager.put("ScrollPane.background", new ColorUIResource(BG_LIGHT));
        UIManager.put("ToolTip.background", new ColorUIResource(new Color(255, 255, 240)));
        UIManager.put("ToolTip.foreground", new ColorUIResource(FG_LIGHT));
        UIManager.put("nimbusFocus", ACCENT_BLUE);
    }

    /** Optional dark theme. */
    public static void applyDarkUI() {
        UIManager.put("control", new ColorUIResource(BG_DARK));
        UIManager.put("Panel.background", new ColorUIResource(BG_DARK));
        UIManager.put("OptionPane.background", new ColorUIResource(CARD_BG_DARK));
        UIManager.put("OptionPane.messageForeground", new ColorUIResource(FG_DARK));
        UIManager.put("TextField.background", new ColorUIResource(FIELD_BG_DARK));
        UIManager.put("TextField.foreground", new ColorUIResource(FG_DARK));
        UIManager.put("TextArea.background", new ColorUIResource(FIELD_BG_DARK));
        UIManager.put("TextArea.foreground", new ColorUIResource(FG_DARK));
        UIManager.put("Label.foreground", new ColorUIResource(FG_DARK));
        UIManager.put("Button.background", new ColorUIResource(BTN_BG_DARK));
        UIManager.put("Button.foreground", new ColorUIResource(FG_DARK));
        UIManager.put("ScrollPane.background", new ColorUIResource(BG_DARK));
        UIManager.put("ToolTip.background", new ColorUIResource(new Color(50, 50, 52)));
        UIManager.put("ToolTip.foreground", new ColorUIResource(FG_DARK));
    }

  
    /**  */
    public static Border roundedBorder()  //BORDERS 
    {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_LIGHT, 1, true),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)
        );
    }

    /** */
    public static Border shadowBorder() {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(2, 2, 4, 4),
                BorderFactory.createLineBorder(BORDER_LIGHT, 1, true)
        );
    }

    /** Red rounded border was selected to highlight invalid inputs. */
    public static Border invalidBorder() {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ERROR_RED, 2, true),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)
        );
    }

    /** Tweaking the  JOptionPane dialogs to match theme. */
    public static void applyDialogDecor(JDialog dialog) {
        dialog.getRootPane().setBorder(shadowBorder());
        dialog.getContentPane().setBackground(CARD_BG_LIGHT);
    }

    public static Color bg()      { return BG_LIGHT; } //COLOR ACCESSORS (used by GUI code)
    public static Color cardBg()  { return CARD_BG_LIGHT; }
    public static Color fieldBg() { return FIELD_BG_LIGHT; }
    public static Color fg()      { return FG_LIGHT; }
    public static Color accent()  { return ACCENT_BLUE; }
    public static Color danger()  { return DANGER_RED; }
    public static Color btnBg()   { return BTN_BG_LIGHT; }
    public static Color error()   { return ERROR_RED; }  // optional accessor
}
