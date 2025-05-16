package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.store.Store;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddBookToStoreScreen extends AddItemToStoreScreen {
    private ArrayList<String> authors = new ArrayList<>();
    private JTextField tfAuthor;
    private JTextArea authorArea;

    public AddBookToStoreScreen(Store store) {
        super(store, "Add Book to Store");

        // Panel thêm tác giả
        JPanel authorPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        tfAuthor = new JTextField();
        JButton btnAddAuthor = new JButton("Add Author");

        authorPanel.add(new JLabel("Author:"));
        authorPanel.add(tfAuthor);
        authorPanel.add(btnAddAuthor);

        // Khu hiển thị danh sách tác giả đã thêm
        authorArea = new JTextArea(4, 20);
        authorArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(authorArea);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(authorPanel, BorderLayout.NORTH);
        centerPanel.add(new JLabel("Authors Added:"), BorderLayout.CENTER);
        centerPanel.add(scrollPane, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);

        // Nút thêm sách
        JButton btnAddBook = new JButton("Add Book");
        add(btnAddBook, BorderLayout.SOUTH);

        // Sự kiện thêm tác giả
        btnAddAuthor.addActionListener(e -> {
            String author = tfAuthor.getText().trim();
            if (!author.isEmpty() && !authors.contains(author)) {
                authors.add(author);
                authorArea.append(author + "\n");
                tfAuthor.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Author is empty or already added!");
            }
        });

        // Sự kiện thêm sách
        btnAddBook.addActionListener(e -> {
            try {
                String title = getTitleInput();
                String category = getCategoryInput();
                float cost = getCostInput();

                Book book = new Book(store.getItemsInStore().size() + 1, title, category, cost);
                for (String author : authors) {
                    book.addAuthor(author);
                }

                store.addMedia(book);
                JOptionPane.showMessageDialog(this, "Book added successfully!");

                new StoreManagerScreen(store);
                this.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid cost. Please enter a number.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}
