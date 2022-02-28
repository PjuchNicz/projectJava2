package projectJava2.formIsen.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import projectJava2.formIsen.App;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("controllers/primary");
    }
}