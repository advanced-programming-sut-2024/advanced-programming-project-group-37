module Gwent {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    exports view;
    exports view.OtherMenu;
    exports view.GameMenu;

    opens view to javafx.fxml;
}