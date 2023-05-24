package util;


import com.jfoenix.controls.JFXButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class validationUtil {
    public static Object validate(LinkedHashMap<TextField,Pattern> map, JFXButton button) {
        for (TextField textField : map.keySet()) {
            Pattern pattern = map.get(textField);
            if (!pattern.matcher(textField.getText()).matches()) {
                if (!textField.getText().isEmpty()){
                    textField.getParent().setStyle("-fx-border-color: red");
                    ((AnchorPane) textField.getParent()).getChildren().get(1).setStyle("-fx-text-fill: red");
                }
                return textField;
            }
            textField.getParent().setStyle("-fx-border-color: green");
            ((AnchorPane) textField.getParent()).getChildren().get(1).setStyle("-fx-text-fill: green");
        }
        button.setDisable(true);
        return true;
    }
}

