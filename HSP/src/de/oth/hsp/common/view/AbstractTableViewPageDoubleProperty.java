/**
 *
 */
package de.oth.hsp.common.view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import de.oth.hsp.common.utils.Decimals;
import de.oth.hsp.common.utils.EditingCellDoubleProperty;

/**
 * @author danielsonnleitner
 *
 */
public abstract class AbstractTableViewPageDoubleProperty {

    /**
     *
     */
    public AbstractTableViewPageDoubleProperty() {
        // TODO Auto-generated constructor stub

    }

    protected void initTable(TableView<?> tableView, boolean editable) {
        // tableView.getColumns().clear();
        // tableView.getItems().clear();
        tableView.setEditable(editable);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    /**
     * 3
     *
     */
    // TODO: Comment method
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected void addColumnWithRowNumber(TableView<DoubleProperty[]> tableView, String rowText) {
        TableColumn numberCol = new TableColumn("");

        numberCol
                .setCellValueFactory(new Callback<CellDataFeatures<DoubleProperty[], Number>, ObservableValue<Number>>() {
                    @Override
                    public ObservableValue<Number> call(CellDataFeatures<DoubleProperty[], Number> p) {
                        return new ReadOnlyObjectWrapper(p.getValue()[0]);
                    }
                });
        numberCol
                .setCellFactory(new Callback<TableColumn<DoubleProperty, Number>, TableCell<DoubleProperty, Number>>() {
                    @Override
                    public TableCell<DoubleProperty, Number> call(TableColumn<DoubleProperty, Number> param) {
                        return new TableCell<DoubleProperty, Number>();
                        // doesn't work right now
                        // return new TableCell<DoubleProperty, Number>() {
                        //
                        // // @Override
                        // @Override
                        // protected void updateItem(Number item, boolean empty)
                        // {
                        // super.updateItem(item, empty);
                        // this.setStyle(" -fx-font-weight: BOLD;-fx-alignment: CENTER;-fx-background-color: -fx-body-color;-fx-padding: 0;-fx-border-style: solid; -fx-border-color: derive(-fx-base, 80%)  linear-gradient(to bottom, derive(-fx-base,80%) 20%, derive(-fx-base,-10%) 90%)derive(-fx-base, 10%) linear-gradient(to bottom, derive(-fx-base,80%) 20%, derive(-fx-base,-10%) 90%),transparent -fx-table-header-border-color -fx-table-header-border-color transparent;");
                        //
                        // if (this.getTableRow() != null && item != null) {
                        // // setText(rowText +
                        // // (this.getTableRow().getIndex() + 1) +
                        // // "");
                        // setText("test1");
                        // } else {
                        // setText("test2");
                        // }
                        // }
                        // };
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
    protected void addTableViewContent(DoubleProperty[][] inputData, TableView<DoubleProperty[]> tableView,
            Decimals decimals, String columnHeader, ObservableList<DoubleProperty[]> dataList) {
        // ObservableList<Number[]> dataList =
        // FXCollections.observableArrayList();

        // ObservableList<DoubleProperty[]> dataList =
        // FXCollections.observableArrayList();

        // dataList.clear();
        // dataList.addAll(Arrays.asList(inputData));
        for (int i = 0; i < inputData[0].length; i++) {
            TableColumn tableColumn = new TableColumn(columnHeader + String.valueOf(i + 1));
            final int colNo = i;
            // tableColumn.setCellFactory(TextFieldTableCell.<String>
            // forTableColumn());

            tableColumn
                    .setCellFactory(new Callback<TableColumn<DoubleProperty, Number>, TableCell<DoubleProperty, Number>>() {
                        @Override
                        public TableCell<DoubleProperty, Number> call(TableColumn<DoubleProperty, Number> p) {
                            return new EditingCellDoubleProperty(decimals);
                        }
                    });

            tableColumn
                    .setCellValueFactory(new Callback<CellDataFeatures<DoubleProperty[], Number>, ObservableValue<Number>>() {

                        @Override
                        public ObservableValue<Number> call(CellDataFeatures<DoubleProperty[], Number> p) {
                            return new SimpleDoubleProperty((p.getValue()[colNo]).doubleValue());
                        }
                    });

            tableColumn.setSortable(false);
            tableColumn.setMinWidth(75);
            tableView.getColumns().add(tableColumn);

        }
        tableView.setItems(dataList);

        resizeTableRowHeight(tableView);

    }

    protected void addTableViewContent(DoubleProperty[] inputData, TableView<DoubleProperty[]> tableView,
            Decimals decimals, String columnHeader, ObservableList<DoubleProperty[]> dataList) {
        DoubleProperty[][] values = new DoubleProperty[1][inputData.length];
        values[0] = inputData;
        addTableViewContent(values, tableView, decimals, columnHeader, dataList);
    }

    protected void resizeTableRowHeight(TableView tableView) {

        // height of table header + (table size + 1) * height of content cell
        // the additional +1 is necessary for a possible scroll bar
        // annotation: using the fixedCellSizeProperty is not possible,
        // because this produced display errors in the tableView on changing
        // the dimensions
        Number n = 28 + 24 * (tableView.getItems().size() + 1);

        ObservableValue<Number> tableSize = new ReadOnlyObjectWrapper<Number>(n);

        tableView.prefHeightProperty().bind(tableSize);
        tableView.minHeightProperty().bind(tableSize);
        tableView.maxHeightProperty().bind(tableSize);

    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected void setTableData(TableView tableView, DoubleProperty[][] data, String rowHeader, String columnHeader,
            Decimals decimals, ObservableList<DoubleProperty[]> dataList) {
        // clear Table Columns and content
        tableView.getItems().clear();
        tableView.getColumns().clear();

        // add a column with row numbers
        addColumnWithRowNumber(tableView, rowHeader);

        // get the data from the model and add it to the TableView
        addTableViewContent(data, tableView, decimals, columnHeader, dataList);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected void setTableData(TableView tableView, DoubleProperty[] data, String rowHeader, String columnHeader,
            Decimals decimals, ObservableList<DoubleProperty[]> dataList) {
        // clear Table Columns and content
        tableView.getItems().clear();
        tableView.getColumns().clear();

        // add a column with row numbers
        addColumnWithRowNumber(tableView, rowHeader);

        // get the data from the model and add it to the TableView
        addTableViewContent(data, tableView, decimals, columnHeader, dataList);
    }

}
