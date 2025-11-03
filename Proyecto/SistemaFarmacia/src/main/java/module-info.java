module com.example.sistemafarmacia {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;

    opens com.example.sistemafarmacia to javafx.graphics, javafx.fxml;
    exports com.example.sistemafarmacia;


    opens com.example.sistemafarmacia.Vista.categoria to javafx.fxml;
    opens com.example.sistemafarmacia.Vista.inventario to javafx.fxml;
    opens com.example.sistemafarmacia.Vista.navegacion to javafx.fxml;
    opens com.example.sistemafarmacia.Vista.principal to javafx.fxml;
    opens com.example.sistemafarmacia.Vista.reportes to javafx.fxml;
    opens com.example.sistemafarmacia.Control.categorias to javafx.fxml;


    exports com.example.sistemafarmacia.Vista.categoria;
    exports com.example.sistemafarmacia.Vista.inventario;
    exports com.example.sistemafarmacia.Vista.navegacion;
    exports com.example.sistemafarmacia.Vista.principal;
    exports com.example.sistemafarmacia.Vista.reportes;
    exports com.example.sistemafarmacia.Control.categorias;
}
