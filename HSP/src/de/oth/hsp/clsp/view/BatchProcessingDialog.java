package de.oth.hsp.clsp.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BatchProcessingDialog extends Stage implements Initializable {

    RootLayoutController root;

    /**
     * Create a new BatchProcessing Dialog based on the reference of the
     * rootLayoutController
     *
     * @param rootLayoutController
     *            - At the moment, this parameter is not used. It is for
     *            possible future use.
     */
    public BatchProcessingDialog(RootLayoutController rootLayoutController) {
        setTitle("Stapelverarbeitung");
        root = rootLayoutController;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BatchProcessingDialog.fxml"));

        try {
            setScene(new Scene((Parent) fxmlLoader.load()));
            this.initModality(Modality.APPLICATION_MODAL);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Called when FXML file is load()ed (via FXMLLoader.load()). It will
     * execute before the form is shown.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // we don't need this, because we use the initialize method in the
        // controller class
    }
}
