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

    private EncryptionHelper encryptionHelper = new EncryptionHelper();

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

    @FXML
    private void encryptionStepOne() {
        //Other two button will be disabled when running for a second time
        disableEncryptionButtons();

        if (sentence == null || sentence.getText().isEmpty()) {
            showMissingInputError();
            return;
        }
        encryptionHelper.setN(sentence.getText());

        long start = System.currentTimeMillis();
        encryptionHelper.findPAndQ();
        long end = System.currentTimeMillis();

        long elapsedTime = end - start;

        String result = "p is: " + encryptionHelper.getP() +
                "\nq is: " + encryptionHelper.getQ() +
                "\nAmount of time busy finding p and q: " + elapsedTime;

        encryptionResultOneText.setText(result);
        stepTwoEncryptionButton.setDisable(false);
    }

    @FXML
    private void encryptionStepTwo() {
        BigInteger e = encryptionHelper.calculateE(encryptionHelper.getP(), encryptionHelper.getQ());

        String result = "e is: " + e;
        encryptionResultTwoText.setText(result);
        stepThreeEncryptionButton.setDisable(false);
    }

    @FXML
    private void encryptionStepThree() {
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
