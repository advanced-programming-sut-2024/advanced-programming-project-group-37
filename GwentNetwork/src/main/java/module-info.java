module GwentNetwork {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.google.gson;

    exports client.ClientView;
    exports client.ClientView.OtherMenu;

    opens client.ClientView to javafx.fxml;

    exports message.client.LoginMenu;
    opens message.client.LoginMenu to com.google.gson;
    opens message.client to com.google.gson;
}