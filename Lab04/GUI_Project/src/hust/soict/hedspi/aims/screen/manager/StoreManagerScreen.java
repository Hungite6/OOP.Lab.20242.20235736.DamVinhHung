package hust.soict.hedspi.aims.screen.manager;

import javax.swing.*;              
import java.awt.*;                
import java.util.ArrayList;       

import hust.soict.hedspi.aims.store.Store;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.screen.manager.MediaStore;

public class StoreManagerScreen extends JFrame {
    private Store store;

    public StoreManagerScreen(Store store) {
        this.store = store;
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        setJMenuBar(createMenuBar()); 

        cp.add(createHeader(), BorderLayout.NORTH); 

        cp.add(createCenter(), BorderLayout.CENTER);

        setTitle("Store");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


JPanel createNorth() {
    JPanel north = new JPanel();
    north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));

    JMenuBar menuBar = createMenuBar();
    JPanel header = createHeader();

    north.add(menuBar);
    north.add(header);
    return north;
}

JMenuBar createMenuBar() {
    JMenu menu = new JMenu("Options");

    JMenu smUpdateStore = new JMenu("Update Store");
    JMenuItem addBook = new JMenuItem("Add Book");
    JMenuItem addCD = new JMenuItem("Add CD");
    JMenuItem addDVD = new JMenuItem("Add DVD");

    // Action: Mở các màn hình thêm sản phẩm
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


JPanel createHeader() {
    JPanel header = new JPanel();
    header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

    JLabel title = new JLabel("AIMS");
    title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
    title.setForeground(Color.CYAN);

    header.add(Box.createRigidArea(new Dimension(10, 10)));
    header.add(title);
    header.add(Box.createHorizontalGlue());
    header.add(Box.createRigidArea(new Dimension(10, 10)));

    return header;
}

JPanel createCenter() {
    JPanel center = new JPanel();
    center.setLayout(new GridLayout(3, 3, 2, 2)); // 3x3 grid, spacing 2px

    ArrayList<Media> mediaInStore = store.getItemsInStore();
    for (int i = 0; i < Math.min(9, mediaInStore.size()); i++) {
        MediaStore cell = new MediaStore(mediaInStore.get(i));
        center.add(cell);
    }

    return center;
}
}