package de.oth.hsp.clsp.view;

import java.io.File;
import java.util.List;

import de.oth.hsp.common.utils.FileOperations;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class BatchProcessingDialogController {

    @FXML
    private Button btnChooseDatFiles;

    @FXML
    private ListView<File> lvChoosenDatFiles;

    @FXML
    private Button btnChooseDestination;

    @FXML
    private TextField txtDestination;

    @FXML
    private Button btnStart;

    @FXML
    private Button btnClose;

    @FXML
    private ProgressIndicator piBatchProcessingProgress;

    private Thread batchProcess;

    public BatchProcessingDialogController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Method called when the close Button is pressed. Closes the application.
     */
    @FXML
    public void onActionClose() {
        closeDialog();
    }

    /**
     * Method called, when the start Button is pressed. Starts the batch
     * processing and creates for each result a separate folder.
     */
    @FXML
    public void onActionStart() {
        if (!lvChoosenDatFiles.getItems().isEmpty()) {
            if (new File(txtDestination.getText()).exists()) {

                // create for each result a directory
                for (File file : lvChoosenDatFiles.getItems()) {
                    new File(txtDestination.getText() + File.separator + FileOperations.getNameWithoutExtension(file))
                            .mkdirs();
                }

                // TODO call ILog paramter dialog (e.g. accuracy), if necessary

                batchProcess = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            btnStart.setDisable(true);
                            btnChooseDatFiles.setDisable(true);
                            btnChooseDestination.setDisable(true);
                            txtDestination.setEditable(false);
                            piBatchProcessingProgress.setVisible(true);

                            // TODO call batch processing
                            // TODO save results in folders
                            Thread.sleep(5000);

                            piBatchProcessingProgress.setVisible(false);
                            btnStart.setDisable(false);
                            btnChooseDatFiles.setDisable(false);
                            btnChooseDestination.setDisable(false);
                            txtDestination.setEditable(true);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                });

                batchProcess.start();

            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Fehler");
                alert.setHeaderText("Fehler beim Zielort");
                alert.setContentText("Der angegebene Zielort existiert nicht, bitte wählen Sie einen anderen.");

                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText("Fehler bei den Eingabedateien");
            alert.setContentText(
                    "Es wurden keine Eingabedateien angegeben, bitte wählen Sie mindestens eine Datei aus.");

            alert.showAndWait();
        }
    }

    /**
     * Method called, when the ChooseDatFiles Button is pressed. Opens a
     * FileChooserDialog and saves the selected files in the ListView.
     */
    @FXML
    public void onActionChooseDatFiles() {
        FileChooser fChooser = new FileChooser();
        fChooser.setTitle("Dat Dateien auswählen");
        fChooser.getExtensionFilters().add(new ExtensionFilter("dat - Datei", "*.dat"));

        List<File> selectedFiles = fChooser.showOpenMultipleDialog(null);
        // I did not use a ObservableList, because the fileChooser only gives a
        // List<File>.
        // To use an ObservableList, we have to copy them in a new List --> no
        // observable function anymore --> no advantage
        if (selectedFiles != null) {
            lvChoosenDatFiles.getItems().setAll(selectedFiles);
        }
    }

    /**
     * Method called, when the ChooseDestiantion Button is pressed. Opens a
     * DirectoryChooserDialog and saves the selected directory in the TextField.
     */
    @FXML
    public void onActionChooseDestination() {
        DirectoryChooser dChooser = new DirectoryChooser();
        dChooser.setTitle("Zielort wählen");
        txtDestination.setText(dChooser.showDialog(null).toString());
    }

    /**
     * close the dialog
     */
    private void closeDialog() {
        if (batchProcess != null) {
            batchProcess.interrupt();
        }
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

}
