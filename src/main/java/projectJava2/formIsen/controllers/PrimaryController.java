package projectJava2.formIsen.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import projectJava2.formIsen.App;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
