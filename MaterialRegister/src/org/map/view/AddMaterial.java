package org.map.view;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import org.map.MaterialRegister;
import org.map.controls.*;
import org.map.hibernate.dao.MaterialData;
import org.map.hibernate.dao.ValidationData;
import org.map.hibernate.ddo.MaterialMaster;
import org.map.hibernate.ddo.ValidationMaster;
import org.map.logger.LoggerUtil;
import org.map.utils.Alert;

public class AddMaterial {

    private double LABEL_WIDTH = 100;
    private double H_SPACE = 8;
    private double V_SPACE = 20;
    private ArrayList<TestGroup> tgp = new ArrayList<>();

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
            VBox.setVgrow(main, Priority.ALWAYS);
            main.getStyleClass().add("category-page");

            Label header = new Label("Add Material");
            header.getStyleClass().add("page-header");
            main.getChildren().add(header);

            Label detailCategoryHeader = new Label("Details");
            detailCategoryHeader.setMaxWidth(Double.MAX_VALUE);
            detailCategoryHeader.setMinHeight(Control.USE_PREF_SIZE);
            detailCategoryHeader.getStyleClass().add("category-header");
            main.getChildren().add(detailCategoryHeader);

            final MaterialMaster material = new MaterialMaster();
            final HBox detail = new HBox(H_SPACE);
            Label ctNumberLabel = new Label("CT Number");
            ctNumberLabel.setPrefWidth(LABEL_WIDTH);
            final ViewBox ctNumberTextField = new ViewBox("", material.ctNumberProperty(), true);
            material.setCtNumber(MaterialData.getNextCtNumber());
            Label agencyLabel = new Label("Inspection Agency");
            agencyLabel.setPrefWidth(LABEL_WIDTH);
            final ComboVBox agencyTextField = new ComboVBox("", "Inspection Agency", "Agency", material.inspectionAgencyProperty(), true);
            Label specLabel = new Label("Specification");
            specLabel.setPrefWidth(LABEL_WIDTH);
            final ComboVBox specTextField = new ComboVBox("", "Specification", "Specification", material.specificationProperty(), true);
            detail.getChildren().addAll(ctNumberLabel, ctNumberTextField, agencyLabel, agencyTextField, specLabel, specTextField);
            main.getChildren().add(detail);

            Label descriptionCategoryHeader = new Label("Description");
            descriptionCategoryHeader.setMaxWidth(Double.MAX_VALUE);
            descriptionCategoryHeader.setMinHeight(Control.USE_PREF_SIZE);
            descriptionCategoryHeader.getStyleClass().add("category-header");
            main.getChildren().add(descriptionCategoryHeader);

            final VBox description = new VBox(V_SPACE);
            final HBox descriptionLine1 = new HBox(H_SPACE);
            final HBox descriptionLine2 = new HBox(H_SPACE);
            Label itemLabel = new Label("Item");
            itemLabel.setPrefWidth(LABEL_WIDTH);
            final ComboVBox itemTextField = new ComboVBox("", "Item Name", "Item", material.itemProperty(), true);
            Label sizeLabel = new Label("Size");
            sizeLabel.setPrefWidth(LABEL_WIDTH);
            final TextBox sizeTextField = new TextBox("", "Size", material.sizeProperty(), true);
            Label quantityLabel = new Label("Quantity");
            quantityLabel.setPrefWidth(LABEL_WIDTH);
            final CountBox quantityTextField = new CountBox("", "Quantity", material.quantityProperty(), true);
            Label heatNumberLabel = new Label("Heat / Lot Number");
            heatNumberLabel.setPrefWidth(LABEL_WIDTH);
            final TextBox heatNumberTextField = new TextBox("", "Heat / Lot Number", material.heatNumberProperty(), true);
            Label plateNumberLabel = new Label("Plate / Product Number");
            plateNumberLabel.setPrefWidth(LABEL_WIDTH);
            final TextBox plateNumberTextField = new TextBox("", "Plate / Product Number", material.plateNumberProperty(), true);
            descriptionLine1.getChildren().addAll(itemLabel, itemTextField, sizeLabel, sizeTextField, quantityLabel, quantityTextField);
            descriptionLine2.getChildren().addAll(heatNumberLabel, heatNumberTextField, plateNumberLabel, plateNumberTextField);
            description.getChildren().addAll(descriptionLine1, descriptionLine2);
            main.getChildren().add(description);

            final VBox testBox = new VBox();
            final TestGroup tg = new TestGroup(material.getCtNumber());
            tgp.add(tg);
            testBox.getChildren().add(tg.getView());
            main.getChildren().add(testBox);

            Label otherCategoryHeader = new Label("Other Details");
            otherCategoryHeader.setMaxWidth(Double.MAX_VALUE);
            otherCategoryHeader.setMinHeight(Control.USE_PREF_SIZE);
            otherCategoryHeader.getStyleClass().add("category-header");
            main.getChildren().add(otherCategoryHeader);

