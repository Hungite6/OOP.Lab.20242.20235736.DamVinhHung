package hust.soict.hedspi.aims.screen.customer.controller;

import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.LimitExceededException;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.Playable;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;

public class ItemController {
    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnPlay;

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblCost;

    @FXML
    void btnAddToCartClicked(ActionEvent event) {
    	if (media != null) {
            try {
                cart.addMedia(media);
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Media added to cart successfully!");
                alert.showAndWait();
            } catch (LimitExceededException e) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Limit Exceeded");
                alert.setHeaderText(null);
                alert.setContentText("ERROR: The number of media has reached its limit!");
                alert.showAndWait();
             }
    	}
    }

    @FXML
    void btnPlayClicked(ActionEvent event) {
    	if(media != null && media instanceof Playable) {
    		try {
                ((Playable) media).play();
            } catch (PlayerException e) {
                // Hiển thị thông báo lỗi cho người dùng
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Playback Error");
                alert.setHeaderText("Unable to play media");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            	}
    	}
    }
    private Cart cart;
    public ItemController(Cart cart) {
        this.cart = cart;
    }
    private Media media;
    public void setData(Media media) {
        this.media = media;
        lblTitle.setText(media.getTitle());
        lblCost.setText(media.getCost() + " $");
        if (media instanceof Playable) {
            btnPlay.setVisible(true);
        } else {
            btnPlay.setVisible(false);
            HBox.setMargin(btnAddToCart, new Insets(0, 0, 0, 60));
        }
    }

}
