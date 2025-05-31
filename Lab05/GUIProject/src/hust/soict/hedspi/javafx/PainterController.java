package hust.soict.hedspi.javafx;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.RadioButton;

public class PainterController {

	@FXML
    private Pane drawingAreaPane;
    @FXML
    private RadioButton penRadioButton;
    @FXML
    private RadioButton eraserRadioButton;

    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
    	Color color = penRadioButton.isSelected() ? Color.BLACK : Color.WHITE;
    	Circle newCircle = new Circle(event.getX(), event.getY(), 4, color);
        drawingAreaPane.getChildren().add(newCircle);
    }

    @FXML
    void clearButtonPressed(ActionEvent event) {
    	drawingAreaPane.getChildren().clear();
    }

}
