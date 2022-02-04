package com.hsbc.qe.ui.utilities;
import java.math.BigInteger;
import java.security.SecureRandom;

public class GeneralUtilities {

    public static String generateRandomHexToken(int byteLength) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] token = new byte[byteLength];
        secureRandom.nextBytes(token);
        return new BigInteger(1, token).toString(16); //hex encoding
    }
}
