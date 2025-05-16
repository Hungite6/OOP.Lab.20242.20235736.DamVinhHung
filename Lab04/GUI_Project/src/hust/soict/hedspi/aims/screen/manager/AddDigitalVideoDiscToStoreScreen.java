package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.store.Store;

import javax.swing.*;
import java.awt.*;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfDirector;
    private JTextField tfLength;

    public AddDigitalVideoDiscToStoreScreen(Store store) {
        super(store, "Add DVD to Store");

        // Panel nhập thêm thông tin
        JPanel dvdPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        tfDirector = new JTextField();
        tfLength = new JTextField();

        dvdPanel.add(new JLabel("Director:"));
        dvdPanel.add(tfDirector);
        dvdPanel.add(new JLabel("Length (int):"));
        dvdPanel.add(tfLength);

        add(dvdPanel, BorderLayout.CENTER);

        // Nút thêm DVD
        JButton btnAddDVD = new JButton("Add DVD");
        add(btnAddDVD, BorderLayout.SOUTH);

        btnAddDVD.addActionListener(e -> {
            try {
                String title = getTitleInput();
                String category = getCategoryInput();
                float cost = getCostInput();
                String director = tfDirector.getText().trim();
                int length = Integer.parseInt(tfLength.getText().trim());

                if (director.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Director cannot be empty.");
                    return;
                }

                DigitalVideoDisc dvd = new DigitalVideoDisc(
                        store.getItemsInStore().size() + 1,
                        title, category, director, length, cost
                );

                store.addMedia(dvd);
                JOptionPane.showMessageDialog(this, "DVD added successfully!");

                new StoreManagerScreen(store);
                this.dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please check your data.");
            }
        });

        setVisible(true);
    }
}
