package ch.fhnw.oop2.hydropowerfx.util;

import javafx.scene.image.Image;

public abstract class ImageUtil {

    public static Image getImage(String url) {
        Image image;

        try {
            image = new Image(url);
        } catch (Exception e) {
            image = new Image("images/invalid_icon.png");
        }
        return image;
    }
}
