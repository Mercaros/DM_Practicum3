package DMPracticum;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.math.BigInteger;

import static java.math.BigInteger.ONE;

public class UIController {
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

    @FXML private Button stepTwoDecryptionButton;

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
        n = new BigInteger(decryptionSentenceN.getText());
        e = new BigInteger(decryptionSentenceE.getText());
        p = getP(n);
        q = n.divide(p);

        BigInteger phi = (p.subtract(ONE)).multiply(q.subtract(ONE));

        while (phi.gcd(e).intValue() > 1) {
            e = e.add(new BigInteger("2"));
        }

        d = e.modInverse(phi);
        decryptionResultOne.setText("d is " + d.toString());
        stepTwoDecryptionButton.setDisable(false);
    }

    @FXML
    private void decryptionStepTwo() {
        BigInteger[] message = getEncryptedMessage(decryptionSentenceC.getText());
        char[] charArray = new char[message.length];
        for (int i = 0; i < message.length; i++) {
            charArray[i] = (char) message[i].modPow(d, n).intValueExact();
        }
        decryptionResultTwo.setText("Message after decryption is: " + String.valueOf(charArray));
    }

    public BigInteger[] getEncryptedMessage(String text) {
        String[] array = text.split(",");
        BigInteger[] plaintext = new BigInteger[array.length];
        for (int i = 0; i < array.length; i++) {
            plaintext[i] = new BigInteger(array[i]);
        }
        return plaintext;
    }

    public BigInteger getP(BigInteger value) {
        BigInteger initNumber = new BigInteger("2");
        BigInteger n = value;
        BigInteger p = initNumber;

        while (p.compareTo(n.divide(initNumber)) <= 0) {
            if (n.mod(p).equals(BigInteger.ZERO)) {
                return p;
            }
            p = p.nextProbablePrime();
        }
        return p;
    }

}
