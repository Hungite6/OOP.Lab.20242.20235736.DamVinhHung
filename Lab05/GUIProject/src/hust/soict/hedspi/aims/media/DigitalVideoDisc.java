package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DigitalVideoDisc extends Disc implements Playable {

    public DigitalVideoDisc(int id, String title, String category, String director, int length, float cost) {
        super(id, title, category, cost, director, length);
    }

    public DigitalVideoDisc(int id, String title, String category, String director, float cost) {
        super(id, title, category, cost, director, 0);
    }

    public DigitalVideoDisc(int id, String title, String category, float cost) {
        super(id, title, category, cost, "", 0);
    }

    public DigitalVideoDisc(int id, String title) {
        super(id, title, "", 0f, "", 0);
    }

    @Override
    public void play() throws PlayerException {
    	if (this.getLength() > 0) {
            // In ra console
            System.out.println("Playing DVD: " + this.getTitle());
            System.out.println("DVD length: " + this.getLength());
            // Hiển thị hộp thoại
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Playing DVD");
            alert.setHeaderText("Now Playing: " + getTitle());
            alert.setContentText("DVD length: " + getLength() + " minutes");
            alert.showAndWait();
        } else {
            throw new PlayerException("ERROR: DVD length is non-positive!");
        }
    }

    @Override
    public String toString() {
        return "DVD - " + getTitle() + " - " + getCategory() + 
               " - " + getDirector() + " - " + getLength() + ": " + getCost() + " $";
    }

    public boolean isMatch(String title) {
        return getTitle().toLowerCase().contains(title.toLowerCase());
    }

}