            final VBox otherDetails = new VBox(V_SPACE);
            final HBox otherDetailsLine1 = new HBox(H_SPACE);
            final HBox otherDetailsLine2 = new HBox(H_SPACE);
            final HBox otherDetailsLine3 = new HBox(H_SPACE);
            Label custLabel = new Label("Customer");
            custLabel.setPrefWidth(LABEL_WIDTH);
            final ComboVBox custTextField = new ComboVBox("", "Customer Name", "Customer", material.customerProperty(), true);
            Label equipLabel = new Label("Equipments");
            equipLabel.setPrefWidth(LABEL_WIDTH);
            final TextBox equipTextField = new TextBox("", "Equipments", material.equipmentsProperty(), true);
            Label labLabel = new Label("Laboratory");
            labLabel.setPrefWidth(LABEL_WIDTH);
            final ComboVBox labTextField = new ComboVBox("", "Laboratory", "Laboratory", material.laboratoryProperty(), true);
            Label repDateLabel = new Label("Report Date");
            repDateLabel.setPrefWidth(LABEL_WIDTH);
            final TextBox repDateCalendarBox = new TextBox("", "dd-MM-yy", material.reportDateProperty(), true);
            Label repNumberLabel = new Label("Report Number");
            repNumberLabel.setPrefWidth(LABEL_WIDTH);
            final TextBox repNumberTextField = new TextBox("", "Report Number", material.reportNumberProperty(), true);
            Label remarksLabel = new Label("Remarks");
            remarksLabel.setPrefWidth(LABEL_WIDTH);
            final ComboVBox remarksTextField = new ComboVBox("", "Remarks", "Remarks", material.remarksProperty(), true);
            Label resulLabel = new Label("Result");
            resulLabel.setPrefWidth(LABEL_WIDTH);
            final TextBox resultTextField = new TextBox("", "Result", material.resultProperty(), true);
            Label witnessedByLabel = new Label("Witnessed By");
            witnessedByLabel.setPrefWidth(LABEL_WIDTH);
            final TextBox witnessedByTextField = new TextBox("", "Witnessed By", material.witnessedByProperty(), true);
            otherDetailsLine1.getChildren().addAll(custLabel, custTextField, equipLabel, equipTextField, labLabel, labTextField);
            otherDetailsLine2.getChildren().addAll(repDateLabel, repDateCalendarBox, repNumberLabel, repNumberTextField, resulLabel, resultTextField);
            otherDetailsLine3.getChildren().addAll(remarksLabel, remarksTextField, witnessedByLabel, witnessedByTextField);
            otherDetails.getChildren().addAll(otherDetailsLine1, otherDetailsLine2, otherDetailsLine3);
            main.getChildren().add(otherDetails);

            final VBox resonBox = new VBox();
            Label reasonLabel = new Label("Reason of Failure (In case of rejected remarks.)");
            final TextArea reasonOfFailure = new TextArea("");
            reasonOfFailure.textProperty().bindBidirectional(material.failureReasonProperty());
            resonBox.getChildren().addAll(reasonLabel, reasonOfFailure);
            main.getChildren().add(resonBox);

            quantityTextField.setOnAddButtonAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    TestGroup tg1 = new TestGroup(material.getCtNumber() + "-" + ((char) (65 + testBox.getChildren().size())));
                    tgp.add(tg1);

                    testBox.getChildren().add(tg1.getView());
                    quantityTextField.setValue(quantityTextField.getValue() + 1);

                    if (quantityTextField.getValue() > 1) {
                        tgp.get(0).setCtNumber(material.getCtNumber() + "-A");
                    }
                }
            });

            quantityTextField.setOnSubButtonAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    tgp.remove(tgp.size() - 1);
                    testBox.getChildren().remove(testBox.getChildren().size() - 1);

                    quantityTextField.setValue((quantityTextField.getValue() < 2) ? 1 : (quantityTextField.getValue() - 1));

                    if (quantityTextField.getValue() == 1) {
                        tgp.get(0).setCtNumber(material.getCtNumber());
                    }
                }
            });

            final HBox buttons = new HBox(H_SPACE);
            buttons.setTranslateY(32);
            final Button submitButton = new Button("Submit");
            submitButton.getStyleClass().add("submit-button");
            submitButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent e) {
                    if (resultTextField.getText().equalsIgnoreCase("Rejected") && reasonOfFailure.getText().trim().length() == 0) {
                        Alert.showAlert(MaterialRegister.getMaterialRegister().getPrimaryStage(), "Error", "Error", "Please enter reason of Failure.");
                    } else {
                        Iterator<TestGroup> it = tgp.iterator();
                        Set<ValidationMaster> vmSet = new TreeSet<>();
                        while (it.hasNext()) {
                            TestGroup tg2 = (TestGroup) it.next();
                            vmSet.addAll(tg2.getNewTestList());
                        }
                        ValidationData.insertTests(vmSet.iterator());

                        it = tgp.iterator();
                        while (it.hasNext()) {
                            TestGroup tg2 = (TestGroup) it.next();
                            material.setCtNumber(tg2.getCtNumber());
                            MaterialData.insertMaterialTestMap(material, tg2.getNewTestList().iterator(), tg2.getTestList().iterator());
                        }
                    }
                }
            });

            buttons.getChildren().addAll(submitButton);
            main.getChildren().add(buttons);

            ScrollPane scrollPane = new ScrollPane();
            scrollPane.getStyleClass().add("noborder-scroll-pane");
            scrollPane.setFitToWidth(true);
            scrollPane.setContent(main);

            return scrollPane;
        } catch (Exception e) {
            LoggerUtil.getLogger().debug(e);
            Alert.showAlert(MaterialRegister.getMaterialRegister().getPrimaryStage(), "Error", "Error", "Some error occured. Details...\n" + e.getMessage());
            return new Text("Failed to create sample because of [" + e.getMessage() + "]");
        }
    }
}
