package encryptdecrypt;

import encryptdecrypt.algorithm.CryptorAlgorithm;

class Cryptor {
    private static final String ENCRYPT_MODE = "enc";
    private final CryptorAlgorithm algorithm;
    private final String mode;

    public Cryptor(CryptorAlgorithm algorithm, String mode) {
        this.algorithm = algorithm;
        this.mode = mode;
    }

    public String useAlgorithm(String stringToCrypt, int key) {
        if (ENCRYPT_MODE.equals(mode) || mode.isEmpty()) {
            return algorithm.encrypt(stringToCrypt, key);
        }
        return algorithm.decrypt(stringToCrypt, key);
    }
}