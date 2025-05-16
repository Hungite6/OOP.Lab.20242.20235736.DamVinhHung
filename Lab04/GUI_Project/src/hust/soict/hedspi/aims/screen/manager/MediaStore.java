package hust.soict.hedspi.aims.screen.manager;

import javax.swing.*;
import java.awt.*;

import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;

public class MediaStore extends JPanel {
    private Media media;

    public MediaStore(Media media) {
        this.media = media;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 15));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel cost = new JLabel("" + media.getCost() + " $");
        cost.setAlignmentX(CENTER_ALIGNMENT);

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        if (media instanceof Playable) {
            JButton playButton = new JButton("Play");

            playButton.addActionListener(e -> {
                showPlayDialog();
            });

            container.add(playButton);
        }

        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(cost);
        this.add(Box.createVerticalGlue());
        this.add(container);

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    private void showPlayDialog() {
        // Tạo dialog modal
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Playing Media", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());

        // Khu vực hiển thị nội dung play
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));

        // Chuẩn bị nội dung thông tin media
        StringBuilder playInfo = new StringBuilder();
        playInfo.append("Now Playing:\n\n");
        playInfo.append(media.toString()).append("\n\n");

        if (media instanceof Playable) {
            // Gọi play() - hiện tại play() chỉ in ra console, bạn có thể custom thêm để trả về text
            ((Playable) media).play();

            // Nếu bạn muốn, thêm mô tả cho nội dung play ở đây:
            playInfo.append("Enjoy your media!\n");
        } else {
            playInfo.append("This media cannot be played.");
        }

        textArea.setText(playInfo.toString());

        // Thêm text area vào scroll pane để có thanh cuộn nếu dài
        JScrollPane scrollPane = new JScrollPane(textArea);
        dialog.add(scrollPane, BorderLayout.CENTER);

        // Nút đóng dialog
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dialog.dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);

        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }
}
