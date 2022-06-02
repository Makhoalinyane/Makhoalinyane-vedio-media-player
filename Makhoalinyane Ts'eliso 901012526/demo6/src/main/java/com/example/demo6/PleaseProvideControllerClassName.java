package com.example.demo6;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;

public class PleaseProvideControllerClassName implements Initializable {
    @FXML
    private Button back,next,pause,play,stop;
    @FXML
    private Slider d,s;
    @FXML
    private Label l;
    @FXML
    private AnchorPane pane;
    @FXML
    private MediaView m;
    private MediaPlayer p;
    @FXML
    private Media media;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String video = getClass().getResource("malome.mp4").toExternalForm();
        media = new Media(video);
        p = new MediaPlayer(media);
        m.setMediaPlayer(p);
        s.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                p.setVolume(s.getValue()*0.01);
            }
        });

        p.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observableValue, Duration duration, Duration t1) {
                d.setValue(t1.toSeconds());
            }
        });
        d.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                p.seek(Duration.seconds(d.getValue()));
            }
        });
        d.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                p.seek(Duration.seconds(d.getValue()));
            }
        });
        p.setOnReady(new Runnable() {
            @Override
            public void run() {
                Duration total= media.getDuration();
                d.setMax(total.toSeconds());
            }
        });

    }
    public void playAction(){
        p.play();
    }
    public void pauseAction(){
        p.pause();
    }
    public void stopAction() {p.stop();}
    public void nextAction() {
        p.seek(p.getCurrentTime().add(Duration.seconds(10)));
        System.out.println("+15");
    }
    public void backAction() {
        p.seek(p.getCurrentTime().add(Duration.seconds(-10)));
        System.out.println("-15");
    }


}
