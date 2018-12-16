package ba.unsa.etf.rpr.tutorija8;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.File;

public class Controller {
    public TextField unos;
    public ListView<String> lista;
    public SimpleStringProperty uneseno;
    ObservableList<String> datoteke;
    File file = new File(System.getProperty("user.home"));

    public Controller() {
        unos = new TextField();
        uneseno = new SimpleStringProperty("");

    }

    public void initialize() {
        unos.textProperty().bindBidirectional(uneseno);
        datoteke = FXCollections.observableArrayList();
    }

    @FXML
    public void traziPritisnut(ActionEvent actionEvent) {
        uneseno.set(unos.getText());
        trazi(file);
        lista.setItems(datoteke);
    }

    void trazi(File file) {
        if (file.isFile()) {
            if ((file.getName()).contains(uneseno.get())) datoteke.add(file.getName());
        }
        if (file.isDirectory()) {
            for (File pom : file.listFiles()) {
                trazi(pom);
            }
        }
    }
}
