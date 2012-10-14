package org.map.controls;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import org.map.hibernate.ddo.MaterialTestMap;
import org.map.hibernate.ddo.ValidationMaster;

public class EditTestGroup extends Region {

	private double H_SPACE = 8;
	private double V_SPACE = 20;
	private VBox testSet;
	private Label testCategoryHeader1;
	private Label testCategoryHeader2;
	private ArrayList<ValidationMaster> newTests = new ArrayList<>();

	public EditTestGroup(String ctNumber, Iterator<MaterialTestMap> testListIt) {

		initcomponents(ctNumber, testListIt);
	}

	private void initcomponents(String ctNumber,
			Iterator<MaterialTestMap> testIteratorIt) {

		testSet = new VBox();

		HBox testHeaderLine = new HBox(H_SPACE);
		final Button addTest = new Button("Add Test");
		addTest.setTranslateX(440);
		addTest.getStyleClass().add("submit-button");
		testCategoryHeader1 = new Label("Tests : ");
		testCategoryHeader2 = new Label(ctNumber);
		testCategoryHeader2.setPrefWidth(104);
		testHeaderLine.setMinWidth(685);
		testHeaderLine.getStyleClass().add("category-header");
		testHeaderLine.getChildren().addAll(testCategoryHeader1,
				testCategoryHeader2, addTest);
		testSet.getChildren().add(testHeaderLine);

		final VBox tests = new VBox(V_SPACE);
		int i = 0;
		HBox testLine = new HBox(H_SPACE * 2);
		while (testIteratorIt.hasNext()) {
			if (i < 3) {
				MaterialTestMap test = (MaterialTestMap) testIteratorIt.next();
				LETSCheckBox testDetail = new LETSCheckBox(test.getTestName(),
						test.getTestValue(), test.TestValueProperty(), true);
				testLine.getChildren().addAll(testDetail);
				i++;
			} else {
				tests.getChildren().add(testLine);
				i = 0;
				testLine = new HBox(H_SPACE * 2);
			}
		}
		if (i > 0) {
			tests.getChildren().add(testLine);
		}
		testSet.getChildren().add(tests);

		addTest.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				ValidationMaster newVm = new ValidationMaster();
				newVm.getId().setValidationType("Test");
				newVm.setValidationStatus("TRUE");

				newTests.add(newVm);
				final TTSCheckBox newTestDetail = new TTSCheckBox("",
						"Test Type", newVm.validationNameProperty(), true,
						newVm.validationValueProperty(), true);
				HBox lastBox = (HBox) tests.getChildren().get(
						tests.getChildren().size() - 1);
				if (lastBox.getChildren().size() >= 3) {
					HBox testLine = new HBox(H_SPACE * 2);
					testLine.getChildren().addAll(newTestDetail);
					tests.getChildren().add(testLine);
				} else {
					lastBox.getChildren().addAll(newTestDetail);
				}
			}
		});
	}

	public void setCtNumber(String ctNumber) {

		testCategoryHeader2.setText(ctNumber);
	}

	public String getCtNumber() {

		return testCategoryHeader2.getText();
	}

	public VBox getView() {

		return testSet;
	}

	public ArrayList<ValidationMaster> getNewTestList() {

		return newTests;
	}
}
