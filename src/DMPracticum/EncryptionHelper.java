package DMPracticum;

import java.math.BigInteger;
import java.util.List;
import java.util.Random;

public class EncryptionHelper {

    private String n;
    private int p;
    private int q;
    private BigInteger e;

    public void findPAndQ() {
        List<Integer> primeNumbers = PrimeNumbersGenerator.primeNumbersBruteForce(1000);

        int p = primeNumbers.get((int) (Math.random() * primeNumbers.size()));
        int q;

        //Prevents same prime numbers
        do {
            q = primeNumbers.get((int) (Math.random() * primeNumbers.size()));
        } while (p == q);

        this.p = p;
        this.q = q;
    }

    public BigInteger calculateE(int p, int q) {
        BigInteger result = BigInteger.valueOf(p*q);
        this.e = result;

        return result;
    }

    public int getP() {
        return p;
    }

    public int getQ() {
        return q;
    }

    public void setN(String n) {
        this.n = n;
    }
}
