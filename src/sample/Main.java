package sample;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    HeavyTile[] tiles = new HeavyTile[20];
    private MouseEvent pe;
    private Pane root;

    @Override
    public void start(Stage primaryStage) throws Exception{
        root = new Pane();
        root.getStyleClass().add("root");


        for (int i = 0; i < 20; i++) {
            HeavyTile t = new HeavyTile("NO NAME");
            t.setLayoutY((i / 5) * (152 + 16));
            t.setLayoutX((i % 5) * (152 + 16));
            t.setPrefSize(152, 152);
            t.setOnMousePressed(this::onTilePress);
            t.setOnMouseDragged(this::onTileDrag);
            root.getChildren().add(t);
            tiles[i] = t;
        }

        root.setOnMouseClicked(this::onClick);

//        primaryStage.setFullScreen(true);
        primaryStage.setTitle("Hello World");
        final Scene scene = new Scene(root);
        scene.getStylesheets().add("sample/main.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void onTilePress(MouseEvent mouseEvent) {
        this.pe = mouseEvent;
    }

    private void onTileDrag(MouseEvent mouseEvent) {
        Node node = ((Node) mouseEvent.getSource());
        final double y = mouseEvent.getSceneY() - pe.getY();
        final double x =  mouseEvent.getSceneX() - pe.getX();
        node.setLayoutX(x < 0 ? 0 : x + node.prefWidth(0) > root.getWidth() ? root.getWidth() - node.prefWidth(0) :  x);
        node.setLayoutY(y < 0 ? 0 : y + node.prefHeight(0) > root.getHeight() ? root.getHeight() - node.prefHeight(0) :  y);
    }

    private void onClick(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            List<KeyFrame> keyFrames = new ArrayList<>();
            final int s = 2;
            for (int i = 0; i < 20; i++) {
                HeavyTile t = tiles[i];
                KeyValue keyValueX = new KeyValue(t.layoutXProperty(), (i % 5) * (152 + 16), new Interpolator() {
                    @Override
                    protected double curve(double t) {
                        return (s+1)*Math.pow(t,3) - s*Math.pow(t,2);
                    }
                });
                KeyValue keyValueY = new KeyValue(t.layoutYProperty(), (i / 5) * (152 + 16), new Interpolator() {
                    @Override
                    protected double curve(double t) {
                        return (s+1)*Math.pow(t,3) - s*Math.pow(t,2);
                    }
                });

                Duration duration = Duration.millis(750 + i * 25);
                final KeyFrame frame = new KeyFrame(duration, keyValueX, keyValueY);
                keyFrames.add(frame);
            }
            Timeline line = new Timeline(keyFrames.toArray(new KeyFrame[keyFrames.size()]));
            line.play();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
