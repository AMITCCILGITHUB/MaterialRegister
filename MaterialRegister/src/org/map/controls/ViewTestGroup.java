package org.map.controls;

import java.util.Iterator;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import org.map.hibernate.ddo.MaterialTestMap;

public class ViewTestGroup extends Region {

    private double H_SPACE = 8;
    private double V_SPACE = 20;
    private VBox testSet;
    private Label testCategoryHeader1;
    private Label testCategoryHeader2;

    public ViewTestGroup(String ctNumber, Iterator<MaterialTestMap> testList) {
        initcomponents(ctNumber, testList);
    }

    private void initcomponents(String ctNumber, Iterator<MaterialTestMap> testIterator) {
        testSet = new VBox();

        HBox testHeaderLine = new HBox(H_SPACE);
        testCategoryHeader1 = new Label("Tests : ");
        testCategoryHeader2 = new Label(ctNumber);
        testHeaderLine.setMinWidth(685);
        testHeaderLine.getStyleClass().add("category-header");
        testHeaderLine.getChildren().addAll(testCategoryHeader1, testCategoryHeader2);
        testSet.getChildren().add(testHeaderLine);

        final VBox tests = new VBox(V_SPACE);
        int i = 0;
        HBox testLine = new HBox(H_SPACE * 2);
        while (testIterator.hasNext()) {
            if (i < 3) {
                MaterialTestMap test = (MaterialTestMap) testIterator.next();
                ViewCheckBox testDetail = new ViewCheckBox(test.getTestName(), test.getTestValue(), test.TestValueProperty(), true);
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
}
