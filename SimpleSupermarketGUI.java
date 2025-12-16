package edu.cw1.supermarket;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Main class for GUI to run
 */
public class SimpleSupermarketGUI extends JFrame 
{
    private final ProductManager manager;
    private final JTextArea output;
    private final JLabel status;

   
    private final JTextField tfIdCreate = new JTextField();  // Creates Product fields
    private final JTextField tfNameCreate = new JTextField();
    private final JTextField tfQtyCreate = new JTextField();

    private final JTextField tfIdDelete = new JTextField();// Deletes fields
    
    private final JTextField tfIdAct = new JTextField(); // Add Activity fields
    private final JComboBox<String> cbTypeAct =
            new JComboBox<>(new String[]{"AddToStock", "RemoveFromStock"});
    private final JTextField tfQtyAct = new JTextField();

    private final JTextField tfIdReport = new JTextField();

    public SimpleSupermarketGUI(ProductManager manager)
    {
        this.manager = manager;

        setTitle("Supermarket Manager — Single Screen (Light)"); // Sets Dimensions and Parameter For GUI
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(900, 600));
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(12, 12));
        ((JComponent) getContentPane()).setBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );

       
        add(buildToolbar(), BorderLayout.NORTH);  // Code for toolbar

        JPanel center = new JPanel(new GridBagLayout()); // Layouts for GUI
        center.setOpaque(false);
        add(center, BorderLayout.CENTER);

        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(10, 10, 10, 10);
        gc.fill = GridBagConstraints.BOTH;
        gc.weighty = 1.0;

       
        gc.gridx = 0; gc.gridy = 0; gc.weightx = 0.6;
        center.add(buildFormsPanel(), gc);

        
        gc.gridx = 1; gc.gridy = 0; gc.weightx = 0.4; // Code For output which can be scrolled
        output = new JTextArea();
        output.setEditable(false);
        output.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        output.setText("Welcome! Use the toolbar or forms and check results here.\n");
        JScrollPane scroll = new JScrollPane(output);
        scroll.setBorder(BorderFactory.createTitledBorder("Output"));
        center.add(scroll, gc);

        
        status = new JLabel("Ready"); // Status Bar
        status.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.setOpaque(false);
        statusBar.add(status, BorderLayout.WEST);
        add(statusBar, BorderLayout.SOUTH);

        
        applyInputDefaults(tfIdCreate, tfNameCreate, tfQtyCreate, tfIdDelete, tfIdAct, tfQtyAct, tfIdReport);
    }

    private JToolBar buildToolbar() // Toolbar Functionality code 
    { 
        JToolBar tb = new JToolBar();
        tb.setFloatable(false);
        tb.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        tb.setOpaque(false);

        tb.add(makeBtn("🆕 Create", 'C', e -> onCreateProduct()));
        tb.add(makeBtn("📋 List", 'L', e -> onListProducts()));
        tb.addSeparator();
        tb.add(makeBtn("➕ Add Activity", 'A', e -> onAddActivity()));
        tb.add(makeBtn("📊 Show Last 4", 'S', e -> onShowLastFourSorted()));
        tb.addSeparator();
        tb.add(makeBtn("🗑️ Delete", 'D', e -> onDeleteProduct()));
        tb.add(makeBtn("❓ Help", 'H', e -> onHelp()));
        return tb;
    }

    private JButton makeBtn(String text, char mnemonic, java.awt.event.ActionListener al) {
        JButton b = new JButton(text);
        b.setMnemonic(mnemonic);
        b.addActionListener(al);
        b.setFocusable(true);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setBorder(Styles.roundedBorder());
        b.setOpaque(true);
        b.setBackground(Styles.btnBg());
        b.setForeground(Styles.fg());
        b.setToolTipText(text);
        return b;
    }

   

    private JPanel buildFormsPanel() // Forms
    {
        JPanel grid = new JPanel(new GridLayout(2, 2, 12, 12));
        grid.setOpaque(false);
        grid.add(buildCreateCard());
        grid.add(buildActivityCard());
        grid.add(buildDeleteCard());
        grid.add(buildReportCard());
        return grid;
    }

    private JPanel titledCard(String title) 
    {
        JPanel p = new JPanel(new GridBagLayout());
        p.setOpaque(true);
        p.setBackground(Styles.cardBg());
        p.setBorder(BorderFactory.createCompoundBorder(
                Styles.shadowBorder(),
                BorderFactory.createTitledBorder(title)
        ));
        return p;
    }

    private JPanel buildCreateCard() 
    {
        JPanel p = titledCard("Create Product");
        GridBagConstraints c = gbcRow();

        addRow(p, c, 0, "Product ID", tfIdCreate);
        addRow(p, c, 1, "Name", tfNameCreate);
        addRow(p, c, 2, "Initial Qty", tfQtyCreate);

        c.gridy = 3; c.gridx = 0; c.gridwidth = 2;
        JButton btn = new JButton("Create");
        btn.addActionListener(e -> onCreateProduct());
        stylizePrimary(btn);
        p.add(btn, c);
        return p;
    }

    private JPanel buildActivityCard() 
    {
        JPanel p = titledCard("Add Activity");
        GridBagConstraints c = gbcRow();

        addRow(p, c, 0, "Product ID", tfIdAct);
        addRow(p, c, 1, "Type", cbTypeAct);
        addRow(p, c, 2, "Quantity", tfQtyAct);

        c.gridy = 3; c.gridx = 0; c.gridwidth = 2;
        JButton btn = new JButton("Add Activity");
        btn.addActionListener(e -> onAddActivity());
        stylizePrimary(btn);
        p.add(btn, c);
        return p;
    }

    private JPanel buildDeleteCard() 
    {
        JPanel p = titledCard("Delete Product");
        GridBagConstraints c = gbcRow();

        addRow(p, c, 0, "Product ID", tfIdDelete);

        c.gridy = 1; c.gridx = 0; c.gridwidth = 2;
        JButton btn = new JButton("Delete");
        btn.addActionListener(e -> onDeleteProduct());
        stylizeDanger(btn);
        p.add(btn, c);
        return p;
    }

    private JPanel buildReportCard() 
    {
        JPanel p = titledCard("Report: Last 4 (Sorted)");
        GridBagConstraints c = gbcRow();

        addRow(p, c, 0, "Product ID", tfIdReport);

        c.gridy = 1; c.gridx = 0; c.gridwidth = 2;
        JButton btn = new JButton("Show");
        btn.addActionListener(e -> onShowLastFourSorted());
        stylizePrimary(btn);
        p.add(btn, c);
        return p;
    }

    private GridBagConstraints gbcRow()  // Row helpers 
    {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 10, 6, 10);
        c.fill = GridBagConstraints.HORIZONTAL;
        return c;
    }

    private void addRow(JPanel container, GridBagConstraints c, int row, String label, JComponent input) 
    {
        c.gridy = row; c.gridx = 0; c.weightx = 0.35;
        JLabel l = new JLabel(label);
        l.setForeground(Styles.fg());
        container.add(l, c);

     
        c.gridx = 1; c.weightx = 0.65; // Input
        input.setOpaque(true);
        input.setBackground(Color.WHITE);
        input.setForeground(Color.BLACK);
        input.setBorder(Styles.roundedBorder());
        input.setPreferredSize(new Dimension(10, 28));
        container.add(input, c);
    }

    private void stylizePrimary(JButton b) 
    {
        b.setBorder(Styles.roundedBorder());
        b.setOpaque(true);
        b.setBackground(Styles.accent());
        b.setForeground(Color.WHITE);
    }

    private void stylizeDanger(JButton b) 
    {
        b.setBorder(Styles.roundedBorder());
        b.setOpaque(true);
        b.setBackground(Styles.danger());
        b.setForeground(Color.WHITE);
    }

    private void applyInputDefaults(JTextField... fields)
    {
        for (JTextField f : fields) {
            markValid(f, null);
            f.setPreferredSize(new Dimension(10, 28));
        }
    }

    
    private void markInvalid(JComponent field, String tooltip)  //Validation Highlighting
    {
        field.setBorder(Styles.invalidBorder());
        field.setToolTipText(tooltip != null ? tooltip : "Invalid input");
    }

    private void markValid(JComponent field, String tooltip) 
    {
        field.setBorder(Styles.roundedBorder());
        field.setToolTipText(tooltip);
    }

    private void clearCreateErrors() 
    {
        markValid(tfIdCreate, null);
        markValid(tfNameCreate, null);
        markValid(tfQtyCreate, null);
    }

    private void clearActivityErrors() 
    {
        markValid(tfIdAct, null);
        markValid(tfQtyAct, null);
        cbTypeAct.setBorder(Styles.roundedBorder());
        cbTypeAct.setToolTipText(null);
    }

    private void clearDeleteErrors() { markValid(tfIdDelete, null); }
    private void clearReportErrors() { markValid(tfIdReport, null); }

 
    private void onCreateProduct()  //Actions
    {
        clearCreateErrors();

        String id = tfIdCreate.getText().trim();
        String name = tfNameCreate.getText().trim();
        String qtyStr = tfQtyCreate.getText().trim();

        // Use StaffName for validation; highlight the specific bad field(s)
        String result = StaffName.validateProductInput(id, name, qtyStr);
        if (!"OK".equals(result)) {
            if (!StaffName.isValidProductId(id))   markInvalid(tfIdCreate, "Must start with 'P' followed by digits (e.g., P001)");
            if (!StaffName.isValidName(name))      markInvalid(tfNameCreate, "Enter a non-empty, non-numeric name");
            if (!StaffName.isValidQuantity(qtyStr))markInvalid(tfQtyCreate, "Enter a whole number ≥ 0");
            Dialogs.warn(this, "Invalid Input", result);
            setStatus("Invalid: " + result);
            return;
        }

        int qty = Integer.parseInt(qtyStr);
        boolean ok = manager.createProduct(id, name, qty);
        if (ok) 
        {
            append("Created: " + id + " | " + name + " | qty=" + qty);
            setStatus("Created product " + id);
            Dialogs.success(this, "Created", "Product created successfully.");
            tfIdCreate.setText(""); tfNameCreate.setText(""); tfQtyCreate.setText("");
        } else {
            Dialogs.error(this, "Create Failed", "Duplicate ID or invalid quantity.");
            setStatus("Create failed");
            // likely duplicate ID → mark ID field to guide user
            markInvalid(tfIdCreate, "ID already exists");
        }
    }

    private void onListProducts() 
    {
        List<Product> items = manager.snapshotProducts();
        if (items.isEmpty()) {
            Dialogs.info(this, "Products", "(No products)");
            append("(No products)");
            setStatus("No products");
            return;
        }
        append("-- Products --");
        for (Product p : items) {
            append(p.getId() + " | " + p.getName() + " | Entry=" + p.getEntryDate() + " | Qty=" + p.getQuantity());
        }
        setStatus(items.size() + " product(s) listed");
        Dialogs.info(this, "Products", "Listed " + items.size() + " product(s).");
    }

    private void onAddActivity() 
    {
        clearActivityErrors();

        String id = tfIdAct.getText().trim();
        String type = cbTypeAct.getSelectedItem().toString();
        String qtyStr = tfQtyAct.getText().trim();

        String result = StaffName.validateActivityInput(id, type, qtyStr);
        if (!"OK".equals(result)) {
            if (!StaffName.isValidProductId(id))    markInvalid(tfIdAct, "Use 'P' + digits (e.g., P001)");
            if (!StaffName.isValidActivityType(type)) {
                cbTypeAct.setBorder(Styles.invalidBorder());
                cbTypeAct.setToolTipText("Choose AddToStock or RemoveFromStock");
            }
            if (!StaffName.isValidQuantity(qtyStr)) markInvalid(tfQtyAct, "Enter a whole number ≥ 0");
            Dialogs.warn(this, "Invalid Activity Input", result);
            setStatus(result);
            return;
        }

        int qty = Integer.parseInt(qtyStr);
        boolean ok = manager.addActivity(id, type, qty);
        if (ok) 
        {
            append("Activity: " + id + " -> " + type + " " + qty);
            setStatus("Activity added");
            Dialogs.success(this, "Activity Recorded", type + " " + qty + " for " + id);
            tfIdAct.setText(""); tfQtyAct.setText("");
            markValid(tfIdAct, null); markValid(tfQtyAct, null);
            cbTypeAct.setBorder(Styles.roundedBorder()); cbTypeAct.setToolTipText(null);
        } 
        else  // jumps to different parameters if the first one fails
        {
            Dialogs.error(this, "Failed", "Missing product, invalid qty, or over-removal.");
            setStatus("Activity failed");
            // Try to hint which one if product missing or ID likely wrong
            
        }
    }

    private void onShowLastFourSorted()  // Parameters and conditional statements for last 4 activities
    {
        clearReportErrors();

        String id = tfIdReport.getText().trim();
        if (!StaffName.isValidProductId(id)) {
            markInvalid(tfIdReport, "Use 'P' + digits (e.g., P001)");
            Dialogs.warn(this, "Invalid Product ID", "Enter a Product ID like P001");
            setStatus("Missing/invalid id");
            return;
        }
        List<Activity> last = manager.getLastFourSortedByQuantityList(id);
        if (last == null) {
            Dialogs.error(this, "Not Found", "No product with ID " + id);
            setStatus("Not found");
            markInvalid(tfIdReport, "Product does not exist");
            return;
        }
        if (last.isEmpty()) {
            Dialogs.info(this, "No Activities", "No activities yet for " + id);
            setStatus("No activities");
            return;
        }
        append("-- Last " + last.size() + " activities for " + id + " (qty desc) --");
        for (Activity a : last) append(a.toString());
        setStatus("Report shown for " + id);
        Dialogs.info(this, "Report Ready", "Shown last " + last.size() + " activities for " + id);
    }

    private void onDeleteProduct() // Sets parameters for delete product using else if operator
    {
        clearDeleteErrors();

        String id = tfIdDelete.getText().trim();
        if (!StaffName.isValidProductId(id)) {
            markInvalid(tfIdDelete, "Use 'P' + digits (e.g., P001)");
            Dialogs.warn(this, "Invalid Product ID", "Enter a Product ID like P001");
            setStatus("Missing/invalid id");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Delete product " + id + "?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );
        if (confirm != JOptionPane.YES_OPTION) return;

        boolean ok = manager.deleteProductById(id);
        if (ok) {
            append("Deleted product: " + id);
            setStatus("Deleted " + id);
            Dialogs.success(this, "Deleted", "Product " + id + " deleted.");
            tfIdDelete.setText("");
            markValid(tfIdDelete, null);
        } else {
            Dialogs.error(this, "Not Found", "No product with ID " + id); // pop up appears if the value is not found
            setStatus("Delete failed");
            markInvalid(tfIdDelete, "Product does not exist");
        }
    }

    private void onHelp() //Handles the Help action by showing an informational dialog and updating the application status
    {
        Dialogs.info(this, "Help",
                "Toolbar: 🆕 Create • 📋 List • ➕ Add Activity • 📊 Show Last 4 • 🗑️ Delete • ❓ Help\n" +
                "Invalid inputs are highlighted in red with tooltips.\n" +
                "Success/info popups auto-dismiss; warnings/errors require OK.");
        setStatus("Help shown");
    }
    

    private void append(String line) //Output helpers 
    {
        output.append(line + "\n");
        output.setCaretPosition(output.getDocument().getLength());
    }

    private void setStatus(String msg) 
    {
        status.setText(msg);
    }
}

