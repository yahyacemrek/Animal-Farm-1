module org.example.animalfarm {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens org.example.animalfarm to javafx.fxml, com.fasterxml.jackson.databind;
    exports org.example.animalfarm;
}