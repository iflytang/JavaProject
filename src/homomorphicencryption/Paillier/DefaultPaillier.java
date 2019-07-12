package src.homomorphicencryption.Paillier;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

/**
 * @author tsf
 * @date 19-7-11
 * @desp a simple notation implementation for Paillier, which works for additive homomorphic encryption.
 */


public class DefaultPaillier {

    /* p and q are two large primes. lambda = lcm(p-1, q-1) = (p-1)*(q-1)/gcd(p-1, q-1). */
    private BigInteger p, q, lambda;

    /* n = p*q */
    private BigInteger n;

    /* nSquare = n*n */
    private BigInteger nSquare;

    /* a random integer in Z*_{n^2}, where gcd(L(g^lambda mod n^2), n) = 1. */
    private BigInteger g;

    /* modular inverse, as sk. */
    private BigInteger u;

    /* the bit-length of modulus. */
    private int bitLength;

    /* to generate 'g', small g can speed up encryption. */
    private int randomRange = 50;

    public BigInteger getP() {
        return p;
    }

    public BigInteger getQ() {
        return q;
    }

    public BigInteger getLambda() {
        return lambda;
    }

    public BigInteger getN() {
        return n;
    }

    public BigInteger getnSquare() {
        return nSquare;
    }

    public BigInteger getG() {
        return g;
    }

    public BigInteger getU() {
        return u;
    }

    public int getBitLength() {
        return bitLength;
    }

    /**
     * define L(x) = (x-1)/n;  n=p*q
     * @param x x
     * @return
     */
    public BigInteger L(BigInteger x) {
        return  (x.subtract(BigInteger.ONE)).divide(n);
    }


    /** to probably generate primes. pk: (n, g); sk: (lambda, u)
     * @param bitLength the big-length of modulus
     * @param certainty the probability to generate a prime number will exceed (1 - (1/2)^certainty)
     * @return g
     */
    public BigInteger KeyGen(int bitLength, int certainty) {

        this.bitLength = bitLength;

        /* p and q are primes */
        p = new BigInteger(bitLength/2, certainty, new Random());
        q = new BigInteger(bitLength/2, certainty, new Random());

        /* n = p*q */
        n = p.multiply(q);
        nSquare = n.multiply(n);

        /* lambda = lcm(p-1, q-1) = (p-1)*(q-1)/gcd(p-1, q-1). */
        lambda = ((p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE)))
                .divide((p.subtract(BigInteger.ONE)).gcd(q.subtract(BigInteger.ONE)));


        /* g is a random in [0, nSquare), where gcd(L(g^lambda mod n^2), n) = 1.
        *  Attention, if g=2 or small g can speed up encryption.
        * */
//        g = new BigInteger(String.valueOf( (int) (Math.random()*randomRange)));
        g =  new BigInteger("2");

        /* check whether g is ok, gcd(L(g^lambda mod n^2), n) = 1 */
        if ((g.intValue() < 0) || (L(g.modPow(lambda, nSquare)).gcd(n).intValue() != 1)) {
//            System.out.println("g is not ok. g = " + g + ". try again.");
            KeyGen(bitLength, certainty);
        }

        u = L(g.modPow(lambda, nSquare)).modInverse(n);

