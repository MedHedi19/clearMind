module tn.esprit.clearmind {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens tn.esprit.clearmind.controllers to javafx.fxml;
    opens tn.esprit.clearmind to javafx.fxml;
    exports tn.esprit.clearmind;
}
