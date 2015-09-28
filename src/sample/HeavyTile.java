package sample;

import java.util.Optional;

import org.apache.commons.lang.StringUtils;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.css.PseudoClass;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
        image.setText(StringUtils.substring(v_name, 0, 2));
    }


}
