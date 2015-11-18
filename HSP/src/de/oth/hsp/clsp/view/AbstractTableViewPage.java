/**
 * 
 */
package de.oth.hsp.clsp.view;

import java.util.Arrays;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

/**
 * @author danielsonnleitner
 *
 */
public abstract class AbstractTableViewPage {

    /**
     * 
     */
    public AbstractTableViewPage() {
        // TODO Auto-generated constructor stub
    }

    /**
     *
     */
    // TODO: Comment method
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected void addColumnWithRowNumber(TableView<String[]> tableView) {
        TableColumn numberCol = new TableColumn("#");
        numberCol.setMinWidth(30);
        numberCol.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<String[], String> p) {
                return new ReadOnlyObjectWrapper(p.getValue()[0]);
            }
        });
        numberCol.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override
            public TableCell<String, String> call(TableColumn<String, String> param) {
                return new TableCell<String, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (this.getTableRow() != null && item != null) {
                            setText(this.getTableRow().getIndex() + 1 + "");
                        } else {
                            setText("");
                        }
                    }
                };
            }
        });
        numberCol.setSortable(false);
        tableView.getColumns().add(numberCol);
    }

    /**
     * @param inputData
     */
    // TODO: Comment method
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected void addTableViewContent(String[][] inputData, TableView<String[]> tableView) {
        ObservableList<String[]> dataList = FXCollections.observableArrayList();
        dataList.addAll(Arrays.asList(inputData));
        for (int i = 0; i < inputData[0].length; i++) {
            TableColumn tableColumn = new TableColumn("k: " + String.valueOf(i + 1));
            final int colNo = i;
            tableColumn.setCellFactory(TextFieldTableCell.<String> forTableColumn());
            tableColumn
                    .setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
                        @Override
                        public ObservableValue<String> call(CellDataFeatures<String[], String> p) {
                            return new SimpleStringProperty((p.getValue()[colNo]));
                        }
                    });
            tableColumn.setSortable(false);
            tableColumn.setMinWidth(75);
            tableView.getColumns().add(tableColumn);
        }
        tableView.setItems(dataList);
    }

}
