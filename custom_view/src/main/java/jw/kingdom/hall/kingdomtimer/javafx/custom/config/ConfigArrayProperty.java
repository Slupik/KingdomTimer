package jw.kingdom.hall.kingdomtimer.javafx.custom.config;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import jw.kingdom.hall.kingdomtimer.javafx.custom.AdvancedTextField;

/**
 * All rights reserved & copyright ©
 */
public class ConfigArrayProperty extends ConfigPropertyBase<String[]> {

    private HBox customContainer;
    private Button btnDelete;
    private Button btnAdd;
    private AdvancedTextField atfNewProperty;
    private ListView<String> list;

    @Override
    protected Region getCustomNode() {
        if(customContainer ==null) {
            customContainer = new HBox();
            list = new ListView<>();
            list.setPrefWidth(150);
            list.setPrefHeight(200);

            HBox deleteContainer = new HBox();
            btnDelete = new Button("Delete");
            btnDelete.setDisable(true);
            deleteContainer.getChildren().add(btnDelete);

            HBox addContainer = new HBox();
            btnAdd = new Button("Add");
            HBox.setMargin(btnAdd, new Insets(0, 0, 0, 8));
            atfNewProperty = new AdvancedTextField();
            atfNewProperty.setPrefHeight(25);
            atfNewProperty.setPrefWidth(149);
            addContainer.getChildren().addAll(atfNewProperty, btnAdd);

            GridPane grid = new GridPane();
            grid.setPadding(new Insets(0, 0, 0, 8));
            grid.add(deleteContainer, 0, 0);
            grid.add(new HBox(), 0, 1);
            grid.add(addContainer, 0, 2);

            RowConstraints constraints = new RowConstraints();
            constraints.fillHeightProperty().setValue(true);
            constraints.setPrefHeight(30);
            constraints.setMinHeight(10);
            constraints.setPercentHeight(-1);
            constraints.setVgrow(Priority.SOMETIMES);
            grid.getRowConstraints().add(constraints);

            customContainer.getChildren().addAll(list, grid);

            bindEvents();
            resetValue();
        }
        return customContainer;
    }

    private void bindEvents() {
        list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(btnDelete.isDisable()) {
                btnDelete.setDisable(false);
            }
        });
        btnDelete.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            list.getItems().remove(list.getSelectionModel().getSelectedIndex());
            if(list.getItems().size()==0) {
                btnDelete.setDisable(true);
            }
        });

        btnAdd.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            String text = atfNewProperty.getText();
            if(!"".equals(text)) {
                list.getItems().add(text);
                atfNewProperty.setText("");
                atfNewProperty.requestFocus();
            }
        });
    }

    @Override
    protected void resetValue() {
        list.getItems().clear();
    }

    @Override
    public String[] getResult() {
        return list.getItems().toArray(new String[0]);
    }

    @Override
    public void setValue(String[] newValues) {
        list.getItems().clear();
        list.getItems().addAll(newValues);
    }
}
