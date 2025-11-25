module com.example.sistema_farmacia {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;


    opens com.example.sistema_farmacia.controller.controladores to javafx.fxml;
    exports com.example.sistema_farmacia.controller.controladores to javafx.fxml;

    opens com.example.sistema_farmacia to javafx.fxml;
    exports com.example.sistema_farmacia;
    opens com.example.sistema_farmacia.view to javafx.fxml;
    exports com.example.sistema_farmacia.view.navegacion;
    opens com.example.sistema_farmacia.view.navegacion to javafx.fxml;
}