        return g;
    }


    /**
     * Encrypt plaintext m with given r. ciphertext c = (g^m * r^n) mod n^2
     * @param m plaintext
     * @param r random
     * @return ciphertext
     */
    public BigInteger Encrypt (BigInteger m, BigInteger r) {
        return g.modPow(m, nSquare).multiply(r.modPow(n, nSquare)).mod(nSquare);
    }

    /**
     * Encrypt plaintext m with auto-generated r. ciphertext c = (g^m * r^n) mod n^2
     * @param m plaintext
     * @return ciphertext
     */
    public BigInteger Encrypt (BigInteger m) {
        BigInteger r = new BigInteger(bitLength, new Random());
        return Encrypt(m, r);
    }

    /**
     * Decrypt ciphertext c. plaintext m = (L(c^lambda mod n^2) * u) mod n
     * @param c ciphertext
     * @return plaintext
     */
    public BigInteger Decrypt (BigInteger c) {
        return L(c.modPow(lambda, nSquare)).multiply(u).mod(n);
    }

    /**
     * Addictive homomorhphic encryption: cipertext multiplication refers to plaintext addiction.
     * @param c1 ciphertext 1
     * @param c2 ciphertext 2
     * @return multiplied ciphertext
     */
    public BigInteger CiperMultiply (BigInteger c1, BigInteger c2) {
        return c1.multiply(c2).mod(nSquare);
    }

    /**
     * ciphertext's power: c^k. Evaluate: c^k mod n^2 = k*m mod n
     * @param c ciphertext, the base
     * @param k the exponent
     * @return c^k mod n^2
     */
    public BigInteger CiperPow (BigInteger c, BigInteger k) {
        return c.modPow(k, nSquare);
    }

    /**
     * calculate gx = g^{n*x} mod n^2. Evaluate: E(m)*gx mod n^2 = (n*x+m) mod n = m
     * @return g^{n*x} mod n^2
     */
    public BigInteger gxPowMod () {
        BigInteger x = new BigInteger(bitLength/2, new Random());
        return g.modPow(n.multiply(x), nSquare);
    }


    public static void main (String[] args) {
        DefaultPaillier paillier = new DefaultPaillier();

        BigInteger key = paillier.KeyGen(32, 64);

        Scanner input = new Scanner(System.in);
        System.out.println("input two integer: ");
        String str1 = String.valueOf(input.nextInt());
        String str2 = String.valueOf(input.nextInt());

        BigInteger m1 = new BigInteger(str1);
        BigInteger m2 = new BigInteger(str2);
        System.out.println("m1: " + m1 + ", m2: " + m2);
        System.out.println("p: " + paillier.getP() + ", q: " + paillier.getQ() + ", n: " + paillier.getN() +
        ", g: " + paillier.getG() + ", lambda: " + paillier.getLambda() + ", u: " + paillier.getU());

        /**
         * PROPERTY ONE: Addictive homomorphic encryption.
         *  E(m1)*E(m2) = E(m1+m2).
         **/
        System.out.println("\n1. Addictive homomorphic encryption");
        BigInteger c1 = paillier.Encrypt(m1);
        BigInteger c2 = paillier.Encrypt(m2);
        BigInteger c = paillier.CiperMultiply(c1, c2);
        System.out.println("c1(E(m1)): " + c1 + ", c2(E(m2)): " + c2 + ", c(E(m1)*E(m2)): " + c);

        BigInteger m = paillier.Decrypt(c);
        System.out.println("m = m1+m2: " + m);

        /**
         * PROPERTY TWO: The second ciphertext's r^n can be ignored.
         */
        System.out.println("\n2. Ignore second ciphertext's r^n");
        c1 = paillier.Encrypt(m1);
        c2 = paillier.Encrypt(m2, BigInteger.ONE);
        c = paillier.CiperMultiply(c1, c2);
        System.out.println("c1(E(m1)): " + c1 + ", c2(g^m2): " + c2 + ", c(E(m1)*g^m2): " + c);

        m = paillier.Decrypt(c);
        System.out.println("m = m1+m2: " + m);

        /**
         * COROLLARY TWO: Self-blinding, change the cipher text without changing the value of the original plaintext.
         **/
        System.out.println("\n3. Self-blinding");
        c1 = paillier.Encrypt(m1);
        c2 = paillier.gxPowMod();
        c = paillier.CiperMultiply(c1, c2);
        System.out.println("c1(E(m1)): " + c1 + ", c2(g^nx): " + c2 + ", c(E(m1)*g^nx): " + c);

        m = paillier.Decrypt(c);
        System.out.println("m = m1: " + m);

        /**
         * PROPERTY THREE: E(m)^k mod n^2 = k*m mod n.
         **/
        System.out.println("\n4. Cipher's power to plaintext's multiplication");
        c1 = paillier.Encrypt(m1);
        c = paillier.CiperPow(c1, m2);      // k = m2
        System.out.println("c1(E(m1)): " + c1 + ", k(m2): " + m2 + ", c(E(m1)^k): " + c);

        m = paillier.Decrypt(c);
        System.out.println("m = m1*k = m1*m2: " + m);
    }

}
