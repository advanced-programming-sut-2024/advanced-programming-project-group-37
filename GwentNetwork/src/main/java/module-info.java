module GwentNetwork {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.google.gson;

    //opens views to javafx.fxml;
    opens client to javafx.fxml, javafx.graphics, com.google.gson;
    opens client.ClientView to javafx.fxml, javafx.graphics, com.google.gson;
    opens client.ClientView.OtherMenu to javafx.fxml, javafx.graphics, com.google.gson;
    opens client.ClientView.GameMenu to javafx.fxml, javafx.graphics, com.google.gson;

    opens message.client to javafx.fxml, javafx.graphics, com.google.gson;
    opens message.client.LoginMenu to javafx.fxml, javafx.graphics, com.google.gson;
    opens message.client.profileMenu to javafx.fxml, javafx.graphics, com.google.gson;
    opens message.client.MainMenu to javafx.fxml, javafx.graphics, com.google.gson;
    opens message.client.gameLobby to javafx.fxml, javafx.graphics, com.google.gson;
    opens message.client.pregame to javafx.fxml, javafx.graphics, com.google.gson;
    opens message.client.Game to javafx.fxml, javafx.graphics, com.google.gson;
    opens message.server  to javafx.fxml, javafx.graphics, com.google.gson;
    opens message.enums.card to javafx.fxml, javafx.graphics, com.google.gson;
    opens message.enums.gameMenu to javafx.fxml, javafx.graphics, com.google.gson;
    opens message.enums.loginMenu to javafx.fxml, javafx.graphics, com.google.gson;
    opens message.enums.profileMenu to javafx.fxml, javafx.graphics, com.google.gson;
    opens message.enums.mainMenu to javafx.fxml, javafx.graphics, com.google.gson;

    opens server to javafx.fxml, javafx.graphics, com.google.gson;
    opens server.controller.loginController to javafx.fxml, javafx.graphics, com.google.gson;
    opens server.controller.profileController to javafx.fxml, javafx.graphics, com.google.gson;
    opens server.controller.MessageController to javafx.fxml, javafx.graphics, com.google.gson;
    opens server.controller.GameController to javafx.fxml, javafx.graphics, com.google.gson;
    opens server.model to javafx.fxml, javafx.graphics, com.google.gson;
    opens server.model.toolClasses to javafx.fxml, javafx.graphics, com.google.gson;
    opens server.model.gameTable to javafx.fxml, javafx.graphics, com.google.gson;

    // open models to gson and jackson


    exports client;
    exports client.ClientView;
    exports client.ClientView.OtherMenu;
    exports client.ClientView.GameMenu;

    exports message.client;
    exports message.client.LoginMenu;
    exports message.client.profileMenu;
    exports message.client.MainMenu;
    exports message.client.Game;
    exports message.client.gameLobby;
    exports message.client.pregame;

    exports server;
    exports server.controller.profileController;
    exports server.controller.loginController;
    exports server.controller.MessageController;
    exports server.controller.GameController;
    exports server.model;
    exports server.model.gameTable;
    exports server.model.toolClasses;
    exports message.enums.loginMenu;
}