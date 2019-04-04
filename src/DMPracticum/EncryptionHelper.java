package DMPracticum;

import java.math.BigInteger;
import java.util.*;

public class EncryptionHelper {

    private BigInteger p;
    private BigInteger q;
    private BigInteger e;

    public void findPAndQ(BigInteger n) {
        this.p = getP(n);
        this.q = getQ(n);
    }


    public BigInteger getQ(BigInteger n) {
        return n.divide(this.p);
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

    public BigInteger calculateE(int p, int q) {
        BigInteger result = BigInteger.valueOf(p*q);
        this.e = result;

        return result;
    }

    public BigInteger getP() {
        return p;
    }

    public BigInteger getQ() {
        return q;
    }
}
