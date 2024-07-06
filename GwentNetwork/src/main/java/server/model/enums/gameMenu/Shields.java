package server.model.enums.gameMenu;

import javafx.scene.image.Image;

public enum Shields {
    MONSTER(new Image(Shields.class.getResource("/asset/img/icons/monster1.png").toExternalForm()),
            new Image(Shields.class.getResource("/asset/img/icons/monster2.png").toExternalForm())),
    EMPIRE_NILFGARDEN(new Image(Shields.class.getResource("/asset/img/icons/nilf1.png").toExternalForm()),
            new Image(Shields.class.getResource("/asset/img/icons/nilf2.png").toExternalForm())),
    REALMS_NORTHERN(new Image(Shields.class.getResource("/asset/img/icons/realms1.png").toExternalForm()),
            new Image(Shields.class.getResource("/asset/img/icons/realms2.png").toExternalForm())),
    SCOIATEAL(new Image(Shields.class.getResource("/asset/img/icons/tael1.png").toExternalForm()),
            new Image(Shields.class.getResource("/asset/img/icons/tael2.png").toExternalForm())),
    SKELLIGE(new Image(Shields.class.getResource("/asset/img/icons/skellige1.png").toExternalForm()),
            new Image(Shields.class.getResource("/asset/img/icons/skellige2.png").toExternalForm()));

    private final Image image1, image2;

    Shields(Image image1, Image image2) {
        this.image1 = image1;
        this.image2 = image2;
    }

    public Image getImage1() {
        return image1;
    }
    public Image getImage2() {
        return image2;
    }
}