package DMPracticum;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class UIController {

    private String resultEncryptionStepOne;
    private String resultEncryptionStepTwo;
    private String resultEncryptionStepThree;

    private String resultDecryptionStepOne;
    private String resultDecryptionStepTwo;

    @FXML private TextField sentence;

    @FXML private Button stepTwoEncryptionButton;
    @FXML private Button stepThreeEncryptionButton;

    @FXML private TextArea encryptionResultOneText;
    @FXML private TextArea encryptionResultTwoText;
    @FXML private TextArea encryptionResultThreeText;

    @FXML
    private void encryptionStepOne() {
        //Other two button will be disabled when running for a second time
        disableEncryptionButtons();

        if (sentence == null || sentence.getText().isEmpty()) {
            showMissingInputError();
            return;
        }

        this.resultEncryptionStepOne = calculateEncryptionStepOne();
        encryptionResultOneText.setText(this.resultEncryptionStepOne);
        stepTwoEncryptionButton.setDisable(false);
    }

    private String calculateEncryptionStepOne() {
        String result = sentence.getText();
        return "The result is: " + result;
    }

    @FXML
    private void encryptionStepTwo() {
        this.resultEncryptionStepTwo = calculateEncryptionStepTwo();
        stepThreeEncryptionButton.setDisable(false);
    }

    private String calculateEncryptionStepTwo() {
        return "";
    }

    @FXML
    private void encryptionStepThree() {
        this.resultEncryptionStepThree = calculateEncryptionStepThree();

    }

    private String calculateEncryptionStepThree() {
        return "";
    }

    private void disableEncryptionButtons() {
        stepTwoEncryptionButton.setDisable(true);
        stepThreeEncryptionButton.setDisable(true);
    }

    private void showMissingInputError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Input is required!");
        alert.showAndWait();
    }

    @FXML
    private void decryptionStepOne() {
    }

    @FXML
    private void decryptionStepTwo() {
    }

}
