package message.client.Game;

import javafx.scene.image.Image;
import message.client.ClientMessage;

public class LastState extends ClientMessage {
    private Image image;

    public LastState(Image image, String token) {
        this.image = image;
        this.token = token;
    }

    public Image getImage() {
        return image;
    }
}
