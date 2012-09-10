package org.map.view;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableValue;
import org.map.controls.TextBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.util.Callback;
import net.sf.jasperreports.engine.JRException;
import org.hibernate.HibernateException;
import org.map.MaterialRegister;
import org.map.calendar.DatePicker;
import org.map.controls.ETFCellFactory;
import org.map.utils.TableContextMenu;
import org.map.hibernate.dao.HeatChartData;
import org.map.hibernate.dao.Reporter;
import org.map.hibernate.ddo.*;
import org.map.logger.LoggerUtil;
import org.map.utils.Alert;
import org.map.utils.Confirm;

public class EditHeatChart {

    private static EditHeatChart viewHeatChart;
    private double COLUMN_WIDTH = 100;
    private double COLUMN_WIDTH_MAX = 120;
    private double LABEL_WIDTH = 100;
    private double H_SPACE = 8;
    private double V_SPACE = 20;
    private ObservableList<HeatChartSheets> data;
    private boolean isFirstSearch = true;

    @SuppressWarnings("LeakingThisInConstructor")
    public EditHeatChart() {
        viewHeatChart = this;
    }

    public static EditHeatChart getViewHeatChart() {
        return viewHeatChart;
    }

    public Node createView() {
        try {
            final VBox main = new VBox(H_SPACE) {

                @Override
                protected double computePrefHeight(double width) {
                    return Math.max(
                            super.computePrefHeight(width),
                            getParent().getBoundsInLocal().getHeight());
                }
            };
            main.getStyleClass().add("category-page");

            Label header = new Label("Heat Chart");
            header.getStyleClass().add("page-header");
            main.getChildren().add(header);

            Label mailboxCategoryHeader = new Label("Search");
            mailboxCategoryHeader.setMaxWidth(Double.MAX_VALUE);
            mailboxCategoryHeader.setMinHeight(Control.USE_PREF_SIZE);
            mailboxCategoryHeader.getStyleClass().add("category-header");
            main.getChildren().add(mailboxCategoryHeader);

            final HBox search1 = new HBox(H_SPACE);
            Label hcNumberFromLabel = new Label("Heat Chart No From");
            hcNumberFromLabel.setPrefWidth(LABEL_WIDTH);
            final TextBox hcNumberFromTextField = new TextBox("", "Heat Chart Number From");
            Label hcNumberToLabel = new Label("Heat Chart No To");
            hcNumberToLabel.setPrefWidth(LABEL_WIDTH);
            final TextBox hcNumberToTextField = new TextBox("", "Heat Chart Number To");
            final Button searchRecordButton1 = new Button("Search");
            searchRecordButton1.getStyleClass().add("submit-button");
            search1.getChildren().addAll(hcNumberFromLabel, hcNumberFromTextField, hcNumberToLabel, hcNumberToTextField, searchRecordButton1);

            final HBox search2 = new HBox(H_SPACE);
            Label fromDateLabel = new Label("Date From");
            fromDateLabel.setPrefWidth(LABEL_WIDTH);
            final DatePicker fromDateTextField = new DatePicker();
            Label toDateLabel = new Label("Date");
            toDateLabel.setPrefWidth(LABEL_WIDTH);
            final DatePicker toDateTextField = new DatePicker();
            final Button searchRecordButton2 = new Button("Search");
            searchRecordButton2.getStyleClass().add("submit-button");
            search2.getChildren().addAll(fromDateLabel, fromDateTextField, toDateLabel, toDateTextField, searchRecordButton2);
            main.getChildren().addAll(search1, search2);

            Label searchCategoryHeader = new Label("List");
            searchCategoryHeader.setMaxWidth(Double.MAX_VALUE);
            searchCategoryHeader.setMinHeight(Control.USE_PREF_SIZE);
            searchCategoryHeader.getStyleClass().add("category-header");
            main.getChildren().add(searchCategoryHeader);

            final TableView<HeatChartMaster> tableMailbox = new TableView<>();
            TableColumn MCol1 = new TableColumn("Chart Number");
            MCol1.setPrefWidth(COLUMN_WIDTH);
            MCol1.setCellValueFactory(new PropertyValueFactory<HeatChartMaster, String>("chartNumber"));
            TableColumn MCol2 = new TableColumn("Equipment");
            MCol2.setPrefWidth(COLUMN_WIDTH);
            MCol2.setCellValueFactory(new PropertyValueFactory<HeatChartMaster, String>("equipment"));
            TableColumn MCol3 = new TableColumn("customer");
            MCol3.setPrefWidth(COLUMN_WIDTH_MAX);
            MCol3.setCellValueFactory(new PropertyValueFactory<HeatChartMaster, String>("customer"));
            TableColumn MCol4 = new TableColumn("PO Details");
            MCol4.setPrefWidth(COLUMN_WIDTH);
            MCol4.setCellValueFactory(new PropertyValueFactory<HeatChartMaster, String>("poDetails"));
            TableColumn MCol5 = new TableColumn("Drawing Number");
            MCol5.setPrefWidth(COLUMN_WIDTH);
            MCol5.setCellValueFactory(new PropertyValueFactory<HeatChartMaster, String>("drawingNumber"));
            TableColumn MCol6 = new TableColumn("Surveyor");
            MCol6.setPrefWidth(COLUMN_WIDTH);
            MCol6.setCellValueFactory(new PropertyValueFactory<HeatChartMaster, String>("surveyor"));
            TableColumn MCol7 = new TableColumn("Status");
            MCol7.setPrefWidth(COLUMN_WIDTH);
            MCol7.setCellValueFactory(new PropertyValueFactory<HeatChartMaster, String>("status"));
            tableMailbox.getColumns().addAll(MCol1, MCol2, MCol3, MCol4, MCol5, MCol6, MCol7);
            main.getChildren().add(tableMailbox);

            final ObservableList<HeatChartMaster> mailboxData = null;
            tableMailbox.setItems(mailboxData);

            Label detailCategoryHeader = new Label("Details");
            detailCategoryHeader.setMaxWidth(Double.MAX_VALUE);
            detailCategoryHeader.setMinHeight(Control.USE_PREF_SIZE);
            detailCategoryHeader.getStyleClass().add("category-header");
            main.getChildren().add(detailCategoryHeader);

            final HeatChartMaster heatChart = new HeatChartMaster();
            heatChart.setChartNumber(HeatChartData.getNextChartNumber());
            final HBox detail1 = new HBox(H_SPACE * 4.5);
            Label equipmentLabel = new Label("Equipment");
            equipmentLabel.setPrefWidth(LABEL_WIDTH);
            final TextBox equipmentTextField = new TextBox("", "Equipment", heatChart.equipmentProperty(), true);
            Label customerLabel = new Label("Customer");
            customerLabel.setPrefWidth(LABEL_WIDTH);
            final TextBox customerTextField = new TextBox("", "Customer", heatChart.customerProperty(), true);
            Label poDetailsLabel = new Label("PO Details");
            poDetailsLabel.setPrefWidth(LABEL_WIDTH);
            detail1.getChildren().addAll(equipmentLabel, equipmentTextField, customerLabel, customerTextField, poDetailsLabel);
            main.getChildren().add(detail1);

            final HBox detail2 = new HBox(H_SPACE * 4.5);
            Label drawingLabel = new Label("Drawing No.");
            drawingLabel.setPrefWidth(LABEL_WIDTH);
            final TextBox drawingTextField = new TextBox("", "Drawing No.", heatChart.drawingNumberProperty(), true);
            Label suryeyorLabel = new Label("Surveyor");
            suryeyorLabel.setPrefWidth(LABEL_WIDTH);
            final TextBox suryeyorTextField = new TextBox("", "Suryeyor", heatChart.surveyorProperty(), true);
            final TextBox poDetailsTextField = new TextBox("", "PO Details", heatChart.poDetailsProperty(), true);
            detail2.getChildren().addAll(drawingLabel, drawingTextField, suryeyorLabel, suryeyorTextField, poDetailsTextField);
            main.getChildren().add(detail2);

            final TableView<HeatChartSheets> table = new TableView<>();
            TableColumn Col1 = new TableColumn("Sr. No.");
            Col1.setPrefWidth(COLUMN_WIDTH);
            Col1.setCellValueFactory(new PropertyValueFactory<HeatChartSheets, Integer>("sequenceNumber"));
            Col1.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, Integer>, ObservableIntegerValue>() {

                @Override
                public ObservableIntegerValue call(CellDataFeatures<HeatChartSheets, Integer> p) {
                    return p.getValue().sequenceNumberProperty();
                }
            });
            TableColumn Col2 = new TableColumn("Sheet No.");
            Col2.setPrefWidth(COLUMN_WIDTH);
            Col2.setCellValueFactory(new PropertyValueFactory<HeatChartSheets, HeatChartSheetsId>("sheetNumber"));
            Col2.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, Integer>, ObservableIntegerValue>() {

                @Override
                public ObservableIntegerValue call(CellDataFeatures<HeatChartSheets, Integer> p) {
                    return p.getValue().getId().sheetNumberProperty();
                }
            });
            TableColumn Col3 = new TableColumn("Part No");
            Col3.setPrefWidth(COLUMN_WIDTH);
            Col3.setCellValueFactory(new PropertyValueFactory<HeatChartSheets, String>("partNumber"));
            Col3.setCellFactory(new ETFCellFactory("PartNumber"));
            TableColumn Col4 = new TableColumn("Part Name(s)");
            Col4.setPrefWidth(COLUMN_WIDTH_MAX);
            Col4.setCellValueFactory(new PropertyValueFactory<HeatChartSheets, String>("partName"));
            Col4.setCellFactory(new ETFCellFactory("PartName"));
            TableColumn Col5 = new TableColumn("Material Specification");
            Col5.setPrefWidth(COLUMN_WIDTH);
            TableColumn Col51 = new TableColumn("Specified");
            Col51.setPrefWidth(COLUMN_WIDTH);
            TableColumn Col511 = new TableColumn("Size");
            Col511.setPrefWidth(COLUMN_WIDTH);
            Col511.setCellValueFactory(new PropertyValueFactory<HeatChartSheets, String>("speciedSize"));
            Col511.setCellFactory(new ETFCellFactory("SpeciedSize"));
            TableColumn Col512 = new TableColumn("Grade");
            Col512.setPrefWidth(COLUMN_WIDTH);
            Col512.setCellValueFactory(new PropertyValueFactory<HeatChartSheets, String>("speciedGrade"));
            Col512.setCellFactory(new ETFCellFactory("SpeciedGrade"));
            TableColumn Col52 = new TableColumn("Utilized");
            Col52.setPrefWidth(COLUMN_WIDTH);
            TableColumn Col521 = new TableColumn("Size");
            Col521.setPrefWidth(COLUMN_WIDTH);
            Col521.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, MaterialMaster>, ObservableValue<String>>() {

                @Override
                public ObservableValue<String> call(CellDataFeatures<HeatChartSheets, MaterialMaster> p) {
                    return p.getValue().getMaterialmaster().sizeProperty();
                }
            });
            TableColumn Col522 = new TableColumn("Grade");
            Col522.setPrefWidth(COLUMN_WIDTH);
            Col522.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, MaterialMaster>, ObservableValue<String>>() {

                @Override
                public ObservableValue<String> call(CellDataFeatures<HeatChartSheets, MaterialMaster> p) {
                    return p.getValue().getMaterialmaster().specificationProperty();
                }
            });
            TableColumn Col53 = new TableColumn("Check / Testing");
            Col53.setPrefWidth(COLUMN_WIDTH_MAX);
            Col53.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, HeatChartSheetsId>, ObservableValue<String>>() {

                @Override
                public ObservableValue<String> call(CellDataFeatures<HeatChartSheets, HeatChartSheetsId> p) {
                    return p.getValue().getId().ctNumberProperty();
                }
            });
            Col53.setCellFactory(new ETFCellFactory("CTNumberEdit"));
            Col51.getColumns().addAll(Col511, Col512);
            Col52.getColumns().addAll(Col521, Col522);
            Col5.getColumns().addAll(Col51, Col52, Col53);
            TableColumn Col6 = new TableColumn("Test Certificate");
            Col6.setPrefWidth(COLUMN_WIDTH);
            TableColumn Col61 = new TableColumn("Number");
            Col61.setPrefWidth(COLUMN_WIDTH);
            Col61.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, MaterialMaster>, ObservableValue<String>>() {

                @Override
                public ObservableValue<String> call(CellDataFeatures<HeatChartSheets, MaterialMaster> p) {
                    return p.getValue().getMaterialmaster().reportNumberProperty();
                }
            });
            TableColumn Col62 = new TableColumn("Date");
            Col62.setPrefWidth(COLUMN_WIDTH);
            Col62.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, MaterialMaster>, ObservableValue<String>>() {

                @Override
                public ObservableValue<String> call(CellDataFeatures<HeatChartSheets, MaterialMaster> p) {
                    return p.getValue().getMaterialmaster().reportDateProperty();
                }
            });
            TableColumn Col63 = new TableColumn("Laboratory");
            Col63.setPrefWidth(COLUMN_WIDTH);
            Col63.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, MaterialMaster>, ObservableValue<String>>() {

                @Override
                public ObservableValue<String> call(CellDataFeatures<HeatChartSheets, MaterialMaster> p) {
                    return p.getValue().getMaterialmaster().laboratoryProperty();
                }
            });
            Col6.getColumns().addAll(Col61, Col62, Col63);
            table.getColumns().addAll(Col1, Col2, Col3, Col4, Col5, Col6);

            HeatChartSheets hs = new HeatChartSheets();
            hs.getId().setChartNumber(heatChart.getChartNumber());
            data = FXCollections.observableArrayList(hs);
            table.setItems(data);

            final HBox buttons = new HBox(H_SPACE);
            buttons.setTranslateY(32);
            final Button updateButton = new Button("Update");
            updateButton.getStyleClass().add("submit-button");
            updateButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent e) {
                    heatChart.setHeatchartsheets(new HashSet<>(data.subList(0, data.size())));
                    HeatChartData.updateHeatChart(heatChart);
                }
            });
            searchRecordButton1.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent e) {
                    try {
                        isFirstSearch = true;

                        tableMailbox.getItems().clear();

                        List<HeatChartMaster> mailboxHcMasters = HeatChartData.searchHeatChartDetailsHc(hcNumberFromTextField.getText(), hcNumberToTextField.getText());
                        mailboxData.setAll(mailboxHcMasters);
                    } catch (Exception ex) {
                        LoggerUtil.getLogger().debug(ex);
                    }
                }
            });

            searchRecordButton2.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent e) {
                    try {
                        isFirstSearch = false;

                        tableMailbox.getItems().clear();

                        List<HeatChartMaster> mailboxHcMasters = HeatChartData.searchHeatChartDetailsDt(fromDateTextField.getSelectedDate(), toDateTextField.getSelectedDate());
                        mailboxData.setAll(mailboxHcMasters);
                    } catch (Exception ex) {
                        LoggerUtil.getLogger().debug(ex);
                    }
                }
            });

            tableMailbox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    Object selectedValue = tableMailbox.getSelectionModel().getSelectedItem();
                    if (selectedValue != null) {
                        HeatChartMaster hcm = (HeatChartMaster) selectedValue;
                        heatChart.resetHeatChart(hcm);

                        table.getItems().clear();
                        data.setAll(hcm.getHeatchartsheets());
                    }
                }
            });

            buttons.getChildren().addAll(updateButton);
            main.getChildren().addAll(table, buttons);

            EventHandler printEventHandler = new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent e) {
                    heatChart.setHeatchartsheets(new HashSet<>(table.getItems().subList(0, table.getItems().size())));
                    try {
                        Reporter.printHeatChartReport(heatChart);
                    } catch (JRException | IOException | URISyntaxException ex) {
                        LoggerUtil.getLogger().debug(ex);
                    }
                }
            };

            EventHandler undoEventHandler = new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent actionEvent) {
                    final HeatChartMaster delHeatChart = tableMailbox.getSelectionModel().getSelectedItem();
                    EventHandler<ActionEvent> delUserEvent = new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent arg0) {
                            delHeatChart.setStatus("TRUE");
                            for (HeatChartSheets delHSheets : delHeatChart.getHeatchartsheets()) {
                                delHSheets.setStatus("TRUE");
                            }
                            HeatChartData.updateHeatChart(delHeatChart);

                            mailboxData.clear();
                            if (isFirstSearch) {
                                searchRecordButton1.fire();
                            } else {
                                searchRecordButton2.fire();
                            }
                        }
                    };
                    Confirm.showConfirm(MaterialRegister.getMaterialRegister().getPrimaryStage(), "Confirm", "Confirm", "Restore " + delHeatChart.getChartNumber() + ". Are you sure?", delUserEvent);
                }
            };

            EventHandler deleteEventHandler = new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent actionEvent) {
                    final HeatChartMaster delHeatChart = tableMailbox.getSelectionModel().getSelectedItem();
                    EventHandler<ActionEvent> delUserEvent = new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent arg0) {
                            delHeatChart.setStatus("FALSE");
                            for (HeatChartSheets delHSheets : delHeatChart.getHeatchartsheets()) {
                                delHSheets.setStatus("FALSE");
                            }
                            HeatChartData.updateHeatChart(delHeatChart);

                            mailboxData.clear();
                            if (isFirstSearch) {
                                searchRecordButton1.fire();
                            } else {
                                searchRecordButton2.fire();
                            }
                        }
                    };
                    Confirm.showConfirm(MaterialRegister.getMaterialRegister().getPrimaryStage(), "Confirm", "Confirm", "Delete " + delHeatChart.getChartNumber() + ". Are you sure?", delUserEvent);
                }
            };
            tableMailbox.setContextMenu(new TableContextMenu(undoEventHandler, deleteEventHandler, printEventHandler));

            ScrollPane scrollPane = new ScrollPane();
            scrollPane.getStyleClass().add("noborder-scroll-pane");
            scrollPane.setFitToWidth(true);
            scrollPane.setContent(main);

            return scrollPane;
        } catch (HibernateException | URISyntaxException e) {
            LoggerUtil.getLogger().debug(e);
            Alert.showAlert(MaterialRegister.getMaterialRegister().getPrimaryStage(), "Error", "Error", "Some error occured. Details...\n" + e.getMessage());
            return new Text("Failed to create sample because of [" + e.getMessage() + "]");
        }
    }

    public void updateTable(MaterialMaster material, int rowIndex) {
        HeatChartSheets heatChartSheet = data.get(rowIndex);
        heatChartSheet.getMaterialmaster().setSize(material.getSize());
        heatChartSheet.getMaterialmaster().setSpecification(material.getSpecification());
        heatChartSheet.getMaterialmaster().setReportNumber(material.getReportNumber());
        heatChartSheet.getMaterialmaster().setReportDate(material.getReportDate());
        heatChartSheet.getMaterialmaster().setLaboratory(material.getLaboratory());
    }
}
