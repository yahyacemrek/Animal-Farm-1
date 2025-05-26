module org.example.animalfarm {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.desktop;

    opens org.example.animalfarm to javafx.fxml;
    exports org.example.animalfarm;
}