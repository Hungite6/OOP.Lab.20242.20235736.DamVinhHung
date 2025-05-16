package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.store.Store;

import javax.swing.*;
import java.awt.*;

public abstract class AddItemToStoreScreen extends JFrame {
    protected Store store;
    protected JTextField tfTitle;
    protected JTextField tfCategory;
    protected JTextField tfCost;

    public AddItemToStoreScreen(Store store, String screenTitle) {
        this.store = store;

        setTitle(screenTitle);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        setJMenuBar(createMenuBar());

        add(createCommonInputPanel(), BorderLayout.NORTH);
    }

    // Giao diện nhập Title, Category, Cost
    private JPanel createCommonInputPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        tfTitle = new JTextField();
        tfCategory = new JTextField();
        tfCost = new JTextField();

        panel.add(new JLabel("Title:"));
        panel.add(tfTitle);
        panel.add(new JLabel("Category:"));
        panel.add(tfCategory);
        panel.add(new JLabel("Cost:"));
        panel.add(tfCost);

        return panel;
    }

    // MenuBar giống StoreManagerScreen
    protected JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");

        JMenu smUpdateStore = new JMenu("Update Store");
        JMenuItem addBook = new JMenuItem("Add Book");
        JMenuItem addCD = new JMenuItem("Add CD");
        JMenuItem addDVD = new JMenuItem("Add DVD");

        addBook.addActionListener(e -> {
            new AddBookToStoreScreen(store);
            this.setVisible(false);
        });
        addCD.addActionListener(e -> {
            new AddCompactDiscToStoreScreen(store);
            this.setVisible(false);
        });
        addDVD.addActionListener(e -> {
            new AddDigitalVideoDiscToStoreScreen(store);
            this.setVisible(false);
        });

        smUpdateStore.add(addBook);
        smUpdateStore.add(addCD);
        smUpdateStore.add(addDVD);

        JMenuItem viewStore = new JMenuItem("View Store");
        viewStore.addActionListener(e -> {
            new StoreManagerScreen(store);
            this.setVisible(false);
        });

        menu.add(smUpdateStore);
        menu.add(viewStore);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        return menuBar;
    }

    // Trợ giúp lớp con đọc dữ liệu
    protected String getTitleInput() {
        return tfTitle.getText().trim();
    }

    protected String getCategoryInput() {
        return tfCategory.getText().trim();
    }

    protected float getCostInput() throws NumberFormatException {
        return Float.parseFloat(tfCost.getText().trim());
    }
}
