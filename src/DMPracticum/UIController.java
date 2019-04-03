package DMPracticum;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.math.BigInteger;

import static java.math.BigInteger.ONE;

public class UIController {

    private BigInteger encryptedMessage = new BigInteger("28546624436443611514125411254163066528125416306482212965276777916528790077856306272812903483811622630627281296565282767662129031151463061286948386306272812965652827676621290311514630648226528128692296306790048386306276748381254112541630648382728630611514790012869229662125416528129651254111622471963062495101846528790012541128694838790063069681229662129034436229652827672767");
    private BigInteger n, d, e;
    private BigInteger p, q;

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

    @FXML private TextField decryptionSentenceN;
    @FXML private TextField decryptionSentenceE;
    @FXML private TextField decryptionSentenceC;
    @FXML private TextArea decryptionResultOne;
    @FXML private TextArea decryptionResultTwo;

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
        n = new BigInteger("13231");
        e = new BigInteger("2");

        BigInteger phi = (p.subtract(ONE)).multiply(q.subtract(ONE));

        d = e.modInverse(phi);
        decryptionResultOne.setText(String.valueOf(d));
    }

    @FXML
    private void decryptionStepTwo() {
    }

}
