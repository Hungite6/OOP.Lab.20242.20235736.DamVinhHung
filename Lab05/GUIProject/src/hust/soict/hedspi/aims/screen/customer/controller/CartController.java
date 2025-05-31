package hust.soict.hedspi.aims.screen.customer.controller;

import java.io.IOException;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.CartEmptyException;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.store.Store;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CartController {

    private Cart cart;
    private Store store;
    public CartController(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
    }
    private FilteredList<Media> filteredMedia;

    @FXML
    private TableView<Media> tblMedia;
    @FXML
    private TableColumn<Media, Integer> colMediaId;
    @FXML
    private TableColumn<Media, String> colMediaTitle;
    @FXML
    private TableColumn<Media, String> colMediaCategory;
    @FXML
    private TableColumn<Media, Float> colMediaCost;
    @FXML
    private Button btnPlay;
    @FXML
    private Button btnRemove;
    @FXML
    private Label lblMediaTitle;
    @FXML
    private Label costLabel;
    @FXML
    private TextField tfFilter;
    @FXML
    private RadioButton radioBtnFilterId;
    @FXML
    private RadioButton radioBtnFilterTitle;
    @FXML
    private ToggleGroup filterCategory;
    @FXML
    private Button btnPlaceOrder;
    @FXML
    public void initialize() {
        // Thiết lập dữ liệu cho các cột trong bảng
        colMediaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        
        //Tính tổng số tiền 
        costLabel.setText(String.format("%.2f $", cart.totalCost()));

        // Gán danh sách sản phẩm từ cart
        if (cart.getItemsOrdered() != null) {
            tblMedia.setItems(cart.getItemsOrdered()); // nếu getItemsOrdered() là ObservableList
        }
        
        // Ẩn nút khi chưa chọn gì
        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        // Khi người dùng chọn một item trong bảng
        tblMedia.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Media>() {
            @Override
            public void changed(ObservableValue<? extends Media> observable, Media oldValue, Media newValue) {
                updateButtonBar(newValue);
                updateMediaInfo(newValue);
            }
        });
        radioBtnFilterId.setSelected(true); // mặc định là lọc theo Title
        // Khởi tạo FilteredList
        filteredMedia = new FilteredList<>(cart.getItemsOrdered(), p -> true);
        tblMedia.setItems(filteredMedia);

        // Thêm ChangeListener cho TextField
        tfFilter.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                showFilteredMedia(newValue);
            }
        });
    }

    // Cập nhật trạng thái nút Play/Remove
    private void updateButtonBar(Media media) {
        boolean hasMedia = media != null;
        btnRemove.setVisible(hasMedia);
        btnPlay.setVisible(hasMedia && media instanceof Playable);
    }

    // Cập nhật thông tin tiêu đề media
    private void updateMediaInfo(Media media) {
        if (lblMediaTitle != null) {
            lblMediaTitle.setText(media != null ? media.getTitle() : "");
        }
    }

    // Sự kiện nhấn nút Remove
    @FXML
    private void btnRemovePressed(ActionEvent event) {
        Media selected = tblMedia.getSelectionModel().getSelectedItem();
        if (selected != null) {
            cart.removeMedia(selected);
            tblMedia.refresh(); // Cập nhật lại bảng
            updateButtonBar(null);
            updateMediaInfo(null);
        }
        // Cập nhật tổng tiền khi remove
        if (costLabel != null && cart != null && cart.getItemsOrdered() != null) {
            costLabel.setText(String.format("%.2f $", cart.totalCost()));
        } else {
            if (costLabel != null) {
                costLabel.setText("0.00 $");
            }
        }
    }

    // Sự kiện nhấn nút Play
    @FXML
    private void btnPlayPressed(ActionEvent event) {
        Media selected = tblMedia.getSelectionModel().getSelectedItem();
        if (selected instanceof Playable) {
        	try {
                ((Playable) selected).play();
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
    @FXML
    private void btnViewStorePressed(ActionEvent event) {
        try {
            final String STORE_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Store.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH));
            fxmlLoader.setController(new ViewStoreController(store, cart));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Store");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Lọc media theo id hoặc title
    private void showFilteredMedia(String filter) {
        if (filter == null || filter.isEmpty()) {
            filteredMedia.setPredicate(media -> true);
        } else {
            if (radioBtnFilterId.isSelected()) {
                try {
                    int filterId = Integer.parseInt(filter);
                    filteredMedia.setPredicate(media -> media.getId() == filterId);
                } catch (NumberFormatException e) {
                    filteredMedia.setPredicate(media -> false); // nếu nhập sai ID (không phải số)
                }
            } else if (radioBtnFilterTitle.isSelected()) {
                String lowerCaseFilter = filter.toLowerCase();
                filteredMedia.setPredicate(media ->
                    media.getTitle().toLowerCase().contains(lowerCaseFilter));
            }
        }
    }
    // Place Order
    @FXML
    private void btnPlaceOrderPressed(ActionEvent event) throws CartEmptyException {
    	try {
            // Kiểm tra giỏ hàng có rỗng không
            if (cart.getItemsOrdered() == null || cart.getItemsOrdered().isEmpty()) {
                throw new CartEmptyException("Your cart is currently empty. Please add items before placing an order!");
            }

            // Tạo hộp thoại xác nhận
            Alert confirmation = new Alert(AlertType.CONFIRMATION);
            confirmation.setTitle("Confirm Order");
            confirmation.setHeaderText("Are you sure you want to place the order?");
            confirmation.setContentText("Please confirm to proceed");

            // Hiển thị hộp thoại xác nhận và chờ phản hồi
            confirmation.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // Người dùng chọn Có, tiếp tục đặt hàng
                    Alert success = new Alert(AlertType.INFORMATION);
                    success.setTitle("Success");
                    success.setHeaderText(null);
                    success.setContentText("Order placed successfully!");
                    success.showAndWait().ifPresent(ok -> {
                        // Xóa toàn bộ media trong giỏ hàng
                        cart.getItemsOrdered().clear();
                        tblMedia.refresh();
                        costLabel.setText("0.00 $");
                        updateButtonBar(null);
                        updateMediaInfo(null);
                    });
                }
            });
        } catch (CartEmptyException e) {
            // Xử lý ngoại lệ khi giỏ hàng rỗng
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Empty Cart");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}