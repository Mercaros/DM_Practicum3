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

    public BigInteger calculateE(BigInteger p, BigInteger q) {
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        this.e = BigInteger.valueOf(2);
        return getE(phi.gcd(e), phi, this.e);
    }

    public static BigInteger getE(BigInteger gcd, BigInteger phi, BigInteger e) {
        while (gcd.intValue() != 1) {
            e = e.add(BigInteger.valueOf(1));
            gcd = phi.gcd(e);
        }
        return e;
    }

    public BigInteger getP() {
        return p;
    }

    public BigInteger getQ() {
        return q;
    }
}
