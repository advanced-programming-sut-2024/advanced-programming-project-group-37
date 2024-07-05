module GwentNetwork {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.google.gson;

    exports client.ClientView;

    opens client.ClientView to javafx.fxml;
}