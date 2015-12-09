/**
 *
 */
package de.oth.hsp.hpplan.view;

import java.util.Arrays;

import de.oth.hsp.common.utils.Decimals;
import de.oth.hsp.common.utils.EditingCell;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
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

    protected void initTable(TableView<?> tableView) {
        tableView.getColumns().clear();
        tableView.getItems().clear();
        tableView.setEditable(true);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    /**
     *
     */
    // TODO: Comment method
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected void addColumnWithRowNumber(TableView<Number[]> tableView, String rowText) {
        TableColumn numberCol = new TableColumn("");

        numberCol.setCellValueFactory(new Callback<CellDataFeatures<Number[], Number>, ObservableValue<Number>>() {
            @Override
            public ObservableValue<Number> call(CellDataFeatures<Number[], Number> p) {
                return new ReadOnlyObjectWrapper(p.getValue()[0]);
            }
        });
        numberCol.setCellFactory(new Callback<TableColumn<Number, Number>, TableCell<Number, Number>>() {
            @Override
            public TableCell<Number, Number> call(TableColumn<Number, Number> param) {
                return new TableCell<Number, Number>() {
                    @Override
                    protected void updateItem(Number item, boolean empty) {
                        super.updateItem(item, empty);
                        this.setStyle(
                                " -fx-font-weight: BOLD;-fx-alignment: CENTER;-fx-background-color: -fx-body-color;-fx-padding: 0;-fx-border-style: solid; -fx-border-color: derive(-fx-base, 80%)  linear-gradient(to bottom, derive(-fx-base,80%) 20%, derive(-fx-base,-10%) 90%)derive(-fx-base, 10%) linear-gradient(to bottom, derive(-fx-base,80%) 20%, derive(-fx-base,-10%) 90%),transparent -fx-table-header-border-color -fx-table-header-border-color transparent;");

                        if (this.getTableRow() != null && item != null) {
                            setText(rowText + (this.getTableRow().getIndex() + 1) + "");
                        } else {
                            setText("");
                        }
                    }
                };
            }
        });

        numberCol.setSortable(false);
        numberCol.setMinWidth(30);
        numberCol.setMaxWidth(30);
        tableView.getColumns().add(numberCol);

    }

    /**
     * @param inputData
     */
    // TODO: Comment method
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected void addTableViewContent(Number[][] inputData, TableView<Number[]> tableView, Decimals decimals,
            String columnHeader) {
        ObservableList<Number[]> dataList = FXCollections.observableArrayList();
        dataList.addAll(Arrays.asList(inputData));
        for (int i = 0; i < inputData[0].length; i++) {
            TableColumn tableColumn = new TableColumn(columnHeader + String.valueOf(i + 1));
            final int colNo = i;
            // tableColumn.setCellFactory(TextFieldTableCell.<String>
            // forTableColumn());

            tableColumn.setCellFactory(new Callback<TableColumn<Number, Number>, TableCell<Number, Number>>() {
                @Override
                public TableCell<Number, Number> call(TableColumn<Number, Number> p) {
                    return new EditingCell(decimals);
                }
            });

            tableColumn
                    .setCellValueFactory(new Callback<CellDataFeatures<Number[], Number>, ObservableValue<Number>>() {

                        @Override
                        public ObservableValue<Number> call(CellDataFeatures<Number[], Number> p) {
                            return new SimpleDoubleProperty((p.getValue()[colNo]).doubleValue());
                        }
                    });

            tableColumn.setSortable(false);
            tableColumn.setMinWidth(75);
            tableView.getColumns().add(tableColumn);
            ;
        }
        tableView.setItems(dataList);

        resizeTableRowHeight(tableView);

    }

    protected void addTableViewContent(Number[] inputData, TableView<Number[]> tableView, Decimals decimals,
            String columnHeader) {
        Number[][] values = new Number[1][inputData.length];
        values[0] = inputData;
        addTableViewContent(values, tableView, decimals, columnHeader);
    }

    protected void resizeTableRowHeight(TableView tableView) {
        tableView.setFixedCellSize(28);
        tableView.prefHeightProperty()
                .bind(tableView.fixedCellSizeProperty().multiply(Bindings.size(tableView.getItems()).add(2)));
        tableView.minHeightProperty().bind(tableView.prefHeightProperty());
        tableView.maxHeightProperty().bind(tableView.prefHeightProperty());
    }

}