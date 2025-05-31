package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.Iterator;

import hust.soict.hedspi.aims.exception.PlayerException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CompactDisc extends Disc implements Playable {
    private String artist;
    private ArrayList<Track> tracks = new ArrayList<>();

    public CompactDisc(int id, String title, String category, float cost, String director, int length, String artist) {
        super(id, title, category, cost, director, length);
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public void addTrack(Track track) {
        if (!tracks.contains(track)) {
            tracks.add(track);
            System.out.println("Track added: " + track.getTitle());
        } else {
            System.out.println("Track already exists: " + track.getTitle());
        }
    }

    public void removeTrack(Track track) {
        if (tracks.contains(track)) {
            tracks.remove(track);
            System.out.println("Track removed: " + track.getTitle());
        } else {
            System.out.println("Track not found: " + track.getTitle());
        }
    }

    public int getLength() {
        int totalLength = 0;
        for (Track track : tracks) {
            totalLength += track.getLength();
        }
        return totalLength;
    }

    @Override
    public void play() throws PlayerException {
    	if (this.getLength() > 0) {
            System.out.println("Playing CD: " + getTitle());
            System.out.println("CD Artist: " + artist);
            
            // Hien thi hop thoai JavaFX
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Playing CD");
            alert.setHeaderText("Now Playing: " + getTitle());
            alert.setContentText("Artist: " + artist + "\nTotal tracks: " + tracks.size() + "\nTotal length: " + getLength() + " minutes");
            alert.showAndWait();
            
            // Su dung Iterator de phat tung track
            Iterator<Track> iter = tracks.iterator();
            Track nextTrack;
            while (iter.hasNext()) {
                nextTrack = iter.next();
                try {
                    nextTrack.play();
                } catch (PlayerException e) {
                    throw e; // Nem lai ngoai le
                }
            }
        } else {
            throw new PlayerException("ERROR: CD length is non-positive!");
        }
    }
}
