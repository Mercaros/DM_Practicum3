package DMPracticum;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class UIController {

    private String resultEncryptionStepOne;
    private String resultEncryptionStepTwo;
    private String resultEncryptionStepThree;

    private String resultDecryptionStepOne;
    private String resultDecryptionStepTwo;

    @FXML private TextField sentence;
    @FXML private TextArea encryptionResultOne;

    @FXML
    private void encryptionStepOne() {
        if (sentence == null) {
            return;
        }

        this.resultEncryptionStepOne = calculateEncryptionStepOne();
        encryptionResultOne.setText(this.resultEncryptionStepOne);
    }

    private String calculateEncryptionStepOne() {
        return sentence.getText();
    }

    @FXML
    private void encryptionStepTwo() {
    }

    @FXML
    private void encryptionStepThree() {
    }

    @FXML
    private void decryptionStepOne() {
    }

    @FXML
    private void decryptionStepTwo() {
    }

}
