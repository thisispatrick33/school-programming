package sample;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    MediaView mediaView;
    MediaPlayer mediaPlayer;
    Media media;

    @FXML
    Slider volumeSlider;

    @FXML
    Slider progressSlider;

    @FXML
    ImageView btnPlay;

    @FXML
    ChoiceBox speed;

    @FXML
    CheckBox loop;

    InvalidationListener updateProgressSliderValue = new InvalidationListener() {
        @Override
        public void invalidated(Observable observable) {
            if(mediaPlayer.currentTimeProperty().getValue().toSeconds()+0.1>=mediaPlayer.totalDurationProperty().getValue().toSeconds()){
                if(loop.isSelected()){
                    mediaPlayer.seek(mediaPlayer.getStartTime());
                }
                else {
                    mediaPlayer.pause();
                    btnPlay.setId("btnPlay");
                    btnPlay.setImage(new Image("/icons/play-button.png"));
                }
            }
            progressSlider.setValue(mediaPlayer.currentTimeProperty().getValue().toSeconds());
        }
    };

    InvalidationListener updateVolumeValue = new InvalidationListener() {
        @Override
        public void invalidated(Observable observable) {
            mediaPlayer.setVolume(volumeSlider.getValue()/100);
        }
    };

    InvalidationListener updateSpeed = new InvalidationListener() {
        @Override
        public void invalidated(Observable observable) {
            if(speed.getValue().toString().compareTo("Normal")==0){
                mediaPlayer.setRate(1);
            }
            if(speed.getValue().toString().compareTo("x0.25")==0){
                mediaPlayer.setRate(0.25);
            }
            if(speed.getValue().toString().compareTo("x0.50")==0){
                mediaPlayer.setRate(0.50);
            }
            if(speed.getValue().toString().compareTo("x1.50")==0){
                mediaPlayer.setRate(1.50);
            }
            if(speed.getValue().toString().compareTo("x2.00")==0){
                mediaPlayer.setRate(2);
            }
        }
    };

    Runnable setProgressSliderMaxValue = new Runnable() {
        @Override
        public void run() {
            progressSlider.setMax(mediaPlayer.getTotalDuration().toSeconds());
        }
    };

    @Override
    public void initialize(URL location, ResourceBundle resources){
        setupSpeed();

        setupMedia();

        volumeSlider.setValue(mediaPlayer.getVolume()*100);
        volumeSlider.valueProperty().addListener(updateVolumeValue);

        mediaPlayer.setOnReady(setProgressSliderMaxValue);

        mediaPlayer.currentTimeProperty().addListener(updateProgressSliderValue);

        speed.valueProperty().addListener(updateSpeed);

    }

    public void setupMedia(){
        String path = new File("src/media/video.mp4").getAbsolutePath();
        media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        DoubleProperty width = mediaView.fitWidthProperty();
        DoubleProperty height = mediaView.fitHeightProperty();
        width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
    }

    public void setupSpeed(){
        speed.getItems().add("x0.25");
        speed.getItems().add("x0.50");
        speed.getItems().add("Normal");
        speed.getItems().add("x1.50");
        speed.getItems().add("x2.00");
        speed.setValue(speed.getItems().get(2));
    }

    public void play(){
        if(btnPlay.getId().compareTo("btnPlay")==0){
            mediaPlayer.play();
            btnPlay.setImage(new Image("/icons/pause.png"));
            btnPlay.setId("btnPause");
        }
        else {
            mediaPlayer.pause();
            btnPlay.setId("btnPlay");
            btnPlay.setImage(new Image("/icons/play-button.png"));
        }
    }

    public void skip10(){
        mediaPlayer.seek(Duration.seconds(mediaPlayer.getCurrentTime().toSeconds()+10));
        progressSlider.setValue(progressSlider.getValue()+10);
    }
    public void reverse10(){
        mediaPlayer.seek(Duration.seconds(mediaPlayer.getCurrentTime().toSeconds()-10));
        progressSlider.setValue(progressSlider.getValue()-10);
    }

    public void updateChange(){
        mediaPlayer.seek(Duration.seconds(progressSlider.getValue()));
    }
}
