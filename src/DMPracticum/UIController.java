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

    @FXML private TextField nInput;

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

        if (nInput == null || nInput.getText().isEmpty()) {
            showMissingInputError();
            return;
        }

        long start = System.currentTimeMillis();
        encryptionHelper.findPAndQ(new BigInteger(nInput.getText()));
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;

        String result = "p is: " + encryptionHelper.getP() +
                "\nq is: " + encryptionHelper.getQ() +
                "\nAmount of time busy finding p and q: " + timeElapsed + " milliseconds";

        encryptionResultOneText.setText(result);
        stepTwoEncryptionButton.setDisable(false);
    }

    @FXML
    private void encryptionStepTwo() {
//        BigInteger e = encryptionHelper.calculateE(encryptionHelper.getP(), encryptionHelper.getQ());
//
//        String result = "e is: " + e;
//        encryptionResultTwoText.setText(result);
//        stepThreeEncryptionButton.setDisable(false);
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
        decryptionResultOne.setText(d.toString());
        stepTwoDecryptionButton.setDisable(false);
    }

    @FXML
    private void decryptionStepTwo() {
        BigInteger[] numbers = getEncryptedMessage(decryptionSentenceC.getText());
        char[] ascii = new char[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            ascii[i] = (char) numbers[i].modPow(d, n).intValueExact();
            System.out.print(ascii[i]);
        }
    }

    public static BigInteger[] getEncryptedMessage(String text) {
        String[] array = text.split(",");
        BigInteger[] numbers = new BigInteger[array.length];
        for (int i = 0; i < array.length; i++) {
            numbers[i] = new BigInteger(array[i]);
        }
        return numbers;
    }

    public static BigInteger getP(BigInteger value) {
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
