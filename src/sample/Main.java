package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    HeavyTile[] tiles = new HeavyTile[60];
    private MouseEvent pe;
    private Pane root;

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new Pane();
        root.getStyleClass().add("root");


        for (int i = 0; i < tiles.length; i++) {
            HeavyTile t = new HeavyTile(i + " NO NAME");
            t.setLayoutX((i % (tiles.length / 6) * (152 + 16)));
            t.setLayoutY((i / (tiles.length / 6)) * (152 + 16));
            t.setPrefSize(152, 152);
            t.setOnMousePressed(this::onTilePress);
            t.setOnMouseDragged(this::onTileDrag);
            root.getChildren().add(t);
            tiles[i] = t;
        }
        root.setOnMouseClicked(this::onClick);

        primaryStage.setFullScreen(true);
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
        final double x = mouseEvent.getSceneX() - pe.getX();
        node.setLayoutX(x < 0 ? 0 : x + node.prefWidth(0) > root.getWidth() ? root.getWidth() - node.prefWidth(0) : x);
        node.setLayoutY(y < 0 ? 0 : y + node.prefHeight(0) > root.getHeight() ? root.getHeight() - node.prefHeight(0) : y);
    }

    private void onClick(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            if (!mouseEvent.isAltDown()) {
                int s = 2;
                List<KeyFrame> keyFrames = new ArrayList<>();
                for (int i = 0; i < tiles.length; i++) {
                    HeavyTile t = tiles[i];
                    if (t == null)
                        continue;
                    KeyValue keyValueX = new KeyValue(t.layoutXProperty(), (i % (tiles.length / 6)) * (152 + 16), new Interpolator() {
                        @Override
                        protected double curve(double t) {
                            return (s + 1) * Math.pow(t, 3) - s * Math.pow(t, 2);
                        }
                    });
                    KeyValue keyValueY = new KeyValue(t.layoutYProperty(), (i / (tiles.length / 6)) * (152 + 16), new Interpolator() {
                        @Override
                        protected double curve(double t) {
                            return (s + 1) * Math.pow(t, 3) - s * Math.pow(t, 2);
                        }
                    });

                    KeyValue keyOpacity = new KeyValue(t.opacityProperty(), 1.0, new Interpolator() {
                        @Override
                        protected double curve(double t) {
                            return (s + 1) * Math.pow(t, 3) - s * Math.pow(t, 2);
                        }
                    });

                    Duration duration = Duration.millis(100 + i * 150);
                    final KeyFrame frame = new KeyFrame(duration, keyValueX, keyValueY, keyOpacity);
                    keyFrames.add(frame);

                    if (Integer.parseInt(t.getName().getText().substring(0,2).trim()) == i) {
                        tiles[i] = null;
                        continue;
                    }

                }
                if (!keyFrames.isEmpty()) {
                    Timeline line = new Timeline(keyFrames.toArray(new KeyFrame[keyFrames.size()]));
                    line.setOnFinished(e -> {
                        onClick(new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 2, false, false, true, false, false, false, false, false, false, false, null));
                    });
                    line.play();
                }
            } else {
                int s = 2;
                List<KeyFrame> keyFrames = new ArrayList<>();
                Random r = new Random();
                shuffleArray(tiles);
                for (int i = 0; i < tiles.length; i++) {
                    HeavyTile t = tiles[i];
                    if (t == null)
                        continue;
//                    if (!mouseEvent.isControlDown() && Integer.parseInt(t.getName().getText().substring(0,2)) == i) {
//                        continue;
//                    }
                    KeyValue keyValueX = new KeyValue(t.layoutXProperty(), r.nextInt((int) (root.getWidth() - 152)), new Interpolator() {
                        @Override
                        protected double curve(double t) {
                            return (s + 1) * Math.pow(t, 3) - s * Math.pow(t, 2);
                        }
                    });
                    KeyValue keyValueY = new KeyValue(t.layoutYProperty(), r.nextInt((int) (root.getHeight() - 152)), new Interpolator() {
                        @Override
                        protected double curve(double t) {
                            return (s + 1) * Math.pow(t, 3) - s * Math.pow(t, 2);
                        }
                    });

                    KeyValue keyOpacity = new KeyValue(t.opacityProperty(), 0.3, new Interpolator() {
                        @Override
                        protected double curve(double t) {
                            return (s + 1) * Math.pow(t, 3) - s * Math.pow(t, 2);
                        }
                    });

                    Duration duration = Duration.millis(100 + i * 150);
                    final KeyFrame frame = new KeyFrame(duration, keyValueX, keyValueY, keyOpacity);
                    keyFrames.add(frame);
                }
                if (!keyFrames.isEmpty()) {
                    Timeline line = new Timeline(keyFrames.toArray(new KeyFrame[keyFrames.size()]));
                    line.setOnFinished(event -> {
                        onClick(new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 2, false, false, false, false, false, false, false, false, false, false, null));
                    });
                    line.play();
                }
            }
        }
    }

    static void shuffleArray(HeavyTile[] ar) {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            HeavyTile a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
