package de.oth.hsp.clsp.view;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javafx.concurrent.Task;
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
import de.oth.hsp.clsp.ilog.CLSPSolvingAlgorithmFloat;
import de.oth.hsp.common.ilog.ILogSolvingAlgorithm;
import de.oth.hsp.common.utils.FileOperations;

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
        if (lvChoosenDatFiles.getItems().isEmpty()) {
            alertNoInput();
            return;
        }

        Path destinationDirectory = Paths.get(txtDestination.getText());
        if (!Files.exists(destinationDirectory)) {
            alertNoDestination();
            return;
        }

        List<Path> datPaths = new ArrayList<Path>();
        List<Path> outputDirectories = new ArrayList<Path>();

        lvChoosenDatFiles.getItems().stream().map(f -> f.toPath()).forEach(p -> {
            datPaths.add(p);
            String filename = FileOperations.getNameWithoutExtension(p);
            outputDirectories.add(destinationDirectory.resolve(filename));
        });

        // create for each result a directory
        for (Path dir : outputDirectories) {
            if (!Files.isDirectory(dir)) {
                try {
                    Files.createDirectory(dir);
                } catch (IOException e) {
                    e.printStackTrace();
                    alertDirectoryExists();
                    return;
                }
            }
        }

        Task<Void> batchTask = new Task<Void>() {

            @Override
            protected Void call() throws Exception {
                btnStart.setDisable(true);
                btnChooseDatFiles.setDisable(true);
                btnChooseDestination.setDisable(true);
                txtDestination.setEditable(false);
                piBatchProcessingProgress.setVisible(true);

                // TODO call batch processing
                // TODO save results in folders
                // Thread.sleep(5000);

                ILogSolvingAlgorithm<?, ?> algo = new CLSPSolvingAlgorithmFloat();
                for (int i = 0; i < datPaths.size(); i++) {
                    Path datPath = datPaths.get(i);
                    Path datDirectory = datPath.getParent();
                    Path outputDirectory = outputDirectories.get(i);

                    String datName = FileOperations.getNameWithoutExtension(datPath);
                    algo.solve(datName, FileOperations.canonicalize(datDirectory) + File.separator,
                            FileOperations.canonicalize(outputDirectory) + File.separator);

                    updateProgress(i, datPaths.size());
                }

                // directories
                // choosenDatFiles
                piBatchProcessingProgress.setVisible(false);
                btnStart.setDisable(false);
                btnChooseDatFiles.setDisable(false);
                btnChooseDestination.setDisable(false);
                txtDestination.setEditable(true);
                return null;
            }
        };

        batchProcess = new Thread(batchTask);
        batchProcess.start();
    }

    private void alertDirectoryExists() {
        // TODO Auto-generated method stub

    }

    private void alertNoInput() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Fehler");
        alert.setHeaderText("Fehler bei den Eingabedateien");
        alert.setContentText("Es wurden keine Eingabedateien angegeben, bitte wählen Sie mindestens eine Datei aus.");

        alert.showAndWait();
    }

    private void alertNoDestination() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Fehler");
        alert.setHeaderText("Fehler beim Zielort");
        alert.setContentText("Der angegebene Zielort existiert nicht, bitte wählen Sie einen anderen.");

        alert.showAndWait();
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
