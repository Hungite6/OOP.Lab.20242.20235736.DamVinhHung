package hust.soict.hedspi.aims.screen.customer.controller;

import hust.soict.hedspi.aims.store.Store;
import hust.soict.hedspi.aims.cart.Cart;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.*;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;

public class ViewStoreController {

    @FXML
    private GridPane gridPane;
    @FXML
    void btnViewCartPressed(ActionEvent event) {
    	try {
            final String CART_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Cart.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(CART_FXML_FILE_PATH));
            	fxmlLoader.setController(new CartController(store,cart));
            	Parent root = fxmlLoader.load();
            	Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            	stage.setScene(new Scene(root));
            	stage.setTitle("Cart");
        } catch (IOException e) {
            e.printStackTrace();
        	}
    }
    private Store store;
    private Cart cart;
    public ViewStoreController(Store store,Cart cart) {
    	this.store = store;
        this.cart = cart;
    }
    @FXML
    public void initialize() {
        final String ITEM_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Item.fxml";
        int column = 0;
        int row = 0;
        for (int i = 0; i < store.getItemsInStore().size(); i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource(ITEM_FXML_FILE_PATH));
                ItemController itemController = new ItemController(cart);
                fxmlLoader.setController(itemController);
                AnchorPane anchorPane = fxmlLoader.load();
                itemController.setData(store.getItemsInStore().get(i));
                
                if (column == 3) {
                    column = 0;
                    row++;
                }
                gridPane.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(20, 10, 10, 10));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
