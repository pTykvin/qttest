package sample;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class HeavyTile extends StackPane {
    private final String v_name;
    @FXML
    private Label image;
    @FXML
    private Label name;
    @FXML
    private StackPane stub;
    @FXML
    private BorderPane product;
    private StringProperty imageNameProperty = new SimpleStringProperty();
    private StringBinding reduceTextProperty;
    private ObjectBinding<ImageView> imageProperty;

    public HeavyTile(String name) {
        v_name = name;
        SimpleLoader.load(this);
    }

    @FXML
    private void initialize() {

        product.setVisible(true);
        name.setText(v_name);
        image.setText(v_name.substring(0, 2));
    }

    public Label getName() {
        return name;
    }
}
