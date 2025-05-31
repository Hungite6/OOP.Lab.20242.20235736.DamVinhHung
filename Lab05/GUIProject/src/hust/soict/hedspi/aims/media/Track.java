package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Track implements Playable {
    private String title;
    private int length;

    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    @Override
    public void play() throws PlayerException {
    	if (this.getLength() > 0) {
            System.out.println("Playing Track: " + this.title);
            System.out.println("Track length: " + this.length);
            
            // Hiển thị hộp thoại
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Playing Track");
            alert.setHeaderText("Track: " + title);
            alert.setContentText("Length: " + length + " minutes");
            alert.showAndWait();
        } else {
            throw new PlayerException("ERROR: Track length is non-positive!");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Track) {
            Track other = (Track) obj;
            return this.title.equals(other.title) && this.length == other.length;
        }
        return false;
    }
}
