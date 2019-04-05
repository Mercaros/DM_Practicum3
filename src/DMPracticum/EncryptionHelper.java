package DMPracticum;

import java.math.BigInteger;
import java.util.*;

public class EncryptionHelper {

    private BigInteger p;
    private BigInteger q;
    private BigInteger e;
    private BigInteger n;
    private String m;

    public void findPAndQ(BigInteger n) {
        this.n = n;
        this.p = getP(n);
        this.q = getQ(n);
    }


    public BigInteger getQ(BigInteger n) {
        return n.divide(this.p);
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

    public BigInteger calculateE(BigInteger p, BigInteger q) {
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        this.e = BigInteger.valueOf(2);
        return getE(phi.gcd(e), phi, this.e);
    }

    public BigInteger getE(BigInteger gcd, BigInteger phi, BigInteger e) {
        while (gcd.intValue() != 1) {
            e = e.add(BigInteger.valueOf(1));
            gcd = phi.gcd(e);
        }
        this.e = e;
        return e;
    }

    public String getC(String m) {
        List<String> sipherText = new ArrayList<>();

        for (char letter: m.toCharArray()) {
            String temp = String.valueOf(letter);
            sipherText.add(sipherChar(temp).toString());
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < sipherText.size(); i++) {
            BigInteger temp = encrypt(new BigInteger(sipherText.get(i)), this.e, this.n);
            result.append(temp);

            if (i != sipherText.size() - 1) {
                result.append(",");
            }
        }

        return result.toString();
    }

    public BigInteger sipherChar(String sipher) {
        return new BigInteger(sipher.getBytes());
    }

    public static BigInteger encrypt(BigInteger m, BigInteger e, BigInteger n) {
        return m.modPow(e, n);
    }

    public BigInteger getP() {
        return p;
    }

    public BigInteger getQ() {
        return q;
    }

    public BigInteger getE() {
        return e;
    }

    public BigInteger getN() {
        return n;
    }
}
