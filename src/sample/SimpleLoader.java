package sample;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class SimpleLoader {

    public static void load(Parent parent) {
        Class< ? > clazz = parent.getClass();
        FXMLLoader loader = new FXMLLoader(clazz.getResource(clazz.getSimpleName() + ".fxml"), null);
        loader.setRoot(parent);
        loader.setController(parent);
        try {
            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
