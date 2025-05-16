package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.Track;
import hust.soict.hedspi.aims.store.Store;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfDirector;
    private JTextField tfArtist;
    private JTextField tfTrackTitle;
    private JTextField tfTrackLength;
    private JTextArea trackArea;

    private ArrayList<Track> tracks = new ArrayList<>();

    public AddCompactDiscToStoreScreen(Store store) {
        super(store, "Add CD to Store");

        // Panel nhập thông tin CD
        JPanel infoPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        tfDirector = new JTextField();
        tfArtist = new JTextField();

        infoPanel.add(new JLabel("Director:"));
        infoPanel.add(tfDirector);
        infoPanel.add(new JLabel("Artist:"));
        infoPanel.add(tfArtist);

        // Panel thêm track
        JPanel trackPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        tfTrackTitle = new JTextField();
        tfTrackLength = new JTextField();
        JButton btnAddTrack = new JButton("Add Track");

        trackPanel.add(new JLabel("Track Title:"));
        trackPanel.add(tfTrackTitle);
        trackPanel.add(new JLabel("Track Length (int):"));
        trackPanel.add(tfTrackLength);

        // Area hiển thị danh sách track
        trackArea = new JTextArea(5, 20);
        trackArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(trackArea);

        // Gộp trung tâm
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(infoPanel, BorderLayout.NORTH);
        centerPanel.add(trackPanel, BorderLayout.CENTER);
        centerPanel.add(btnAddTrack, BorderLayout.EAST);
        centerPanel.add(scrollPane, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);

        // Nút tạo CD
        JButton btnAddCD = new JButton("Add CD");
        add(btnAddCD, BorderLayout.SOUTH);

        // Xử lý thêm track
        btnAddTrack.addActionListener(e -> {
            String title = tfTrackTitle.getText().trim();
            String lengthStr = tfTrackLength.getText().trim();

            if (title.isEmpty() || lengthStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Track title and length cannot be empty.");
                return;
            }

            try {
                int length = Integer.parseInt(lengthStr);
                Track track = new Track(title, length);
                tracks.add(track);
                trackArea.append(title + " (" + length + "s)\n");
                tfTrackTitle.setText("");
                tfTrackLength.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Track length must be an integer.");
            }
        });

        // Xử lý tạo CD
        btnAddCD.addActionListener(e -> {
            try {
                String title = getTitleInput();
                String category = getCategoryInput();
                float cost = getCostInput();
                String director = tfDirector.getText().trim();
                String artist = tfArtist.getText().trim();

                if (director.isEmpty() || artist.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Director and Artist cannot be empty.");
                    return;
                }

                CompactDisc cd = new CompactDisc(store.getItemsInStore().size() + 1,
                        title, category, cost, director, 0, artist);

                for (Track track : tracks) {
                    cd.addTrack(track);
                }

                store.addMedia(cd);
                JOptionPane.showMessageDialog(this, "CD added successfully!");

                new StoreManagerScreen(store);
                this.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid cost. Please enter a valid number.");
            }
        });

        setVisible(true);
    }
}
